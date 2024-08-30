package view.usr.usr1000.usr1000;

import java.util.Map;
import java.util.Scanner;

import com.web.MessageSource;
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
		Usr1000VO user = (Usr1000VO) datas.get("user");
		
		//회원 정보가 없는 경우
		if (user == null) {
			System.out.println("존재하지 않는 회원입니다.");
			
			//회원 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", MessageSource.getMessage("message.usr"));
			
			//WAS에 요청
			webContainer.service(requestData);
		}
		//회원 정보가 있는 경우
		else {
			System.out.println(user.toString());
			
			//TODO 회원 수정 작업 진행 필요
			System.out.println("닫기 - 아무키나 누르세요");
			scanner.nextLine();
			
			//회원 메뉴로 이동하는 전송 객체 생성
			requestData.put("url", MessageSource.getMessage("message.usr"));
			
			//WAS에 요청
			webContainer.service(requestData);
		}
		
	}
}
