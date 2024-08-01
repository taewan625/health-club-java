package sta.sta1000.sta1000.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.propertiesconvert.MessageSource;

import memorydb.USR1000;
import memorydb.domain.Status;
import memorydb.domain.User;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Sta1000DAO.java
 * @Description 회원 data로 통계정보를 출력한다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000DAO {
	public static Properties message = MessageSource.properties;
	// db table
	private Map<Long, User> userStore;

	/**
	 * @Class Name Sta1000DAOHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.28.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Sta1000DAOHolder {
		private static final Sta1000DAO INSTANCE = new Sta1000DAO();
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Sta1000DAO
	 * @throws Exception
	 */
	public static Sta1000DAO getInstance() throws Exception {
		return Sta1000DAOHolder.INSTANCE;
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
	public void setDBTable(USR1000 db) throws Exception {
		try {
			this.userStore = USR1000.getInstance().userStore;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 회원 전체 정보, 회원수, 임박 회원 수, 만료 회원 수를 반환하는 메서드
	 * 
	 * @desc 회원 전체 정보, 회원수, 임박 회원 수, 만료 회원 수를 반환, 해당 메서드 사용전 회원 상태를 업데이트를 해야한다.
	 * @param
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		try {
			// 전체 회원 리스트
			List<Usr1000VO> totalUsers = new ArrayList<>();
			for (User user : userStore.values()) {
				totalUsers.add(new Usr1000VO(user));
			}
			return totalUsers;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * Func : 모든 회원 상태를 업데이트하는 메서드
	 * 
	 * @desc 삭제된 사용자의 정보는 전체 회원이 정보보다 비중이 매우 낮기 때문에 같이 상태를 업데이트 시킨다.
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void updateAllUsr1000Status() throws Exception {
		try {
			for (User user : userStore.values()) {
				Status userStatus = checkUserStatus(user.getExpireDate());
				user.setStatus(userStatus);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func : 마감일자 기준 회원 상태 업데이트 메서드
	 * 
	 * @desc 현일자와 마감일자 차이가 0이하는 만기, 5이하는 임박 이외는 정상 상태 반환
	 * @param LocalDate
	 *            expireDate
	 * @return Status
	 * @throws Exception
	 */
	private Status checkUserStatus(LocalDate expireDate) throws Exception {
		try {
			// now와 expireDate 사이의 일수를 계산
			long daysExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expireDate);

			if (daysExpiration <= 0) {
				return Status.EXPIRATION;
			} else if (daysExpiration <= 5) {
				return Status.IMMINENT;
			}
			return Status.EFFECTIVE;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
