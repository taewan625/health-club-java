package view.cmm.cmm2000.cmm2000;

import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import view.JavaHTML;

/**
 * @Class Name : Cmm2000.java
 * @Description 에러페이지
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.01.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm2000 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * @desc 에러 화면을 표출해주고 메인 화면으로 이동하도록 한다.
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request request) throws Exception {
		//request의 map data 받아오기
		Map<String, Object> responseData = request.getClientDatas();
		
		//저장한 데이터 조회
		String errorMsg = (String) responseData.get("errorMsg");
		
		//데이터 출력
		System.out.println(errorMsg);
		
		//에러페이지 기본 문구
		System.out.println("메뉴화면으로 돌아가려면 아무 키나 누르세요.");
		
		//입력값을 받는다.
		scanner.nextLine();
		
		//전송 객체 생성
		Map<String, Object> requestData = Map.of("url", "cmm/cmm1000/cmm1000/selectCmm1000View");
		
		//WAS에 요청
		webContainer.service(requestData);
	}

}
