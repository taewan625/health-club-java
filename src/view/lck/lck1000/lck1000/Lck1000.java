package view.lck.lck1000.lck1000;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1001;
import view.cmm.cmm1000.cmm1000.Cmm1004;

/**
 * @Class Name : Lck1000.java
 * @Description 사물함관리 리스트를 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func : 초기화 메서드가 존재하는 생성자
	 * 
	 * @desc :초기화 메서드의 예외를 처리하기 위해 try-catch와 new RuntimeExceptioin()을
	 *       던진다. @param @return @throws
	 */
	public Lck1000() {
		try {
			initUrl();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Func : url 초기화 메서드
	 * 
	 * @desc JavaHTML interface에서 Map<String, String>을 담았기 때문에 이동할 url 경로만 초기화 해주면
	 *       된다. 해당 value 사용시, urlMacth로 선언된 default 메서드를 사용하면된다.
	 * @param
	 * @return void
	 * @throws Exception
	 */
	private void initUrl() throws Exception {
		urlMap.put("1", msg.getProperty("lckS"));
		urlMap.put("2", msg.getProperty("lckC"));
		urlMap.put("3", msg.getProperty("lckU"));
		urlMap.put("4", msg.getProperty("lckDF"));
		urlMap.put("5", msg.getProperty("menu"));
	}

	@Override
	public void response(Request requestWithResponseData) throws Exception {
		while (true) {
			Cmm1004.failReason(requestWithResponseData);
			// 사물함 리스트 보여주기
			Map<String, ?> responseData = requestWithResponseData.getResponseData();
			Object data = responseData.get("lockers");
			showLckList((List<Lck1000VO>) data);
			// 메뉴선택 공통 팝업창
			String inputData = Cmm1001.choiceMenu("1.사물함 조회 2.사물함 등록 3.사물함 수정 4.사물함 삭제 5.menu \n이동하실 메뉴의 번호를 입력하세요.", 1,
					6);
			// 새로운 request에 값을 담는다.
			Request request = new Request(urlMatch(inputData));
			writeId(inputData, request);
		}
	}

	/**
	 * Func : 조회, 수정, 삭제 시, id를 물어봐서 request에 담는 메서드
	 * 
	 * @desc 조회, 수정, 삭제 시, id를 물어봐서 request에 담는다.
	 * @param String
	 *            inputData, Request request
	 * @return void
	 * @throws Exception
	 */
	private void writeId(String inputData, Request request) throws Exception {
		switch (inputData) {
		case "1":
			String choiceMenu = Cmm1001.choiceMenu("조회 방법: 1.회원번호 2.사물함번호", 1, 3);
			selectByMenu(choiceMenu, request);
			dispatcherServlet.service(request);
			break;
		case "2":
			dispatcherServlet.service(request);
			break;
		case "3":
			dispatcherServlet.service(request);
			break;
		case "4":
			setLockerNum("삭제할 사물함 번호를 작성하세요", request);
			dispatcherServlet.service(request);
			break;
		case "5":
			dispatcherServlet.service(request);
		}
	}

	/**
	 * Func : 사물함 조회시, 회원 혹은 사물함 번호로 조회하는 메서드
	 * 
	 * @desc locker 조회 선택 창
	 * @param String
	 *            choiceMenu, Request request
	 * @return void
	 * @throws Exception
	 */
	private void selectByMenu(String choiceMenu, Request request) throws Exception {
		if (choiceMenu.equals("1")) {
			System.out.println("회원번호를 작성하세요");
			String id = scanner.next();
			request.setClientData("id", id);
		} else {
			setLockerNum("사물함 번호를 작성하세요", request);
		}
	}

	/**
	 * Func : 사물함 번호를 요청값에 담는 메서드
	 * 
	 * @desc 사물함 번호를 요청에 담는다.
	 * @param String
	 *            prompt, Request request
	 * @return void
	 * @throws Exception
	 */
	private void setLockerNum(String prompt, Request request) throws Exception {
		String lockerNum = Cmm1001.choiceMenu(prompt, 1, 100);
		request.setClientData("lockerNum", lockerNum);

	}

	/**
	 * Func : lockerList 를 보여주는 메서드
	 * 
	 * @desc locker 정보를 보여주는 메서드
	 * @param List<Lck1000VO>
	 *            data
	 * @return void
	 * @throws Exception
	 */
	private void showLckList(List<Lck1000VO> data) throws Exception {
		for (int i = 0; i < 11; i++) {
			System.out.println("순번 = " + (i + 1) + ", 사물함 정보 " + data.get(i).toString());
		}
		System.out.println("...");
	}

}
