package usr.usr1000.usr1000.service;

import java.util.List;

import usr.usr1000.usr1000.vo.Usr1000VO;

public interface Usr1000Service {
	//회원 목록
	List<Usr1000VO> selectUsr1000List() throws Exception;
	
	//회원 상세
	Usr1000VO selectUsr1000(String userId) throws Exception;
	
	//회원 등록
	void createUsr1000UserInfo(Usr1000VO userInfo) throws Exception;
	//회원 수정
	void updateUsr1000UserInfo(Usr1000VO userInfo) throws Exception;
	
//	void deleteUsr1000(String requestParams, Usr1000VO user) throws Exception;

}
