package sta.sta1000.sta1000.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.config.AppConfig;

import sta.sta1000.sta1000.service.Sta1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원 통계 관련 작업 수행
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000ServiceImpl implements Sta1000Service {
	//생성자 주입
	private final Sta1000DAO sta1000DAO;

	// 생성자
	private Sta1000ServiceImpl(Sta1000DAO sta1000DAO) {
		this.sta1000DAO = sta1000DAO;
	}
	
	//싱글톤 내부 클래스
	private static class Sta1000ServiceImplHolder {
		private static final Sta1000ServiceImpl INSTANCE = new Sta1000ServiceImpl(AppConfig.sta1000DAO());
	}
	
	//싱글톤
	public static Sta1000ServiceImpl getInstance() {
		return Sta1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * @desc 회원 상태에 따른 통계를 반환	 - 회원 상태값은 매일 00시에 스케줄이 돌아서 업데이트된 상태라 가정한다.
	 * @return Map<String, Integer>
	 * @throws Exception
	 */
	@Override
	public Map<String, Integer> selectSta1000UserInfo() throws Exception {
		//사용중인 회원 목록 조회
		List<Usr1000VO> users = sta1000DAO.selectSta1000UserInfo();
		
		//전체 회원 수
		int totalUserCount = users.size();
		
		//임박 회원 수
		int imminentUserCount = 0;
		
		//만료 회원 수
		int expireUserCount = 0;
		
		//오늘 날짜
		LocalDate today = LocalDate.now();
		
		//임박 및 만료 회원 정보 조회
		for (Usr1000VO user : users) {
			//만료일이 지난 경우
			if (user.getExpireDate().isBefore(today)) {
				expireUserCount++;
			}
			//만료일이 5일 이내로 남은 경우
			else if (user.getExpireDate().isBefore(today.plusDays(6))) {
				imminentUserCount++;
			}
		}
		
		// 해당 data를 VO에 담아서 반환
		return Map.of("totalUserCount", totalUserCount, "imminentUserCount", imminentUserCount, "expireUserCount", expireUserCount);
	}
}
