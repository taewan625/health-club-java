package lck.lck1000.lck1000.vo;

import java.time.LocalDate;

/**
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.26.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Lck1000VO {
	//사물함 번호
	private int lockerNum;
	//회원 번호
	private String userId;
	//시작일자
	private LocalDate startDate;
	//만료일자
	private LocalDate endDate;
	
	//생성자
	public Lck1000VO() {};
	
	//생성자
	public Lck1000VO(int lockerNum, String userId, LocalDate startDate, LocalDate endDate) {
		super();
		this.lockerNum = lockerNum;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	//생성자 - 깊은 복사
	public Lck1000VO(Lck1000VO locker) {
		this.lockerNum = locker.getLockerNum();
		this.userId = locker.getUserId();
		this.startDate = locker.getStartDate();
		this.endDate = locker.getEndDate();
	}
	
	/* getter & setter */
	public int getLockerNum() {
		return lockerNum;
	}
	public void setLockerNum(int lockerNum) {
		this.lockerNum = lockerNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Lck1000VO [lockerNum=" + lockerNum + ", userId=" + userId + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
}