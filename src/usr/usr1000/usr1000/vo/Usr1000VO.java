package usr.usr1000.usr1000.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000VO {
	// 회원 id
	private String id;
	// 회원명
	private String name;
	// 회원 성별 ("m" : 남자, "f" : 여자)
	private String gender;
	// 회원 연락처
	private String phoneNumber;
	// 회원 주소
	private String address;
	// 회원 설명
	private String description;
	// 회원 상태 ("01" : 정상, "02" : 만료)
	private String status;
	// 가입 일자
	private LocalDate joinDate;
	// 만료 일자
	private LocalDate expireDate;
	// 사용 유무
	private String use;
	// 삭제 유무
	private String delete;
	// 등록 일시
	private LocalDateTime joinDateTime;
	// 수정 일시
	private LocalDateTime editDateTime;
	
	//생성자
	public Usr1000VO() {};
	
	//생성자
	public Usr1000VO(String id, String name, String gender, String phoneNumber, String address, String description, String status, LocalDate joinDate, LocalDate expireDate, String use, String delete, LocalDateTime joinDateTime, LocalDateTime editDateTime) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.description = description;
		this.status = status;
		this.joinDate = joinDate;
		this.expireDate = expireDate;
		this.use = use;
		this.delete = delete;
		this.joinDateTime = joinDateTime;
		this.editDateTime = editDateTime;
	}
	
	/* getter & setter */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public LocalDateTime getJoinDateTime() {
		return joinDateTime;
	}
	public void setJoinDateTime(LocalDateTime joinDateTime) {
		this.joinDateTime = joinDateTime;
	}
	public LocalDateTime getEditDateTime() {
		return editDateTime;
	}
	public void setEditDateTime(LocalDateTime editDateTime) {
		this.editDateTime = editDateTime;
	}
	
	@Override
	public String toString() {
		return "Usr1000VO [id=" + id + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", description=" + description + ", status=" + status + ", joinDate="
				+ joinDate + ", expireDate=" + expireDate + ", use=" + use + ", delete=" + delete + ", joinDateTime="
				+ joinDateTime + ", editDateTime=" + editDateTime + "]";
	}
}
