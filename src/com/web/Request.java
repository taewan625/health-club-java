package com.web;

import java.util.HashMap;
import java.util.Map;

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
	private Map<String, Object> datas = new HashMap<>();
	

	//생성자
	public Request(String url) {
		this.url = url;
	}

	//생성자
	public Request(String url, Map<String, Object> datas) {
		this(url);
		this.datas = datas;
	}
	
	/*getter & setter*/
	public String getUrl() {
		return url;
	}
	
	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(String key, Object value) {
		this.datas.put(key, value);
	}
}
