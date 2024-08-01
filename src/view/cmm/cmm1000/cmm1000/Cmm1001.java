package view.cmm.cmm1000.cmm1000;

import com.val.Validator;

/**
 * @Class Name : Cmm1001.java
 * @Description 메뉴 선택 팝업창
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.27.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Cmm1001 {
	/**
	 * Func : 이동할 메뉴 선택 메서드
	 * 
	 * @desc 사물함, 메뉴 번호 선택시 사용한다.
	 * @param String
	 *            inputData
	 * @return String
	 * @throws Exception
	 */
	public static String choiceMenu(String prompt, int from, int to) throws Exception {
		// 질문지
		System.out.println(prompt);
		// 반환값
		return Validator.chooseRangeNum(from, to);
	}

}
