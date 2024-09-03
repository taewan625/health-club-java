package memorydb;

import java.util.LinkedHashMap;
import java.util.Map;

import lck.lck1000.lck1000.vo.Lck1000VO;

/**
 * @Description 사물함 테이블
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.23.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class LCK1000 {
	// LCK1000 table
	private LinkedHashMap<Integer, Lck1000VO> lck1000 = new LinkedHashMap<>();
	
	//생성자
	private LCK1000() {
		//사물함 데이터 세팅
		for (int i = 1; i < 100; i++) {
			lck1000.put(i, new Lck1000VO(i, null, null, null));
		}
	}
	
	//싱글톤 내부 클래스
	private static class LCK1000Holder {
		private static final LCK1000 INSTANCE = new LCK1000();
	}
	
	//싱글톤
	public static LCK1000 getInstance() {
		return LCK1000Holder.INSTANCE;
	}
	
	//테이블 조회 메서드
	public Map<Integer, Lck1000VO> getLck1000() {
		return lck1000;
	}
}
