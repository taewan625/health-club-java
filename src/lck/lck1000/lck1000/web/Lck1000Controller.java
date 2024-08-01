package lck.lck1000.lck1000.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;
import com.web.ModelView;
import com.web.ModelViewController;
import com.web.Request;

import lck.lck1000.lck1000.service.Lck1000Service;
import lck.lck1000.lck1000.vo.Lck1000VO;

/**
 * @Class Name : Lck1000Controller.java
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000Controller implements ModelViewController {
	public static Properties message = MessageSource.properties;
	private final Lck1000Service lck1000Service;

	// 생성자
	private Lck1000Controller(Lck1000Service lck1000Service) {
		this.lck1000Service = lck1000Service;
	}

	/**
	 * @Class Name : Lck1000ControllerHolder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.26.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Lck1000ControllerHolder {
		private static final Lck1000Controller INSTANCE = new Lck1000Controller(AppConfig.lck1000Service());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 싱글톤 객체 반환
	 * @param
	 * @return Lck1000Controller
	 * @throws Exception
	 */
	public static Lck1000Controller getInstance() throws Exception {
		return Lck1000ControllerHolder.INSTANCE;
	}

	/**
	 * Func : Reflection API를 활용한 동적으로 수행되는 메서드
	 * 
	 * @desc 메서드명을 이용하여 사용할 메서드를 호출
	 * @param Request
	 *            request
	 * @return ModelView
	 * @throws Exception
	 */
	@Override
	public ModelView findMethod(Request request) throws Exception {
		try {
			String methodName = request.getMethodUrl();
			// client 요청 데이터
			Map<String, Object> clientData = request.getClientData();
			// 핸들러 클래스 객체
			Class<?> lck1000Controller = Lck1000Controller.class;
			// 핸들러 객체
			Object controllerInstance = Lck1000ControllerHolder.INSTANCE;

			Object result;
			// 관리페이지, 등록
			if (clientData.isEmpty()) {
				Method method = lck1000Controller.getDeclaredMethod(methodName);
				result = method.invoke(controllerInstance);
			}
			// 조회, 수정, 삭제
			else {
				Method method = lck1000Controller.getMethod(methodName, Map.class);
				result = method.invoke(controllerInstance, clientData);
			}
			return (ModelView) result;
		} catch (Exception e) {
			// 예외 페이지로 이동
			ModelView modelView = new ModelView(message.getProperty("ERROR.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.NOT_FOUND"));
			return modelView;
		}
	}

	/**
	 * Func : 사물함관리 페이지를 보여주는 메서드
	 * 
	 * @desc : 사물함관리 페이지를 보여준다. 사물함 리스트도 보여준다.
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectLck1000List() throws Exception {
		ModelView modelView;
		try {
			List<Lck1000VO> lockers = lck1000Service.selectLck1000List();
			modelView = new ModelView("lck.lck1000.lck1000.Lck1000");
			modelView.setDatas("lockers", lockers);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.SELECT_LIST"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함조회 시, 보여줄 사물함 정보 반환 메서드
	 * 
	 * @desc 사물함 정보를 보여준다. 그리고 보여주기 전 만료 기간 확인 후 값을 반환한다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectLck1000(Map<String, Object> clientData) throws Exception {
		ModelView modelView;
		try {
			if (lck1000Service.isValidSelectKeyValue(clientData)) {
				Lck1000VO locker = lck1000Service.selectLck1000(clientData);

				modelView = new ModelView("lck.lck1000.lck1000.Lck1001");
				modelView.setDatas("locker", locker);
				return modelView;
			} else {
				modelView = new ModelView("lck.lck1000.lck1000.Lck1001");
				modelView.setDatas("errMsg", message.getProperty("FAIL.SELECT"));
			}
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.SELECT"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함등록 화면 메서드
	 * 
	 * @desc 등록화면 반환시, userid도 같이 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createLck1000() throws Exception {
		ModelView modelView;
		try {
			List<String> userIds = lck1000Service.selectUsr1000List();
			modelView = new ModelView("lck.lck1000.lck1000.Lck1002");
			modelView.setDatas("userIds", userIds);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.CREATE"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함등록 메서드
	 * 
	 * @desc 사물함 등록 메서드로 올바르지 않은 값이 들어오면 다시, 1002view로 보내고 아니면 1000view로 이동한다.
	 * @param Lck1000VO
	 *            lck1000VO
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createLck1000(Map<String, Object> clientData) throws Exception {
		Lck1000VO locker = (Lck1000VO) clientData.get("clientData");
		ModelView modelView;
		try {
			lck1000Service.createLck1000(locker);
			// 갱신된 유저 정보를 보여준다.
			modelView = selectLck1000List();
			modelView.setDatas("locker", locker);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.CREATE"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함수정 화면 메서드
	 * 
	 * @desc 수정화면 반환시, userid도 같이 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView updateLck1000() throws Exception {
		ModelView modelView;
		try {
			List<String> userIds = lck1000Service.selectUsr1000List();
			modelView = new ModelView("lck.lck1000.lck1000.Lck1003");
			modelView.setDatas("userIds", userIds);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함수정 메서드
	 * 
	 * @desc 사물함수정 메서드로 올바르지 않은 값이 들어오면 다시, 1003view로 보내고 아니면 1000view로 이동한다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView updateLck1000(Map<String, Object> clientData) throws Exception {
		Lck1000VO locker = (Lck1000VO) clientData.get("clientData");
		ModelView modelView;
		try {
			lck1000Service.updateLck1000(locker);
			// 갱신된 유저 정보를 보여준다.
			modelView = selectLck1000List();
			modelView.setDatas("locker", locker);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
		}
		return modelView;
	}

	/**
	 * Func : 회원 수정 화면 메서드
	 * 
	 * @desc 수정화면 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView deleteLck1000Form(Map<String, Object> clientData) throws Exception {
		ModelView modelView;
		try {
			Lck1000VO locker = lck1000Service.selectLck1000(clientData);
			modelView = new ModelView("lck.lck1000.lck1000.Lck1004");
			modelView.setDatas("locker", locker);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
		}
		return modelView;
	}

	/**
	 * Func : 사물함삭제 메서드
	 * 
	 * @desc 사물함 삭제 메서드로 올바르지 않은 값이 들어오면 다시, 1004view로 보내고 아니면 1000view로 이동한다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView deleteLck1000(Map<String, Object> clientData) throws Exception {
		String lockerNum = String.valueOf(clientData.get("clientData"));
		ModelView modelView;
		try {
			Lck1000VO locker = new Lck1000VO();
			lck1000Service.deleteLck1000(Integer.parseInt(lockerNum), locker);
			modelView = selectLck1000List();
			modelView.setDatas("locker", locker);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
		}
		return modelView;
	}

}
