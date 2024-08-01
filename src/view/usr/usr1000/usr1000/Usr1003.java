package view.usr.usr1000.usr1000;

import java.util.Map;
import java.util.Scanner;

import com.val.Validator;
import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1002;

/**
 * @Class Name : Usr1003.java
 * @Description 회원수정 팝업창
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1003 implements JavaHTML {

	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func : view 실행 메서드
	 * 
	 * @desc model이 없는 메서드
	 * @param Request
	 *            requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request usr1000Request) throws Exception {
		System.out.println("수정 회원 정보");
		Map<String, ?> responseData = usr1000Request.getResponseData();
		Usr1000VO user = (Usr1000VO) responseData.get("user");
		System.out.println(user);

		Usr1000VO userVO;
		while (true) {
			String id = user.getId();
			// 회원정보 수정시작
			System.out.println("회원 수정 시작");
			userVO = editUserForm(id);
			// 제출 여부 질문
			if (Cmm1002.askSubmit(msg.getProperty("AGAIN.FORM")).equals("y")) {
				continue;
			}
			break;
		}
		Cmm1002.submitPage("usrU", userVO);
	}

	/**
	 * Func : User 수정 메서드
	 * 
	 * @desc User 수정 값 등록
	 * @param Usr1000VO
	 *            user
	 * @return Usr1000VO
	 * @throws Exception
	 */
	private Usr1000VO editUserForm(String id) throws Exception {
		System.out.println("수정은 이름,연락처,주소,설명 4가지를 변경 가능. \n변경할 일이 없으면 엔터, \n수정 원치 않으며  n을 눌러주세요");
		System.out.println("이름 수정");
		String name = scanner.nextLine().trim();
		stop(name);
		while (!(Validator.isKorean(name) || Validator.isEnglish(name))) {
			System.out.println("이름은 영어 혹은 한글만 가능.");
			name = scanner.nextLine().trim();
		}

		String phoneNumber = getUserInput("연락처 수정 - 010-xxxx-xxxx 형식");
		// 빈문자열 일경우 넘어가고 아닌 경우 올바른 휴대번호 작성하는 팝업창을 통과해야한다.
		if (!phoneNumber.isEmpty()) {
			System.out.println(phoneNumber);
			stop(phoneNumber);
			Validator.isValidPhoneNumber(phoneNumber);
			System.out.println("010-xxxx-xxxx 형식으로 작성");
			phoneNumber = scanner.nextLine().trim();
		}

		String address = getUserInput("주소 수정");
		stop(address);

		String description = getUserInput("특이사항 등록");
		stop(description);
		return new Usr1000VO(id, name, phoneNumber, address, description);
	}

	/**
	 * Func : 중간에 멈추는 메서드
	 * 
	 * @desc 다시 회원 목록으로 돌아감
	 * @param String
	 *            id
	 * @return void
	 * @throws Exception
	 */
	private void stop(String id) throws Exception {
		try {
			if (id.equals("n") || id.equals("N")) {
				Request request = new Request(msg.getProperty("usr"));
				dispatcherServlet.service(request);
			}
		} catch (Exception e) {
			System.out.println("view/usr1002/stop() Error : " + e.getMessage());
			System.out.println("중간에 멈추는 걸 실패함");
		}
	}

	/**
	 * Func : editData Map에 값넣는 메서드
	 * 
	 * @desc 수정할 값 넣거나 넣을 값이 없으면 빈문자열 처리한다.
	 * @param String
	 *            prompt
	 * @return String
	 * @throws Exception
	 */
	private String getUserInput(String prompt) throws Exception {
		System.out.println(prompt);
		// 빈값을 받을 수 있는 nextLine()
		return scanner.nextLine().trim();
	}
}
