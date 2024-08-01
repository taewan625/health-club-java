package usr.usr1000.usr1000.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.propertiesconvert.MessageSource;

import memorydb.USR1000;
import memorydb.domain.Availability;
import memorydb.domain.Status;
import memorydb.domain.User;
import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Class Name : Usr1000DAO.java
 * @Description 회원 data를 조회, 등록 ,수정, 삭제 하는 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000DAO {
	public static Properties message = MessageSource.properties;
	// db table
	private Map<Long, User> userStore;
	// db index
	private long index = 10L;

	/**
	 * @Class Name Usr1000DAOHolder 내부 클래스
	 * @Description 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.23.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class Usr1000DAOHolder {
		private static final Usr1000DAO INSTANCE = new Usr1000DAO();
		
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param USR1000
	 *            db
	 * @return void
	 * @throws Exception
	 */
	public void setDBTable(USR1000 db) throws Exception {
		
		try {
			
			this.userStore = USR1000.getInstance().userStore;
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return Usr1000DAO
	 * @throws Exception
	 */
	public static Usr1000DAO getInstance() throws Exception {
		return Usr1000DAOHolder.INSTANCE;
	}

	/**
	 * Func : userList 반환 메서드
	 * 
	 * @desc 회원 list를 반환한다.
	 * @param
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	public List<Usr1000VO> selectUsr1000List() throws Exception {
		try {
			
			List<Usr1000VO> users = new ArrayList<>();

			// 삭제 상태로 된 유저를 제외하고 보여주는 메서드
			for (User user : userStore.values()) {
				users.add(new Usr1000VO(user));
			}
			
			return users;
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원 정보를 반환하는 메서드
	 * 
	 * @desc 회원 정보를 반환, 반환 전 isContainsUsr10000()을 이용해서 회원 id 존재 여부 검증 필수
	 * @param String
	 *            id
	 * @return Usr1000VO
	 * @throws Exception
	 */

	public Usr1000VO selectUsr1000(String id) throws Exception {
		try {
			User selectUser = selectUser(id);
			
			if (selectUser == null) {
				return null;
			}
			
			return new Usr1000VO(selectUser);
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 조회,수정 시 회원 정보의 상태를 업데이트 하는 메서드
	 * 
	 * @desc 회원 상태를 업데이트, 사용하기전 isContainsUsr10000()을 이용해서 회원 id 존재 여부 검증 필수
	 * @param String
	 *            id, Status userStatus
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000Status(String id, Status userStatus) throws Exception {
		try {
			
			User user = selectUser(id);
			
			user.setStatus(userStatus);
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원등록 메서드
	 * 
	 * @desc 유효성 검사가 완료된 회원 등록
	 * @param Usr1000VO
	 *            userVO
	 * @return void
	 * @throws Exception
	 */
	public void createUsr1000(Usr1000VO userVO) throws Exception {
		try {
			
			userStore.put(++index, new User.Builder(userVO.getId(), userVO.getName(), userVO.getGender(), userVO.getPhoneNumber(), userVO.getAddress()).build());
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원수정 메서드
	 * 
	 * @desc 회원 수정 데이터 db로 전달
	 * @param String
	 *            id, Usr1000VO userVO
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000(String id, Usr1000VO userVO) throws Exception {
		try {
			// id로 회원 호출
			User user = selectUser(id);

			// updateUserDatas 값으로 수정할 목록들, 값이 존재하지 않을 시 빈문자열
			String name = userVO.getName();
			String phoneNumber = userVO.getPhoneNumber();
			String address = userVO.getAddress();
			String description = userVO.getDescription();

			// userVO 의 이름, 전화번호, 주소, 특이사항만 변경 - 변경이 안된 경우는 빈문자열로 처리됨
			if (!name.isEmpty()) {
				user.setName(name);
			}
			
			if (!phoneNumber.isEmpty()) {
				user.setPhoneNumber(phoneNumber);
			}
			
			if (!address.isEmpty()) {
				user.setAddress(address);
			}
			
			if (!description.isEmpty()) {
				user.setDescription(description);
			}
			// 수정 후, 수정 시간 변경
			user.setEdit(LocalDateTime.now());
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원 삭제 메서드
	 * 
	 * @desc 회원 삭제 시,삭제여부, 사용여부만 업데이트
	 * @param String
	 *            id
	 * @return void
	 * @throws Exception
	 */
	public void deleteUsr1000(String id) throws Exception {
		try {
			
			User deleteUser = selectUser(id);
			
			deleteUser.setUse(Availability.NO);
			deleteUser.setDelete(Availability.YES);
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원 존재여부 확인 메서드
	 * 
	 * @desc 회원이 존재하는지 확인한다. 삭제된 회원 아이디도 포함됨에 주의하자
	 * @param String
	 *            id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isContainsUsr1000(String id) throws Exception {
		try {
			
			for (User user : userStore.values()) {
				
				if (user.getId().equals(id)) {
					return true;
				}
				
			}
			
			return false;
			
		} catch (Exception e) {
			
			System.out.println("usr/DAO/isContainsUsr1000() Error : " + e.getMessage());
			throw new Exception(e);
			
		}
	}

	/**
	 * Func : 회원 id로 회원을 조회하는 메서드
	 * 
	 * @desc 회원 id로 회원을 조회
	 * @param String
	 *            id
	 * @return User
	 * @throws Exception
	 */
	private User selectUser(String id) throws Exception {
		
		try {
			
			for (User user : userStore.values()) {
				if (user.getId().equals(id)) {
					return user;
				}
			}
			
			return null;
			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
	}
}
