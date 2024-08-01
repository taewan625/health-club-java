package view.lck.lck1000.lck1000;

import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;

/**
 * @Class Name : Lck1001.java
 * @Description 사물함조회 리스트를 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1001 implements JavaHTML {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public void response(Request requestWithResponseData) throws Exception {
		Map<String, ?> responseData = requestWithResponseData.getResponseData();
		// 예외 문구가 존재할 시
		if (responseData.containsKey("errMsg")) {
			System.out.println(responseData.get("errMsg"));
			System.out.println("해당회원은 사물함을 가지고 있지 않기 때문에 사물함 관리 화면으로 돌아갑니다.");
			return;
		}
		// 정상 로직 수행
		Lck1000VO locker = (Lck1000VO) responseData.get("locker");
		System.out.println(locker.toString());
		// 메뉴로 돌아가기
		System.out.println("사물함 관리 화면으로 돌아가실려면 아무 키나 누르세요.");
		scanner.nextLine();
	}

}