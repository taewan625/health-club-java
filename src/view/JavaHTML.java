package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.propertiesconvert.MessageSource;
import com.web.DispatcherServlet;
import com.web.Request;

/**
 * @Class Name : JavaHTML.java
 * @Description 자바 코드이지만 html 확장자처럼 나타낸 인터페이스
 * 
 * @version 1.0
 * @author 권태완
 * @Since 2024.1.1.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public interface JavaHTML {
	// dispatcher servlet
	DispatcherServlet dispatcherServlet = DispatcherServlet.getInstance();
	// messageProperties
	Properties msg = MessageSource.properties;
	// 에러메시지 key값
	String ERROR_KEY = "error";
	// 에러는 아닌 로직 처리 실패 key값
	String FAIL= "-1";
	// url 요청값을 미리 담아두는 map
	Map<String, String> urlMap = new HashMap<>();

	void response(Request requestWithResponseData) throws Exception;

	/**
	 * Func : 요청 url 반환 메서드
	 * 
	 * @desc 사용자 요청과 맞는 properties의 주소를 반환
	 * @param String
	 *            clientInputValue
	 * @return String client 입력값과 매칭되는 url 주소
	 * @throws Exception
	 */
	default String urlMatch(String clientInputValue) throws Exception {
		return urlMap.get(clientInputValue);
	}

}
