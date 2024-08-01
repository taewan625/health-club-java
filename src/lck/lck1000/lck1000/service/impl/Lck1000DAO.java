package lck.lck1000.lck1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.propertiesconvert.MessageSource;
import com.val.Validator;

import lck.lck1000.lck1000.vo.Lck1000VO;
import memorydb.LCK1000;
import memorydb.domain.Locker;

/**
 * @Class Name : Lck1000DAO.java
 * @Description 사물함 data를 조회, 등록 ,수정, 삭제 하는 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000DAO {
	public static Properties message = MessageSource.properties;
	// db table
	private Map<Integer, Locker> lockerStore;

	/**
	 * @Class Name Lck1000DAOHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.26.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Lck1000DAOHolder {
		private static final Lck1000DAO INSTANCE = new Lck1000DAO();
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Map<Integer, Locker>
	 * @throws Exception
	 */
	public static Lck1000DAO getInstance() throws Exception {
		return Lck1000DAOHolder.INSTANCE;
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param LCK1000
	 *            db
	 * @return void
	 * @throws Exception
	 */
	public void setDBTable(LCK1000 db) throws Exception {
		try {
			this.lockerStore = LCK1000.getInstance().lockerStore;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 리스트 반환 메서드
	 * 
	 * @desc 사물함 리스트 반환
	 * @param
	 * @return List<Lck1000VO>
	 * @throws Exception
	 */
	public List<Lck1000VO> selectLck1000List() throws Exception {
		try {
			List<Lck1000VO> lockers = new ArrayList<>();
			for (Locker value : lockerStore.values()) {
				lockers.add(new Lck1000VO(value.getUserId(), value.getLockerNumber(), value.getExpireDate()));
			}
			return lockers;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 id로 사물함 정보 반환 메서드
	 * 
	 * @desc 회원 id 이용해서 사물함 정보 반환, 없으면 예외 발생, 해당 메서드 수행전, 회원 id를 가진 사물함 존재 유무 확인 필수
	 * @param String
	 *            userId
	 * @return Lck1000VO
	 * @throws Exception
	 * 
	 */
	public Lck1000VO selectLck1000(String userId) throws Exception {
		try {
			for (Locker locker : lockerStore.values()) {
				// 회원 부분은 null일 수 있다.
				if (locker.getUserId() == null) {
					continue;
				}
				// 회원이 null이 아니고 userId가 동일한 경우 Lck1000VO 반환
				if (locker.getUserId().equals(userId)) {
					return new Lck1000VO(userId, locker.getLockerNumber(), locker.getExpireDate());
				}
			}
			throw new Exception(message.getProperty("ERROR.SELECT"));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 번호로 사물함 정보 반환 메서드
	 * 
	 * @desc 사물함 번호를 이용해서 사물함 정보 반환, 해당 메서드 수행전 사물함 번호 존재 유무 확인 필수
	 * @param int
	 *            lockerNumber
	 * @return Lck1000VO
	 * @throws Exception
	 * 
	 */
	public Lck1000VO selectLck1000(int lockerNumber) throws Exception {
		try {
			// db에서 view 역할 수행
			Locker selectLocker = selectLocker(lockerNumber);
			return new Lck1000VO(selectLocker.getUserId(), lockerNumber, selectLocker.getExpireDate());
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 등록, 수정 메서드
	 * 
	 * @desc 회원 id 를 넣어서 등록한다. 등록전 사물함번호 존재 여부 확인 필수
	 * @param Lck1000VO
	 *            lck1000vo
	 * @return void
	 * @throws Exception
	 */
	public void saveLck1000(Lck1000VO lck1000VO) throws Exception {
		try {
			int lockerNum = lck1000VO.getLockerNum();
			Locker registerLocker = selectLocker(lockerNum);

			registerLocker.setUserId(lck1000VO.getUserId());
			registerLocker.setExpireDate(lck1000VO.getExpireDate());
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 만료된 사물함 회원 id 및 만료일 제거 메서드
	 * 
	 * @desc 만료된 사물함의 회원id, 사물함 만료일 제거 후 true 반환, 해당 메서드 사용전 사물함 번호 존재 유무 확인 필수
	 * @param int
	 *            lockerNum
	 * @return Locker
	 * @throws Exception
	 */
	public void deleteLck1000(int lockerNum) throws Exception {
		try {
			Locker locker = selectLocker(lockerNum);
			locker.setUserId(null);
			locker.setExpireDate(null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 id를 가진 사물함 존재 유무 확인 메서드
	 * 
	 * @desc 사물함에 회원명이 기입되고 사물함 만료일이 지나지 않았을 경우. cf) 회원이 만료되었는지, 제거 되었는지 유무는 회원측에서
	 *       확인 필요. 따라서 해당 메서드 수행전 회원 사용유무부터 확인 필수
	 * @param String
	 *            userId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isContainsUsr1000(String userId) throws Exception {
		try {
			for (Locker locker : lockerStore.values()) {
				if (locker.getUserId() == null) {
					continue;
				}
				if (locker.getUserId().equals(userId)) {
					// 사물함 만료기간 지났으면 회원이 없는 것
					// 만료가 됨 == 회원이 없는 것 == false 반환
					return !Validator.isExpire(locker.getExpireDate());
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 사물함 번호 존재 유무 확인 메서드
	 * 
	 * @desc 존재하는 사물함 번호일 시 , true 반환
	 * @param int
	 *            lockerNumber
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isContainsLck1000(int lockerNumber) throws Exception {
		try {
			for (Locker locker : lockerStore.values()) {
				if (locker.getLockerNumber() == lockerNumber) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : locker 반환 내부 메서드
	 * 
	 * @desc 사물함 번호를 이용해서 Locker 자체를 반환하는 메서드, 사용전 사물함 번호 존재유무 확인 필요
	 * @param int
	 *            lockerNumber
	 * @return Locker
	 * @throws Exception
	 */
	private Locker selectLocker(int lockerNumber) throws Exception {
		try {
			for (Locker locker : lockerStore.values()) {
				if (locker.getLockerNumber() == lockerNumber) {
					return locker;
				}
			}
			throw new Exception(message.getProperty("ERROR.SELECT"));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
