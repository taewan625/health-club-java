package sta.sta1000.sta1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;

import memorydb.domain.Availability;
import memorydb.domain.Status;
import sta.sta1000.sta1000.service.Sta1000Service;
import sta.sta1000.sta1000.vo.Sta1000VO;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Sta1000ServiceImpl.java
 * @Description 회원 통계 관련 작업을 수행한다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000ServiceImpl implements Sta1000Service {
	public static Properties message = MessageSource.properties;

	private final Sta1000DAO sta1000DAO;

	// 생성자
	private Sta1000ServiceImpl(Sta1000DAO sta1000DAO) {
		this.sta1000DAO = sta1000DAO;
	}

	/**
	 * @Class Name Sta1000ServiceImplHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.28.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
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
	 * Func : 회원 통계 반환 정보
	 * 
	 * @desc 회원 통계를 반환하기전 회원 상태 업데이트 후 임박, 만료 기준에 따라 리스트 형태로 VO에 담아서 반환한다.
	 * @param
	 * @return Sta1000VO
	 * @throws Exception
	 */
	@Override
	public Sta1000VO selectUsr1000List() throws Exception {
		try {
			// 전체 회원 상태 다 업데이트
			sta1000DAO.updateAllUsr1000Status();
			// 전체 회원 리스트
			List<Usr1000VO> selectUsr1000List = sta1000DAO.selectUsr1000List();
			// 임박 회원 리스트
			List<Usr1000VO> imminentUsers = new ArrayList<>();
			// 만료 회원 리스트
			List<Usr1000VO> expireatoinUsers = new ArrayList<>();

			// 통계 계산하기
			staticUser(imminentUsers, expireatoinUsers, selectUsr1000List);
			// 해당 data를 VO에 담아서 반환
			return new Sta1000VO(sta1000DAO.selectUsr1000List().size(), imminentUsers.size(), expireatoinUsers.size(),
					sta1000DAO.selectUsr1000List(), imminentUsers, expireatoinUsers);
		} catch (Exception e) {
			// 예외 페이지는 해당 메서드를 호출한 컨트롤러에서 처리하도록 한다.
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 통계 계산 메서드
	 * 
	 * @desc 회원 통계를 계산 한다.
	 * @param List<Usr1000VO>
	 *            imminentUsers, List<Usr1000VO> expireatoinUsers, List<Usr1000VO>
	 *            selectUsr1000List
	 * @return void
	 * @throws Exception
	 */
	private void staticUser(List<Usr1000VO> imminentUsers, List<Usr1000VO> expireatoinUsers,
			List<Usr1000VO> selectUsr1000List) throws Exception {

		try {
			for (Usr1000VO user : selectUsr1000List) {
				// 삭제된 상태의 회원은 임박, 만료 의미가 없으므로 제외
				if (user.getDelete()== Availability.YES) {
				}
				// 임박 회원수
				else if (user.getStatus() == Status.IMMINENT) {
					imminentUsers.add(user);
				}
				// 만료 회원수
				else if (user.getStatus() == Status.EXPIRATION) {
					expireatoinUsers.add(user);
				}
			}
		} catch (Exception e) {
			// 예외 페이지는 해당 메서드를 호출한 컨트롤러에서 처리하도록 한다.
			throw new Exception(e);
		}
	}
}
