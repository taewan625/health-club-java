package com.web;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class Name : ModelView.java
 * @Description : view 논리 path와 datas를 list로 가지는 class
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class ModelView {
	//논리적 경로
	private String viewName;
	
	//서버에서 view로 보내주는 데이터
	private Map<String, Object> datas = new HashMap<>();

	//생성자
	public ModelView(String viewName) {
		this.viewName = viewName;
	}
	
	//생성자
	public ModelView(String viewName, Map<String, Object> datas) {
		this(viewName);
		this.datas = datas;
	}
	
	public String getViewName() throws Exception {
		return viewName;
	}
	
	public Map<String, Object> getDatas() throws Exception {
		return datas;
	}
	
	public void setDatas(String name, Object value) throws Exception {
		this.datas.put(name, value);
	}
}
