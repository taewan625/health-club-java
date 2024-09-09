package lck.lck1000.lck1000.web;

import java.util.List;
import java.util.Map;

import com.config.AppConfig;
import com.web.MessageSource;
import com.web.ModelView;
import com.web.Request;
import com.web.Response;

import lck.lck1000.lck1000.service.Lck1000Service;
import lck.lck1000.lck1000.vo.Lck1000VO;
import usr.usr1000.usr1000.service.Usr1000Service;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 사물함 관리 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000Controller {
	//생성자 주입
	private final Lck1000Service lck1000Service;
	
	//생성자 주입
	private final Usr1000Service usr1000Service;
	
	//생성자
	private Lck1000Controller(Lck1000Service lck1000Service, Usr1000Service usr1000Service) {
		this.lck1000Service = lck1000Service;
		this.usr1000Service = usr1000Service;
	}
	
	//싱글톤 내부 클래스
	private static class Lck1000ControllerHolder {
		private static final Lck1000Controller INSTANCE = new Lck1000Controller(AppConfig.lck1000Service(), AppConfig.usr1000Service());
	}
	
	//싱글톤
	public static Lck1000Controller getInstance() {
		return Lck1000ControllerHolder.INSTANCE;
	}
	
	//총 사물함 수 고정
	private final int TOTAL_CNT = 99;
	
	//사물함 목록 표출 시 총 페이지
	private final int TOTAL_PAGE = (int) Math.ceil((double) TOTAL_CNT / 10);
	
	//사물함 목록당 표출되는 항목 수
	private final String RANGE = "10";
	
	/**
	 * @desc 사물함 목록 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectLck1000View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//선택한 페이지
			String selectPage = String.valueOf(request.getClientDatas().get("selectPage"));
			
			//선택한 페이지가 없을 경우
			selectPage = ("null".equals(selectPage)) ? "1" : selectPage;
			
			//회원 목록 조회 결과를 담은 modelView 받기
			modelView = selectLck1000LockerList(selectPage);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.select"));
		}
		return modelView;
	}
	
	/**
	 * @desc 사물함 단건 조회 화면
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectLck1001View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//조회 유형 (1 사물함 번호, 2 회원 번호)
			String selectType = String.valueOf(request.getClientDatas().get("selectType"));
			
			//조회 데이터
			String selectValue = String.valueOf(request.getClientDatas().get("selectValue"));
			
			//사물함 조회
			Lck1000VO locker = lck1000Service.selectLck1000(selectType, selectValue);
			
			//사물함 조회 팝업 경로 등록
			modelView = new ModelView("lck.lck1000.lck1000.Lck1001");
			
			//사물함 정보 등록
			modelView.setDatas("locker", locker);
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.select"));
		}
		return modelView;
	}
	
	/**
	 * @desc 사물함 등록 팝업 조회
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView selectLck1002View(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView = new ModelView("lck.lck1000.lck1000.Lck1002");
		
		try {
			//조회 데이터
			String lockerNumber = String.valueOf(request.getClientDatas().get("lockerNumber"));
			
			//사물함 정보 조회
			Lck1000VO locker = lck1000Service.selectLck1000("1", lockerNumber);
			
			//사물함이 비어있는 경우
			if (locker.getUserId() == null) {
				//전체 회원 목록 조회
				List<Usr1000VO> users = usr1000Service.selectUsr1000List(Map.of("selectPage", "all"));
				
				//사물함 정보 세팅
				modelView.setDatas("locker", locker);
				
				//회원 목록 세팅
				modelView.setDatas("users", users);
			}
			
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
	 * @desc 사물함 등록 로직
	 * @param Request request, Response response
	 * @return ModelView
	 * @throws Exception
	 */
	public ModelView createLck1000LockerInfo(Request request, Response response) throws Exception {
		//반환 변수
		ModelView modelView;
		
		try {
			//등록할 회원 데이터 조회
			Lck1000VO lockerInfo = (Lck1000VO) request.getClientDatas().get("lockerInfo");
			
			//사물함 정보 등록
			boolean isCreated = lck1000Service.createLck1000LockerInfo(lockerInfo);
			
			//사물함 목록 조회 결과를 담은 modelView 받기
			modelView = selectLck1000LockerList("1");
			
			//사물함 등록 성공
			if (isCreated) {
				//성공 메시지 등록 
				modelView.setDatas("successMsg", MessageSource.getMessage("message.success.create"));
				
			}
			//사물함 등록 실패
			else {
				//실패 메시지 등록 
				modelView.setDatas("failMsg", MessageSource.getMessage("message.fail.create"));
			}
			
		} catch (Exception e) {
			//에러 페이지 이동
			modelView = new ModelView("cmm.cmm2000.cmm2000.Cmm2000");
			
			//에러 메시지 등록
			modelView.setDatas("errorMsg", MessageSource.getMessage("message.error.create"));
		}
		
		//modelView 반환
		return modelView;
		
	}
//
//	/**
//	 * Func : 사물함수정 화면 메서드
//	 * 
//	 * @desc 수정화면 반환시, userid도 같이 반환
//	 * @param
//	 * @return ModelView
//	 * @throws Exception
//	 */
//	public ModelView updateLck1000() throws Exception {
//		ModelView modelView;
//		try {
//			List<String> userIds = lck1000Service.selectUsr1000List();
//			modelView = new ModelView("lck.lck1000.lck1000.Lck1003");
//			modelView.setDatas("userIds", userIds);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
//			modelView.setClientDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
//		}
//		return modelView;
//	}
//
//	/**
//	 * Func : 사물함수정 메서드
//	 * 
//	 * @desc 사물함수정 메서드로 올바르지 않은 값이 들어오면 다시, 1003view로 보내고 아니면 1000view로 이동한다.
//	 * @param Map<String,
//	 *            Object> clientData
//	 * @return ModelView
//	 * @throws Exception
//	 */
//	public ModelView updateLck1000(Map<String, Object> clientData) throws Exception {
//		Lck1000VO locker = (Lck1000VO) clientData.get("clientData");
//		ModelView modelView;
//		try {
//			lck1000Service.updateLck1000(locker);
//			// 갱신된 유저 정보를 보여준다.
//			modelView = selectLck1000List();
//			modelView.setDatas("locker", locker);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
//			modelView.setClientDatas(ERROR_KEY, message.getProperty("ERROR.UPDATE"));
//		}
//		return modelView;
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
//	public ModelView deleteLck1000Form(Map<String, Object> clientData) throws Exception {
//		ModelView modelView;
//		try {
//			Lck1000VO locker = lck1000Service.selectLck1000(clientData);
//			modelView = new ModelView("lck.lck1000.lck1000.Lck1004");
//			modelView.setDatas("locker", locker);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
//			modelView.setClientDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
//		}
//		return modelView;
//	}
//
//	/**
//	 * Func : 사물함삭제 메서드
//	 * 
//	 * @desc 사물함 삭제 메서드로 올바르지 않은 값이 들어오면 다시, 1004view로 보내고 아니면 1000view로 이동한다.
//	 * @param Map<String,
//	 *            Object> clientData
//	 * @return ModelView
//	 * @throws Exception
//	 */
//	public ModelView deleteLck1000(Map<String, Object> clientData) throws Exception {
//		String lockerNum = String.valueOf(clientData.get("clientData"));
//		ModelView modelView;
//		try {
//			Lck1000VO locker = new Lck1000VO();
//			lck1000Service.deleteLck1000(Integer.parseInt(lockerNum), locker);
//			modelView = selectLck1000List();
//			modelView.setDatas("locker", locker);
//		} catch (Exception e) {
//			modelView = new ModelView(message.getProperty("LOCKER.PAGE"));
//			modelView.setClientDatas(ERROR_KEY, message.getProperty("ERROR.DELETE"));
//		}
//		return modelView;
//	}

	/**
	 * @desc 사물함 목록 조회 내부 메서드
	 * @param String selectPage
	 * @return ModelView
	 * @throws Exception
	 */
	private ModelView selectLck1000LockerList(String selectPage) throws Exception {
		//사물함 목록 조회
		List<Lck1000VO> lockers = lck1000Service.selectLck1000List(Map.of("selectPage", selectPage, "range", RANGE));
		
		//사물함 화면 경로 등록
		ModelView modelView = new ModelView("lck.lck1000.lck1000.Lck1000");
		
		//회원 목록 등록
		modelView.setDatas("lockers", lockers);
		
		//페이징 정보 등록
		modelView.setDatas("pageInfo", Map.of("selectPage", selectPage, "totalCnt", TOTAL_CNT, "totalPage", TOTAL_PAGE));
		
		return modelView;
	}

}
