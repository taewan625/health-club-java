package view.lck.lck1000.lck1000;

import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;

/**
 * @Description 사물함 단건 정보 조회
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1001 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * @desc 사물함 정보 표출
	 * @param Request request
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void response(Request request) throws Exception {
		System.out.println("[사물함 상세]");
		
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//회원 정보 조회
		Lck1000VO locker = (Lck1000VO) datas.get("locker");
		
		//사물함 정보가 없는 경우
		if (locker == null) {
			System.out.println("해당 회원은 사물함이 만료되었거나 등록된 사물함이 없습니다.");
			
		}
		//사물함 정보가 있는 경우
		else {
			System.out.println(locker.toString());
		}
		
		//닫기 문구
		System.out.println("닫기 - 아무키나 누르세요");
		
		//닫기 동작
		scanner.nextLine();
		
		//사물함 메뉴로 이동하는 전송 객체 생성
		requestData.put("url", "lck/lck1000/lck1000/selectLck1000View");
		
		//WAS에 요청
		webContainer.service(requestData);
	}
}