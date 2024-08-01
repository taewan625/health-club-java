package com.propertiesconvert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Class Name : BeanSource.java
 * @Description bean properties 파일을 받아서 해시테이블에 저장 후, 사용
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.29.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class BeanSource {
	public static final Properties properties = new Properties();

	// static 초기화 메서드
	static {
		try {
			properties.load(new FileInputStream(
					"C:\\sdf\\DEV\\eGovFrame-3.8.0\\javaproject\\health-club\\src\\resource\\bean.properties"));
		} catch (IOException e) {
			System.out.println("properties에서 문제가 발생했습니다.");
		}

	}
}