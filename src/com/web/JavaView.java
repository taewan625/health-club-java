package com.web;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import view.JavaHTML;

/**
 * @Class Name : JavaView.java
 * @Description .java 형태의 view를 처리하는 View 객체 주요 메서드로 render()
 *              예) JSP, Thymeleaf 를 처리하는 View 객체 (InternalResourceView, ThymeleafView)도 동일하게 render() 기능 존재
 *              cf) 콘솔 프로젝트 특성상 render과정이 필요 없지만 구조상 학습을 목적으로 표출 
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.19.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class JavaView {
	//view 절대 경로
	private String viewPath;

	//url 경로 넣는 부분
	Map<String, String> map = new HashMap<>();

	//생성자
	public JavaView(String viewPath) {
		this.viewPath = viewPath;
	}

	/**
	 * @desc View 객체의 핵심 기능인 rendering 기능으로 jsp, thymeleaf를 html 혹은 json으로 반환하는 역할 수행하는 메서드
	 * @param Map<?,Object>  data
	 * @return String client 입력값
	 * @throws Exception
	 */
	//TODO javaView에서 실제표출해야하는 view 파일 객체 정보만 동적으로 받아오기
	public JavaHTML render(Request request, Map<String, ?> model) throws Exception {
		//model 객체의 data를 request에 담기
		modelToRequestAttribute(request, model);
		
		//타겟 뷰 클래스의 생성자 생성
		Constructor<?> targetViewConstructor = Class.forName(viewPath).getConstructor();
		
		//타겟 뷰 객체 생성 및 반환 - 콘솔 프로젝트 특성상, html이 아닌 실제 자바 객체를 생성하는 과정이 렌더링 과정과 동일하다고 가정하여 구조 작성
		return (JavaHTML) targetViewConstructor.newInstance();
	};

	/**
	 * @param model controller가 준 setAttribute() 정보가 있는 modelview의 map을 request로 변환
	 * @param request model 정보를 request에 담을 것임
	 */
	private void modelToRequestAttribute(Request request, Map<String, ?> model) {
		if (model != null) {
			for (Map.Entry<String, ?> entry : model.entrySet()) {
				request.setResponseData(entry.getKey(), entry.getValue());
			}
		}
	}

}
