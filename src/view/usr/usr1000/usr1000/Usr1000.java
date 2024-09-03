package view.usr.usr1000.usr1000;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import com.val.Validator;
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
		//회원 상세 화면
		urlMap.put("1", "usr/usr1000/usr1000/selectUsr1001View");
		//회원 등록 화면
		urlMap.put("2", "usr/usr1000/usr1000/selectUsr1002View");
		//회원 수정 화면
		urlMap.put("3", "usr/usr1000/usr1000/selectUsr1003View");
		//회원 삭제 기능
		urlMap.put("4", "usr/usr1000/usr1000/deleteUsr1000UserInfo");
		//메인 메뉴
		urlMap.put("5", "cmm/cmm1000/cmm1000/selectCmm1000View");
		//회원 메뉴
		urlMap.put("6", "usr/usr1000/usr1000/selectUsr1000View");
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
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//등록, 수정, 삭제 시, 성공 메시지 전달
		String successMsg = Objects.toString(datas.get("successMsg"), "");
		
		//출력
		System.out.println(successMsg);
		
		//페이징 정보
		Map<String, Object> pageInfo = (Map<String, Object>)datas.get("pageInfo");
		
		//전체 페이징 정보
		int totalPage = (int) pageInfo.get("totalPage");
		
		//회원 목록 정보
		System.out.println("[회원 목록] - 총 " + totalPage + "page("+ pageInfo.get("totalCnt") + "건) 중 " + pageInfo.get("selectPage") + "page");
		
		//회원리스트 데이터 조회
		List<Usr1000VO> users = (List<Usr1000VO>) datas.get("users");
		
		//회원 목록 출력
		for (Usr1000VO user : users) {
			System.out.println(user.toString());
		}
		
		//전체 페이지가 1일 경우
		if (1 == totalPage) {
			System.out.println("1.회원조회 2.회원 등록 3.회원 수정 4.회원 삭제 5.메인메뉴");
			
		}
		//전체 페이지가 1보다 클 경우
		else {
			System.out.println("1.회원조회 2.회원 등록 3.회원 수정 4.회원 삭제 5.메인메뉴 6.페이지 변경");
		}
		
		//client 입력값
		String inputData = Validator.chooseRangeNum(1, 7);
		
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
			clientDatas.put("userId", userId);
			
			//요청할 객체에 담기
			requestData.put("clientDatas", clientDatas);
			
		} 
		//페이징 변화일 경우
		else if ("6".equals(inputData)) {
			//질문
			System.out.println("이동할 페이지번호를 작성해주세요.[1 ~ " + totalPage + "]");
			
			//client 입력값
			clientDatas.put("selectPage", Validator.chooseRangeNum(1, totalPage + 1));
		}
		
		//전송 객체 세팅
		requestData.put("url", urlMatch(inputData));
		
		//WAS에 요청
		webContainer.service(requestData);
	}
}
