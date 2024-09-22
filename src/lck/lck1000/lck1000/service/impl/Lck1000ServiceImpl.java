package lck.lck1000.lck1000.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.config.AppConfig;

import lck.lck1000.lck1000.service.Lck1000Service;
import lck.lck1000.lck1000.vo.Lck1000VO;

/**
 * @Description 사물함 정보를 조회, 저장, 삭제
 * @version 1.0
 * @author 권태완
 * @Since 2024.09.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000ServiceImpl implements Lck1000Service {
	//생성자 주입
	private final Lck1000DAO lck1000DAO;
	
	//생성자
	private Lck1000ServiceImpl(Lck1000DAO lck1000DAO) {
		this.lck1000DAO = lck1000DAO;
	}
	
	//싱글톤 생성용 내부 클래스
	private static class Lck1000ServiceImplHolder {
		private static final Lck1000ServiceImpl INSTANCE = new Lck1000ServiceImpl(AppConfig.lck1000DAO());
	}
	
	//싱글톤
	public static Lck1000ServiceImpl getInstance() {
		return Lck1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * @desc 사물함 목록 반환
	 * @param Map<String, String> pageInfo
	 * @return Map<Integer, Locker>
	 * @throws Exception
	 */
	@Override
	public List<Lck1000VO> selectLck1000List(Map<String, String> pageInfo) throws Exception {
		return lck1000DAO.selectLck1000List(pageInfo);
	}
	
	/**
	 * @desc 사물함 단건 조회
	 * @param String selectType, String selectValue
	 * @return Lck1000VO
	 * @throws Exception
	 */
	@Override
	public Lck1000VO selectLck1000(String selectType, String selectValue) throws Exception {
		//사물함 정보 조회
		Lck1000VO locker = lck1000DAO.selectLck1000(selectType, selectValue);
		
		//사물함 정보와 사물함 만료일 정보가 비어있지 않고 만료일을 넘긴 경우
		if (locker != null && locker.getEndDate() != null && locker.getEndDate().isBefore(LocalDate.now())) {
			//해당 사물함 회원 정보 비우기
			locker.setUserId(null);
			
			//헤당 사물함 등록일자 정보 비우기
			locker.setStartDate(null);
			
			//헤당 사물함 만료일자 정보 비우기
			locker.setEndDate(null);
			
			//사물함 정보 업데이트하기
			lck1000DAO.saveLck1000LockerInfo(locker);
			
			//조회 유형이 회원 아이디로 조회한 경우
			if ("2".equals(selectType)) {
				//전달할 사물함 정보 비우기
				locker = null;
			}
		}
		
		//사물함 정보 반환
		return locker;
	}
	
	/**
	 * @desc 사물함 저장 로직
	 * @param Lck1000VO lockerInfo
	 * @return boolean
	 * @throws Exception
	 * 
	 */
	@Override
	public boolean saveLck1000LockerInfo(Lck1000VO lockerInfo) throws Exception {
		//사물함 등록 결과
		boolean result = true;
		
		//사물함을 중복으로 가진 경우
		boolean hasLocker = false;
		
		//모든 사물함 정보 조회
		List<Lck1000VO> lockers = lck1000DAO.selectLck1000List(Map.of("selectPage", "all"));
		
		//등록할 사물함의 회원이 다른 사물함을 가지고 있는지 검증
		for (Lck1000VO locker : lockers) {
			//등록할 사물함의 회원이 이미 사물함을 가지고 있는 경우 && 사물함 번호는 다른 경우
			if (lockerInfo.getUserId().equals(locker.getUserId()) && lockerInfo.getLockerNum() != locker.getLockerNum()) {
				//중복 여부 값 변경
				hasLocker = true;
				
				//만료일을 지난 경우
				if (locker.getEndDate().isBefore(LocalDate.now())) {
					//해당 사물함 회원 정보 비우기
					locker.setUserId(null);
					
					//헤당 사물함 등록일자 정보 비우기
					locker.setStartDate(null);
					
					//헤당 사물함 만료일자 정보 비우기
					locker.setEndDate(null);
					
					//사물함 정보 업데이트하기
					lck1000DAO.saveLck1000LockerInfo(locker);
					
					//사물함 등록하기
					lck1000DAO.saveLck1000LockerInfo(lockerInfo);
					
				}
				else {
					//실패 결과
					result = false;
				}
				
				//for문 나오기
				break;
			}
		}
		
		//사물함을 가지고 있지 않은 경우
		if (!hasLocker) {
			//사물함 등록하기
			lck1000DAO.saveLck1000LockerInfo(lockerInfo);
		}
		
		//결과 값 반환
		return result;
	}
	
	/**
	 * @desc 사물함 삭제
	 * @param String lockerNumber
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteLck1000LockerInfo(String lockerNumber) throws Exception {
		lck1000DAO.deleteLck1000LockerInfo(lockerNumber);
	}
}
