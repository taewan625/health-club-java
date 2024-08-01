package memorydb;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import memorydb.domain.Gender;
import memorydb.domain.User;

/**
 * @Class Name : USR1000.java
 * @Description 회원 테이블
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class USR1000 {
	// LCK1000 table
	public final Map<Long, User> userStore = new HashMap<>();

	/**
	 * Func :기본 db 데이터가 들어간 생성자
	 * 
	 * @desc 기본 db 데이터를 넣어주고 기본 데이터 초기화 실패시 RuntimeException
	 *       던짐 @param @return @throws
	 */
	private USR1000() {
		try {
			initUserData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Class Name : USR1000Holder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.23.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class USR1000Holder {
		private static final USR1000 INSTANCE = new USR1000();
	}

	/**
	 * Func :기본 db 데이터 넣기
	 * 
	 * @desc : 기본 db 데이터를 넣어준다.
	 * @param
	 * @return
	 * @throws Exception
	 */
	private void initUserData() throws Exception {
		userStore.put(0L, new User.Builder("id" + 1, "임성준", Gender.BOY, "010-1234-5678", "양천구").description("잠수회원")
				.expireDate(LocalDate.now().minusDays(1)).build());
		userStore.put(1L, new User.Builder("id" + 2, "송지훈", Gender.BOY, "010-9876-5432", "종로구").description("잠수회원")
				.expireDate(LocalDate.now().plusDays(4)).build());
		userStore.put(2L, new User.Builder("id" + 3, "김예린", Gender.GIRL, "010-2468-1354", "중구").description("잠수회원")
				.expireDate(LocalDate.now().minusDays(6)).build());
		userStore.put(3L, new User.Builder("id" + 4, "이준호", Gender.BOY, "010-5555-7777", "용산구").description("잠수회원")
				.expireDate(LocalDate.now().minusDays(8)).build());
		userStore.put(4L,
				new User.Builder("id" + 5, "박서연", Gender.GIRL, "010-8888-9999", "성동구").description("잠수회원").build());
		userStore.put(5L,
				new User.Builder("id" + 6, "정민지", Gender.GIRL, "010-4321-8765", "광진구").description("잠수회원").build());
		userStore.put(6L,
				new User.Builder("id" + 7, "최성호", Gender.BOY, "010-9999-8888", "동대문구").description("잠수회원").build());
		userStore.put(7L,
				new User.Builder("id" + 8, "강지영", Gender.GIRL, "010-7777-5555", "양천구").description("잠수회원").build());
		userStore.put(8L,
				new User.Builder("id" + 9, "황재원", Gender.BOY, "010-1357-2468", "양천구").description("잠수회원").build());
		userStore.put(9L,
				new User.Builder("id" + 10, "윤수빈", Gender.GIRL, "010-6543-2109", "중랑구").description("잠수회원").build());
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc : 싱글톤 객체 반환
	 * @param
	 * @return USR1000
	 * @throws Exception
	 */
	public static USR1000 getInstance() throws Exception {
		return USR1000Holder.INSTANCE;
	}

}
