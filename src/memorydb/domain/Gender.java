package memorydb.domain;
/**
 * @Class Name : Gender.java
 * @Description 성별 이넘 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public enum Gender {
	BOY("1", "남자"), GIRL("2", "여자");

	private final String genderNum;
	private final String name;

	// 생성자
	Gender(String genderNum, String name) {
		this.genderNum = genderNum;
		this.name = name;
	}

	// getter
	public String getName() {
		return name;
	}

	/**
	 * Func : 성별 선택 메서드
	 * 
	 * @desc 번호에 맞는 성별을 반환해준다.
	 * @param String
	 *            gender
	 * @return Gender
	 * @throws Exception
	 */
	public static Gender choose(String gender) throws Exception {
		for (Gender value : Gender.values()) {
			if (value.genderNum.equals(gender)) {
				return value;
			}
		}
		throw new IllegalArgumentException();
	}
}
