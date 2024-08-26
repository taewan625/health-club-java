package view.usr.usr1000.usr1000;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.val.Validator;
import com.web.MessageSource;
import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Description 회원관리 리스트를 보여주는 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	//생성자
	public Usr1000() {
		urlMap.put("1", MessageSource.getMessage("message.usrS"));
		urlMap.put("2", MessageSource.getMessage("message.usrC"));
		urlMap.put("3", MessageSource.getMessage("message.usrUF"));
		urlMap.put("4", MessageSource.getMessage("message.usrDF"));
		urlMap.put("5", MessageSource.getMessage("message.menu"));
	}

	/**
	 * @desc model을 가지고 있는 메서드, 메뉴는 포함관계를 이용하여 보여준다.
	 * @param Request requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void response(Request request) throws Exception {
		while (true) {
			//회원 목록 정보 TODO 페이징 처리
			System.out.println("[회원 목록] - 1 페이지");
			
			//데이터 조회
			Map<String, Object> datas = request.getDatas();
			
			//회원리스트 데이터 조회
			List<Usr1000VO> users = (List<Usr1000VO>) datas.get("users");
			
			//회원 목록 출력
			for (Usr1000VO user : users) {
				System.out.println(user.toString());
			}
			
			//회원 화면
			System.out.println("1.회원조회 2.회원 등록 3.회원 수정 4.회원 삭제 5.메인메뉴");
			
			//client 입력값
			String inputData = Validator.chooseRangeNum(1, 5);
			
			//조회, 수정, 삭제 시, 아이디 정보 추가
			if ("1".equals(inputData) || "3".equals(inputData) || "4".equals(inputData)) {
				//입력할 id
				String userId = "";
				
				do {
					//질문
					System.out.println("아이디를 작성해주세요.");
					
					//데이터 입력
					userId = scanner.nextLine().trim();
					
				} while("".equals(userId));
				
				//사용자 정보를 map에 담기
				Map<String, String> userInfo = new HashMap<String, String>();
				
				userInfo.put("userId", userId);
				//요청할 객체에 담기
				requestData.put("clientDatas", userInfo);
			}
			
			//전송 객체 세팅
			requestData.put("url", urlMatch(inputData));
			
			//WAS에 요청
			webContainer.service(requestData);
		}
	}
	
}
