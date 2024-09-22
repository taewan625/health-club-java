package sta.sta1000.sta1000.service;

import java.util.Map;

public interface Sta1000Service {
	//통계 정보
	Map<String, Integer> selectSta1000UserInfo() throws Exception;
}
