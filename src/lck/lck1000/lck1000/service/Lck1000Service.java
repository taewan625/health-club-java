package lck.lck1000.lck1000.service;

import java.util.List;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;

public interface Lck1000Service {
	//사물함 목록
	List<Lck1000VO> selectLck1000List(Map<String, String> pageInfo) throws Exception;
	
	//사물함 단건 조회
	Lck1000VO selectLck1000(String selectType, String selectValue) throws Exception;
	
//	boolean isValidSelectKeyValue(Map<String, Object> clientData) throws Exception;
//
//
//	void createLck1000(Lck1000VO lck1000VO) throws Exception;
//
//	void updateLck1000(Lck1000VO lck1000vo) throws Exception;
//
//	void deleteLck1000(int lockerNum, Lck1000VO lck1000vo) throws Exception;
//
//	List<String> selectUsr1000List() throws Exception;

}
