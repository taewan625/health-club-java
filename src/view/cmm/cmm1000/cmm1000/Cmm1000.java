package view.cmm.cmm1000.cmm1000;

import java.util.Collections;
import java.util.Map;

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
	 * @desc 화면을 보여주고 요청을 보낼 경우 해당 화면에서 dispatcherServlet으로 값이 전달이 된다.
	 * @param Object data
	 * @return ClientRequest<?>
	 * @throws Exception
	 */
	//TODO 화면 내용만 여기에 존재하고 실제 response 정보는 response와 startWeb에서 수행 필요
	@Override
	public void response(Request Request) throws Exception {
		while (true) {
			System.out.println("헬스프로그램 메뉴");
			//TODO JAVA HTML의 역할 : 접근해야하는 메뉴 url 경로 매핑해주는 역할 필요
			//System.out.println("1. 회원관리, 2.사물함 관리, 3.회원통계, 4.프로그램 종료");
			//System.in()
			// 받은 번호와 JAVA HTML에 저장된 url 경로 매핑 후 요청 보내기
			String inputData = Cmm1001.choiceMenu("1. 회원관리, 2.사물함 관리, 3.회원통계, 4.프로그램 종료 \n메뉴의 번호를 입력하세요.", 1, 5);

			//시스템 종료
			if (inputData.equals("4")) {
				systemExit();
			} 
			//URL 정보와 데이터를 객체에 담아서 WebContainer에 전송
			else {
				//전송 객체 생성
				Map<String, Object> requestData = Collections.singletonMap("url", urlMatch(inputData));
				
				//WAS에 요청
				webContainer.service(requestData);
				
			}
		}
	}

	/**
	 * Func : 시스템 종료 메서드
	 * 
	 * @desc 해당 스택에서 즉시 시스템을 종료 시킨다.
	 */
	private void systemExit() {
		System.out.println("시스템이 종료 됩니다.");
		System.exit(0);
	}

}
