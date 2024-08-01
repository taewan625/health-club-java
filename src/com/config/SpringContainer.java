package com.config;

import java.util.HashMap;
import java.util.Map;

import cmm.cmm1000.cmm1000.web.Cmm1000Controller;
import cmm.cmm2000.cmm2000.web.Cmm2000Controller;
import lck.lck1000.lck1000.service.impl.Lck1000DAO;
import lck.lck1000.lck1000.service.impl.Lck1000ServiceImpl;
import lck.lck1000.lck1000.web.Lck1000Controller;
import sta.sta1000.sta1000.service.impl.Sta1000DAO;
import sta.sta1000.sta1000.service.impl.Sta1000ServiceImpl;
import sta.sta1000.sta1000.web.Sta1000Controller;
import usr.usr1000.usr1000.service.impl.Usr1000DAO;
import usr.usr1000.usr1000.service.impl.Usr1000ServiceImpl;
import usr.usr1000.usr1000.web.Usr1000Controller;

/**
 * @Class Name : SpringContainer.java
 * @Description 스프링 컨테이너긴 하지만 거의 bean container에 가깝다.
 * 
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class SpringContainer {
	// controller - service - dao 를 beanContainer에 담는다.
	public final Map<String, Object> beanContainer = new HashMap<>();

	/**
	 * Func 초기화 메서드를 담는 생성자
	 * 
	 * @desc 싱글톤 생성시, Map 초기화 예외 발생 경우 초기화 문제를 잡기 위해 try-catch 수행 main method에서 객체를
	 *       초기화 할 경우 try-catch로 잡는 것과 같이 생성자 내부에서 초기화 수행시, try-catch로 잡는 형식으로
	 *       생성 @param @return
	 * @throw RuntimeException
	 */
	private SpringContainer() {
		try {
			initBeanContainer();
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	/**
	 * @Class Name BeanContainerHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.26.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class BeanContainerHolder {
		private static final SpringContainer INSTANCE = new SpringContainer();
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return BeanContainer
	 * @throws Exception
	 */
	public static SpringContainer getInstance() throws Exception {
		return SpringContainer.BeanContainerHolder.INSTANCE;
	}

	/**
	 * Func : 초기화 메서드
	 * 
	 * @desc 초기화 메서드, 현재 사용하는 값은 핸들러 밖에 없기 때문에 핸들러만 map에 넣는다.
	 * @param
	 * @return void
	 * @throws Exception
	 */
	public void initBeanContainer() throws Exception {
		// 컨트롤러 등록
		beanContainer.put("cmm1000Controller", Cmm1000Controller.getInstance());
		beanContainer.put("usr1000Controller", Usr1000Controller.getInstance());
		beanContainer.put("lck1000Controller", Lck1000Controller.getInstance());
		beanContainer.put("sta1000Controller", Sta1000Controller.getIntanace());
		beanContainer.put("cmm2000Controller", Cmm2000Controller.getInstance());
		// service 등록
		beanContainer.put("usr1000Service", Usr1000ServiceImpl.getInstance());
		beanContainer.put("lck1000Service", Lck1000ServiceImpl.getInstance());
		beanContainer.put("sta1000Service", Sta1000ServiceImpl.getInstance());
		// DAO 등록
		beanContainer.put("usr1000DAO", Usr1000DAO.getInstance());
		beanContainer.put("lck1000DAO", Lck1000DAO.getInstance());
		beanContainer.put("sta1000DAO", Sta1000DAO.getInstance());
	}
}
