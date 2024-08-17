package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @Class Name : MessageSource.java
 * @Description message properties 파일을 받아서 해시테이블에 저장 후, 사용
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.29.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class MessageSource {
	//메시지 프로퍼티 객체 생성 
	private static final Map<String, Properties> properties = new HashMap<>();
	
	//class loading 시점에 초기화
	static {
		//.properties가 존재하는 경로
		String propertiesPath = "/Users/taewan/Documents/develop/project/health-club/health-club-java/src/resource";		
		
		//경로 내 탐색한 파일명 조회
		String[] filenames = new File(propertiesPath).list();
		
		//파일 경로마다 프로퍼티 추가
        for (String filename : filenames) {
        	//.properties 파일인 경우만 map에 담기 
        	if (filename.contains(".properties")) {
        		//try-with-resource로 자원 누수 방지
                try (FileInputStream fis = new FileInputStream(propertiesPath + "/" + filename)) {
                    //프로퍼티 객체 생성
                	Properties prop = new Properties();
                	//파일 정보 담기
                    prop.load(fis);
                    //map에 담기
                    properties.put(filename.split("\\.")[0], prop);
                    
                } catch (IOException e) {
        			//*1.URL 경로도 해당 프로퍼티에 존재하기 때문에 예외 던지기로 프로그램 강제 종료
        			//TODO readMe에 들어갈 내용 - 내부클래스는 클래스 로더 시점이 달라서 try-catch롤 잡을 수 없어서 이 시점에 예외를 발생 시킨 후 강제종료 
        			throw new RuntimeException(e);
        		}
        	}
        }
        
        //더 이상 사 안하는 객체 참조 해제
        filenames = null;
	}
	
	/**
	 * @desc   properties 객체에 직접 접근하지 않고 값만 반환
	 * @param  key
	 * @return String
	 */
	public static String getMessage(String key) {
		//반환할 변수
		String value = "";
		
		try {
			//split key and file name
			int firstIndex = key.indexOf(".");
			
			//get value
			value = properties.get(key.substring(0, firstIndex)).getProperty(key.substring(firstIndex + 1));
			
		}
		//올바르지 않는 key 접근으로 예외 발생 시, 오류 로그 표출 및 빈 문자열 반환
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return value;
	}
}