package 분류없음;

import java.util.Scanner;

public class P1952_수영장_DPver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int[] cost = new int[4]; // 이용권 요금 (1일, 1달, 3달, 1년)
			for (int i = 0; i < 4; i++) {
				cost[i] = sc.nextInt();
			}

			int[] plan = new int[13]; // 12개월 이용 계획
			int[] money = new int[13];
			for (int i = 1; i <= 12; i++) {
				plan[i] = sc.nextInt();
				money[i] = cost[3]; // 1년권 쓰는 경우
			}

			for (int j = 1; j <= 12; j++) { // 월별로 탐색
				money[j] = Math.min(money[j], money[j - 1] + cost[0] * plan[j]); // 1일권 쓰는 경우
				money[j] = Math.min(money[j], money[j - 1] + cost[1]); // 1달권 쓰는 경우
				if (j >= 3)
					money[j] = Math.min(money[j - 3] + cost[2], money[j]); // 3달권 쓰는 경우
			}

			System.out.println("#" + tc + " " + money[12]);
		}
	}
}
