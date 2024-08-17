import java.util.HashMap;
import java.util.Map;

import com.web.MessageSource;
import com.web.WebContainer;

/**
 * @desc 헬스프로그램을 시작하는 main Class로 webContainer를 올린다.
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.21.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class StartWeb {

	/**
	 * @desc main 메소드로 webContaine를 올린다.
	 * @param String[] args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		try {
			//webContainer 생성
			WebContainer webContainer =  WebContainer.getInstance();
			
			//초기 메인 메뉴 강제 접속 요청 map 생
			Map<String, Object> requestData = new HashMap<>();
			
			//메인 화면 url 데이터 넣기
			requestData.put("url", MessageSource.getMessage("message.menu"));
			
			//main 화면 접근
			webContainer.service(requestData);
			
		} catch (Exception e) {
			System.out.println("웹 컨테이너 초기화 시, 문제가 발생했습니다.\n" + e.getMessage());
		}
	}
}
