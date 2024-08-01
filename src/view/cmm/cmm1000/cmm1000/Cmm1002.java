package view.cmm.cmm1000.cmm1000;

import java.util.Properties;
import java.util.Scanner;

import com.propertiesconvert.MessageSource;
import com.val.Validator;
import com.web.DispatcherServlet;
import com.web.Request;

/**
 * @Class Name : Cmm1002.java
 * @Description 등록, 수정, 삭제 시 재질문 하는 공통 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.27.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1002 {
	// messageProperties
	static Properties msg = MessageSource.properties;

	static Scanner scanner = new Scanner(System.in);

	public static void submitPage(String handler, Object objectVO) throws Exception {
		// 제출 여부 질문
		String submitAnswer = askSubmit(msg.getProperty("SUBMIT.FORM"));
		Request request = new Request(msg.getProperty(handler));
		// 제출
		submit(submitAnswer, request, objectVO);
	}

	/**
	 * Func : 등록, 수정, 삭제 시 재질문 하는 공통 메서드
	 * 
	 * @desc y,n 로 대답을 하고 해당 문자열을 반환
	 * @param
	 * @return String
	 * @throws Exception
	 */
	public static String askSubmit(String prompt) throws Exception {
		System.out.println(prompt);
		String clientAnswer = scanner.next().trim();

		while (!Validator.isYN(clientAnswer)) {
			System.out.println("올바른 값을 넣어주세요");
			clientAnswer = scanner.next().trim();
		}
		// 등록 여부에 관한 재확인 메서드 반환
		return clientAnswer;
	}

	/**
	 * Func : 사용자 응답에 맞춰서 요청값 반환하는 메서드
	 * 
	 * @desc 사용자 응답에 맞춰서 요청값 반환
	 * @param String
	 *            clientAnswer, String url, Object lckVO
	 * @return ClientRequest<?>
	 * @throws Exception
	 */
	public static void submit(String clientAnswer, Request request, Object clientData) throws Exception {
		if (clientAnswer.equals("n")) {
			return;
		} else {
			request.setClientData("clientData", clientData);
		}
		DispatcherServlet.getInstance().service(request);
	}
}
