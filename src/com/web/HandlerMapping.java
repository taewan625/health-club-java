package com.web;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.MessageSource;

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
	//실제 Class 객체와 method 객체가 담긴 객체를 반환 
	public Map<String, Object> getHandler(Request request) {
		//반환 객체
		Map<String, Object> returnMap = new HashMap<>();

		//URL 요청과 관련된 Controller 정보 호출
		String rawHandlerInfo = MessageSource.getMessage("bean." + request.getUrl());

		//URL이 없는 경우 - Error Page Controller 정보 호출
		if (null == rawHandlerInfo) {
			rawHandlerInfo = MessageSource.getMessage("bean.errorPage");
		}
		
		//controller 정보와 method 정보 분리
		String[] handlerInfo = rawHandlerInfo.split("-");
		
		//Reflection API를 이용한 class 및 method 객체 생성
		try {
			//클래스
			Class<?> targetClass = Class.forName(handlerInfo[0]);
			
			//싱글톤 호출 메서드
			Method getInstance = targetClass.getMethod("getInstance");
			
			//클래스 인스턴스
			Object targetInstance = getInstance.invoke(targetClass);
			
			//메서드
			Method method = targetClass.getMethod(handlerInfo[1], Request.class);
			
			//반환 객체에 데이터를 담는다.
			returnMap.put("targetInstance", targetInstance);
			returnMap.put("method", method);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnMap;
	}
	
}
