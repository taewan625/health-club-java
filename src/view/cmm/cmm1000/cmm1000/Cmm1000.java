package view.cmm.cmm1000.cmm1000;

import java.util.Map;

import com.val.Validator;
import com.web.MessageSource;
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
		urlMap.put("1", MessageSource.getMessage("message.usr"));
		urlMap.put("2", MessageSource.getMessage("message.lck"));
		urlMap.put("3", MessageSource.getMessage("message.sta"));
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
				//전송 객체 생성
				Map<String, Object> requestData = Map.of("url", urlMatch(inputData));
				
				//WAS에 요청
				webContainer.service(requestData);
			}
		}
	}

}
