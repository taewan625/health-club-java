package view.usr.usr1000.usr1000;

import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Class Name : Usr1001.java
 * @Description 회원정보를 보여주는 메서드
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.24.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1001 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func : 회원 조회페이지를 보여주는 메서드
	 * 
	 * @desc 회원 조회 정보를 보여준다. 서버로부터 온 요청 값 중에서 err문구가 존재시, 예외 문구를 보여주고 user 메인 화면으로
	 *       이동한다.
	 * @param Request
	 *            requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request requestWithResponseData) throws Exception {
		Map<String, ?> responseData = requestWithResponseData.getResponseData();

		Usr1000VO user = (Usr1000VO) responseData.get("user");
		// 예외 존재시
		if (user.getErrCode().equals("-1")) {
			System.out.println(user.getErrMsg());
			System.out.println("조회한 회원이 없기 때문에 회원 관리 화면으로 돌아갑니다.");
			return;
		}
		// 정상 로직 수행
		System.out.println(user.toString());
		// 메뉴로 돌아가기
		System.out.println("회원관리페이지로 돌아가실려면 아무 키나 누르면 돌아갑니다.");
		scanner.nextLine();
	}
}
