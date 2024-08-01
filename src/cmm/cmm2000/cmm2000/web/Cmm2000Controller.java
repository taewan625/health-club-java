package cmm.cmm2000.cmm2000.web;

import java.lang.reflect.Method;
import java.util.Properties;

import com.propertiesconvert.MessageSource;
import com.web.ModelView;
import com.web.ModelViewController;
import com.web.Request;

public class Cmm2000Controller implements ModelViewController {
	public static Properties message = MessageSource.properties;
	// 싱글톤
	private static volatile Cmm2000Controller instance;

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 객체가 이미 존재하면 존재하는 객체를 반환하고 없으면 생성 후 반환. 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를
	 *       최소화
	 * @param
	 * @return Cmm1000Controller
	 * @throws Exception
	 */
	public static Cmm2000Controller getInstance() throws Exception {
		if (instance == null) {
			// 메서드에 동기화 거는게 아닌, Singleton 클래스 자체를 동기화 걸어버림
			synchronized (Cmm2000Controller.class) {
				if (instance == null) {
					// 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를 최소화
					instance = new Cmm2000Controller();
				}
			}
		}
		return instance;
	}

	@Override
	public ModelView findMethod(Request request) throws Exception {
		try {
			// client 요청 데이터
			String methodName = request.getMethodUrl();
			Class<?> cmm2000Controller = Cmm2000Controller.class;
			// 메뉴 메서드 호출
			Method cmm2000ControllerMethod = cmm2000Controller.getMethod(methodName);
			// 메서드 실행
			Object result = cmm2000ControllerMethod.invoke(instance);

			return (ModelView) result;
		} catch (Exception e) {
			ModelView modelView = new ModelView(message.getProperty("ERROR.PAGE"));
			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.NOT_FOUND"));
			return modelView;
		}
	}

	public ModelView selectCmm2000() throws Exception {
		ModelView modelView = new ModelView(message.getProperty("ERROR.PAGE"));
		modelView.setDatas("error", message.getProperty("ERROR.NOT_FOUND"));
		return modelView;
	}
}
