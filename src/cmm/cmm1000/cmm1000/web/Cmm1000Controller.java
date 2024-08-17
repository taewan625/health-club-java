package cmm.cmm1000.cmm1000.web;

import com.web.ModelView;
import com.web.Request;
import com.web.Response;

/**
 * @Class Name : Cmm1000Controller.java
 * @Description : index page인 메뉴 화면을 반환해주는 핸들러
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.19.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1000Controller {
	// 싱글톤
	private static volatile Cmm1000Controller instance;

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 객체가 이미 존재하면 존재하는 객체를 반환하고 없으면 생성 후 반환. 최초 초기화만 동기화 작업이 일어나서 리소스 낭비를 최소화
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
	 * @desc : menu view Path 반환
	 * @param
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectCmm1000List(Request request, Response response) throws Exception {
		return new ModelView("cmm.cmm1000.cmm1000.Cmm1000");
	}
}
