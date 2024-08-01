package lck.lck1000.lck1000.vo;

import java.time.LocalDate;

/**
 * @Class Name : Lck1000VO.java
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000VO {
	// 에러 코드
	private String errCode = "1";
	// 에러 문구
	private String errMsg = "";

	// 회원 번호
	private String userId = "";
	// 사물함 번호
	private int lockerNum;
	// 만료날짜
	private LocalDate expireDate;

	// 생성자
	public Lck1000VO() {
	}

	public Lck1000VO(LocalDate exLocalDate) {
		this.expireDate = exLocalDate;
	}

	public Lck1000VO(int lockerNum) {
		this.lockerNum = lockerNum;
	}

	public Lck1000VO(String userId, int lockerNum) {
		super();
		this.userId = userId;
		this.lockerNum = lockerNum;
	}

	public Lck1000VO(String userId, int lockerNum, LocalDate expireDate) {
		super();
		this.userId = userId;
		this.lockerNum = lockerNum;
		this.expireDate = expireDate;
	}

	public Lck1000VO(String userId, int lockerNum, int expireDate) {
		super();
		this.userId = userId;
		this.lockerNum = lockerNum;
		this.expireDate = LocalDate.now().plusDays(expireDate);
	}

	// getter
	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public String getUserId() {
		return userId;
	}

	public int getLockerNum() {
		return lockerNum;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	// setter
	public void setError(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		String expireDate = "";
		if (this.expireDate != null) {
			expireDate = this.expireDate.toString();
		}
		return "사물함 상세 정보 [회원아이디=" + userId + ", 사물함 번호=" + lockerNum + ", 만료일=" + expireDate + "]";
	}

}