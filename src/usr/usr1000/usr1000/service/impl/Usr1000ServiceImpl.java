package usr.usr1000.usr1000.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;

import memorydb.domain.Availability;
import memorydb.domain.Status;
import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Usr1000ServiceImpl.java
 * @Description 회원관리의 조회, 등록, 수정, 삭제 관리 핵심 기능을 수행한다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000ServiceImpl implements Usr1000Service {
	public static Properties message = MessageSource.properties;

	private final Usr1000DAO usr1000DAO;

	// 생성자
	private Usr1000ServiceImpl(Usr1000DAO usr1000DAO) {
		this.usr1000DAO = usr1000DAO;
	}

	/**
	 * @Class Name Usr1000ServiceImplHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.23.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Usr1000ServiceImplHolder {
		private static final Usr1000ServiceImpl INSTANCE = new Usr1000ServiceImpl(AppConfig.usr1000DAO());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Usr1000ServiceImpl
	 * @throws Exception
	 */
	public static Usr1000ServiceImpl getInstance() throws Exception {
		return Usr1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * Func : 회원관리 화면의 default 정보를 보여주는 메서드
	 * 
	 * @desc 회원 데이터중 id, 이름 정보만 전달해준다.
	 * @param
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	@Override
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		return usr1000DAO.selectUsr1000List();
	};

	/**
	 * Func : 회원 id 리스트 반환 메서드
	 * 
	 * @desc 회원 id 리스트 반환 메서드
	 * @param
	 * @return List<String>
	 * @throws Exception
	 */
	@Override
	public List<String> selectUsrId1000List() throws Exception {
		try {
			List<Usr1000VO> selectUsr1000List = usr1000DAO.selectUsr1000List();
			List<String> userIds = new ArrayList<>();
			for (Usr1000VO user : selectUsr1000List) {
				userIds.add(user.getId());
			}
			return userIds;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 테이블에서 삭제되지 않은 id 중에서 값이 존재하면 true를 반환하는 메서드
	 * 
	 * @desc 입력한 회원 id가 유효한지 검증, 회원 id가 존재하고 회원이 삭제된 정보가 아닌 경우
	 * @param String
	 *            selectId
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean isContainsUsr1000WithOutDeleteUsr(String userId) throws Exception {
		try {
			// 삭제된 회원이든 그냥 회원이든 해당 테이블에 id가 존재하지 않으면 false
			if (!usr1000DAO.isContainsUsr1000(userId)) {
				return false;
			}
			// 존재하는 회원이고 삭제되지 않은경우 true;
			Usr1000VO user = usr1000DAO.selectUsr1000(userId);
			if (user.getDelete() == Availability.NO) {
				return true;
			}
			// 나머지 모든 경우 false
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * Func :회원 조회시, 회원 상태 업데이트, 회원 정보 반환 메서드
	 * 
	 * @desc 회원 조회시, 회원 상태 업데이트, 회원 정보 반환
	 * @param String
	 *            selectId
	 * @return Usr1000VO
	 * @throws Exception
	 */
	@Override
	public Usr1000VO selectUsr1000(String selectId) throws Exception {
		try {
			// 회원 정보 조회
			Usr1000VO user = usr1000DAO.selectUsr1000(selectId);
			if (user == null) {
				user = new Usr1000VO();
				user.setError("-1", message.getProperty("FAIL.SELECT"));
			} else {
				// 금일과 회원 만료 일자를 비교하여 회원 상태 업데이트
				LocalDate expireDate = user.getExpireDate();
				Status userStatus = checkUserStatus(expireDate);
				usr1000DAO.updateUsr1000Status(selectId, userStatus);
				user = usr1000DAO.selectUsr1000(selectId);
			}

			return user;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 등록 메서드
	 * 
	 * @desc 중복 아이디가 존재하지 않을 시, 회원을 등록한다. expireDate
	 * @param Usr1000VO
	 *            user
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void createUsr1000(Usr1000VO user) throws Exception {
		try {
			// 삭제된 id이든 사용되는 id이든 존재하기만 하면 false
			if (usr1000DAO.isContainsUsr1000(user.getId())) {
				user.setError("-1", message.getProperty("FAIL.CREATE"));
				return;
			}
			usr1000DAO.createUsr1000(user);
		} catch (Exception e) {
			throw new Exception(e);
		}
	};

	/**
	 * Func : 회원 수정 메서드
	 * 
	 * @desc 회원 수정 시, 회원 수정 일시도 자동 업데이트, null로 들어온 값은 수정 안하는 값
	 * @param String
	 *            updateId, Usr1000VO user
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updateUsr1000(String updateId, Usr1000VO user) throws Exception {
		try {
			// 삭제된 id이든 사용되는 id이든 존재하기만 하면 false
			if (!usr1000DAO.isContainsUsr1000(updateId)) {
				user.setError("-1", message.getProperty("FAIL.UPDATE"));
				return;
			}
			// id가 유일하면 수정한 값만 수정
			usr1000DAO.updateUsr1000(updateId, user);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 삭제 메서드
	 * 
	 * @desc 회원 삭제 시,삭제여부, 사용여부만 업데이트
	 * @param String
	 *            deleteId
	 * @return User
	 * @throws Exception
	 */
	@Override
	public void deleteUsr1000(String deleteId, Usr1000VO user) throws Exception {
		try {
			if (!isContainsUsr1000WithOutDeleteUsr(deleteId)) {
				user.setError("-1", message.getProperty("FAIL.DELETE"));
				return;
			}
			usr1000DAO.deleteUsr1000(deleteId);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 마감일자 기준 회원 상태 업데이트 메서드
	 * 
	 * @desc 현일자와 마감일자 차이가 0이하는 만기, 5이하는 임박 이외는 정상 상태 반환
	 * @param LocalDate
	 *            expireDate
	 * @return Status
	 * @throws Exception
	 */
	private Status checkUserStatus(LocalDate expireDate) throws Exception {
		try {
			// now와 expireDate 사이의 일수를 계산
			long daysExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expireDate);

			if (daysExpiration <= 0) {
				return Status.EXPIRATION;
			} else if (daysExpiration <= 5) {
				return Status.IMMINENT;
			}
			return Status.EFFECTIVE;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
