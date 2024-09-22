package view.lck.lck1000.lck1000;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import com.val.Validator;
import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Description 사물함관리 등록을 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1002 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);
	
	//공통 문구
	private final String COMMON_PROMP = "[사물함 등록을 멈추고 나가고 싶으면 n 작성]";
	
	/**
	 * @desc 사물함 등록
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void response(Request request) throws Exception {
		System.out.println("[사물함 등록]");
		
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//등록할 사물함이 없을 경우
		if (datas.isEmpty()) {
			//경고문구 표출
			System.out.println("사용중인 사물함입니다.");
			
			//사물함 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", "lck/lck1000/lck1000/selectLck1000View");
			
			//WAS에 요청
			webContainer.service(requestData);
		}
		
		//사물함 데이터 조회
		Lck1000VO locker = (Lck1000VO) datas.get("locker");
		
		//객체 참조로 인한 오류를 방지하기 위한 깊은 복사 객체 생성
		Lck1000VO lockerInfo = new Lck1000VO(locker);
		
		//회원 목록 데이터 조회
		List<Usr1000VO> users = (List<Usr1000VO>) datas.get("users");
		
		/*사물함 등록 로직*/
		//회원 id -> 존재하는 회원이여야한다. 
		String userId = execute("* 아이디 작성하세요. [존재하는 아이디 작성] " + COMMON_PROMP
				, "존재하지 않거나 만료일이 지난 회원입니다."
				, answer -> users.stream()
								.noneMatch(user -> user.getId().equals(answer) && (!user.getExpireDate().isBefore(LocalDate.now())))
				);
		
		lockerInfo.setUserId(userId);
		
		//해당 회원 정보
		Usr1000VO userInfo = users.stream().filter(user -> user.getId().equals(userId)).findFirst().get();
		
		//회원 등록일 (등록일이 금일 이후이면 등록일, 이전이면 금일을 기준으로 한다.)
		LocalDate registerDate = (userInfo.getJoinDate().isBefore(LocalDate.now())) ? LocalDate.now() : userInfo.getJoinDate();
		
		//회원 만료일
		LocalDate expireDate = userInfo.getExpireDate();
		
		//사물함 등록 가능일
		System.out.println("사물함 등록 가능일 : " + registerDate + " ~ " + expireDate);
		
		//등록 시작일자
		String startDate = execute("*사물함 등록일을 작성하세요. [2024-09-06 형식, 등록가능일 내 설정] " + COMMON_PROMP
									, "유효한 사물함 등록일을 작성하세요. *사물함 등록 가능일 : " + registerDate + " ~ " + expireDate
									, input -> !Validator.isValidatedDate(input, registerDate, expireDate));
		
		lockerInfo.setStartDate(LocalDate.parse(startDate));
		
		//사물함 종료 가능일
		System.out.println("사물함 종료 가능일 : "+ startDate + " ~ " + expireDate);
		
		//등록 종료일자
		String endDate = execute("*사물함 만료일을 작성하세요. [2024-09-06 형식, 종료 가능일 내 설정] " + COMMON_PROMP
				, "유효한 사물함 만료일을 작성하세요. *사물함 만료 가능일 : "+ startDate + " ~ " + expireDate
				, input -> !Validator.isValidatedDate(input, LocalDate.parse(startDate), expireDate));
		
		lockerInfo.setEndDate(LocalDate.parse(endDate));
		
		//전송용 데이터 객체에 담기
		clientDatas.put("lockerInfo", lockerInfo);
		
		//사물함 정보 담기
		requestData.put("clientDatas", clientDatas);
		
		//접근 경로 담기
		requestData.put("url", "lck/lck1000/lck1000/saveLck1000LockerInfo");
		
		//WAS에 요청
		webContainer.service(requestData);
	}
	
	/**
	 * @desc 사물함 등록 시 검증 작업 및 사물함 등록 취소 작업 수행
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
			
			//n을 작성할 경우 사물함 화면으로 이동
			if ("n".equals(answer.toLowerCase())) {
				//경로 데이터 담기
				requestData.put("url", "lck/lck1000/lck1000/selectLck1000View");
				
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
