package memorydb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import usr.usr1000.usr1000.vo.Usr1000VO;

/**
 * @Description 회원 테이블
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class USR1000 {
	//회원 테이블 인스턴스
	private LinkedHashMap<String, Usr1000VO> usr1000 = new LinkedHashMap<>();
	
	//생성자
	private USR1000() {
		//회원 데이터 세팅
		usr1000.put("id1", new Usr1000VO("id1", "user1", "m", "010-1234-1234", "adress1", "01", "정상", LocalDate.now().minusDays(3),LocalDate.now().minusDays(1), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id2", new Usr1000VO("id2", "user2", "m", "010-1234-1234", "adress1", "01", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id3", new Usr1000VO("id3", "user3", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id4", new Usr1000VO("id4", "user4", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id5", new Usr1000VO("id5", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id6", new Usr1000VO("id6", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id7", new Usr1000VO("id7", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id8", new Usr1000VO("id8", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id9", new Usr1000VO("id9", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id10", new Usr1000VO("id10", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id11", new Usr1000VO("id11", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id12", new Usr1000VO("id12", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id13", new Usr1000VO("id13", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id14", new Usr1000VO("id14", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id15", new Usr1000VO("id15", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id16", new Usr1000VO("id16", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
		usr1000.put("id17", new Usr1000VO("id17", "user5", "m", "010-1234-1234", "adress1", "02", "정상", LocalDate.now(),LocalDate.now(), "사용", LocalDateTime.now(), LocalDateTime.now()));
	}
	
	//싱글톤 내부 클래스
	private static class USR1000Holder {
		private static final USR1000 INSTANCE = new USR1000();
	}
	
	//싱글톤
	public static USR1000 getInstance() {
		return USR1000Holder.INSTANCE;
	}
	
	//테이블 조회 메서드
	public Map<String, Usr1000VO> getUsr1000() {
		return usr1000;
	}
}
