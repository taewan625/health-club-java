package sta.sta1000.sta1000.vo;

import java.util.List;

import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Sta1000VO.java
 * @Description 회원 통계 정보를 받음
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000VO {
	// 회원 수
	private int totalUserNumber;
	// 임박 회원 수
	private int imminentUserNumber;
	// 만료 회원 수
	private int expireUserNumber;
	// 전체 회원 목록
	private List<Usr1000VO> totalUsers;
	// 임박 회원 리스트
	private List<Usr1000VO> imminentUsers;
	// 만료 회원 리스트
	private List<Usr1000VO> expireatoinUsers;

	// 생성자
	public Sta1000VO(int totalUserNumber, int imminentUserNumber, int expireUserNumber, List<Usr1000VO> totalUsers,
			List<Usr1000VO> imminentUsers, List<Usr1000VO> expireatoinUsers) {
		super();
		this.totalUserNumber = totalUserNumber;
		this.imminentUserNumber = imminentUserNumber;
		this.expireUserNumber = expireUserNumber;
		this.totalUsers = totalUsers;
		this.imminentUsers = imminentUsers;
		this.expireatoinUsers = expireatoinUsers;
	}

	// getter
	public int getTotalUserNumber() {
		return totalUserNumber;
	}

	public int getImminentUserNumber() {
		return imminentUserNumber;
	}

	public int getExpireUserNumber() {
		return expireUserNumber;
	}

	public List<Usr1000VO> getTotalUsers() {
		return totalUsers;
	}

	public List<Usr1000VO> getImminentUsers() {
		return imminentUsers;
	}

	public List<Usr1000VO> getExpireatoinUsers() {
		return expireatoinUsers;
	}

}
