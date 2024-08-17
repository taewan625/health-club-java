package cmm.cmm2000.cmm2000.web;

import com.web.MessageSource;
import com.web.ModelView;
import com.web.Request;
import com.web.Response;

public class Cmm2000Controller {
	//싱글톤
	private static volatile Cmm2000Controller instance;
	
	//싱글톤 조회 메서드
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

	/**
	 * @desc 예외 페이지 조회
	 * @param  Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectCmm2000(Request request, Response response) throws Exception {
		//modelView에 view 경로 주입
		ModelView modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
		
		//예외 메시지 주입
		modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.notFound"));
		
		//반환
		return modelView;
	}
}
