package lck.lck1000.lck1000.service;

import java.util.List;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;

public interface Lck1000Service {
	//사물함 목록 조회
	List<Lck1000VO> selectLck1000List(Map<String, String> pageInfo) throws Exception;
	
	//사물함 단건 조회
	Lck1000VO selectLck1000(String selectType, String selectValue) throws Exception;
	
	//사물함 저장 로직
	boolean saveLck1000LockerInfo(Lck1000VO lck1000VO) throws Exception;
	
	//사물함 삭제 로직
	void deleteLck1000LockerInfo(String lockerNumber) throws Exception;
}
