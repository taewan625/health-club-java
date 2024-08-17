package com.web;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @Class Name : HandlerMapping.java
 * @Description : Handler를 Map 으로 가지고 있는 클래스로 핸들러를 반환해준다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class HandlerMapping {
	/**
	 * @desc   실제 Class 객체와 method 객체가 담긴 객체를 반환 
	 * @param  Request request
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> getHandler(Request request) {
		//반환 객체
		Map<String, Object> returnMap = new HashMap<>();
		
		try {
			//URL 요청과 관련된 Controller 정보 호출
			String rawHandlerInfo = MessageSource.getMessage("bean." + request.getUrl());
	
			//URL이 없는 경우 - Error Page Controller 정보 호출
			if (null == rawHandlerInfo) {
				rawHandlerInfo = MessageSource.getMessage("bean.errorPage");
			}
			
			//controller 정보와 method 정보 분리
			String[] handlerInfo = rawHandlerInfo.split("-");
		
			//클래스 객체 생성
			Class<?> targetClass = Class.forName(handlerInfo[0]);
			
			//싱글톤 호출 메서드
			Method getInstance = targetClass.getMethod("getInstance");
			
			//클래스 인스턴스
			Object classInstance = getInstance.invoke(targetClass);
			
			//메서드
			Method method = targetClass.getMethod(handlerInfo[1], Request.class, Response.class);
			
			//반환 객체에 데이터를 담는다.
			returnMap.put("classInstance", classInstance);
			returnMap.put("method", method);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return returnMap;
	}
}
