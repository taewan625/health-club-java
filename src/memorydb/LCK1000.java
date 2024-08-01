package memorydb;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import memorydb.domain.Locker;

/**
 * @Class Name : LCK1000.java
 * @Description 사물함 테이블
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class LCK1000 {
	// LCK1000 table
	public final Map<Integer, Locker> lockerStore = new HashMap<>();

	/**
	 * Func :기본 db 데이터가 들어간 생성자
	 * 
	 * @desc : 기본 db 데이터를 넣어주고 기본 데이터 초기화 실패시 RuntimeException 던짐 @param @return
	 *       LCK1000 @throws
	 */
	private LCK1000() {
		try {
			initLockerData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Class Name : LCK1000Holder 내부 클래스
	 * @Description : 싱글톤 객체 생성 - 다중 접근 null 방지 및 동기화 성능 향상 목적 클래스
	 * @version 1.0
	 * @author 권태완
	 * @Since 2023.12.23.
	 * @Modification Information
	 * @see Copyright (C) All right reserved.
	 */
	private static class LCK1000Holder {
		private static final LCK1000 INSTANCE = new LCK1000();
	}

	/**
	 * Func :기본 db 데이터 넣기
	 * 
	 * @desc : 기본 db 데이터를 넣어준다.
	 * @param
	 * @return
	 * @throws Exception
	 */
	private void initLockerData() throws Exception {
		lockerStore.put(1, new Locker(1, "id1", LocalDate.now().minusDays(1)));
		lockerStore.put(2, new Locker(2, "id2", LocalDate.now().plusDays(2)));
		lockerStore.put(3, new Locker(3, "id3", LocalDate.now().minusDays(6)));
		lockerStore.put(4, new Locker(4, "id4", LocalDate.now().minusDays(8)));
		lockerStore.put(5, new Locker(5, "id5", LocalDate.now().plusDays(6)));
		for (int i = 6; i < 100; i++) {
			lockerStore.put(i, new Locker(i));
		}
	}

	/**
	 * Func : 싱글톤 객체 반환 메서드
	 * 
	 * @desc 싱글톤 객체 반환
	 * @param
	 * @return LCK1000
	 * @throws Exception
	 */
	public static LCK1000 getInstance() throws Exception {
		return LCK1000Holder.INSTANCE;
	}

}
