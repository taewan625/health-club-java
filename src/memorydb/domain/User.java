package memorydb.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Class Name : User.java
 * @Description user 객체
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class User {
	// 만료 기간 설정
	private static final int EXPIRE_DATE = 10;

	// 회원 id
	private final String id;
	// 회원명
	private String name;
	// 회원 성별
	private final Gender gender;
	// 회원 연락처
	private String phoneNumber;
	// 회원 주소
	private String address;
	// 회원 설명
	private String description;
	// 회원 상태(정상, 만료, 임박)
	private Status status = Status.EFFECTIVE;
	// 가입 일자
	private final LocalDate joinDate = LocalDate.now();
	// 만료 일자
	private final LocalDate expireDate;
	// 사용 유무
	private Availability use = Availability.YES;
	// 삭제 유무
	private Availability delete = Availability.NO;
	// 등록 일시
	private final LocalDateTime join = LocalDateTime.now();
	// 수정 일시
	private LocalDateTime edit = LocalDateTime.now();

	/**
	 * @Class Name : Builder 내부 클래스
	 * @Description builder pattern을 활용하여 넣어야 할 iv가 많을 경우 사용
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.23.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	public static class Builder {
		// 필수 매개변수 == 필수적으로 작성해야하는 값
		private final String id;
		private String name;
		private final Gender gender;
		private String phoneNumber;
		private String address;

		// 선택 매개변수1 - 기본값으로 초기화 혹은 직접 작성 가능
		private String description = "";
		private LocalDate expireDate = LocalDate.now().plusDays(EXPIRE_DATE);

		/**
		 * Func : 내부 클래스 생성시, 필수 값을 받는 생성자
		 * 
		 * @desc 내부 클래스 생성시, 필수 값을 받는 생성자 @param String id, String name, Gender gender,
		 *       String phoneNumber, String address @return Builder @throws
		 */
		public Builder(String id, String name, Gender gender, String phoneNumber, String address) {
			this.id = id;
			this.name = name;
			this.gender = gender;
			this.phoneNumber = phoneNumber;
			this.address = address;
		}

		/**
		 * Func 선택 작성 데이터 메서드
		 * 
		 * @desc 필수 값이 아닌 값도 초기화를 원할 때 사용 @param String description @return
		 *       Builder @throws
		 */
		public Builder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Func 선택 작성 데이터 메서드
		 * 
		 * @desc 필수 값이 아닌 값도 초기화를 원할 때 사용 @param LocalDate expireDate @return
		 *       Builder @throws
		 */
		public Builder expireDate(LocalDate expireDate) {
			this.expireDate = expireDate;
			return this;
		}

		/**
		 * Func User 객체 생성 메서드
		 * 
		 * @desc 빌더를 활용하여 user 객체를 생성한다. @param @return User @throws
		 */
		public User build() {
			return new User(this);
		}

	}

	/**
	 * Func 빌더를 활용한 생성자
	 * 
	 * @desc 빌더를 활용한 생성자 @param Builder builder
	 * @return User @throws
	 */
	private User(Builder builder) {
		id = builder.id;
		name = builder.name;
		gender = builder.gender;
		phoneNumber = builder.phoneNumber;
		address = builder.address;
		description = builder.description;
		expireDate = builder.expireDate;
	}

	// getter, setter

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public Availability getUse() {
		return use;
	}

	public Availability getDelete() {
		return delete;
	}

	public Gender getGender() {
		return gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public LocalDateTime getJoin() {
		return join;
	}

	public LocalDateTime getEdit() {
		return edit;
	}

	// setter

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUse(Availability use) {
		this.use = use;
	}

	public void setDelete(Availability delete) {
		this.delete = delete;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setEdit(LocalDateTime edit) {
		this.edit = edit;
	}

	public void setExpireDate(LocalDateTime edit) {
		this.edit = edit;
	}
}
