package com.web;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import view.JavaHTML;

/**
 * @Class Name : ConsoleView.java
 * @Description JavaViewResolver에서 반환하는 view 객체의 타입의 인터페이스. 해당 인터페이스는 modelView의
 *              값을 request로 전달하고 화면출력을 하는 렌더링 과정을 거친다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.19.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class JavaView {
	// view 절대 경로
	private String viewPath;

	// url 경로 넣는 부분
	Map<String, String> map = new HashMap<>();

	// 생성자
	public JavaView(String viewPath) {
		this.viewPath = viewPath;
	}

	/**
	 * Func : view 실행 메서드
	 * 
	 * @desc model을 가지고 있는 메서드
	 * @param Map<?,Object>
	 *            data
	 * @return String client 입력값
	 * @throws Exception
	 */
	public void render(Request request, Map<String, ?> model) throws Exception {
		// 1. viewName으로 실제 수행해야될 실제 view 생성
		JavaHTML html = createViewInstance(viewPath);
		// 2. data는 request로 값 이동
		modelToRequestAttribute(request, model);
		// 3. 실제 응답과정 수행
		html.response(request);
	};

	/**
	 * @param model
	 *            controller가 준 setAttribute() 정보가 있는 modelview의 map을 request로 변환
	 * @param request
	 *            model 정보를 request에 담을 것임
	 */
	private void modelToRequestAttribute(Request request, Map<String, ?> model) {
		if (model != null) {
			for (Map.Entry<String, ?> entry : model.entrySet()) {
				request.setResponseData(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * Func : ReflectionAPI를 활용하여 실제 View객체를 반환
	 * 
	 * @desc 모든 view는 viewTemplate을 인터페이스로 가진다.
	 * @param String
	 *            absoluteViewName
	 * @return ViewTemplate
	 * @throws Exception
	 */
	private static JavaHTML createViewInstance(String absoluteViewName) throws Exception {
		Class<?> viewClass = Class.forName(absoluteViewName);
		Constructor<?> viewConstructor = viewClass.getConstructor();
		return (JavaHTML) viewConstructor.newInstance();
	}
}
