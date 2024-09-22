package sta.sta1000.sta1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;

import memorydb.domain.Availability;
import memorydb.domain.Status;
import sta.sta1000.sta1000.service.Sta1000Service;
import sta.sta1000.sta1000.vo.Sta1000VO;
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
	private final Sta1000DAO sta1000DAO;

	// 생성자
	private Sta1000ServiceImpl(Sta1000DAO sta1000DAO) {
		this.sta1000DAO = sta1000DAO;
	}
	
	//
	private static class Sta1000ServiceImplHolder {
		private static final Sta1000ServiceImpl INSTANCE = new Sta1000ServiceImpl(AppConfig.sta1000DAO());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Sta1000ServiceImpl
	 * @throws Exception
	 */
	public static Sta1000ServiceImpl getInstance() {
		return Sta1000ServiceImplHolder.INSTANCE;
	}

	/**
	 * @desc 회원 상태에 따른 통계를 반환 - 회원 상태값은 매일 00시에 스케줄이 돌아서 업데이트된 상태라 가정한다.
	 * @return Map<String, Usr1000VO>
	 * @throws Exception
	 */
	@Override
	public Map<String, Usr1000VO> selectSta1000UserInfo() throws Exception {
		//임박 회원 리스트
		List<Usr1000VO> imminentUsers = new ArrayList<>();
		
		// 만료 회원 리스트
		List<Usr1000VO> expireatoinUsers = new ArrayList<>();
		
		//TODO 통계 계산하기
		staticUser(imminentUsers, expireatoinUsers, selectUsr1000List);
		
		// 해당 data를 VO에 담아서 반환
		return ;
	}
}
