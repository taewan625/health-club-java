package view.cmm.cmm1000.cmm1000;

import com.val.Validator;
import com.web.Request;

import view.JavaHTML;

/**
 * @desc 메뉴화면을 출력하는 index page
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1000 implements JavaHTML {
	//생성자
	public Cmm1000() {
		//회원 메뉴
		urlMap.put("1", "usr/usr1000/usr1000/selectUsr1000View");
		//사물함 메뉴
		urlMap.put("2", "lck/lck1000/lck1000/selectLck1000View");
		//통계 메뉴
		urlMap.put("3", "sta/sta1000/sta1000/selectSta1000View");
	}
	
	/**
	 * @desc 화면을 보여주고 요청을 보낼 경우 해당 화면에서 webContainer로 값이 전달이 된다.
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request request) throws Exception {
		while (true) {
			//메인 메뉴 화면
			System.out.println("헬스프로그램 메뉴\n1. 회원관리, 2.사물함 관리, 3.회원통계, 4.프로그램 종료 \n메뉴의 번호를 입력하세요.");
			
			//client 입력값
			String inputData = Validator.chooseRangeNum(1, 5);
			
			//시스템 종료
			if (inputData.equals("4")) {
				//종료 메시지
				System.out.println("시스템이 종료 됩니다.");
				//시스템 종료
				System.exit(0);
				
			} 
			//URL 정보와 데이터를 객체에 담아서 WebContainer에 전송
			else {
				//전송 객체 생성 - url 담기
				requestData.put("url", urlMatch(inputData));
				
				//WAS에 요청
				webContainer.service(requestData);
			}
		}
	}
}