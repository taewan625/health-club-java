package view.usr.usr1000.usr1000;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import com.val.Validator;
import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
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
	 * @desc model이 없는 메서드
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request request) throws Exception {
		System.out.println("[회원 수정]");
		
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//회원 정보 조회
		Usr1000VO userInfo = (Usr1000VO) datas.get("user");
		
		//회원 정보가 없는 경우
		if (userInfo == null) {
			System.out.println("존재하지 않는 회원입니다.");
			
			//회원 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", "usr/usr1000/usr1000/selectUsr1000View");
			
			//WAS에 요청
			webContainer.service(requestData);
			
		}
		//회원 정보가 있는 경우
		else {
			//회원 정보 표출
			System.out.println("[기존 회원 정보 ]\n" + userInfo.toString());
			
			//공통 문구
			final String COMMON_PROMP = "[회원 수정을 멈추고 나가고 싶으면 n 작성 | 해당 단계 스킵하고 싶으면 s 작성]";
			
			/*회원 수정 로직*/
			//회원명
			String userName = execute("* 이름을 작성하세요. " + COMMON_PROMP
					, "이름은 영어 혹은 한글만 가능합니다."
					, answer -> !(Validator.isValidatedName(answer)));
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(userName)) {
				userInfo.setName(userName);
			}
			
			//성별
			String gender = execute("* 성별을 작성하세요. [m | f] " + COMMON_PROMP
					, "올바른 성별을 작성하세요. [m | f]"
					, answer -> !("f".equals(answer) || "m".equals(answer)));
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(gender)) {
				userInfo.setGender(gender);
			}
			
			//연락처
			String phoneNumber = execute("* 연락처를 작성하세요. [010-xxxx-xxxx 형식으로 작성] " + COMMON_PROMP
					, "올바른 연락처를 작성하세요."
					, answer -> !Pattern.matches("^010-\\d{4}-\\d{4}$", answer));
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(phoneNumber)) {
				userInfo.setPhoneNumber(phoneNumber);
			}
			
			//주소 작성
			String address = execute("주소를 작성하세요. [주소는 필수 값이 아닙니다.] " + COMMON_PROMP
					, null
					, input -> false);
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(address)) {
				userInfo.setAddress(address);
			}
			
			//회원 설명
			String description = execute("회원 설명을 작성하세요. [회원 설명은 필수 값이 아닙니다.] " + COMMON_PROMP
					, null
					, input -> false);
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(description)) {
				userInfo.setDescription(description);
			}
			
			//등록일이 금일 이후인 경우만 수정 가능 
			if (userInfo.getJoinDate().isAfter(LocalDate.now())) {
				//회원 등록일
				String joinDate = execute("*회원 등록일을 작성하세요. [2024-08-28 형식으로 작성] " + COMMON_PROMP
						, "금일 이후 올바른 일자를 작성하세요."
						, input -> Validator.isValidatedDate(input, LocalDate.now()));
				
				//skip이 아닌 경우 데이터 세팅
				if (!"s".equals(joinDate)) {
					userInfo.setJoinDate(LocalDate.parse(joinDate));
				}
			}
			
			//회원 만료일
			String expireDate = execute("*회원 만료일을 작성하세요. [2024-08-28 형식으로 작성] " + COMMON_PROMP
					, "금일 이후 올바른 일자를 작성하세요."
					, input -> Validator.isValidatedDate(input, (userInfo.getJoinDate().isAfter(LocalDate.now())) ? userInfo.getJoinDate() : LocalDate.now()));
			
			//skip이 아닌 경우 데이터 세팅
			if (!"s".equals(expireDate)) {
				userInfo.setExpireDate(LocalDate.parse(expireDate));
			}
			
			//사용 유무 정보
			userInfo.setUse("Y");
			
			//회원 상태 [정상: 5일 이상, 임박: 5일 미만, 만기: 초과된 경우]
			userInfo.setStatus(userInfo.getExpireDate().isAfter(LocalDate.now().plusDays(4)) ? "정상" : "임박");
			
			//최종수정 메타 정보
			userInfo.setModifyDateTime(LocalDateTime.now());
			
			//전송용 데이터 객체에 담기
			clientDatas.put("userInfo", userInfo);
			
			//회원 정보 담기
			requestData.put("clientDatas", clientDatas);
			
			//접근 경로 담기
			requestData.put("url", "usr/usr1000/usr1000/updateUsr1000UserInfo");
			
			//WAS에 요청
			webContainer.service(requestData);
			
		}
	}
	
	/**
	 * @desc 회원 수정 시 검증 작업, 회원 수정 취소 작업, 수정 skip 작업 수행
	 * @param String questionPromp, String warningPromp, Predicate<String> validate
	 * @return String
	 * @throws Exception
	 */
	private String execute(String questionPromp, String warningPromp, Predicate<String> validate) throws Exception {
		//중복 혹은 유효성에 문제가 존재하는지 여부 확인 변수
		boolean isWrong = false;
		
		//반환 될 사용자 입력값 변수
		String answer;
		
		do {
			//질문
			System.out.println(questionPromp);
			
			//사용자 입력값
			answer = scanner.nextLine().trim();
			
			//해당 작업 스킵하는 경우
			if ("s".equals(answer.toLowerCase())) {
				//문자열 통일화
				answer = "s";
				
				//루프문 탈출
				break;
			}
			
			//대소문자 구분 없이 n을 작성할 경우 회원 화면으로 이동
			if ("n".equals(answer.toLowerCase())) {
				//회원 메뉴로 이동하는 전송 객체 생성
				requestData.put("url", "usr/usr1000/usr1000/selectUsr1000View");
				
				//WAS에 요청
				webContainer.service(requestData);
			}
			
			//제약조건 검사
			isWrong = validate.test(answer);
			
			//입력값에 오류가 존재할 경우 경고문 
			if (isWrong) {
				System.out.println(warningPromp);
			}
			
		} while(isWrong);
		
		//사용자 입력값 반환
		return answer;
	}
}
