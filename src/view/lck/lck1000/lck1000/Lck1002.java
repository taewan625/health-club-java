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
 * @Class Name : Lck1002.java
 * @Description 사물함관리 등록을 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1002 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Func : locker 등록하는 메서드
	 * 
	 * @desc 사물함 등록
	 * @param Request
	 *            requestWithResponseData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request lck1000Request) throws Exception {
		System.out.println("사물함 등록을 시작");
		// create 들어올 때, 회원 id가지고 들어오기
		Lck1000VO lckVO;
		while (true) {
			// 사물함 정보 등록 메서드 호출
			Map<String, ?> responseData = lck1000Request.getResponseData();
			List<String> userIds = (List<String>) responseData.get("userIds");
			lckVO = createLockerOwner(userIds);
			// 등록한 회원 정보 확인
			System.out.println("등록한 정보를 확인합니다.\n" + lckVO.toString());
			if (Cmm1002.askSubmit(msg.getProperty("AGAIN.FORM")).equals("y")) {
				continue;
			}
			break;
		}
		// 제출
		Cmm1002.submitPage("lckC", lckVO);
	}

	/**
	 * Func : locker 등록하는 메서드
	 * 
	 * @desc lockerVO 생성
	 * @param
	 * @return Lck1000VO
	 * @throws Exception
	 */
	private Lck1000VO createLockerOwner(List<String> userIds) throws Exception {
		System.out.println("등록할 회원의 id를 넣어주세요");
		System.out.println("현재 존재하는 id : " + userIds);
		String userId = scanner.next().trim();
		// 사물함 번호 등록 공통 팝업창
		String inputData = Cmm1001.choiceMenu("어떤 사물함에 등록할 것인가요?", 1, 100);
		String date = Cmm1001.choiceMenu("사용하실 일수를 작성해주세요. 최소 10일 부터 최대 999일", 10, 1000);

		return new Lck1000VO(userId, Integer.parseInt(inputData), Integer.parseInt(date));
	}

}
