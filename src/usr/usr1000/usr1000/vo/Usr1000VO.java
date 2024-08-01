package usr.usr1000.usr1000.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import memorydb.domain.Availability;
import memorydb.domain.Gender;
import memorydb.domain.Status;
import memorydb.domain.User;

/**
 * @Class Name : Usr1000VO.java
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000VO {
	// 에러 코드
	private String errCode = "1";
	// 에러 문구
	private String errMsg = "";
	// 회원 id
	private String id;
	// 회원명
	private String name;
	// 회원 성별
	private Gender gender;
	// 회원 연락처
	private String phoneNumber;
	// 회원 주소
	private String address;
	// 회원 설명
	private String description;
	// 회원 상태(정상, 만료, 임박)
	private Status status;
	// 가입 일자
	private LocalDate joinDate;
	// 만료 일자
	private LocalDate expireDate;
	// 사용 유무
	private Availability use;
	// 삭제 유무
	private Availability delete;
	// 등록 일시
	private LocalDateTime join;
	// 수정 일시
	private LocalDateTime edit;

	// 생성자
	public Usr1000VO() {
	}

	public Usr1000VO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Usr1000VO(Availability use, LocalDate expireDate) {
		super();
		this.use = use;
		this.expireDate = expireDate;
	}

	public Usr1000VO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.gender = user.getGender();
		this.phoneNumber = user.getPhoneNumber();
		this.address = user.getAddress();
		this.description = user.getDescription();
		this.status = user.getStatus();
		this.joinDate = user.getJoinDate();
		this.expireDate = user.getExpireDate();
		this.use = user.getUse();
		this.delete = user.getDelete();
		this.join = user.getJoin();
		this.edit = user.getEdit();
	}

	public Usr1000VO(String id, String name, Gender gender, String phoneNumber, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Usr1000VO(String id, String name, String phoneNumber, String address, String description) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.description = description;
	}

	// getter

	public Availability getUse() {
		return use;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public Availability getDelete() {
		return delete;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
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
	public void setError(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	// toString

	// 회원 목록용 회원 정보
	public String toStringUsrList() {
		String status = "";
		if (this.status != null) {
			status = this.status.getName();
		}
		return "회원 정보 summary [아이디=" + id + ", 이름=" + name + ", 전화번호=" + phoneNumber + ", 상태=" + status + ", 삭제유무="
				+ delete + "]";
	}

	// 회원등록폼용 회원 정보
	public String toStringUsrForm() {
		String gender = "";

		if (this.gender != null) {
			gender = this.gender.getName();
		}
		return " 회원 정보 [아이디=" + id + ", 이름=" + name + ", 성별=" + gender + ", 전화번호=" + phoneNumber + ", 주소=" + address
				+ "]";
	}

	// 통계용 회원 정보
	public String toStringStatistic() {
		String status = "";
		String gender = "";

		if (this.status != null) {
			status = this.status.getName();
		}
		if (this.gender != null) {
			gender = this.gender.getName();
		}
		return " 회원 정보 [아이디=" + id + ", 이름=" + name + ", 성별=" + gender + ", 전화번호=" + phoneNumber + ", 주소=" + address
				+ ", 상태=" + status + ", 삭제유무=" + delete + "]";
	}

	@Override
	// npe 조심
	public String toString() {
		String status = "";
		String gender = "";

		if (this.status != null) {
			status = this.status.getName();
		}
		if (this.gender != null) {
			gender = this.gender.getName();
		}

		// 사용할 날짜 및 시간 형식 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

		// LocalDateTime 객체를 문자열로 변환
		String join = formatter.format(this.join);
		String edit = formatter.format(this.edit);

		return "회원 상세 정보 [아이디=" + id + ", 이름=" + name + ", 성별=" + gender + ", 전화번호=" + phoneNumber + ", 주소=" + address
				+ ", 비고=" + description + ",\n상태=" + status + ", 가입일자=" + joinDate + ", 만료일자=" + expireDate + ", 사용유무="
				+ use + ", 삭제유무=" + delete + ", 등록일시=" + join + ", 수정일시=" + edit + "]";
	}

}
