package com.web;

/**
 * @Class Name : ModelViewHandler.java
 * @Description 모델뷰반환 타입을 가지는 컨트롤러의 상위 인터페이스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.31.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public interface ModelViewController {
	// 에러메시지 key값
	String ERROR_KEY = "error";

	/**
	 * Func :modelView 반환 메서드
	 * 
	 * @desc 사용자 요청 중, url 메서드 와 get 요청, post요청을 methodDatas, data로 나눠서 담았다.
	 * @param Map<?,Object>
	 *            data
	 * @return String client 입력값
	 * @throws Exception
	 */
	ModelView findMethod(Request request) throws Exception;
}
