package com.val;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @Description 검증기
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Validator {
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * @desc 이름 검증 메서드
	 * @param String clientAnswer
	 * @return boolean
	 */
	public static boolean isValidatedName(String clientAnswer) {
		//빈문자열이 아니고 한글로만 이뤄지거나 영어로만 이뤄진 경우 통과
		return !clientAnswer.isEmpty() && (Pattern.matches("^[ㄱ-ㅣ가-힣]*$", clientAnswer) || Pattern.matches("^[a-zA-Z]*$", clientAnswer));
	}
	
	/**
	 * @desc 유효 범위 숫자 검증
	 * @param int from, int to
	 * @return String
	 * @throws Exception
	 */
	public static String chooseRangeNum(int from, int to) throws Exception {
		//유효성 검증 후 반환할 숫자를 담는 변수
		String inputData;
		
		//중복 혹은 유효성에 문제가 존재하는지 여부 확인 변수
		boolean isWrong = true;
		
		do {
			//사용자 입력 값
			inputData = scanner.nextLine().trim();
			
			//특정 범위 안에 존재하지 않는 경우
			try {
				//숫자로 값 치환
				int number = Integer.parseInt(inputData);
				
				//검증 작업 진행
				isWrong = (from <= number && number < to) ? false : true;
				
				//유효성을 통과하지 못한 경우
				if(isWrong) {
					//경고 문구 주기
					System.out.println("해당 범위의 번호가 아닙니다.");
				}
				
			}
			catch (NumberFormatException e) {
				//경고 문구 주기
				System.out.println("해당 범위의 번호가 아닙니다.");
			}
			
		} while (isWrong);
		
		return inputData;
	}
	
	/**
	 * @desc 유효 일자 범위 검증
	 * @param String clientAnswer, LocalDate minDate, LocalDate maxDate
	 * @return LocalDate
	 * @throws 
	 */
	public static boolean isValidatedDate(String clientAnswer, LocalDate minDate, LocalDate maxDate) {
		//유효성 결과 변수
		boolean result = false;
		
		try {
			//일자 정규식 통과 시 로직
			if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", clientAnswer)) {
				//타입 변환
				LocalDate choiseDate = LocalDate.parse(clientAnswer);
				
				//minDate가 존재하면 clientDate보다 이전이 아니고 && maxDate가 존재하면 clientDate보다 이후가 아닌 경우 유효성 통과 
				result = (minDate == null || !choiseDate.isBefore(minDate)) && (maxDate == null || !choiseDate.isAfter(maxDate));
			}
			
		} catch (DateTimeException e) {
			result = false;
		}
		
		return result;
	}
}
