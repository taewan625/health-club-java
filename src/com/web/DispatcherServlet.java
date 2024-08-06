package com.web;

import java.lang.reflect.Method;
import java.util.Map;

import com.MessageSource;


/**
 * @Description 1. handlerAdapter, handlerMapping 등록 
 * 				2. handlerMapping : 핸들러반환 
 * 				3. handlerAdapter : 핸들러 형변환 및 핸들러 동작 실행 후 modelView 반환 
 * 				4. ViewResolver : view 논리명 반환 및 반환 데이터를 담을 view 객체 반환 
 * 				5. 최종적으로 view에서 반환하는 데이터 반환
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class DispatcherServlet {
	//내부 클래스 
	private static class DispatcherServletHolder {
		private static final DispatcherServlet INSTANCE = new DispatcherServlet();
	}
	
	//객체 호출 메서드
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
//개념 : view로 데이터를 주는 역할이 modelView, 외부 요청 : request,	 modelView 데이터를 조합해서 화면으로 뿌려주는게 response
	//콘솔 프로젝트에서는 response 활용도는 없다고 봐야함.
	public void service(Request request, Response response) {
		try {
			//request 정보를 HandlerMapping에게 넘겨주고 Controller Class 동적으로 가져온다.
			Map<String, Object> handler = HandlerMapping.getHandler(request);
			
			/*TODO 나중에 HandlerAdapter로 변경
			 * Spring의 HadnlerAdapter의 메서드인 invokeHandlerMethod에서 매개변수로 
			 * request, response, handlerMethod( 내코드에선 Map<String, Object> handler)를 받아서 최종적으로 ModelAndView 반환
			 * */
			
			//Instance 추출
			Object controller = handler.get("classInstance");

			//Method 추출
			Method method = (Method) handler.get("method");
			
			//TODO HandlerAdapter가 실제 동작 수행해서 ModelAndView 반환. String ModelView 2가지 형태로 반환하는거 try 해서 만들기
			//실제 로직 수행
			ModelView modelView = (ModelView) method.invoke(controller, request, response);
			
			// JavaViewResolver가 다루는 Javaview 객체를 반환. JavaView객체는 JavaHTML 확장자 파일을 다룬다.
			JavaView view = JavaViewResolver.resolveView(modelView.getViewName());

			// JavaView 객체를 이용해 응답화면을 출력
			//view.render(request, modelView.getDatas());

		} catch (Exception e) {
			System.out.println("예상치 못한 예외 발생 : " + e.getMessage());

			if (request == null) {
				request = new Request(MessageSource.getMessage("message.err"));
			} else {
				request.setFullUrl(MessageSource.getMessage("message.err"));
			}
			
			service(request, response);
		}
	}
}
