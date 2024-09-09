package lck.lck1000.lck1000.service;

import java.util.List;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;

public interface Lck1000Service {
	//사물함 목록 조회
	List<Lck1000VO> selectLck1000List(Map<String, String> pageInfo) throws Exception;
	
	//사물함 단건 조회
	Lck1000VO selectLck1000(String selectType, String selectValue) throws Exception;
	
	//사물함 등록 로직
	boolean createLck1000LockerInfo(Lck1000VO lck1000VO) throws Exception;
	
//	void deleteLck1000(int lockerNum, Lck1000VO lck1000vo) throws Exception;
//
//	List<String> selectUsr1000List() throws Exception;

}
