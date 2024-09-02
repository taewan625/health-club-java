package com.web;

import java.lang.reflect.Constructor;
import java.util.Map;

import view.JavaHTML;

/**
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

	//생성자
	public JavaView(String viewPath) {
		this.viewPath = viewPath;
	}

	/**
	 * @desc View 객체의 핵심 기능인 rendering 기능으로 jsp, thymeleaf를 html 혹은 json으로 반환하는 역할 수행하는 메서드
	 * @param Request request, Map<String, Object> model
	 * @return JavaHTML
	 * @throws Exception
	 */
	public JavaHTML render(Request request, Map<String, Object> model) throws Exception {
		//model 객체의 data를 request에 담기
		modelToRequestAttribute(request, model);
		
		//타겟 뷰 클래스의 생성자 생성
		Constructor<?> targetViewConstructor = Class.forName(viewPath).getConstructor();
		
		//타겟 뷰 객체 생성 및 반환 - 콘솔 프로젝트 특성상, html이 아닌 실제 자바 객체를 생성하는 과정이 렌더링 과정과 동일하다고 가정하여 구조 작성
		return (JavaHTML) targetViewConstructor.newInstance();
	};

	/**
	 * @desc modelView에 담은 값을 request에 넘겨준다.
	 * @param Request request, Map<String, Object> model
	 */
	private void modelToRequestAttribute(Request request, Map<String, Object> model) {
		//모델이 비어있지 않은 경우
		if (model != null) {
			//공유되는 clientDats 초기화 - 실제 웹 통신이 아니어서 통신, 응답이 분리되지 않아서 해당 시점에서 request 내부 데이터를 비운다.
			request.clearClientDatas();
			
			//반복문을 통해 데이터 세팅
			for (Map.Entry<String, Object> entry : model.entrySet()) {
				request.setClientDatas(entry.getKey(), entry.getValue());
			}
		}
	}

}
