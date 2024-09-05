package lck.lck1000.lck1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;
import memorydb.LCK1000;

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
	//사물함 db table
	private Map<Integer, Lck1000VO> lockerStore = LCK1000.getInstance().getLck1000();
	
	//싱글톤 내부 클래스
	private static class Lck1000DAOHolder {
		private static final Lck1000DAO INSTANCE = new Lck1000DAO();
	}
	
	//싱글톤
	public static Lck1000DAO getInstance() {
		return Lck1000DAOHolder.INSTANCE;
	}


	/**
	 * @desc 사물함 목록 반환
	 * @param Map<String, String> pageInfo
	 * @return List<Lck1000VO>
	 * @throws Exception
	 */
	public List<Lck1000VO> selectLck1000List(Map<String, String> pageInfo) throws Exception {
		ArrayList<Lck1000VO> lockers = new ArrayList<>(lockerStore.values());
		
		//선택한 페이지
		String selectPage = pageInfo.get("selectPage");
		
		//시작 범위
		int from = (Integer.parseInt(selectPage) - 1) * Integer.parseInt(pageInfo.get("range"));
		
		//종료 범위
		int to = (Integer.parseInt(selectPage)) * Integer.parseInt(pageInfo.get("range"));
		
		//종료 범위가 토탈 개수보다 작을 경우
		to = (lockers.size() < to) ? lockers.size() : to;
		
		//조회 사물함 목록
		return lockers.subList(from, to);
	}
	
	/**
	 * @desc 사물함 조회
	 * @param String selectType, String selectValue
	 * @return Lck1000VO
	 * @throws Exception
	 */
	public Lck1000VO selectLck1000(String selectType, String selectValue) throws Exception {
		//반환할 사물함 변수
		Lck1000VO locker = null;
		
		//사물함 번호로 조회하는 경우
		if ("1".equals(selectType)) {
			locker = lockerStore.get(Integer.parseInt(selectValue));
			
		}
		//회원 아이디로 조회하는 경우
		else {
			//사물함 정보 담기
			ArrayList<Lck1000VO> lockers = new ArrayList<>(lockerStore.values());
			
			//반복 루프를 통해서 사물함 정보를 조회
			for (Lck1000VO lockerInfo : lockers) {
				//사물함 정보에 등록된 아이디와 조회 아이디 비교
				if (selectValue.equals(lockerInfo.getUserId())) {
					//변수에 사물함 정보 담기
					locker = lockerInfo;
					
					//for문 나오기
					break;
				}
			}
		}
		
		//데이터 반환
		return locker;
	}
	
	/**
	 * @desc 사물함 정보 수정
	 * @param Lck1000VO lockerVO
	 * @return void
	 * @throws Exception
	 */
	public void updateLck1000LockerInfo(Lck1000VO lockerVO) throws Exception {
		lockerStore.replace(lockerVO.getLockerNum(), lockerVO);
	}
//	/**
//	 * Func : 회원 id로 사물함 정보 반환 메서드
//	 * 
//	 * @desc 회원 id 이용해서 사물함 정보 반환, 없으면 예외 발생, 해당 메서드 수행전, 회원 id를 가진 사물함 존재 유무 확인 필수
//	 * @param String
//	 *            userId
//	 * @return Lck1000VO
//	 * @throws Exception
//	 * 
//	 */
//	public Lck1000VO selectLck1000(String userId) throws Exception {
//		try {
//			for (Locker locker : lockerStore.values()) {
//				// 회원 부분은 null일 수 있다.
//				if (locker.getUserId() == null) {
//					continue;
//				}
//				// 회원이 null이 아니고 userId가 동일한 경우 Lck1000VO 반환
//				if (locker.getUserId().equals(userId)) {
//					return new Lck1000VO(userId, locker.getLockerNumber(), locker.getExpireDate());
//				}
//			}
//			throw new Exception(message.getProperty("ERROR.SELECT"));
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//

//
//	/**
//	 * Func : 사물함 등록, 수정 메서드
//	 * 
//	 * @desc 회원 id 를 넣어서 등록한다. 등록전 사물함번호 존재 여부 확인 필수
//	 * @param Lck1000VO
//	 *            lck1000vo
//	 * @return void
//	 * @throws Exception
//	 */
//	public void saveLck1000(Lck1000VO lck1000VO) throws Exception {
//		try {
//			int lockerNum = lck1000VO.getLockerNum();
//			Locker registerLocker = selectLocker(lockerNum);
//
//			registerLocker.setUserId(lck1000VO.getUserId());
//			registerLocker.setExpireDate(lck1000VO.getExpireDate());
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : 만료된 사물함 회원 id 및 만료일 제거 메서드
//	 * 
//	 * @desc 만료된 사물함의 회원id, 사물함 만료일 제거 후 true 반환, 해당 메서드 사용전 사물함 번호 존재 유무 확인 필수
//	 * @param int
//	 *            lockerNum
//	 * @return Locker
//	 * @throws Exception
//	 */
//	public void deleteLck1000(int lockerNum) throws Exception {
//		try {
//			Locker locker = selectLocker(lockerNum);
//			locker.setUserId(null);
//			locker.setExpireDate(null);
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : 회원 id를 가진 사물함 존재 유무 확인 메서드
//	 * 
//	 * @desc 사물함에 회원명이 기입되고 사물함 만료일이 지나지 않았을 경우. cf) 회원이 만료되었는지, 제거 되었는지 유무는 회원측에서
//	 *       확인 필요. 따라서 해당 메서드 수행전 회원 사용유무부터 확인 필수
//	 * @param String
//	 *            userId
//	 * @return boolean
//	 * @throws Exception
//	 */
//	public boolean isContainsUsr1000(String userId) throws Exception {
//		try {
//			for (Locker locker : lockerStore.values()) {
//				if (locker.getUserId() == null) {
//					continue;
//				}
//				if (locker.getUserId().equals(userId)) {
//					// 사물함 만료기간 지났으면 회원이 없는 것
//					// 만료가 됨 == 회원이 없는 것 == false 반환
//					return !Validator.isExpire(locker.getExpireDate());
//				}
//			}
//			return false;
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : 사물함 번호 존재 유무 확인 메서드
//	 * 
//	 * @desc 존재하는 사물함 번호일 시 , true 반환
//	 * @param int
//	 *            lockerNumber
//	 * @return boolean
//	 * @throws Exception
//	 */
//	public boolean isContainsLck1000(int lockerNumber) throws Exception {
//		try {
//			for (Locker locker : lockerStore.values()) {
//				if (locker.getLockerNumber() == lockerNumber) {
//					return true;
//				}
//			}
//			return false;
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
//
//	/**
//	 * Func : locker 반환 내부 메서드
//	 * 
//	 * @desc 사물함 번호를 이용해서 Locker 자체를 반환하는 메서드, 사용전 사물함 번호 존재유무 확인 필요
//	 * @param int
//	 *            lockerNumber
//	 * @return Locker
//	 * @throws Exception
//	 */
//	private Locker selectLocker(int lockerNumber) throws Exception {
//		try {
//			for (Locker locker : lockerStore.values()) {
//				if (locker.getLockerNumber() == lockerNumber) {
//					return locker;
//				}
//			}
//			throw new Exception(message.getProperty("ERROR.SELECT"));
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}

}
