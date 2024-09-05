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
	public static Usr1000Controller getInstance() {
		return Usr1000ControllerHolder.INSTANCE;
	}
	
	/**
	 * @desc 회원관리 페이지 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1000View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//선택한 페이지
			String selectPage = String.valueOf(request.getClientDatas().get("selectPage"));
			
			//선택한 페이지가 없을 경우
			selectPage = ("null".equals(selectPage)) ? "1" : selectPage;
			
			//회원 목록 조회 결과를 담은 modelView 받기
			modelView = selectUsr1000UserList(selectPage);
			
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
	 * @desc 회원조회 팝업 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1001View(Request request, Response response) throws Exception {
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
	public ModelView selectUsr1002View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//전체 회원 목록 조회
			List<Usr1000VO> users = usr1000Service.selectUsr1000List(Map.of("selectPage", "all"));
			
			//회원 등록 경로 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1002");
			
			//회원 목록 등록
			modelView.setDatas("users", users);
			
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
			usr1000Service.createUsr1000UserInfo(userInfo);
			
			//회원 목록 조회 결과를 담은 modelView 받기
			modelView = selectUsr1000UserList("1");
			
			//성공 메시지 등록 
			modelView.setDatas("successMsg", MessageSource.getMessage("message.success.create"));
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.create"));
		}
		
		//modelView 반환
		return modelView;
	}
	
	/**
	 * @desc 회원 수정화면 반환
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectUsr1003View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//userId 조회
			String userId = String.valueOf(request.getClientDatas().get("userId"));
			
			//회원 조회
			Usr1000VO user = usr1000Service.selectUsr1000(userId);
			
			//회원 조회 팝업 등록
			modelView = new ModelView("usr.usr1000.usr1000.Usr1003");
			
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
	 * @desc 회원 수정 로직
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView updateUsr1000UserInfo(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//수정할 회원 데이터 조회
			Usr1000VO userInfo = (Usr1000VO) request.getClientDatas().get("userInfo");
			
			//회원 정보 수정
			usr1000Service.updateUsr1000UserInfo(userInfo);
			
			//회원 목록 조회 결과를 담은 modelView 받기
			modelView = selectUsr1000UserList("1");
			
			//성공 메시지 등록 
			modelView.setDatas("successMsg", MessageSource.getMessage("message.success.update"));
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.update"));
		}
		
		//modelView 반환
		return modelView;
	}
	
	/**
	 * @desc 회원 삭제 로직 - 회원 삭제 여부 및 사용여부 상태 값만 업데이트
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView deleteUsr1000UserInfo(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//userId 조회
			String userId = String.valueOf(request.getClientDatas().get("userId"));
			
			//회원 정보 삭제
			Usr1000VO deleteResult = usr1000Service.deleteUsr1000UserInfo(userId);
			
			//회원 목록 조회 결과를 담은 modelView 받기
			modelView = selectUsr1000UserList("1");
			
			//회원 정보가 없어서 삭제하지 않은 경우 메시지 
			String messgae = (deleteResult == null) ? MessageSource.getMessage("message.fail.delete") : MessageSource.getMessage("message.success.delete"); 
			
			//성공 메시지 등록 : fail도 정상 로직을 통해 나온 결과여서 success 범주에 둔다.
			modelView.setDatas("successMsg", messgae);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.delete"));
		}
		
		//modelView 반환
		return modelView;
	}
	
	/**
	 * @desc 회원 목록 조회 내부 메서드
	 * @param String selectPage
	 * @return ModelView
	 * @throws Exception
	 */
	private ModelView selectUsr1000UserList(String selectPage) throws Exception {
		//회원 목록 조회
		List<Usr1000VO> users = usr1000Service.selectUsr1000List(Map.of("selectPage", selectPage, "range", "10"));
		
		//총 회원 목록 수
		int totalCnt = usr1000Service.selectUsr1000ListCnt();
		
		//회원 화면 경로 등록
		ModelView modelView = new ModelView("usr.usr1000.usr1000.Usr1000");
		
		//회원 목록 등록
		modelView.setDatas("users", users);
		
		//페이징 정보 등록
		modelView.setDatas("pageInfo", Map.of("selectPage", selectPage, "totalCnt", totalCnt, "totalPage", (int) Math.ceil((double) totalCnt / 10)));
		
		return modelView;
	}
}
