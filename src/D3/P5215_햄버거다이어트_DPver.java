package D3;

import java.util.Scanner;

public class P5215_햄버거다이어트_DPver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			int N = sc.nextInt(); // 재료 개수
			int L = sc.nextInt(); // 제한 칼로리
			int[] T = new int[N + 1]; // 점수
			int[] K = new int[N + 1]; // 칼로리
			int Kmin = Integer.MAX_VALUE;
			// 재료별 맛, 점수 입력받기
			for (int i = 1; i <= N; i++) {
				T[i] = sc.nextInt();
				K[i] = sc.nextInt();
				Kmin = Math.min(K[i], Kmin);
			}

			int[][] dp = new int[N + 1][L + 1]; // dp로 저장하기 (행 : 재료 개수, 열 : 칼로리)
			for (int i = 1; i <= N; i++) { // i번째 재료에 대해
				for (int j = 0; j <= L; j++) { // 칼로리가 j일 때 가능한 최고 점수 구하기
					if (K[i] <= j) {
						// i번째 재료의 칼로리가 현재 칼로리보다 낮으면
						// 1. i번째 재료를 쓰지 않는 경우 (이전까지 구한 최대값)
						// 2. i번째 재료를 쓰는 경우 (이전 단계에서 현재 재료의 칼로리만큼 뺀 점수 + 현재 재료의 점수)
						// 둘 중 더 큰 값으로 갱신!
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - K[i]] + T[i]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			System.out.println("#" + tc + " " + dp[N][L]);
		} // testcase
	}
}
