package com.web;

/**
 * @Class Name : ViewResolver.java
 * @Description ViewResolver는 view template 마다의 방식으로 view 객체를 반환 | ps. 각 view Template은 인터페이스로 View를 구현한다.
 *              View 객체란 : login.jsp 파일과 같은 특정 파일을 찾는 것이 아니라, 뷰를 나타내는 추상적인 객체를 찾는다.
 *              Spring 구조에서는 ViewResolver 인터페이스를 구현한 여러 종류의 ViewResolver객체들이 존재하고 선택된 viewResovler가 동작하는 방식
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class ViewResolver {
	/**
	 * @desc 논리 view 경로를 물리 view 경로로 변환 후 View 객체 반환		예) JSP View 구현체 - InternalResourceView, Thymeleaf View 구현체 - ThymeleafView
	 *       java 클래스로 만든 view를 처리해주는 JavaView 내가 생성한 JavaView를 반환
	 * @param String logicalViewPath
	 * @return JavaView
	 * @throws Exception
	 */
	public static JavaView resolveView(String logicalViewPath) throws Exception {
		return new JavaView("view." + logicalViewPath);
	}

}
