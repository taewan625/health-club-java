package view.sta.sta1000.sta1000;

import java.util.Map;

import com.val.ViewCore;
import com.web.Request;

import view.JavaHTML;

/**
 * @Description 통계 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.29.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000 implements JavaHTML {
	@SuppressWarnings("unchecked")
	@Override
	public void response(Request request) throws Exception {
		System.out.println("[통계]");
		
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//회원 통계 조회
		Map<String, Integer> userStatistics = (Map<String, Integer>) datas.get("userStatistics");
		
		System.out.println("사용중인 전체 회원 수 : " + userStatistics.get("totalUserCount"));
		System.out.println("임박 회원 수 : " + userStatistics.get("imminentUserCount"));
		System.out.println("만료 회원 수 : " + userStatistics.get("expireUserCount"));
		
		//닫기 문구
		System.out.println("닫기 - 아무키나 누르세요");
		
		//닫기 동작
		ViewCore.scanner.nextLine();
		
		//사물함 메뉴로 이동하는 전송 객체 생성
		requestData.put("url", "cmm/cmm1000/cmm1000/selectCmm1000View");
		
		//WAS에 요청
		webContainer.service(requestData);
	}
}
