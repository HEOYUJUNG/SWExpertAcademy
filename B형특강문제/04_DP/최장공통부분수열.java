package B형특강03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최장공통부분수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[][] dp = new int[1001][1001];
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			char[] A = st.nextToken().toCharArray();
			char[] B = st.nextToken().toCharArray();

			for (int r = 1; r <= A.length; r++) {
				for (int c = 1; c <= B.length; c++) {
					if (A[r - 1] == B[c - 1]) {
						dp[r][c] = dp[r - 1][c - 1] + 1;
					} else {
						dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
					}
				}
			}
			sb.append(dp[A.length][B.length] + "\n");
		}
		System.out.println(sb);
	}
}
