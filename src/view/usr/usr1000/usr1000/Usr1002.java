package view.usr.usr1000.usr1000;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		Map<String, Object> datas = request.getClientDatas();
		
		//회원리스트 데이터 조회
		List<Usr1000VO> users = (List<Usr1000VO>) datas.get("users");
		
		//회원 정보를 담을 객체
		Usr1000VO userInfo = new Usr1000VO();
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
				userInfo.setId(userId);
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
				userInfo.setName(userName);
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
				userInfo.setGender(gender);
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
				userInfo.setPhoneNumber(phoneNumber);
			}
			
		} while(isWrong);
		
		//주소 작성
		System.out.println("주소를 작성하세요. [주소는 필수 값이 아닙니다.] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
		
		//주소 입력 값
		String address = scanner.nextLine().trim();
		
		//중간 종료 여부 확인
		wantStop(address);
		
		//map에 데이터 담기
		userInfo.setAddress(address);
		
		//회원 설명
		System.out.println("회원 설명을 작성하세요. [회원 설명은 필수 값이 아닙니다.] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
		
		//회원 설명 입력 값
		String description = scanner.nextLine().trim();
		
		//중간 종료 여부 확인
		wantStop(description);
		
		//map에 데이터 담기
		userInfo.setDescription(description);
		//TODO. 회원 등록일 유효성 검사 필요함. do-while 사용필요
		//회원 등록일
		System.out.println("*회원 등록일을 작성하세요. [2024-08-28 형식으로 작성] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
		
		//회원 등록일 입력 값
		String joinDate = scanner.nextLine().trim();
		
		//중간 종료 여부 확인
		wantStop(joinDate);
		
		//map에 데이터 담기
		userInfo.setJoinDate(LocalDate.parse(joinDate));
		
		//TODO. 회원 만료일 유효성 검사 필요함. do-while 사용필요
		System.out.println("*회원 만료일을 작성하세요. [2024-08-28 형식으로 작성] [회원 등록을 멈추고 나가고 싶으면 n 작성]");
		
		//회원 만료일 입력 값
		String expireDate = scanner.nextLine().trim();
		
		//중간 종료 여부 확인
		wantStop(expireDate);
		
		//map에 데이터 담기
		userInfo.setExpireDate(LocalDate.parse(expireDate));
		
		//메타정보 넣기
		userInfo.setUse("Y");
		userInfo.setStatus("정상");
		userInfo.setDelete("N");
		userInfo.setJoinDateTime(LocalDateTime.now());
		userInfo.setEditDateTime(LocalDateTime.now());
		
		//전송용 데이터 객체에 담기
		clientDatas.put("userInfo", userInfo);
		
		//회원 정보 담기
		requestData.put("clientDatas", clientDatas);
		
		//접근 경로 담기
		requestData.put("url", MessageSource.getMessage("message.usrCreateInfo"));
		
		//WAS에 요청
		webContainer.service(requestData);
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
			requestData.put("url", "usr/usr1000/usr1000/selectUsr1000View");
			
			//WAS에 요청
			webContainer.service(requestData);
		}
	}

}
