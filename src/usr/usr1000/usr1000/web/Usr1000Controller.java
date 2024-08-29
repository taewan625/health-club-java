package usr.usr1000.usr1000.web;

import java.util.List;
import java.util.Map;

import com.config.AppConfig;
import com.web.MessageSource;
import com.web.ModelView;
import com.web.Request;
import com.web.Response;

import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000Controller {
	//생성자 주입
	private final Usr1000Service usr1000Service;

	//생성자
	private Usr1000Controller(Usr1000Service usr1000Service) {
		this.usr1000Service = usr1000Service;
	}

	//싱글톤 내부 클래스
	private static class Usr1000ControllerHolder {
		private static final Usr1000Controller INSTANCE = new Usr1000Controller(AppConfig.usr1000Service());
	}

	//싱글톤
	public static Usr1000Controller getInstance() throws Exception {
		return Usr1000ControllerHolder.INSTANCE;
	}

	/**
	 * @desc 회원관리 페이지 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1000List(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//회원 목록 조회
			List<Usr1000VO> users = usr1000Service.selectUsr1000List();
			
			//회원 화면 경로 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1000");
			
			//회원 목록 등록
			modelView.setDatas("users", users);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.selectList"));
		}
		
		//modelView 반환
		return modelView;
	}

	/**
	 * @desc 회원조회 팝업 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1000(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//userId 조회
			String userId = String.valueOf(request.getClientDatas().get("userId"));
			
			//회원 조회
			Usr1000VO user = usr1000Service.selectUsr1000(userId);
			
			//회원 조회 팝업 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1001");
			
			//회원 정보 등록
			modelView.setDatas("user", user);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.select"));
		}
		
		//modelView 반환
		return modelView;
	}

	/**
	 * @desc 회원 등록 팝업 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createUsr1000(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//회원 목록 조회
			List<Usr1000VO> users = usr1000Service.selectUsr1000List();
			
			//회원 등록 경로 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1002");
			
			//회원 목록 등록
			modelView.setDatas("users", users);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.notFound"));
		}
		
		//modelView 반환
		return modelView;
	}

	/**
	 * @desc 회원 등록 후, 회원 메뉴로 이동
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createUsr1000UserInfo(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//등록할 회원 데이터 조회
			Usr1000VO userInfo = (Usr1000VO) request.getClientDatas().get("userInfo");
			
			//회원 정보 등록
			usr1000Service.createUsr1000(userInfo);
			
			//회원 목록 조회
			List<Usr1000VO> users = usr1000Service.selectUsr1000List();
			
			//회원 화면 경로 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1000");
			
			//회원 목록 등록
			modelView.setDatas("users", users);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.notFound"));
		}
		
		//modelView 반환
		return modelView;
	}
//
//	/**
//	 * Func : 회원 수정 화면 메서드
//	 * 
//	 * @desc 수정화면 반환
//	 * @param
//	 * @return ModelView
//	 * @throws Exception
//	 */
//	public ModelView updateUsr1000Form(Map<String, Object> clientData) throws Exception {
//		ModelView modelView;
//		String id = (String) clientData.get("id");
//		try {
//			Usr1000VO user = usr1000Service.selectUsr1000(id);
//			modelView = new ModelView("usr.usr1000.usr1000.Usr1003");
//			modelView.setDatas("user", user);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("USER.PAGE"));
//			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
//		}
//		return modelView;
//	}
//
//	/**
//	 * Func : 회원수정 완료 후, 회원 관리페이지를 보여주는 메서드
//	 * 
//	 * @desc : 회원수정 메서드로 올바르지 않은 값이 들어오면 다시, 1003view로 보내고 아니면 1000view로 이동한다.
//	 * @param Map<String,
//	 *            Object> clientData
//	 * @return ModelView<?>
//	 * @throws Exception
//	 */
//	public ModelView updateUsr1000(Map<String, Object> clientData) throws Exception {
//		Usr1000VO user = (Usr1000VO) clientData.get("clientData");
//		ModelView modelView;
//		try {
//			usr1000Service.updateUsr1000(user.getId(), user);
//			// 갱신된 유저 정보를 보여준다.
//			modelView = selectUsr1000List();
//			modelView.setDatas("user", user);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("USER.PAGE"));
//			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
//		}
//		return modelView;
//
//	}
//
//	/**
//	 * Func : 회원 수정 화면 메서드
//	 * 
//	 * @desc 수정화면 반환
//	 * @param
//	 * @return ModelView
//	 * @throws Exception
//	 */
//	public ModelView deleteUsr1000Form(Map<String, Object> clientData) throws Exception {
//		ModelView modelView;
//		String id = (String) clientData.get("id");
//		try {
//			Usr1000VO user = usr1000Service.selectUsr1000(id);
//			modelView = new ModelView("usr.usr1000.usr1000.Usr1004");
//			modelView.setDatas("user", user);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("USER.PAGE"));
//			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
//		}
//		return modelView;
//	}
//
//	/**
//	 * Func : 회원삭제 페이지를 보여주는 메서드
//	 * 
//	 * @desc 회원삭제 페이지를 보여준다. 삭제는 삭제여부, 사용여부만 업데이트 된다.
//	 * @param Map<String,
//	 *            Object> clientData
//	 * @return ModelView<Map<String, String>>
//	 * @throws Exception
//	 */
//	public ModelView deleteUsr1000(Map<String, Object> clientData) throws Exception {
//		String id = String.valueOf(clientData.get("clientData"));
//		ModelView modelView;
//		try {
//			Usr1000VO user = new Usr1000VO();
//			usr1000Service.deleteUsr1000(id, user);
//			// 갱신된 유저 정보를 보여준다.
//			modelView = selectUsr1000List();
//			modelView.setDatas("user", user);
//
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("USER.PAGE"));
//			modelView.setDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
//		}
//		return modelView;
//	}

}
