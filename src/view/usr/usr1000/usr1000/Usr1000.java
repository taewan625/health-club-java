package view.usr.usr1000.usr1000;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.val.Validator;
import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1001;
import view.cmm.cmm1000.cmm1000.Cmm1004;

/**
 * @Class Name : Usr1000.java
 * @Description 회원관리 리스트를 보여주는 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func : 초기화 메서드가 존재하는 생성자
	 * 
	 * @desc :초기화 메서드의 예외를 처리하기 위해 try-catch와 new RuntimeExceptioin()을
	 *       던진다. @param @return @throws
	 */
	public Usr1000() {
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
		urlMap.put("1", msg.getProperty("usrS"));
		urlMap.put("2", msg.getProperty("usrC"));
		urlMap.put("3", msg.getProperty("usrUF"));
		urlMap.put("4", msg.getProperty("usrDF"));
		urlMap.put("5", msg.getProperty("menu"));
	}

	/**
	 * Func : view 실행 메서드
	 * 
	 * @desc model을 가지고 있는 메서드, 메뉴는 포함관계를 이용하여 보여준다.
	 * @param Request
	 *            requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request requestWithResponseData) throws Exception {
		while (true) {
			Cmm1004.failReason(requestWithResponseData);
			// 회원리스트를 보여준다.
			Map<String, ?> responseData = requestWithResponseData.getResponseData();
			List<Usr1000VO> data = (List<Usr1000VO>) responseData.get("users");
			showUsrList(data);
			// 메뉴선택 공통 팝업창
			String inputData = Cmm1001.choiceMenu("1.회원조회 2.회원 등록 3.회원 수정 4.회원 삭제 5.menu \n이동하실 메뉴의 번호를 입력하세요.", 1, 6);
			// 새로운 request에 값을 담는다.
			Request request = new Request(urlMatch(inputData));
			// 조회, 수정, 삭제 시, 아이디를 작성하세요
			List<String> userIds = getUserIds(data);
			writeId(inputData, request, userIds);
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
	private void writeId(String inputData, Request request, List<String> userIds) throws Exception {
		switch (inputData) {
		case "1":
			setId("조회할 회원 id를 작성해주세요", request, userIds);
			dispatcherServlet.service(request);
			break;
		case "2":
			dispatcherServlet.service(request);
			break;
		case "3":
			setId("수정하실 회원 id를 작성해주세요", request, userIds);
			dispatcherServlet.service(request);
			break;
		case "4":
			setId("삭제하실 회원 id를 작성해주세요", request, userIds);
			dispatcherServlet.service(request);
			break;
		case "5":
			dispatcherServlet.service(request);
		}
	}

	/**
	 * Func : id를 request 담는 메서드
	 * 
	 * @desc id를 request 담는 메서드로 코드 중복 방지
	 * @param String
	 *            prompt, Request request, List<String> userIds
	 * @return void
	 * @throws Exception
	 */
	private void setId(String prompt, Request request, List<String> userIds) throws Exception {
		try {
			System.out.println(prompt);
			String id = scanner.next();
			while (!Validator.isDuplicateId(id, userIds)) {
				System.out.println("존자하지 않는 id 입니다.");
				id = scanner.next();
			}
			request.setClientData("id", id);
		} catch (Exception e) {
			System.out.println("view/Usr1000/getUserIds() Error : " + e.getMessage());
		}
	}

	/**
	 * Func : 회원 id 모음 반환 메서드
	 * 
	 * @desc 회원 id 모음 반환
	 * @param List<Usr1000VO>
	 *            data
	 * @return List<String>
	 * @throws Exception
	 */
	private List<String> getUserIds(List<Usr1000VO> data) throws Exception {
		try {
			List<String> userIds = new ArrayList<>();
			for (Usr1000VO user : data) {
				userIds.add(user.getId());
			}
			return userIds;
		} catch (Exception e) {
			System.out.println("view/Usr1000/getUserIds() Error : " + e.getMessage());
			throw new Exception(e);
		}
	}

	/**
	 * Func :userList 를 보여주는 메서드
	 * 
	 * @desc id,name 정보를 보여주는 userList 메서드
	 * @param List<Usr1000VO>
	 *            users
	 * @return void
	 * @throws Exception
	 */
	private void showUsrList(List<Usr1000VO> users) throws Exception {
		int index = 1;
		for (Usr1000VO user : users) {
			System.out.println((index++) + " " + user.toStringUsrList());
		}
	}

}
