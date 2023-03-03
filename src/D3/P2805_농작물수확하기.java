package D3;

import java.util.Scanner;

public class P2805_농작물수확하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 농장 크기. 항상 홀수!!
			char[][] farm = new char[N][N]; // 농장 N*N
			// 농작물 가치 입력받기
			for (int r = 0; r < N; r++) {
				String temp = sc.next();
				farm[r] = temp.toCharArray();
//				System.out.println();
			}

			int money = 0; // 수익
			int center = N / 2;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if ((Math.abs(r - center) + Math.abs(c - center)) <= center) {
						money += (farm[r][c] - '0');
					}
				}
			}

			System.out.printf("#%d %d\n", tc, money);
		} // testcase
	}
}
