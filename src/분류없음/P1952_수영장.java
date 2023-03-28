package 분류없음;

import java.util.Scanner;

public class P1952_수영장 {
	static int[] cost;
	static int[] plan;
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			cost = new int[4]; // 이용 요금
			// 이용 요금 입력받기. 순서대로 1일, 1달, 3달, 1년
			for (int i = 0; i < 4; i++) {
				cost[i] = sc.nextInt();
			}

			plan = new int[12]; // 이용 계획
			// 이용 계획 입력받기
			for (int i = 0; i < 12; i++) {
				plan[i] = sc.nextInt();
			}

			// 가장 적은 비용으로 수영장을 이용한 경우의 비용
			// 1년권만 사용한 경우로 초기화
			min = cost[3];

			BT(0, 0);

			System.out.println("#" + tc + " " + min);

		} // testcase
	} // main

	private static void BT(int month, int c) {
		// 기저 조건
		if (c > min) {
			return;
		}
		if (month >= 12) {
			min = Math.min(min, c);
			return;
		}
		// 유도 조건
		BT(month + 1, c + cost[0] * plan[month]); // 1일권
		BT(month + 1, c + cost[1]); // 1달권
		BT(month + 3, c + cost[2]); // 3달권
	}

}
