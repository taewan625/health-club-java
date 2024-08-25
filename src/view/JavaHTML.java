package view;

import java.util.HashMap;
import java.util.Map;

import com.web.Request;
import com.web.WebContainer;

/**
 * @Class Name : JavaHTML.java
 * @Description 자바 프로젝트에서 처리하는 View 객체의 확장자 클래스로 View에서 공통적으로 수행할 정보를 가진다. 
 * @version 1.0
 * @author 권태완
 * @Since 2024.1.1.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public interface JavaHTML {
	//webContainer 
	WebContainer webContainer = WebContainer.getInstance();
	
	//url 요청값을 미리 담아두는 map
	Map<String, String> urlMap = new HashMap<>();
	
	//요청 데이터 (webContainer에 전송할 데이터 객체)
	Map<String,Object> requestData = new HashMap<>();

	/**
	 * @desc 콘솔 출력 데이터 
	 * @param Request requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	void response(Request requestWithResponseData) throws Exception;

	/**
	 * @desc 사용자 요청과 맞는 properties의 주소를 반환
	 * @param String clientInputValue
	 * @return String client 입력값과 매칭되는 url 주소
	 * @throws Exception
	 */
	default String urlMatch(String clientInputValue) throws Exception {
		return urlMap.get(clientInputValue);
	}

}
