package sta.sta1000.sta1000.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import memorydb.USR1000;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @version 1.0
 * @author 권태완
 * @Since 2024.09.22.
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

	/**
	 * @desc 사용중인 회원 목록 반환
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectSta1000UserInfo() throws Exception {
		//회원 목록 조회
		List<Usr1000VO> users = new ArrayList<>(userStore.values());
		
		//안전한 제거를 위한 iterator 사용 
		Iterator<Usr1000VO> iterator = users.iterator();
		
		//다음 요소가 있으면 동작
		while (iterator.hasNext()) {
			//
			Usr1000VO user = iterator.next();
			
			if (!"사용".equals(user.getUse())) {
				iterator.remove();
			}
		}
		
		//사용중인 회원만 반환
		return users;
	}
}
