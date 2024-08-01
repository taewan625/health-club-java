package view.usr.usr1000.usr1000;

import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1002;

/**
 * @Class Name : Usr1004.java
 * @Description 회원등록 팝업창
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.25.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1004 implements JavaHTML {
	/**
	 * Func : view 실행 메서드
	 * 
	 * @desc model이 없는 메서드
	 * @param Request
	 *            usr1000Request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request usr1000Request) throws Exception {
		System.out.println("삭제 회원 정보");
		Map<String, ?> responseData = usr1000Request.getResponseData();
		Usr1000VO user = (Usr1000VO) responseData.get("user");
		System.out.println(user);

		// 제출
		Cmm1002.submitPage("usrD", user.getId());
	}

}
