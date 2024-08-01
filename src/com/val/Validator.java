package com.val;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import memorydb.domain.Availability;
import usr.usr1000.usr1000.vo.Usr1000VO;

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
	 * Func : 한글 정규식 메서드
	 * 
	 * @desc 한글만 허용하는 정규식 메서드
	 * @param String
	 *            clientAnswer
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
	 * Func : 영어 정규식 메서드
	 * 
	 * @desc 영어만 허용하는 정규식 메서드
	 * @param String
	 *            clientAnswer
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
	 * Func : 자연수 정규식 메서드
	 * 
	 * @desc 자연수 인지 확인하는 정규식 메서드
	 * @param String
	 *            clientAnswer
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isDecimal(String clientAnswer) throws Exception {
		try {
			// 자연수 정규식
			if ((clientAnswer.matches("^[1-9]\\d*$"))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Func : 허용 범위 자연수 확인 메서드
	 * 
	 * @desc start, end
	 * @param String
	 *            clientAnswer
	 * @return boolean
	 * @throws Exception
	 */
	private static boolean rangeValue(int start, int end, String data) {
		int number = Integer.parseInt(data);
		if (start <= number && number < end) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Func : 유효한 범위의 숫자를 선택하는 검증 메서드
	 * 
	 * @desc 숫자인지, 유효 범위에 있는지 검증
	 * @param int
	 *            from, int to
	 * @return String
	 * @throws Exception
	 */
	public static String chooseRangeNum(int from, int to) throws Exception {
		String inputData = scanner.nextLine().trim();
		try {
			while (true) {
				try {
					if (!isDecimal(inputData) || !rangeValue(from, to, inputData)) {
						System.out.println("해당 범위의 번호가 아닙니다.");
						inputData = scanner.nextLine().trim();
					} else {
						return inputData;
					}
				} catch (NumberFormatException e) {
					System.out.println("해당 범위의 번호가 아닙니다.");
					inputData = scanner.nextLine().trim();
				}
			}
		} catch (Exception e) {
			System.out.println("Validator/chooseRangeNum() Error : " + e.getMessage());
			throw new Exception(e);
		}
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
	 * Func : 사용자 대답이 y,n인지 확인하는 메서드
	 * 
	 * @desc 사용자 대답이 y,n인지 확인
	 * @param String
	 *            clientAnswer
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isYN(String clientAnswer) throws Exception {
		try {
			// 대소문자 상관 없음
			String lowerCase = clientAnswer.toLowerCase();
			if ((lowerCase.equals("y") || lowerCase.equals("n"))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Func : 사물함 등록시 적용 가능한 회원인지 확인하는 메서드
	 * 
	 * @desc 사물함 등록시 적용 가능한 회원인지 확인 1. 사용 유무 2. 만료일이 남았는지 확인
	 * @param Usr1000VO
	 *            selectUsr
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isValidUser(Usr1000VO selectUsr) throws Exception {
		try {
			return selectUsr.getUse() == Availability.YES && !Validator.isExpire(selectUsr.getExpireDate());
		} catch (Exception e) {
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

	public static boolean isDuplicateId(String id, List<String> userIds) {
		try {
			for (String userId : userIds) {
				if (userId.equals(id)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

}
