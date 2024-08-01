package view.usr.usr1000.usr1000;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.val.Validator;
import com.web.Request;

import memorydb.domain.Gender;
import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1002;

/**
 * @Class Name : Usr1002.java
 * @Description 회원등록 팝업창
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1002 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func 회원등록 메서드
	 * 
	 * @desc :초기화 메서드의 예외를 처리하기 위해 try-catch와 new RuntimeExceptioin()을 던진다.
	 * @param Request
	 *            usr1000Request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request usr1000Request) throws Exception {
		Map<String, ?> responseData = usr1000Request.getResponseData();
		List<String> userIds = (List<String>) responseData.get("userIds");

		System.out.println("회원 등록 시작");
		Usr1000VO userVO;

		while (true) {
			System.out.println("등록하고 싶지 않으시면 n을 작성하시면 됩니다.");
			System.out.println("id기입  (중복불가)");
			String id = scanner.nextLine().trim();
			// 중간에 멈추는 메서드
			stop(id);
			// id 중복 검사
			if (id.isEmpty()) {
				System.out.println("사용불가한 id 입니다.");
				continue;
			}
			if (Validator.isDuplicateId(id, userIds)) {
				System.out.println("중복된 id 입니다.");
				continue;
			}

			// 회원 정보 등록 메서드 호출
			userVO = createUserForm(id);
			// 등록한 회원 정보 확인
			System.out.println("등록한 정보를 확인합니다.\n" + userVO.toStringUsrForm());
			if (Cmm1002.askSubmit(msg.getProperty("AGAIN.FORM")).equals("y")) {
				continue;
			}
			break;
		}
		Cmm1002.submitPage("usrC", userVO);
	}

	/**
	 * Func : Usr1000VO생성 메서드
	 * 
	 * @desc Usr1000VO 객체 등록
	 * @param
	 * @return Usr1000VO
	 * @throws Exception
	 */
	private Usr1000VO createUserForm(String id) throws Exception {
		System.out.println("이름 기입");
		String name = scanner.nextLine().trim();
		stop(name);
		while (!(Validator.isKorean(name) || Validator.isEnglish(name)) || name.isEmpty()) {
			System.out.println("이름은 영어 혹은 한글만 가능합니다.");
			name = scanner.nextLine().trim();
		}

		System.out.println("성별 선택 ( 남자=1 여자=2 )");
		String gender = Validator.chooseRangeNum(1, 3);

		System.out.println("연락처는 기입(010-xxxx-xxxx 형식으로 작성)");
		String phoneNumber = scanner.nextLine().trim();
		stop(phoneNumber);
		while (!Validator.isValidPhoneNumber(phoneNumber)) {
			System.out.println("010-xxxx-xxxx 형식으로 작성");
			phoneNumber = scanner.nextLine().trim();
		}

		System.out.println("주소 기입");
		String address = scanner.nextLine().trim();
		stop(address);
		while (address.isEmpty()) {
			System.out.println("값을 작성");
			address = scanner.nextLine().trim();
		}

		return new Usr1000VO(id, name, Gender.choose(gender), phoneNumber, address);
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

}
