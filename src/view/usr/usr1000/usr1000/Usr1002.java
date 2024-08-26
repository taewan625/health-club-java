package view.usr.usr1000.usr1000;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.val.Validator;
import com.web.MessageSource;
import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Description 회원등록 팝업
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1002 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * @desc :초기화 메서드의 예외를 처리하기 위해 try-catch와 new RuntimeExceptioin()을 던진다.
	 * @param Request usr1000Request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void response(Request request) throws Exception {
		//데이터 조회
		Map<String, Object> datas = request.getDatas();
		
		//회원리스트 데이터 조회
		List<Usr1000VO> users = (List<Usr1000VO>) datas.get("users");
		
		System.out.println("[회원 등록]");
		
		//중복 혹은 유효성에 문제가 존재하는지 여부 확인 변수
		boolean isWrong = false;
		
		/*회원 등록 로직*/
		//회원 id
		do {
			//id 작성
			System.out.println("* 아이디 작성하세요. [중복 불가, 최소 4자 이상 작성] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
			
			//입력 id 값
			String userId = scanner.nextLine().trim();
			
			//중간 종료 여부 확인
			wantStop(userId);
			
			//회원 아이디 중복 확인
			isWrong = users.stream().map(Usr1000VO::getId).anyMatch(id -> id.equals(userId));
			
			//id가 중복 될 경우 문구 
			if (isWrong) {
				System.out.println("중복된 id가 존재합니다.");
			}
			//map에 데이터 담기
			else {
				paramData.put("userId", userId);
			}
			
		} while(isWrong);
		
		//회원명
		do {
			System.out.println("* 이름을 작성하세요. [회원 등록을 멈추고 나가고 싶으면 n 작성]");
			
			//회원명 입력 값
			String userName = scanner.nextLine().trim();
			
			//중간 종료 여부 확인
			wantStop(userName);
			
			//유효성 검증 - 빈값 || 한글만 있거나 영어만 있거나
			isWrong = userName.isEmpty() || !(Validator.isKorean(userName) || Validator.isEnglish(userName));
			
			//id가 중복 될 경우 문구 
			if (isWrong) {
				System.out.println("이름은 영어 혹은 한글만 가능합니다.");
			}
			//map에 데이터 담기
			else {
				paramData.put("userName", userName);
			}
			
		} while (isWrong);
		
		//성별 선택
		do {
			System.out.println("* 성별을 작성하세요. [회원 등록을 멈추고 나가고 싶으면 n 작성]");
			
			//성별 입력 값
			String gender = scanner.nextLine().trim().toLowerCase();
			
			//중간 종료 여부 확인
			wantStop(gender);
			
			//유효성 검증 - f, m 이 아닌 경우
			isWrong = !("f".equals(gender) || "m".equals(gender));
			
			//id가 중복 될 경우 문구 
			if (isWrong) {
				System.out.println("올바른 성별을 작성하세요.");
			}
			//map에 데이터 담기
			else {
				paramData.put("gender", gender);
			}
			
		} while(isWrong);
		
		//연락처 작성
		do {
			System.out.println("* 연락처를 작성하세요. [010-xxxx-xxxx 형식으로 작성] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
			
			//연락처 입력 값
			String phoneNumber = scanner.nextLine().trim();
			
			//중간 종료 여부 확인
			wantStop(phoneNumber);
			
			//유효성 검증
			isWrong = !Validator.isValidPhoneNumber(phoneNumber);
			
			//id가 중복 될 경우 문구 
			if (isWrong) {
				System.out.println("올바른 연락처를 작성하세요.");
			}
			//map에 데이터 담기
			else {
				paramData.put("phoneNumber", phoneNumber);
			}
			
		} while(isWrong);
		
		//주소 작성
		System.out.println("주소를 작성하세요. [주소는 필수 값이 아닙니다.] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
		
		//연락처 입력 값
		String address = scanner.nextLine().trim();
		
		//중간 종료 여부 확인
		wantStop(address);
		
		//map에 데이터 담기
		paramData.put("address", address);
		
		//등록된 데이터 값 보여주기
		System.out.println(paramData.toString());
		
		//등록 수행
		requestData.put("userInfo", paramData);
		requestData.put("url", MessageSource.getMessage("message.usr"));
	}


	/**
	 * @desc 회원등록을 멈추고 회원 화면 메뉴로 이동
	 * @param String inputValue
	 * @return void
	 * @throws Exception
	 */
	private void wantStop(String inputValue) throws Exception {
		//대소문자 구분 없이 n을 작성할 경우 회원 화면으로 이동
		if ("n".equals(inputValue) || "N".equals(inputValue)) {
			//회원 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", MessageSource.getMessage("message.usr"));
			
			//WAS에 요청
			webContainer.service(requestData);
		}
	}

}
