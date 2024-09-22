package view.sta.sta1000.sta1000;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.web.Request;

import sta.sta1000.sta1000.vo.Sta1000VO;
import usr.usr1000.usr1000.vo.Usr1000VO;
import view.JavaHTML;

/**
 * @Description 통계 페이지
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.29.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Sta1000 implements JavaHTML {
	private Scanner scanner = new Scanner(System.in);

	@Override
	public void response(Request requeset) throws Exception {
		Map<String, ?> responseData = requeset.getResponseData();
		if (responseData.containsKey(ERROR_KEY)) {
			String error_message = (String) responseData.get(ERROR_KEY);
			System.out.println(error_message + "\n메인메뉴창으로 돌아갑니다.");
			return;
		}
		Sta1000VO userStatistic = (Sta1000VO) responseData.get("userStatistic");

		// 통계 값 출력
		showStatistic(userStatistic.getTotalUserNumber(), userStatistic.getImminentUserNumber(),
				userStatistic.getExpireUserNumber());

		// 통계 데이터 출력
		showStatistics(userStatistic.getImminentUsers(), userStatistic.getExpireatoinUsers(),
				userStatistic.getTotalUsers());

		System.out.println("메뉴로 돌아가실려면 아무 키나 누르면 돌아갑니다.");
		scanner.nextLine();
	}

	private void showStatistics(List<Usr1000VO> imminentUsers, List<Usr1000VO> expireatoinUsers,
			List<Usr1000VO> totalUsers) {
		System.out.println("임박 회원 정보 ");
		for (Usr1000VO user : imminentUsers) {
			System.out.println(user.toStringStatistic());
		}

		System.out.println("만료 회원 정보");
		for (Usr1000VO user : expireatoinUsers) {
			System.out.println(user.toStringStatistic());
		}

		System.out.println("회원 목록");
		for (Usr1000VO user : totalUsers) {
			System.out.println(user.toStringStatistic());
		}
	}

	private void showStatistic(int total, int imminent, int expire) {
		System.out.println("전체 회원수 : " + total + " 명");
		System.out.println("임박 회원수 : " + imminent + " 명");
		System.out.println("만료 회원수 : " + expire + " 명");
	}

}
