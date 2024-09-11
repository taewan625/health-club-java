package view.lck.lck1000.lck1000;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import com.val.Validator;
import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Description 사물함 수정 팝업창
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.27.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1003 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);
	
	//공통 문구
	private final String COMMON_PROMP = "[사물함 수정을 멈추고 나가고 싶으면 n 작성] | 해당 단계 스킵하고 싶으면 s 작성]";
	
	/**
	 * @desc 사물함 수정
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request request) throws Exception {
		System.out.println("[사물함 수정]");
		
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//사물함 정보 조회
		Lck1000VO locker = (Lck1000VO) datas.get("locker");
		
		//사물함 회원 정보 조회
		Usr1000VO user = (Usr1000VO) datas.get("user");
		
		//사물함 정보가 없는 경우
		if (user == null) {
			System.out.println("해당 사물함은 수정할 정보가 존재하지 않습니다.");
			
			//닫기 문구
			System.out.println("닫기 - 아무키나 누르세요");
			
			//닫기 동작
			scanner.nextLine();
			
			//사물함 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", "lck/lck1000/lck1000/selectLck1000View");
			
		}
		//사물함 정보가 있는 경우
		else {
			//사물함 정보 보여주기
			System.out.println(locker.toString());
			
			//객체 참조로 인한 오류를 방지하기 위한 깊은 복사 객체 생성
			Lck1000VO lockerInfo = new Lck1000VO(locker);
			
			/*사물함 수정 로직 : 수정 범위는 일자 변경 가능*/
			//회원 등록일 (등록일이 금일 이후이면 등록일, 이전이면 금일을 기준으로 한다.)
			LocalDate userRegisterDate = (user.getJoinDate().isBefore(LocalDate.now())) ? LocalDate.now() : user.getJoinDate();
			
			//회원 만료일
			LocalDate userExpireDate = user.getExpireDate();
			
			//사물함 만료일
			LocalDate lockerEndDate = locker.getEndDate();
			
			//사물함 시작일이 시작되지 않았을 경우
			if (lockerInfo.getStartDate().isAfter(LocalDate.now())) {
				//사물함 수정 가능일
				System.out.println("사물함 등록 가능일 : " + userRegisterDate + " ~ " + userExpireDate);
				
				//현재 사물함 종료일 경고 문구
				System.out.println("현재 사물함 종료일 : " + lockerEndDate + "\n주의 : 수정한 사물함 시작일이 현재 사물함 종료일을 넘길 경우 사물함 종료일은 필수로 변경해야됩니다.");
				
				//등록 시작일자
				String startDate = execute("사물함 등록일을 작성하세요. [2024-09-06 형식, 수정가능일 내 설정] " + COMMON_PROMP
						, "유효한 사물함 등록일을 작성하세요.\n*사물함 등록 가능일 : " + userRegisterDate + " ~ " + userExpireDate + "현재 사물함 종료일 : " + lockerEndDate + "\n주의 : 수정한 사물함 시작일이 현재 사물함 종료일을 넘길 경우 사물함 종료일은 필수로 변경해야됩니다."
						, input -> !Validator.isValidatedDate(input, userRegisterDate, userExpireDate));
				
				lockerInfo.setStartDate(LocalDate.parse(startDate));
				
			}
			
			//사물함 시작일
			LocalDate lockerStartDate = (locker.getStartDate().isAfter(LocalDate.now())) ? locker.getStartDate() : LocalDate.now();
			
			//사물함 시작일이 사물함 종료일 보다 이후일 경우
			boolean isRequired = lockerStartDate.isAfter(locker.getEndDate()); 
			
			//사물함 종료 가능일
			System.out.println("사물함 종료 가능일 : "+ lockerStartDate + " ~ " + userExpireDate);
			
			if (isRequired) {
				//등록 종료일자
				String endDate = execute("*사물함 만료일을 작성하세요. [2024-09-06 형식, 종료 가능일 내 설정] [사물함 수정을 멈추고 나가고 싶으면 n 작성]"
						, "유효한 사물함 만료일을 작성하세요. *사물함 만료 가능일 : "+ lockerStartDate + " ~ " + userExpireDate
						, input -> !Validator.isValidatedDate(input, lockerStartDate, userExpireDate));
				
				lockerInfo.setEndDate(LocalDate.parse(endDate));
			}
			else {
				//등록 종료일자
				String endDate = execute("사물함 만료일을 작성하세요. [2024-09-06 형식, 종료 가능일 내 설정] " + COMMON_PROMP
						, "유효한 사물함 만료일을 작성하세요. *사물함 만료 가능일 : "+ lockerStartDate + " ~ " + userExpireDate
						, input -> !Validator.isValidatedDate(input, lockerStartDate, userExpireDate));
				
				lockerInfo.setEndDate(LocalDate.parse(endDate));
				
			}
			
			//전송용 데이터 객체에 담기
			clientDatas.put("lockerInfo", lockerInfo);
			
			//사물함 정보 담기
			requestData.put("clientDatas", clientDatas);
			
			//접근 경로 담기
			requestData.put("url", "lck/lck1000/lck1000/updateLck1000LockerInfo");
		}
		
		//WAS에 요청
		webContainer.service(requestData);
	}
	
	/**TODO skip 가능하도록 수정 필요
	 * @desc 사물함 수정 시 검증 작업 및 사물함 수정 취소 작업 수행
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
