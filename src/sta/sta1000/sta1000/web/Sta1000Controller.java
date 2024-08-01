package sta.sta1000.sta1000.web;

import java.lang.reflect.Method;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;
import com.web.ModelView;
import com.web.ModelViewController;
import com.web.Request;

import sta.sta1000.sta1000.service.Sta1000Service;
import sta.sta1000.sta1000.vo.Sta1000VO;

/**
 * @Class Name : Sta1000Controller.java
 * @Description 회원 통계 controller
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000Controller implements ModelViewController {
	public static Properties message = MessageSource.properties;
	private final Sta1000Service sta1000service;

	// 생성자
	private Sta1000Controller(Sta1000Service sta1000Service) {
		this.sta1000service = sta1000Service;
	}

	/**
	 * @Class Name : Sta1000ControllerHolder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.28.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Sta1000ControllerHolder {
		private static final Sta1000Controller INSTANCE = new Sta1000Controller(AppConfig.sta1000service());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 싱글톤 객체 반환
	 * @param
	 * @return Sta1000Controller
	 * @throws Exception
	 */
	public static Sta1000Controller getIntanace() throws Exception {
		return Sta1000ControllerHolder.INSTANCE;
	}

	/**
	 * Func : Reflection API를 활용한 동적으로 수행되는 메서드
	 * 
	 * @desc : 메서드명을 이용하여 사용할 메서드를 호출
	 * @param List<String>
	 *            methodDatas
	 * @return ModelView
	 * @throws Exception
	 */
	@Override
	public ModelView findMethod(Request request) throws Exception {
		try {
			// client 요청 데이터
			String methodName = request.getMethodUrl();
			Class<?> sta1000Controller = Sta1000Controller.class;
			// 메뉴 메서드 호출
			Method sta1000ControllerMethod = sta1000Controller.getMethod(methodName);
			// 메서드 실행
			Object result = sta1000ControllerMethod.invoke(Sta1000ControllerHolder.INSTANCE);

			return (ModelView) result;
		} catch (Exception e) {
			System.out.println("sta/findMethod() Error : " + message.getProperty("ERROR.FIND_METHOD") + e.getMessage());
			ModelView modelView = new ModelView(message.getProperty("ERROR.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.NOT_FOUND"));
			return modelView;
		}
	}

	/**
	 * Func : menu view Path 반환 메서드
	 * 
	 * @desc : menu view Path 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */

	public ModelView selectSta1000() throws Exception {
		ModelView modelView;
		try {
			// 회원 수, 임박회원 수, 만료 회원수 - VO 내부 list로 받아오자
			Sta1000VO userStatistic = sta1000service.selectUsr1000List();
			modelView = new ModelView("sta.sta1000.sta1000.Sta1000");
			modelView.setDatas("userStatistic", userStatistic);
		} catch (Exception e) {
			// 회원통계 조회 중 예외가 발생
			modelView = new ModelView("sta.sta1000.sta1000.Sta1000");
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.SELECT_LIST"));
		}
		return modelView;
	}

}
