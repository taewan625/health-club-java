package sta.sta1000.sta1000.web;

import java.util.Map;

import com.config.AppConfig;
import com.web.MessageSource;
import com.web.ModelView;
import com.web.Request;
import com.web.Response;

import sta.sta1000.sta1000.service.Sta1000Service;

/**
 * @Description 회원 통계 controller
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000Controller {
	//생성자 주입
	private final Sta1000Service sta1000service;
	
	// 생성자
	private Sta1000Controller(Sta1000Service sta1000Service) {
		this.sta1000service = sta1000Service;
	}
	
	//싱글톤 내부 클래스
	private static class Sta1000ControllerHolder {
		private static final Sta1000Controller INSTANCE = new Sta1000Controller(AppConfig.sta1000service());
	}
	
	//싱글톤
	public static Sta1000Controller getInstance() {
		return Sta1000ControllerHolder.INSTANCE;
	}

	/**
	 * @desc 회원 통계 정보 반환
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectSta1000View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//통계 화면 경로 등록
			modelView = new ModelView("sta.sta1000.sta1000.Sta1000");
			
			//통계 정보 조회 - 회원 통계 정보
			Map<String, Integer> userStatistics = sta1000service.selectSta1000UserInfo();
			
			//통계 정보 등록 - 회원 통계 정보
			modelView.setDatas("userStatistics", userStatistics);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.select"));
		}
		
		//modelView 반환
		return modelView;
	}
}
