package view.lck.lck1000.lck1000;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.val.Validator;
import com.val.ViewCore;
import com.web.Request;

import lck.lck1000.lck1000.vo.Lck1000VO;
import view.JavaHTML;

/**
 * @Description 사물함관리 리스트를 보여주는 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000 implements JavaHTML {
	//생성자
	public Lck1000() {
		//사물함 상세 화면
		urlMap.put("1", "lck/lck1000/lck1000/selectLck1001View");
		//사물함 등록 화면
		urlMap.put("2", "lck/lck1000/lck1000/selectLck1002View");
		//사물함 수정 화면
		urlMap.put("3", "lck/lck1000/lck1000/selectLck1003View");
		//사물함 삭제 기능
		urlMap.put("4", "lck/lck1000/lck1000/deleteLck1000LockerInfo");
		//메인 메뉴
		urlMap.put("5", "cmm/cmm1000/cmm1000/selectCmm1000View");
		//사물함 메뉴
		urlMap.put("6", "lck/lck1000/lck1000/selectLck1000View");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void response(Request request) throws Exception {
		//데이터 조회
		Map<String, Object> datas = request.getClientDatas();
		
		//CRUD 결과 메시지 출력
		System.out.println(Objects.toString(datas.get("message"), ""));
		
		//페이징 정보
		Map<String, Object> pageInfo = (Map<String, Object>)datas.get("pageInfo");
		
		//전체 페이징 정보
		int totalPage = (int) pageInfo.get("totalPage");
		
		//사물함 목록 정보
		System.out.println("[사물함 목록] - 총 " + totalPage + "page("+ pageInfo.get("totalCnt") + "건) 중 " + pageInfo.get("selectPage") + "page");
		
		//사물함 목록 데이터 조회
		List<Lck1000VO> lockers = (List<Lck1000VO>) datas.get("lockers");
		
		//사물함 목록 출력
		for (Lck1000VO locker : lockers) {
			System.out.println(locker.toString());
		}
		
		//메뉴 선택
		System.out.println("1.사물함 조회 2.사물함 등록 3.사물함 수정 4.사물함 삭제 5.메인메뉴 6.페이지 변경");
		
		//client 입력값
		String inputData = Validator.chooseRangeNum(1, 7);
		
		//사물함 조회, 사물함 수정 시 사물함 번호 혹은 회원 아이디로 정보 조회
		if ("1".equals(inputData) || "3".equals(inputData)) {
			//질문
			System.out.println("1[사물함 번호] | 2[회원 아이디] 선택하세요.");
			
			//조회 유형 (1 사물함 번호, 2 회원 번호)
			String selectType  = Validator.chooseRangeNum(1, 3);
			
			//조회 데이터 변수
			String selectValue = "";
			
			//사물함 번호로 조회
			if ("1".equals(selectType)) {
				//질문
				System.out.println("사물함 번호를 작성해주세요. [1 ~ 99]");
				
				//선택한 사물함 번호
				selectValue = Validator.chooseRangeNum(1, 100);
				
			}
			//회원 아이디로 조회
			else {
				do {
					//질문
					System.out.println("아이디를 작성해주세요.");
					
					//데이터 입력
					selectValue = ViewCore.scanner.nextLine().trim();
					
				} while("".equals(selectValue));
			}
			
			//사물함 조회 유형 담기
			clientDatas.put("selectType", selectType);
			
			//조회 데이터 정보를 map에 담기
			clientDatas.put("selectValue", selectValue);
			
			//요청할 객체에 담기
			requestData.put("clientDatas", clientDatas);
			
		} 
		//사물함 등록, 삭제 시 사물함 번호로 정보 조회
		else if ("2".equals(inputData) || "4".equals(inputData)) {
			//질문
			System.out.println("사물함 번호를 작성해주세요. [1 ~ 99]");
			
			//선택한 사물함 번호
			String lockerNumber = Validator.chooseRangeNum(1, 100);
			
			//조회 데이터 정보를 map에 담기
			clientDatas.put("lockerNumber", lockerNumber);
			
			//요청할 객체에 담기
			requestData.put("clientDatas", clientDatas);
			
		}
		//페이징 변화일 경우
		else if ("6".equals(inputData)) {
			//질문
			System.out.println("이동할 페이지번호를 작성해주세요.[1 ~ " + totalPage + "]");
			
			//client 입력값
			clientDatas.put("selectPage", Validator.chooseRangeNum(1, totalPage + 1));
		}
		
		//전송 객체 세팅
		requestData.put("url", urlMatch(inputData));
		
		//WAS에 요청
		webContainer.service(requestData);
	}
}
