package com.web;

import view.JavaHTML;

/**
 * @desc 응답 객체 
 * @version 1.0
 * @author 권태완
 * @Since 2024.08.06
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Response {
	/**
	 * @desc 응답 처리
	 */
	void write(JavaHTML view, Request request) {
		try {
			view.response(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
