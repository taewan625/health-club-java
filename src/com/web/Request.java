package com.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utl.Parser;

/**
 * @Class Name : ClientRequest.java
 * @Description 터미널 프로그램 특성상, 외부로부터 요청을 받을 수가 없다. 때문에 렌더링을 하면서 바로 요청을 받아야하기 때문에
 *              url뿐만 아니랑 정보를 받기 위해 고객 요청 정보를 받는 클래스를 생성
 * 
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.24.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Request {
	//전체 url
	private String url;
	
	//요청 데이터
	private Map<String, Object> clientDatas = new HashMap<>();
	

	//생성자
	public Request(String url) {
		this.url = url;
	}

	//생성자
	public Request(String url, Map<String, Object> clientData) {
		this(url);
		this.clientDatas = clientData;
	}

	//컨트롤러 메서드명 조회
	public String getMethodUrl() throws Exception {
		String methodUrlWithParams = Parser.methodUrl(url);
		List<String> params = Parser.params(methodUrlWithParams);
		return params.get(0);
	}

	// 컨트롤러 get요청
	public String getParams() throws Exception {
		String methodUrlWithParams = Parser.methodUrl(url);
		List<String> params = Parser.params(methodUrlWithParams);
		if (params.size() == 2) {
			return params.get(1);
		}
		return null;
	}

	public Map<String, Object> getClientData() {
		return clientDatas;
	}

	public String getUrl() {
		return url;
	}

	public void setClientData(String key, Object value) {
		this.clientDatas.put(key, value);
	}

	public void setResponseData(String key, Object value) {
		this.responseDatas.put(key, value);
	}

	//응답 데이터 TODO 제거 예정 
	private Map<String, Object> responseDatas = new HashMap<>();	
	public Map<String, ?> getResponseData() {
		return responseDatas;
	}
	public void setFullUrl(String fullUrl) {
		this.url = fullUrl;
	}

}
