package view.lck.lck1000.lck1000;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;
import view.cmm.cmm1000.cmm1000.Cmm1001;
import view.cmm.cmm1000.cmm1000.Cmm1002;

/**
 * @Class Name : Lck1003.java
 * @Description 사물함관리 수정을 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.27.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1003 implements JavaHTML {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public void response(Request lck1000Request) throws Exception {
		Lck1000VO locker;
		// 수정들어올 때 회원 id 미리 보여주기
		while (true) {
			System.out.println("사물함 수정을 시작");
			// 회원 정보 수정 메서드 호출
			Map<String, ?> responseData = lck1000Request.getResponseData();
			List<String> userIds = (List<String>) responseData.get("userIds");
			locker = updateLockerOwner(userIds);
			// 제출 여부 질문
			if (Cmm1002.askSubmit(msg.getProperty("AGAIN.FORM")).equals("y")) {
				continue;
			}
			break;
		}
		// 제출
		Cmm1002.submitPage("lckU", locker);
	}

	/**
	 * Func : locker 수정 정보 작성하는 메서드
	 * 
	 * @desc locker 수정
	 * @param
	 * @return Lck1000VO
	 * @throws Exception
	 */
	private Lck1000VO updateLockerOwner(List<String> userIds) throws Exception {
		System.out.println("새로운 사물함에 등록할 사용자 id를 작성해주세요");
		System.out.println("현재 존재하는 id : " + userIds);
		String userId = scanner.next().trim();

		// 메뉴선택 공통 팝업창
		String inputData = Cmm1001.choiceMenu("몇번 사물함에 등록하시겠습니까?", 1, 100);
		String date = Cmm1001.choiceMenu("사용하실 일수를 작성해주세요. 최소 10일 부터 최대 999일", 10, 1000);

		return new Lck1000VO(userId, Integer.parseInt(inputData), Integer.parseInt(date));
	}

}
