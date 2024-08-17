package com.web;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc WAS애서 올린 webContainer 역할로 dispatcherServlet을 올리고 request, response를 받는 역할
 * 		 구조적으로 맞추기 위해서 생성하였고 실제 설정 정보를 읽고 context 객체를 생성하는 부분은 생략
 * @version 1.0
 * @author 권태완
 * @Since 2024.08.17.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class WebContainer {
	//내부 클래스 
	private static class WebContainerHolder {
		private static final WebContainer INSTANCE = new WebContainer();
	}
	
	//객체 호출 메서드
	public static WebContainer getInstance() {
		return WebContainerHolder.INSTANCE;
	}
	
	//생성자
	private WebContainer() {
		init();
	}
	
	//webContainer 초기화
	private void init() {
		//설정파일에 맞춰서 초기화할 객체 초기화 - 현프로젝트에서는 생략
	}
	
	//webContainer 서비스		TODO 쓰레드에 영향 받지 않도록 처리 필요	 및 싱글톤 객체로 변환
	@SuppressWarnings("unchecked")
	public void service(Map<String, Object> requestData) {
		//requestData 전처리 - url 정보 추출
		String url = String.valueOf(requestData.get("url"));
		
		//requestData 전처리 - 요청 데이터 추출
		Map<String, Object> clientDatas = (Map<String, Object>)requestData.get("clientDatas");
		
		//요청 데이터가 없을 경우 빈 값 넣어주기
		if (clientDatas == null) {
			clientDatas = new HashMap<String, Object>();
		}
		
		//요청 객체 생성
		Request request = new Request(url, clientDatas);
		
		//응답 객체 생성
		Response response = new Response();

		//싱글톤 디스패처 서블릿 조회
		DispatcherServlet dispatcherServlet = DispatcherServlet.getInstance();
		
		//요청 처리
		dispatcherServlet.service(request, response);
		
		//response, request 파괴
		request = null;
		response = null;
	}
	
	//webContainer 파괴
	@SuppressWarnings("unused")
	private void destroy() {}
}
