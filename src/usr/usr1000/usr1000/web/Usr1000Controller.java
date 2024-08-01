package usr.usr1000.usr1000.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.config.AppConfig;
import com.propertiesconvert.MessageSource;
import com.web.ModelView;
import com.web.ModelViewController;
import com.web.Request;

import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Usr1000Controller.java
 * @Description 회원 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000Controller implements ModelViewController {
	public static Properties message = MessageSource.properties;
	private final Usr1000Service usr1000Service;

	// 생성자
	private Usr1000Controller(Usr1000Service usr1000Service) {
		this.usr1000Service = usr1000Service;
	}

	/**
	 * @Class Name : Usr1000ControllerHolder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.22.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Usr1000ControllerHolder {
		private static final Usr1000Controller INSTANCE = new Usr1000Controller(AppConfig.usr1000Service());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 싱글톤 객체 반환
	 * @param
	 * @return Usr1000Controller
	 * @throws Exception
	 */
	public static Usr1000Controller getInstance() throws Exception {
		return Usr1000ControllerHolder.INSTANCE;
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
			Class<?> usr1000Controller = Usr1000Controller.class;
			
			/** 생성자 생성 */
			// 핸들러 매핑의 역할
			Class<?> ClassInstance = Class.forName("usr.usr1000.usr1000.web.Usr1000Controller");
			// 생성자 메서드 가지고 오기
			Method getMethod = ClassInstance.getMethod("getInstance");
			// 실제 생성자 내부 객체 가지고 오기, 매개변수가 필요 없는 경우 null
			Object instance = getMethod.invoke(null);
			
			
			// 객체의 클래스를 가지고 오고 메서드 추출
			// getInstanceMethod  = instance.getClass().getMethod(methodName);
			getMethod  = ClassInstance.getMethod(methodName);
			
			Object result1 = getMethod.invoke(instance);
			
			/** */
			
			
			
			// 핸들러 객체
			Object controllerInstance = Usr1000ControllerHolder.INSTANCE;

			Object result;
			// 관리페이지, 등록
			if (clientData.isEmpty()) {
				Method method = usr1000Controller.getMethod(methodName);
				result = method.invoke(controllerInstance);
			}
			// 조회, 수정, 삭제
			else {
				Method method = usr1000Controller.getMethod(methodName, Map.class);
				result = method.invoke(controllerInstance, clientData);
			}
			return (ModelView) result1;
		} catch (Exception e) {
			// 예외 페이지로 이동
			ModelView modelView = new ModelView(message.getProperty("ERROR.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.NOT_FOUND"));
			return modelView;
		}
	}

	/**
	 * Func : 회원관리 페이지를 보여주는 메서드
	 * 
	 * @desc : 회원관리 페이지를 보여준다. 기본 회원 리스트도 보여준다.
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1000List() throws Exception {
		ModelView modelView;
		try {
			List<Usr1000VO> users = usr1000Service.selectUsr1000List();
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas("users", users);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.SELECT_LIST"));
		}
		return modelView;
	}

	/**
	 * Func : 회원조회 페이지를 보여주는 메서드
	 * 
	 * @desc : 회원조회 페이지를 보여준다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1000(Map<String, Object> clientData) throws Exception {
		ModelView modelView;
		try {
			String userId = String.valueOf(clientData.get("id"));
			// 회원 id 존재 여부 확인
			Usr1000VO user = usr1000Service.selectUsr1000(userId);
			// 회원 조회에서 조회한 경우
			modelView = new ModelView("usr.usr1000.usr1000.Usr1001");
			modelView.setDatas("user", user);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.SELECT"));
		}
		return modelView;
	}

	/**
	 * Func : 회원 등록 화면 메서드
	 * 
	 * @desc 등록 화면 반환시, userid도 같이 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createUsr1000() throws Exception {
		ModelView modelView;
		try {
			List<String> userIds = usr1000Service.selectUsrId1000List();
			modelView = new ModelView("usr.usr1000.usr1000.Usr1002");
			modelView.setDatas("userIds", userIds);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.CREATE"));
		}
		return modelView;
	}

	/**
	 * Func : 회원등록 페이지를 보여주는 메서드
	 * 
	 * @desc 회원등록 성공 시 회원 페이지 실패시 다시 등록 페이지.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView<?>
	 * @throws Exception
	 */
	public ModelView createUsr1000(Map<String, Object> clientData) throws Exception {
		Usr1000VO user = (Usr1000VO) clientData.get("clientData"); // "clientData"라는 키는 존재하는데 값이 안잡힘
		ModelView modelView;
		try {
			usr1000Service.createUsr1000(user);
			// 갱신된 유저 정보를 보여준다.
			modelView = selectUsr1000List();
			modelView.setDatas("user", user);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.CREATE"));
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
	public ModelView updateUsr1000Form(Map<String, Object> clientData) throws Exception {
		ModelView modelView;
		String id = (String) clientData.get("id");
		try {
			Usr1000VO user = usr1000Service.selectUsr1000(id);
			modelView = new ModelView("usr.usr1000.usr1000.Usr1003");
			modelView.setDatas("user", user);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
		}
		return modelView;
	}

	/**
	 * Func : 회원수정 완료 후, 회원 관리페이지를 보여주는 메서드
	 * 
	 * @desc : 회원수정 메서드로 올바르지 않은 값이 들어오면 다시, 1003view로 보내고 아니면 1000view로 이동한다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView<?>
	 * @throws Exception
	 */
	public ModelView updateUsr1000(Map<String, Object> clientData) throws Exception {
		Usr1000VO user = (Usr1000VO) clientData.get("clientData");
		ModelView modelView;
		try {
			usr1000Service.updateUsr1000(user.getId(), user);
			// 갱신된 유저 정보를 보여준다.
			modelView = selectUsr1000List();
			modelView.setDatas("user", user);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
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
	public ModelView deleteUsr1000Form(Map<String, Object> clientData) throws Exception {
		ModelView modelView;
		String id = (String) clientData.get("id");
		try {
			Usr1000VO user = usr1000Service.selectUsr1000(id);
			modelView = new ModelView("usr.usr1000.usr1000.Usr1004");
			modelView.setDatas("user", user);
		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
		}
		return modelView;
	}

	/**
	 * Func : 회원삭제 페이지를 보여주는 메서드
	 * 
	 * @desc 회원삭제 페이지를 보여준다. 삭제는 삭제여부, 사용여부만 업데이트 된다.
	 * @param Map<String,
	 *            Object> clientData
	 * @return ModelView<Map<String, String>>
	 * @throws Exception
	 */
	public ModelView deleteUsr1000(Map<String, Object> clientData) throws Exception {
		String id = String.valueOf(clientData.get("clientData"));
		ModelView modelView;
		try {
			Usr1000VO user = new Usr1000VO();
			usr1000Service.deleteUsr1000(id, user);
			// 갱신된 유저 정보를 보여준다.
			modelView = selectUsr1000List();
			modelView.setDatas("user", user);

		} catch (Exception e) {
			modelView = new ModelView(message.getProperty("USER.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
		}
		return modelView;
	}

}
