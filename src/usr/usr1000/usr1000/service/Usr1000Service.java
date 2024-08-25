package usr.usr1000.usr1000.service;

import java.util.List;

import usr.usr1000.usr1000.vo.Usr1000VO;

public interface Usr1000Service {
	List<Usr1000VO> selectUsr1000List() throws Exception;

	Usr1000VO selectUsr1000(String userId) throws Exception;

	//	boolean isContainsUsr1000WithOutDeleteUsr(String userId) throws Exception;
//	void createUsr1000(Usr1000VO user) throws Exception;
//
//	void updateUsr1000(String requestParams, Usr1000VO user) throws Exception;
//
//	void deleteUsr1000(String requestParams, Usr1000VO user) throws Exception;
//
//	List<String> selectUsrId1000List() throws Exception;

}
