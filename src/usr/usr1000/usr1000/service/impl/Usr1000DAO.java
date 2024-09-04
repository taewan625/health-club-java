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
	 * @param Map<String,String> PageInfo {"selectPage", "1", "range", "10"}
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectUsr1000List(Map<String, String> pageInfo) throws Exception {
		//회원 목록 변수
		List<Usr1000VO> users = new ArrayList<>(userStore.values());
		
		//선택한 페이지
		String selectPage = pageInfo.get("selectPage");
		
		//전체 회원 목록을 받지 않는 경우 == 페이징 처리를 하는 경우
		if (!"all".equals(selectPage)) {
			//시작 범위
			int from = (Integer.parseInt(selectPage) - 1) * Integer.parseInt(pageInfo.get("range"));
			
			//종료 범위
			int to = (Integer.parseInt(selectPage)) * Integer.parseInt(pageInfo.get("range"));
			
			//종료 범위가 토탈 개수보다 작을 경우
			to = (users.size() < to) ? users.size() : to;
			
			//조회 회원 목록
			users = users.subList(from, to);
		}
		
		//회원목록 반환
		return users;
	}
	
	/**
	 * @desc 회원 목록 수 반환
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr1000ListCnt() throws Exception {
		//회원목록 수 반환 
		return userStore.size();
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
	 * @desc 회원 정보 수정
	 * @param Usr1000VO usr1000VO
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000UserInfo(Usr1000VO usr1000VO) throws Exception {
		userStore.replace(usr1000VO.getId(), usr1000VO);
	}
	
	/**
	 * @desc 회원 삭제 시,삭제여부, 사용여부만 업데이트
	 * @param String userId
	 * @return Usr1000VO
	 * @throws Exception
	 */
	public Usr1000VO deleteUsr1000(String userId) throws Exception {
		return userStore.remove(userId);
	}
}
