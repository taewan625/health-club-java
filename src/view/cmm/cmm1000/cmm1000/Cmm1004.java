package view.cmm.cmm1000.cmm1000;

import java.util.Map;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Class Name : Cmm1004.java
 * @Description 성공여부 알람
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.01.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1004 {
	public static void failReason(Request requestWithResponseData) throws Exception {
		Map<String, ?> responseData = requestWithResponseData.getResponseData();
		if (responseData.containsKey("user")) {
			Usr1000VO user = (Usr1000VO) responseData.get("user");
			// 예외 존재시
			if (user.getErrCode().equals("-1")) {
				System.out.println(user.getErrMsg());
				System.out.println("실패");
				return;
			} else {
				System.out.println("성공");
			}
		}

		if (responseData.containsKey("locker")) {
			Lck1000VO locker = (Lck1000VO) responseData.get("locker");
			// 예외 존재시
			if (locker.getErrCode().equals("-1")) {
				System.out.println(locker.getErrMsg());
				System.out.println("실패");
				return;
			} else {
				System.out.println("성공");
			}
		}
	}

}
