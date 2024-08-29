package usr.usr1000.usr1000.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.config.AppConfig;

import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원관리의 조회, 등록, 수정, 삭제 관리 핵심 기능을 수행한다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000ServiceImpl implements Usr1000Service {
	//생성자 주입
	private final Usr1000DAO usr1000DAO;

	// 생성자
	private Usr1000ServiceImpl(Usr1000DAO usr1000DAO) {
		this.usr1000DAO = usr1000DAO;
	}

	//싱글톤 생성용 내부 클래스
	private static class Usr1000ServiceImplHolder {
		private static final Usr1000ServiceImpl INSTANCE = new Usr1000ServiceImpl(AppConfig.usr1000DAO());
	}

	//싱글톤 객체 반환
	public static Usr1000ServiceImpl getInstance() throws Exception {
		return Usr1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * @desc 회원 데이터중 id, 이름 정보만 전달
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	@Override
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		return usr1000DAO.selectUsr1000List();
	};
	
	/**
	 * @desc 회원 조회 시, 회원 상태 업데이트 후 회원 정보 반환
	 * @param String userId
	 * @return Usr1000VO
	 * @throws Exception
	 */
	@Override
	public Usr1000VO selectUsr1000(String userId) throws Exception {
		//회원 정보 조회
		Usr1000VO user = usr1000DAO.selectUsr1000(userId);
		
		//회원 정보 존재 시, 회원 상태 업데이트 후, 전송
		if (user != null) {
			//회원 만료일 조회
			LocalDate expireDate = user.getExpireDate();
			
			//만료일이 금일을 넘겼을 경우 회원 상태 업데이트
			if (expireDate.isBefore(LocalDate.now())) {
				//회원 상태 변경
				user.setStatus("02");
				
				//회원 수정 일시 변경
				user.setEditDateTime(LocalDateTime.now());
				
				//회원 상태 업데이트
				usr1000DAO.updateUsr1000(userId, user);
			 }
		}
		
		//회원 정보 반환
		return user;
	}

	/**
	 * @desc 중복 아이디가 존재하지 않을 시, 회원을 등록한다. expireDate
	 * @param Usr1000VO userInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void createUsr1000(Usr1000VO userInfo) throws Exception {
		//DB에 데이터 담기
		usr1000DAO.createUsr1000(userInfo);
	};
	
//	/**
//	 * Func : 회원 테이블에서 삭제되지 않은 id 중에서 값이 존재하면 true를 반환하는 메서드
//	 * 
//	 * @desc 입력한 회원 id가 유효한지 검증, 회원 id가 존재하고 회원이 삭제된 정보가 아닌 경우
//	 * @param String
//	 *            selectId
//	 * @return boolean
//	 * @throws Exception
//	 */
//	@Override
//	public boolean isContainsUsr1000WithOutDeleteUsr(String userId) throws Exception {
//		try {
//			// 삭제된 회원이든 그냥 회원이든 해당 테이블에 id가 존재하지 않으면 false
//			if (!usr1000DAO.isContainsUsr1000(userId)) {
//				return false;
//			}
//			// 존재하는 회원이고 삭제되지 않은경우 true;
//			Usr1000VO user = usr1000DAO.selectUsr1000(userId);
//			if (user.getDelete() == Availability.NO) {
//				return true;
//			}
//			// 나머지 모든 경우 false
//			return false;
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//
//	}
//


//
//	/**
//	 * Func : 회원 수정 메서드
//	 * 
//	 * @desc 회원 수정 시, 회원 수정 일시도 자동 업데이트, null로 들어온 값은 수정 안하는 값
//	 * @param String
//	 *            updateId, Usr1000VO user
//	 * @return void
//	 * @throws Exception
//	 */
//	@Override
//	public void updateUsr1000(String updateId, Usr1000VO user) throws Exception {
//		try {
//			// 삭제된 id이든 사용되는 id이든 존재하기만 하면 false
//			if (!usr1000DAO.isContainsUsr1000(updateId)) {
//				user.setError("-1", message.getProperty("FAIL.UPDATE"));
//				return;
//			}
//			// id가 유일하면 수정한 값만 수정
//			usr1000DAO.updateUsr1000(updateId, user);
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : 회원 삭제 메서드
//	 * 
//	 * @desc 회원 삭제 시,삭제여부, 사용여부만 업데이트
//	 * @param String
//	 *            deleteId
//	 * @return User
//	 * @throws Exception
//	 */
//	@Override
//	public void deleteUsr1000(String deleteId, Usr1000VO user) throws Exception {
//		try {
//			if (!isContainsUsr1000WithOutDeleteUsr(deleteId)) {
//				user.setError("-1", message.getProperty("FAIL.DELETE"));
//				return;
//			}
//			usr1000DAO.deleteUsr1000(deleteId);
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : 마감일자 기준 회원 상태 업데이트 메서드
//	 * 
//	 * @desc 현일자와 마감일자 차이가 0이하는 만기, 5이하는 임박 이외는 정상 상태 반환
//	 * @param LocalDate
//	 *            expireDate
//	 * @return Status
//	 * @throws Exception
//	 */
//	private Status checkUserStatus(LocalDate expireDate) throws Exception {
//		try {
//			// now와 expireDate 사이의 일수를 계산
//			long daysExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expireDate);
//
//			if (daysExpiration <= 0) {
//				return Status.EXPIRATION;
//			} else if (daysExpiration <= 5) {
//				return Status.IMMINENT;
//			}
//			return Status.EFFECTIVE;
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}

}
