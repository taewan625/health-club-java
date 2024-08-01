package lck.lck1000.lck1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;
import com.val.Validator;

import lck.lck1000.lck1000.service.Lck1000Service;
import lck.lck1000.lck1000.vo.Lck1000VO;
import usr.usr1000.usr1000.service.impl.Usr1000DAO;
import usr.usr1000.usr1000.vo.Usr1000VO;

public class Lck1000ServiceImpl implements Lck1000Service {
	public static Properties message = MessageSource.properties;

	private final Lck1000DAO lck1000DAO;
	private final Usr1000DAO usr1000DAO;

	/**
	 * Func 초기화 메서드를 담는 생성자
	 * 
	 * @desc 싱글톤 생성시, Map 초기화 예외 발생 경우 초기화 문제를 잡기 위해 try-catch 수행 main method에서 객체를
	 *       초기화 할 경우 try-catch로 잡는 것과 같이 생성자 내부에서 초기화 수행시, try-catch로 잡는 형식으로
	 *       생성 @param @return
	 */
	private Lck1000ServiceImpl(Lck1000DAO lck1000DAO, Usr1000DAO usr1000DAO) {
		this.lck1000DAO = lck1000DAO;
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
	private static class Lck1000ServiceImplHolder {
		private static final Lck1000ServiceImpl INSTANCE = new Lck1000ServiceImpl(AppConfig.lck1000DAO(),
				AppConfig.usr1000DAO());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Usr1000ServiceImpl
	 * @throws Exception
	 */
	public static Lck1000ServiceImpl getInstance() throws Exception {
		return Lck1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * Func : 사물함 화면의 default 정보를 보여주는 메서드
	 * 
	 * @desc 사물함 데이터중 회원 id, 사물함 번호, 만료일자 보여준다.
	 * @param
	 * @return Map<Integer, Locker>
	 * @throws Exception
	 */
	@Override
	public List<Lck1000VO> selectLck1000List() throws Exception {
		return lck1000DAO.selectLck1000List();
	}

	/**
	 * Func : 요청 url의 key와 value의 값이 유효한지 확인하는 메서드
	 * 
	 * @desc key 존재 시, key의 value가 유효한지 확인한다.
	 * @param String
	 *            requestParams
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean isValidSelectKeyValue(Map<String, Object> clientData) throws Exception {
		try {
			if (clientData.containsKey("lockerNum")) {
				// 존재하는 사물함 번호인지 검증. 값을 받아오기전 숫자 검증을 수행함.
				String lockerNum = String.valueOf(clientData.get("lockerNum"));
				return lck1000DAO.isContainsLck1000(Integer.parseInt(lockerNum));
			} else {
				// 사물함에 회원이 존재하는지 확인
				return lck1000DAO.isContainsUsr1000(String.valueOf(clientData.get("id")));
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 id와 사물함 번호로 사물함 정보 반환하는 메서드
	 * 
	 * @desc url의 key 값의 종류에 따라서 값을 들고 온다.
	 * @param String
	 *            requestParams
	 * @return Lck1000VO
	 * @throws Exception
	 */
	@Override
	public Lck1000VO selectLck1000(Map<String, Object> clientData) throws Exception {
		try {
			// userId로 호출할 경우
			if (clientData.containsKey("id")) {
				return lck1000DAO.selectLck1000(String.valueOf(clientData.get("id")));
			}
			// 사물함 번호
			String lockerNum = String.valueOf(clientData.get("lockerNum"));
			// 사물함 번호로 호출하는 경우
			Lck1000VO locker = lck1000DAO.selectLck1000(Integer.parseInt(lockerNum));

			// 사물함 만료 기간이 지났으면 update 후 다시 받아와서 return
			if (Validator.isExpire(locker.getExpireDate())) {
				lck1000DAO.deleteLck1000(Integer.parseInt(lockerNum));
				return lck1000DAO.selectLck1000(Integer.parseInt(lockerNum));
			}
			// 만료되지 않았으면 바로 return
			return locker;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * Func : 회원 id 리스트 반환 메서드
	 * 
	 * @desc 회원 id 리스트 반환 메서드
	 * @param
	 * @return List<String>
	 * @throws Exception
	 */
	@Override
	public List<String> selectUsr1000List() throws Exception {
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
	 * Func : 사물함 등록 메서드
	 * 
	 * @desc 회원아이디가 존재하지 않고 비어있는 사물함 및 만료된 사물함에 한해서 등록 가능. 실패시 false 반환, 성공시 등록 후
	 *       true 반환
	 * @param Lck1000VO
	 *            lck1000VO
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public void createLck1000(Lck1000VO locker) throws Exception {
		String userId = locker.getUserId();
		try {
			int lockerNum = locker.getLockerNum();

			// 등록, 수정에서 공통으로 적용되는 검증 부분
			if (commonValidate(userId, lockerNum, locker)) {
				return;
			}

			// 넣으려는 사물함에 회원이 있고 만기일이 안넘으면 false
			Lck1000VO exsitLocker = lck1000DAO.selectLck1000(lockerNum);

			// 해당 사물함에 회원이 존재하고 사물함 만료일이 지나지 않으면 false
			if (!exsitLocker.getUserId().isEmpty() && !Validator.isExpire(exsitLocker.getExpireDate())) {
				locker.setError("-1", message.getProperty("FAIL.CREATE.LOCKER"));
				return;
			}

			// 이제 true의 상황 밖에 없으니깐 여기서는 등록을 한다.
			lck1000DAO.saveLck1000(locker);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 수정 메서드
	 * 
	 * @desc 사물함에 회원의 존재 여부와 관계 없이, 사물함 번호가 존재하고 회원 정보가 저장되어있을 시, 수정에 들어간다.
	 * @param Lck1000VO
	 *            lck1000VO
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public void updateLck1000(Lck1000VO locker) throws Exception {
		try {
			String userId = locker.getUserId();
			int lockerNum = locker.getLockerNum();
			// 등록, 수정에서 공통으로 적용되는 검증 부분
			if (commonValidate(userId, lockerNum, locker)) {
				return;
			}
			// 이제 true의 상황 밖에 없으니깐 여기서는 등록을 한다.
			lck1000DAO.saveLck1000(locker);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 삭제 메서드
	 * 
	 * @desc 사물함 삭제
	 * @param int
	 *            lockerNum
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteLck1000(int lockerNum, Lck1000VO lck1000VO) throws Exception {
		try {
			if (!lck1000DAO.isContainsLck1000(lockerNum)) {
				lck1000VO.setError("-1", message.getProperty("FAIL.DELETE"));
				return;
			}
			lck1000DAO.deleteLck1000(lockerNum);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * Func : 등록, 수정 시 공통으로 검증해야하는 내부 메서드
	 * 
	 * @desc 등록, 수정 시 공통으로 검증해야하는 부분 분리
	 * @param String
	 *            userId, int lockerNum
	 * @return boolean
	 * @throws Exception
	 */
	private boolean commonValidate(String userId, int lockerNum, Lck1000VO locker) throws Exception {
		try {
			// 회원 table에서 회원 id가 존재하는지 확인
			if (!usr1000DAO.isContainsUsr1000(userId)) {
				locker.setError("-1", message.getProperty("FAIL.CREATE.EXIT.MEMBER"));
				return true;
			}
			// 사물함 테이블에서 사물함 번호가 존재하는지 확인
			if (!lck1000DAO.isContainsLck1000(lockerNum)) {
				locker.setError("-1", message.getProperty("FAIL.CREATE.EXIT.LOCKER"));
				return true;
			}
			// 회원 정보를 가지고 온다.
			Usr1000VO selectUsr = usr1000DAO.selectUsr1000(userId);

			// 회원 id의 사용 유무가 n이 아닌지, 만료된 회원인지 검증
			if (!Validator.isValidUser(selectUsr)) {
				locker.setError("-1", message.getProperty("FAIL.CREATE.EXPIRE.MEMBER"));
				return true;
			}

			// 회원 정보가 사물함 table에 존재
			if (lck1000DAO.isContainsUsr1000(userId)) {
				locker.setError("-1", message.getProperty("FAIL.CREATE.DUPLICATE.MEMBER"));
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
