package com.utl;

import java.util.Arrays;
import java.util.List;

/**
 * @Class Name : Parser.java
 * @Description : String 분리 후 반환하는 메서드들의 집합 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Parser {

	/**
	 * Func view에서 받는 전체 url 을 url과 method+params로 자르는 메서드
	 * 
	 * @desc "usr/usr1000/usr1000/selectUsr1000?key=value" -> "usr/usr1000/usr1000"
	 *       형태로 반환
	 * @param String url
	 * @return String
	 * @throws Exception
	 */
	public static String handlerUrl(String url) throws Exception {
		try {
			int lastIndex = url.lastIndexOf("/");
			String handlerUrl = url.substring(0, lastIndex);

			return handlerUrl;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func view에서 받는 전체 url 을 url과 method+params로 자르는 메서드
	 * 
	 * @desc "usr/usr1000/usr1000/selectUsr1000?key=value" ->
	 *       "selectUsr1000?key=value"형태로 반환
	 * @param String url
	 * @return String
	 * @throws Exception
	 */
	public static String methodUrl(String url) throws Exception {
		try {
			int lastIndex = url.lastIndexOf("/");
			String methodUrl = url.substring(lastIndex + 1);

			return methodUrl;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func 메서드명+params로 구성된 String 분리 메서드
	 * 
	 * @desc "selectUsr1000?key=value" -> list("selectUsr1000", "key=value") 형태로 반환
	 * @param String methodUrl
	 * @return List<String>
	 * @throws Exception
	 */
	public static List<String> params(String methodUrl) throws Exception {
		try {
			return Arrays.asList(methodUrl.split("\\?"));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func params 의 value를 반환하는 메서드
	 * 
	 * @desc "key=value" -> "key" 형태로 파싱
	 * @param String params
	 * @return String
	 * @throws Exception
	 */
	public static String paramsKey(String params) throws Exception {
		try {
			int lastIndex = params.lastIndexOf("=");
			return params.substring(0, lastIndex);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Func params 의 value를 반환하는 메서드
	 * 
	 * @desc "key=value" -> "value" 형태로 파싱
	 * @param String params
	 * @return String
	 * @throws Exception
	 */
	public static String paramsValue(String params) throws Exception {
		try {
			int lastIndex = params.lastIndexOf("=");
			return params.substring(lastIndex + 1);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
