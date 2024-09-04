package cmm.cmm1000.cmm1000.web;

import com.web.ModelView;
import com.web.Request;
import com.web.Response;

/**
 * @Description index page인 메뉴 화면을 반환해주는 핸들러
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.19.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1000Controller {
	// 싱글톤
	private static volatile Cmm1000Controller instance;
	
	//싱글톤 생성 메서드
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
	 * @desc menu view Path 반환
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectCmm1000View(Request request, Response response) throws Exception {
		return new ModelView("cmm.cmm1000.cmm1000.Cmm1000");
	}
}
