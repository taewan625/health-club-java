package view.lck.lck1000.lck1000;

import java.util.Map;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1002;

/**
 * @Class Name : Lck1004.java
 * @Description 사물함관리 삭제을 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1004 implements JavaHTML {
	/**
	 * Func : 삭제 실행 메서드
	 * 
	 * @desc 사물함 정보 삭제에 사용
	 * @param Request
	 *            lck1000Request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request lck1000Request) throws Exception {
		System.out.println("삭제 사물함 정보");
		Map<String, ?> responseData = lck1000Request.getResponseData();
		Lck1000VO locker = (Lck1000VO) responseData.get("locker");
		System.out.println(locker.toString());
		// 제출
		Cmm1002.submitPage("lckD", locker.getLockerNum());
	}
}