import java.util.Map;

import com.config.SpringContainer;
import com.propertiesconvert.MessageSource;
import com.web.DispatcherServlet;
import com.web.Request;

import lck.lck1000.lck1000.service.impl.Lck1000DAO;
import memorydb.LCK1000;
import memorydb.USR1000;
import sta.sta1000.sta1000.service.impl.Sta1000DAO;
import usr.usr1000.usr1000.service.impl.Usr1000DAO;

/**
 * @Class StartWeb : StartWeb.java
 * @Description : 헬스프로그램을 시작하는 서버 클래스
 * 
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.21.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class StartWeb {

	/**
	 * Func : 헬스프로그램 서버 실행 및 데이터 초기화
	 * 
	 * @desc 1.서버 실행시 초기화 및 서블릿 실행
	 * 
	 * @param String[] args
	 * @return void
	 * @throws Exception
	 */
	public static void main(String[] args) {
		/*
		 * TODO 1. StartWeb에 설정파일 초기화 수행 2. Config.java 파일을 읽어서 BeanContainer에
		 * handler-Service-DAO 인스턴스 등록 3. DB 등록 4. DB와 DAO 연결
		 * 
		 * TODO DB가 실제 웹 컨테이너와 어떤 식으로 연결이 되는지 파악이 필요로 함.
		 * 콘솔(사용자) - startWeb(서버 구동) - 실제 Service(Controller-Service-DAO) 구성 - DB (java파일로 영역 분리)
		 * 
		 * TODO 이번 목적은 Java, MVC 구조 적용이다. -Spring 설정 순서 혹은 web service 올릴 때 올라가는 파일 순서는 고려하지 말 것
		 * 최소한으로 모든 것을 끝낸다.
		 */
		try {
			//db 등록
			USR1000 userDB = USR1000.getInstance();
			LCK1000 lockerDB = LCK1000.getInstance();
			
			//springContainer 등록
			SpringContainer springContainer = SpringContainer.getInstance();
			Map<String, Object> beanContainer = springContainer.beanContainer;

			//db 연결
			connectDB(userDB, lockerDB, beanContainer);
			
			//Welcome page 요청 생성
			Request request = new Request(MessageSource.getMessage("message.menu"));
			
			// weclome page 요청처리 수행
			DispatcherServlet dispatcherServlet = DispatcherServlet.getInstance();
			dispatcherServlet.service(request);
			
		} catch (Exception e) {
			//오류페이지 역할
			System.out.println("웹 서버 초기화 시, 문제가 발생했습니다. 다시 시도하십시오");
		}
	}

	
	private static void connectDB(USR1000 userDB, LCK1000 lockerDB, Map<String, Object> beanContainer)
			throws Exception {
		try {
			Usr1000DAO userDAO = (Usr1000DAO) beanContainer.get("usr1000DAO");
			Lck1000DAO lckDAO = (Lck1000DAO) beanContainer.get("lck1000DAO");
			Sta1000DAO staDAO = (Sta1000DAO) beanContainer.get("sta1000DAO");

			userDAO.setDBTable(userDB);
			lckDAO.setDBTable(lockerDB);
			staDAO.setDBTable(userDB);
		} catch (Exception e) {
			System.out.println("db 초기화 실패");
		}
	}
}
