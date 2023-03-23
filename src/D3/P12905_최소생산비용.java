package D3;

import java.util.Scanner;

public class P12905_최소생산비용 {
	static int[][] V;
	static int N;
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 제품 개수
			V = new int[N][N];
			min = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					V[r][c] = sc.nextInt();
				}
			}

			check(0, 0, 0);

			System.out.println("#" + tc + " " + min);

		} // testcase
	}

	private static void check(int r, int sum, int visited) {
//		System.out.println("r : " + r + " / sum : " + sum + " / visited : " + visited);

		// 기저 조건
		if (r == N) {
			min = (sum < min) ? sum : min;
			return;
		}

		if (sum > min) {
			return;
		}

		// 유도 조건
		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) != 0) { // 사용했으면!
				continue;
			}

			// 사용 안했으면
			check(r + 1, sum + V[r][i], (visited | (1 << i)));
		}
	}
}
