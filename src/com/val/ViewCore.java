package com.val;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import com.web.WebContainer;


/**
 * @Description 화면단에서 사용하는 공통 코어 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.09.13.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class ViewCore {
	public static final Scanner scanner = new Scanner(System.in);
	
	/**
	 * @desc 등록 && 수정 시 사용자 동작에 맞춰 값 반환 - 값 검증, 스킵, 중단
	 * @param String questionPromp
	 * @param String warningPromp
	 * @param Predicate<String> validate
	 * @param boolean isOptional
	 * @param String url
	 * @return String
	 * @throws Exception
	 */
	public static String formAnswer(String questionPromp, String warningPromp, Predicate<String> validate, boolean isOptional, String url) throws Exception {
		//중복 혹은 유효성에 문제가 존재하는지 여부 확인 변수
		boolean isCollect = false;
		
		//반환 될 사용자 입력값 변수
		String answer;
		
		do {
			//질문
			System.out.println(questionPromp);
			
			//사용자 입력값
			answer = scanner.nextLine().trim();
			
			//해당 작업 스킵하는 경우
			if (isOptional && "s".equalsIgnoreCase(answer)) {
				//문자열 통일화
				answer = "s";
				
				//루프문 탈출
				break;
			}
			//n을 작성할 경우 해당 작업 상위 메뉴로 이동
			else if ("n".equalsIgnoreCase(answer)) {
				//문자열 통일화
				answer = "n";
				
				//WAS에 요청
				WebContainer.getInstance().service(Map.of("url", url));
				
			}
			//해당 작업을 진행하는 경우
			else {
				//제약조건 검사
				isCollect = validate.test(answer);
				
				//입력값에 오류가 존재할 경우 경고문 
				if (!isCollect) {
					System.out.println(warningPromp);
				}
			}
			
		} while(isCollect);
		
		return answer;
	}	

}
