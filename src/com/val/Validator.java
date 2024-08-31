package com.val;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @Class Name : Validator.java
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
	 * @desc 한글만 허용하는 정규식 메서드
	 * @param String clientAnswer
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isKorean(String clientAnswer) {
		try {
			// 정규식: 한글만 허용
			String regex = "^[ㄱ-ㅣ가-힣]*$";
			return Pattern.matches(regex, clientAnswer);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @desc 영어만 허용하는 정규식 메서드
	 * @param String clientAnswer
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isEnglish(String clientAnswer) {
		try {
			// 정규식: 영어만 허용
			String regex = "^[a-zA-Z]*$";
			return Pattern.matches(regex, clientAnswer);
		} catch (Exception e) {
			return false;
		}
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
	 * Func : 만기일 확인 메서드
	 * 
	 * @desc 금일 기준 만기일이 지나지 않았으면 true
	 * @param Usr1000VO
	 *            selectUsr
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isExpire(LocalDate expireDate) throws Exception {
		try {
			// date1.isBefore(date2) : date1 이 date2보다 이전이면 true
			if (expireDate == null) {
				return false;
			}
			return expireDate.isBefore(LocalDate.now());
		} catch (Exception e) {
			System.out.println("Validator/isExpire() Error : " + e.getMessage());
			return false;
		}
	}

	/**
	 * Func : 전화번호 정규식 메서드
	 * 
	 * @desc 사물함 등록시 적용 가능한 회원인지 확인 1. 사용 유무 2. 만료일이 남았는지 확인
	 * @param Usr1000VO
	 *            selectUsr
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isValidPhoneNumber(String phoneNumber) throws Exception {
		try {
			// 전화번호 형식을 나타내는 정규표현식
			String regex = "^010-\\d{4}-\\d{4}$";
			// 정규표현식과 문자열을 비교하여 일치하는지 확인
			return Pattern.matches(regex, phoneNumber);
		} catch (Exception e) {
			return false;
		}
	}
}
