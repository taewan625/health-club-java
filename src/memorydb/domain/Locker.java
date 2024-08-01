package memorydb.domain;

import java.time.LocalDate;

/**
 * @Class Name : Locker.java
 * @Description Locker 객체
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Locker {

	// 사물함 번호
	private final int lockerNumber;
	// 회원 id
	private String userId;
	// 만료 일자
	private LocalDate expireDate ;

	// 생성자
	public Locker(int lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	// 생성자
	public Locker(int lockerNumber, String userId) {
		this(lockerNumber);
		this.userId = userId;
	}

	// 생성자
	public Locker(int lockerNumber, String userId, LocalDate expireDate) {
		this(lockerNumber);
		this.userId = userId;
		this.expireDate = expireDate;
	}

	// Getter, Setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public int getLockerNumber() {
		return lockerNumber;
	}

}
