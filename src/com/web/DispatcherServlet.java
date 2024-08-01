package com.web;

import java.util.Properties;

import com.propertiesconvert.MessageSource;

/**
 * @Class Name : DispatcherServlet.java
 * @Description : 1. handlerAdapter, handlerMapping 등록 2. handlerMapping : 핸들러
 *              반환 3. handlerAdapter : 핸들러 형변환 및 핸들러 동작 실행 후 modelView 반환 4.
 *              ViewResolver : view 논리명 반환 및 반환 데이터를 담을 view 객체 반환 5. 최종적으로
 *              view에서 반환하는 데이터 반환
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class DispatcherServlet {
	private final HandlerMapping handlerMapping = HandlerMapping.getInstance();
	public static Properties message = MessageSource.properties;

	/**
	 * @Class Name : DispatcherServletHolder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.22.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class DispatcherServletHolder {
		private static final DispatcherServlet INSTANCE = new DispatcherServlet();
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return DispatcherServlet
	 * @throws Exception
	 */
	public static DispatcherServlet getInstance() {
		return DispatcherServletHolder.INSTANCE;
	}

	/**
	 * Func : dispatcherServlet의 핵심 메서드
	 * 
	 * @desc : 요청 url로 핸들러 호출 -> 핸들러로 핸들러 어댑터 호출 -> 핸들러 어댑터가 modelView 반환 ->
	 *       viewResolver로 view 반환 -> view를 렌더링 하여 화면 출력 및 다음 요청 값 대기 @param
	 *       ClientRequest<?> request @return void : view Render 후 역할 종료 @throws
	 */
	public void service(Request request) {
		try {
			// handler 호출
			String handlerUrl = request.getHadlerUrl();
			ModelViewController controller = (ModelViewController) handlerMapping.getHandler(handlerUrl);

			// controller interface를 활용해서 핸들러 수행 -> 우선 순위 완료 후, handler adpater 수행
			ModelView modelView = controller.findMethod(request);

			// JavaViewResolver가 다루는 Javaview 객체를 반환. JavaView객체는 JavaHTML 확장자 파일을 다룬다.
			JavaView view = JavaViewResolver.resolveView(modelView.getViewName());

			// JavaView 객체를 이용해 응답화면을 출력
			view.render(request, modelView.getDatas());

		} catch (Exception e) {
			System.out.println("예상치 못한 예외 발생 : " + e.getMessage());

			if (request == null) {
				request = new Request(message.getProperty("err"));
			} else {
				request.setFullUrl(message.getProperty("err"));
			}
			
			service(request);
		}
	}
}
