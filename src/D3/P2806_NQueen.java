package D3;

import java.util.Scanner;

public class P2806_NQueen {
	static int[][] board;
	static int N;
	static int cnt;
	// 순서대로 왼쪽 아래, 아래, 오른쪽 아래
	static int[] dr = { 1, 1, 1 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			board = new int[N][N]; // 놓을 수 있는지 체크. 놓을 수 없으면 true, 놓을 수 있으면 false
			cnt = 0;

			check(0);

			System.out.println("#" + tc + " " + cnt);
		} // testcase
	}

	private static void check(int r) {
		// 기저 조건
		if (r == N) {
			cnt++;
			return;
		}

		// 유도 조건
		for (int i = 0; i < N; i++) {
			if (board[r][i] != 0) { // 방문한 적 있거나, 놓을 수 없으면 통과하기
				continue;
			}

			board[r][i]++; // 방문했으니까 true

			// 놓을 수 없으니까 true

			for (int d = 0; d < 3; d++) {
				for (int k = 1; r + k < N; k++) {
					if (i + k * dc[d] >= 0 && i + k * dc[d] < N) {
						board[r + k][i + k * dc[d]]++;
					}
				}
			}

			// 하나 내려가기
			check(r + 1);

			// 갔다오면 원복 해주기
			board[r][i]--;

			for (int k = 1; r + k < N; k++) {
				for (int d = 0; d < 3; d++) {
					if (i + k * dc[d] >= 0 && i + k * dc[d] < N) {
						board[r + k][i + k * dc[d]]--;
					}
				}
			}
		}
	}
}