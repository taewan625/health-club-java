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
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000DAO {
	//회원 db table
	private Map<String, Usr1000VO> userStore = USR1000.getInstance().getUsr1000();
	
	//싱글톤 내부 클래스
	private static class Sta1000DAOHolder {
		private static final Sta1000DAO INSTANCE = new Sta1000DAO();
	}
	
	//싱글톤
	public static Sta1000DAO getInstance() {
		return Sta1000DAOHolder.INSTANCE;
	}

	/** TODO 
	 * @desc 전체 회원수, 임박 회원 수, 만료 회원 수 및 정보 반환
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		//전체 회원 리스트
		List<Usr1000VO> totalUsers = new ArrayList<>();
		for (User user : userStore.values()) {
			totalUsers.add(new Usr1000VO(user));
		}
		return totalUsers;
	}
}
