package com.config;

import cmm.cmm1000.cmm1000.web.Cmm1000Controller;
import cmm.cmm2000.cmm2000.web.Cmm2000Controller;
import lck.lck1000.lck1000.service.Lck1000Service;
import lck.lck1000.lck1000.service.impl.Lck1000DAO;
import lck.lck1000.lck1000.service.impl.Lck1000ServiceImpl;
import lck.lck1000.lck1000.web.Lck1000Controller;
import sta.sta1000.sta1000.service.Sta1000Service;
import sta.sta1000.sta1000.service.impl.Sta1000DAO;
import sta.sta1000.sta1000.service.impl.Sta1000ServiceImpl;
import sta.sta1000.sta1000.web.Sta1000Controller;
import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.service.impl.Usr1000DAO;
import usr.usr1000.usr1000.service.impl.Usr1000ServiceImpl;
import usr.usr1000.usr1000.web.Usr1000Controller;

/**
 * @Class Name : AppConfig.java
 * @Description Controller, Service, DAO 의존 관계 설정 파일
 * 
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class AppConfig {
	// 예외 의존관계 설정 메서드
	public static Cmm2000Controller cmm2000Controller() throws Exception {
		return Cmm2000Controller.getInstance();
	}

	// 메뉴 의존관계 설정 메서드
	public static Cmm1000Controller cmm1000Controller() throws Exception {
		return Cmm1000Controller.getInstance();
	}

	// 회원 의존관계 설정 메서드들
	public static Usr1000Controller usr1000Controller() throws Exception {
		return Usr1000Controller.getInstance();
	}

	public static Usr1000Service usr1000Service() {
		try {
			return Usr1000ServiceImpl.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	public static Usr1000DAO usr1000DAO() {
		try {
			return Usr1000DAO.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	// 사물함 의존관계 메서드들
	public static Lck1000Controller lck1000Controller() throws Exception {
		return Lck1000Controller.getInstance();
	}

	public static Lck1000Service lck1000Service() {
		try {
			return Lck1000ServiceImpl.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	public static Lck1000DAO lck1000DAO() {
		try {
			return Lck1000DAO.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	// 회원 통계 메서드들
	public static Sta1000Controller sta1000Controller() throws Exception {
		return Sta1000Controller.getIntanace();
	}

	public static Sta1000Service sta1000service() {
		try {
			return Sta1000ServiceImpl.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

	public static Sta1000DAO sta1000DAO() {
		try {
			return Sta1000DAO.getInstance();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
	}

}
