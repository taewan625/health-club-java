package usr.usr1000.usr1000.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import memorydb.USR1000;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원 data를 조회, 등록 ,수정, 삭제 하는 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000DAO {
	//회원 db table
	private Map<String, Usr1000VO> userStore = USR1000.getInstance().getUsr1000();
	
	//싱글톤 내부 클래스
	private static class Usr1000DAOHolder {
		private static final Usr1000DAO INSTANCE = new Usr1000DAO();
	}

	//싱글톤
	public static Usr1000DAO getInstance() throws Exception {
		return Usr1000DAOHolder.INSTANCE;
	}

	/**
	 * @desc 회원 목록 반환
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		//회원 목록 변수
		List<Usr1000VO> users = new ArrayList<>();
		
		//회원 목록을 list에 담는다.
		for (Usr1000VO user : userStore.values()) {
			users.add(user);
		}
		
		//회원목록 반환 
		return users;
	}

	/**
	 * @desc 회원 정보를 반환
	 * @param String userId
	 * @return Usr1000VO
	 * @throws Exception
	 */
	public Usr1000VO selectUsr1000(String userId) throws Exception {
		return userStore.get(userId);
	}
	
	/**
	 * @desc 회원 등록
	 * @param Usr1000VO usr1000VO
	 * @return void
	 * @throws Exception
	 */
	public void createUsr1000UserInfo(Usr1000VO usr1000VO) throws Exception {
		userStore.put(usr1000VO.getId(), usr1000VO);
	}
	
	/**
	 * @desc 회원 데이터 수정
	 * @param String userId, Usr1000VO userVO
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000UserInfo(Usr1000VO usr1000VO) throws Exception {
		//TODO replace 같은 경우 key값이 존재하지 않을 때 null 반환, 필요시 해당 정보를 이용해서 service단 검증로직 추가
		userStore.replace(usr1000VO.getId(), usr1000VO);
	}
	
//
//	/**
//	 * @desc 회원 삭제 시,삭제여부, 사용여부만 업데이트
//	 * @param String id
//	 * @return void
//	 * @throws Exception
//	 */
//	public void deleteUsr1000(String id) throws Exception {
//		try {
//			User deleteUser = selectUser(id);
//			
//			deleteUser.setUse(Availability.NO);
//			deleteUser.setDelete(Availability.YES);
//			
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//	}
}
