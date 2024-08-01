package memorydb.domain;


/**
 * @Class Name : Status.java
 * @Description 상태 이넘 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public enum Status {
	EFFECTIVE("정상"), EXPIRATION("만료"), IMMINENT("임박");

	private final String name;

	Status(String name) {
		this.name = name;
	}

	// getter
	public String getName() {
		return name;
	}
}
