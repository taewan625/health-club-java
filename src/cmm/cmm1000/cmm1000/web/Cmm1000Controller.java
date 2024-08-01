package cmm.cmm1000.cmm1000.web;

import java.lang.reflect.Method;
import java.util.Properties;

import com.propertiesconvert.MessageSource;
import com.web.ModelView;
import com.web.ModelViewController;
import com.web.Request;

/**
 * @Class Name : Cmm1000Controller.java
 * @Description : index page인 메뉴 화면을 반환해주는 핸들러
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.19.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1000Controller implements ModelViewController {
	// 메시지 프로퍼티스
	public static Properties message = MessageSource.properties;
	// 싱글톤
	private static volatile Cmm1000Controller instance;

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 객체가 이미 존재하면 존재하는 객체를 반환하고 없으면 생성 후 반환. 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를
	 *       최소화
	 * @param
	 * @return Cmm1000Controller
	 * @throws Exception
	 */
	public static Cmm1000Controller getInstance() throws Exception {
		if (instance == null) {
			// 메서드에 동기화 거는게 아닌, Singleton 클래스 자체를 동기화 걸어버림
			synchronized (Cmm1000Controller.class) {
				if (instance == null) {
					// 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를 최소화
					instance = new Cmm1000Controller();
				}
			}
		}
		return instance;
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
			Class<?> cmm1000Controller = Cmm1000Controller.class;
			// 메뉴 메서드 호출
			Method cmm1000ControllerMethod = cmm1000Controller.getMethod(methodName);
			// 메서드 실행
			Object result = cmm1000ControllerMethod.invoke(instance);

			return (ModelView) result;
		} catch (Exception e) {
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
	public ModelView selectCmm1000List() throws Exception {
		return new ModelView("cmm.cmm1000.cmm1000.Cmm1000");
	}
}
