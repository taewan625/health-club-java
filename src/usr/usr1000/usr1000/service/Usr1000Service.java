package usr.usr1000.usr1000.service;

import java.util.List;
import java.util.Map;

import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원관리의 조회, 등록, 수정, 삭제 관리 핵심 기능을 수행한다.
 * @version 1.0
 * @author 권태완
 * @Since 2024.08.31.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public interface Usr1000Service {
	//회원 목록
	List<Usr1000VO> selectUsr1000List(Map<String, String> pageInfo) throws Exception;
	
	//총 회원 수
	int selectUsr1000ListCnt() throws Exception;
	
	//회원 상세
	Usr1000VO selectUsr1000(String userId) throws Exception;
	
	//회원 등록
	void createUsr1000UserInfo(Usr1000VO userInfo) throws Exception;
	
	//회원 수정
	void updateUsr1000UserInfo(Usr1000VO userInfo) throws Exception;
	
	//회원 삭제
	Usr1000VO deleteUsr1000UserInfo(String userId) throws Exception;

}
