package B형특강03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[][] dp = new int[101][1001];
		int[] w = new int[101];
		int[] v = new int[101];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 물건 개수 1~100
			int V = Integer.parseInt(st.nextToken()); // 가방 부피 1~1000
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				w[i] = Integer.parseInt(st.nextToken()); // 부피
				v[i] = Integer.parseInt(st.nextToken()); // 가치
			}

			for (int r = 1; r <= N; r++) { // r개의 물건을 사용할 수 있음
				for (int c = 1; c <= V; c++) { // 가방의 부피는 c
					// r번재 물건 넣지 않은 경우
					dp[r][c] = dp[r - 1][c];
					// r번째 물건 넣은 경우와 비교
					if (w[r] <= c) {
						if (dp[r][c] < dp[r - 1][c - w[r]] + v[r]) {
							dp[r][c] = dp[r - 1][c - w[r]] + v[r];
						}
					}
				}
			}
			sb.append(dp[N][V] + "\n");
		} // testcase
		System.out.println(sb);
	}
}
