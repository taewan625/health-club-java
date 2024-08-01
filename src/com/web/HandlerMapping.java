package com.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.config.SpringContainer;

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
	 * Func 초기화 메서드를 담는 생성자
	 * 
	 * @desc 싱글톤 생성시, Map 초기화 예외 발생 경우 초기화 문제를 잡기 위해 try-catch 수행 main method에서 객체를
	 *       초기화 할 경우 try-catch로 잡는 것과 같이 생성자 내부에서 초기화 수행시, try-catch로 잡는 형식으로
	 *       생성 @param @return
	 * @throw RuntimeException
	 */
	private HandlerMapping() {
		try {
			this.handlerMaps = initHandlerMappings();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Class Name : HandlerMappingHolder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.22.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class HandlerMappingHolder {
		private static final HandlerMapping INSTANCE = new HandlerMapping();
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 싱글톤 객체 반환
	 * @param
	 * @return HandlerMapping
	 * @throws Exception
	 */
	public static HandlerMapping getInstance() {
		return HandlerMappingHolder.INSTANCE;
	}

	/**
	 * Func : 객체 생성 시 handlerMaps를 초기화 하는 메서드
	 * 
	 * @desc
	 * @param
	 * @return Map<String, String> 읽기 전용 map으로 반환
	 * @throws Exception
	 */
	private Map<String, Object> initHandlerMappings() throws Exception {

		Map<String, Object> beanContainer = SpringContainer.getInstance().beanContainer;

		Map<String, Object> handlerMaps = new HashMap<>();
		handlerMaps.put("cmm/cmm1000/cmm1000", beanContainer.get("cmm1000Controller"));
		handlerMaps.put("usr/usr1000/usr1000", beanContainer.get("usr1000Controller"));
		handlerMaps.put("lck/lck1000/lck1000", beanContainer.get("lck1000Controller"));
		handlerMaps.put("sta/sta1000/sta1000", beanContainer.get("sta1000Controller"));
		handlerMaps.put("cmm/cmm2000/cmm2000", beanContainer.get("cmm2000Controller"));
		return Collections.unmodifiableMap(handlerMaps);
	};

	private Map<String, Object> handlerMaps;

	/**
	 * Func : handler 객체 반환 메서드
	 * 
	 * @desc : handler 객체 참조 반환
	 * @param String
	 *            url
	 * @return Handler
	 * @throws Exception
	 */
	public Object getHandler(String url) throws Exception {
		// 존재하지 않는 url의 경우 보내주는 예외 페이지 컨트롤러
		if (handlerMaps.get(url) == null) {
			return handlerMaps.get("cmm/cmm2000/cmm2000");
		}
		return handlerMaps.get(url);
	}
}
