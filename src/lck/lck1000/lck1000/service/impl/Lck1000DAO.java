package lck.lck1000.lck1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;
import memorydb.LCK1000;

/**
 * @Description 사물함 정보를 조회, 저장, 삭제
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
		//사물함 목록 변수
		List<Lck1000VO> lockers = new ArrayList<>(lockerStore.values());
		
		//선택한 페이지
		String selectPage = pageInfo.get("selectPage");
		
		//전체 회원 목록을 받지 않는 경우 == 페이징 처리를 하는 경우
		if (!"all".equals(selectPage)) {
			//시작 범위
			int from = (Integer.parseInt(selectPage) - 1) * Integer.parseInt(pageInfo.get("range"));
			
			//종료 범위
			int to = (Integer.parseInt(selectPage)) * Integer.parseInt(pageInfo.get("range"));
			
			//종료 범위가 토탈 개수보다 작을 경우
			to = (lockers.size() < to) ? lockers.size() : to;
			
			//조회 사물함 목록
			lockers = lockers.subList(from, to);
		}
		
		//조회 사물함 목록 반환
		return lockers;
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
	 * @desc 사물함 정보 저장
	 * @param Lck1000VO lockerVO
	 * @return void
	 * @throws Exception
	 */
	public void saveLck1000LockerInfo(Lck1000VO lockerVO) throws Exception {
		lockerStore.replace(lockerVO.getLockerNum(), lockerVO);
	}
	
	/**
	 * @desc 사물함 정보 삭제
	 * @param String lockerNumber
	 * @return void
	 * @throws Exception
	 */
	public void deleteLck1000LockerInfo(String lockerNumber) throws Exception {
		//삭제할 사물함 정보 조회
		Lck1000VO lockerInfo = lockerStore.get(Integer.parseInt(lockerNumber));
		
		//사물함 id 삭제
		lockerInfo.setUserId(null);
		
		//사물함 시작일 삭제
		lockerInfo.setStartDate(null);
		
		//사물함 종료일 삭제
		lockerInfo.setEndDate(null);
	}
}
