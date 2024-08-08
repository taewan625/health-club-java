package com.web;

/**
 * @Class Name : JavaViewResolver.java
 * @Description ViewResolver는 view template마다의 방식으로 view 객체를 반환.
 *              JavaViewResolver는 콘솔창으로 값을 반환하기 때문에 View반환형식은 ConsoleView라는
 *              인터페이스뷰를 구현한 view 구현체들을 Reflection API기술로 구현한다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class JavaViewResolver {
	/**
	 * Func : 논리 view명을 물리 view명으로 변환
	 * 
	 * @desc 논리 view명을 물리 view명으로 변환
	 * @param String
	 *            logicalViewName
	 * @return ViewTemplate
	 * @throws Exception
	 */
	public static JavaView resolveView(String logicalViewName) throws Exception {
		return new JavaView("view." + logicalViewName);
	}

}
