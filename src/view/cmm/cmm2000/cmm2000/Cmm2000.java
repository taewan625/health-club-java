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

	@Override
	public void response(Request requestWithResponseData) throws Exception {
		Map<String, ?> responseData = requestWithResponseData.getResponseData();
		String error_message = (String) responseData.get(ERROR_KEY);
		System.out.println(error_message);
		// 메뉴로 돌아가기
		System.out.println("메뉴화면으로 돌아가려면 아무 키나 누르세요.");
		scanner.nextLine();
		Request request = new Request(msg.getProperty("menu"));
		dispatcherServlet.service(request);
	}

}
