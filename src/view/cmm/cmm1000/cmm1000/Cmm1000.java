package view.cmm.cmm1000.cmm1000;

import com.web.Request;

import view.JavaHTML;

/**
 * @Class Name : Cmm1000.java
 * @Description : 메뉴화면을 출력하는 index page
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.22.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1000 implements JavaHTML {
	/**
	 * Func : 초기화 메서드가 존재하는 생성자
	 * 
	 * @desc :초기화 메서드의 예외를 처리하기 위해 try-catch와 new RuntimeExceptioin()을
	 *       던진다. @param @return @throws
	 */
	public Cmm1000() {
		try {
			initUrl();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Func : url 초기화 메서드
	 * 
	 * @desc JavaHTML interface에서 Map<String, String>을 담았기 때문에 이동할 url 경로만 초기화 해주면
	 *       된다. 해당 value 사용시, urlMacth로 선언된 default 메서드를 사용하면된다.
	 * @param
	 * @return void
	 * @throws Exception
	 */
	private void initUrl() throws Exception {
		urlMap.put("1", msg.getProperty("usr"));
		urlMap.put("2", msg.getProperty("lck"));
		urlMap.put("3", msg.getProperty("sta"));
	}

	/**
	 * Func : 응답 화면을 뿌려주는 메서드
	 * 
	 * @desc 화면을 보여주고 요청을 보낼 경우 해당 화면에서 dispatcherServlet으로 값이 전달이 된다.
	 * @param Object
	 *            data
	 * @return ClientRequest<?>
	 * @throws Exception
	 */
	@Override
	public void response(Request requestWithResponseData) throws Exception {
		while (true) {
			System.out.println("헬스프로그램 메뉴");
			String inputData = Cmm1001.choiceMenu("1. 회원관리, 2.사물함 관리, 3.회원통계, 4.프로그램 종료 \n메뉴의 번호를 입력하세요.", 1, 5);

			if (inputData.equals("4")) {
				systemExit();
			} else {
				Request request = new Request(urlMatch(inputData));
				// dispatcherServlet에 요청값을 보낸다.
				dispatcherServlet.service(request);
			}
		}
	}

	/**
	 * Func : 시스템 종료 메서드
	 * 
	 * @desc 해당 스택에서 즉시 시스템을 종료 시킨다. @param @return @throws
	 */
	private void systemExit() {
		System.out.println("시스템이 종료 됩니다.");
		System.exit(0);
	}

}
