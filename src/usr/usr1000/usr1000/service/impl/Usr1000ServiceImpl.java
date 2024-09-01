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
	 * @desc 회원 목록 반환
	 * @param Map<String, String> pageInfo
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	@Override
	public List<Usr1000VO> selectUsr1000List(Map<String, String> pageInfo) throws Exception {
		return usr1000DAO.selectUsr1000List(pageInfo);
	};
	
	/**
	 * @desc 회원 목록 수 반환
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectUsr1000ListCnt() throws Exception {
		return usr1000DAO.selectUsr1000ListCnt();
	}
	
	/**
	 * @desc 회원 조회 시, 회원 상태 업데이트 후 회원 정보 반환
	 * @param String userId
	 * @return Usr1000VO
	 * @throws Exception
	 */
	@Override
	public Usr1000VO selectUsr1000(String userId) throws Exception {
		//회원 정보 조회
		Usr1000VO userInfo = usr1000DAO.selectUsr1000(userId);
		
		//회원 정보 존재 시, 회원 상태 업데이트 후, 전송
		if (userInfo != null) {
			//기존 회원 상태값
			String preStatus = userInfo.getStatus();
			
			//회원 만료일
			LocalDate expireDate = userInfo.getExpireDate();
			
			//금일
			LocalDate today = LocalDate.now();
			
			//현재 회원 상태값 조회
			String status = expireDate.isBefore(today) ? "만료" :
							expireDate.isAfter(today.plusDays(4)) ? "정상" : "임박";
			
			//현재 회원 상태값과 새로 조회한 회원 상태값이 다른 경우 업데이트
			if (!preStatus.equals(status)) {
				//회원 상태 변경
				userInfo.setStatus(status);
				
				//회원 수정 일시 변경
				userInfo.setModifyDateTime(LocalDateTime.now());
				
				//회원 상태 업데이트
				usr1000DAO.updateUsr1000UserInfo(userInfo);
			}
		}
		
		//회원 정보 반환
		return userInfo;
	}

	/**
	 * @desc 회원 등록
	 * @param Usr1000VO userInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void createUsr1000UserInfo(Usr1000VO userInfo) throws Exception {
		//DB에 데이터 담기
		usr1000DAO.createUsr1000UserInfo(userInfo);
	};


	/**
	 * @desc 회원 수정
	 * @param Usr1000VO userInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updateUsr1000UserInfo(Usr1000VO userInfo) throws Exception {
		//DB 데이터 업데이트
		usr1000DAO.updateUsr1000UserInfo(userInfo);
	}

	/**
	 * @desc 회원 삭제 로직
	 * @param String userId
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteUsr1000UserInfo(String userId) throws Exception {
		usr1000DAO.deleteUsr1000(userId);
	}
}
