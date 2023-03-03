package D4;

import java.util.Scanner;

public class P4613_러시아국기같은깃발_재귀ver {
	static char[][] map;
	static int N, M, min;
	static char[] colors; // 재귀적으로 선택된 행별 색깔을 담을 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			map = new char[N][M];
			colors = new char[N];

			for (int r = 0; r < N; r++) {
				String str = sc.next();
				char[] arr = str.toCharArray();
				for (int c = 0; c < M; c++) {
					map[r][c] = arr[c];
				}
			}

			min = Integer.MAX_VALUE; // 최소값 초기화

			// select : 재귀적으로 행의 색깔을 선택하는 함수
			// 각 선택은 이전 색깔의 영향을 받음
			// W -> W or B / B -> B or R / R -> R
			// select(i, prevColor) : i-1번째 색이 prevColor일 때, i번째 행의 색을 선택
			select(1, 'W');
			// 0번 행은 W 고정. N-1번 행은 R 고정.
			// 1 ~ (N-2)번 행만 선택하면 됨
			for (int j = 0; j < M; j++) {
				if (map[0][j] != 'W') {
					min++;
				}
				if (map[N - 1][j] != 'R') {
					min++;
				}
			}
			System.out.println("#" + tc + " " + min);
		} // testcase
	} // main

	// i-1번째 색이 주어질 때 i번째 색을 선택하는 함수
	private static void select(int idx, char c) {
		// 기저 조건 (언제 멈춰?)
		if (idx == N - 1) { // N-2까지는 선택을 하고, N-1일 때 멈춤.
			// 선택 완료되었으니까 개수 세기
			int colorCnt = 0;

			for (int i = 1; i < N - 1; i++) {
				int cnt = 0;
				for (int j = 0; j < M; j++) {
					if (map[i][j] != colors[i]) {
						cnt++;
					}					
				}
				colorCnt += cnt;
			}

			min = (min > colorCnt) ? colorCnt : min;

			return;
		}

		// N-3번까지 모두 W였으면 N-1은 R 고정이니까 N-2는 W여야 함.
		if (idx == N - 2 && c == 'W') {
			colors[idx] = 'B';
			select(idx + 1, 'B');
			return;
		}

		// 유도 조건
		if (c == 'W') {
			colors[idx] = 'W';
			select(idx + 1, 'W');
			colors[idx] = 'B';
			select(idx + 1, 'B');
		} else if (c == 'B') {
			colors[idx] = 'B';
			select(idx + 1, 'B');
			colors[idx] = 'R';
			select(idx + 1, 'R');
		} else {
			colors[idx] = 'R';
			select(idx + 1, 'R');
		}
	}
}
