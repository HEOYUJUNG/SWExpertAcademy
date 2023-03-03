package D2;

import java.util.Scanner;

public class P13707_기지국 {
	public static void main(String[] args) {
		// 상, 우, 하, 좌
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			char[][] map = new char[N][N]; // 기지국
			int[][] house = new int[N][N]; // 집. 집이 있는데 커버 안됐으면 1, 집이 없거나 + 집이 있는데 커버 됐으면 0
			for (int r = 0; r < N; r++) {
				String s = sc.next();
				for (int c = 0; c < N; c++) {
					map[r][c] = s.charAt(c);
					if (map[r][c] == 'H') {
						house[r][c] = 1; // 집이면 1로 표시해주기
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 'A') { // 동서남북 1개 커버하는 기지국
						for (int d = 0; d < 4; d++) {
							if (r + dr[d] >= 0 && r + dr[d] < N && c + dc[d] >= 0 && c + dc[d] < N)
								house[r + dr[d]][c + dc[d]] = 0;
						}
					} else if (map[r][c] == 'B') { // 동서남북 2개 커버하는 기지국
						for (int d = 0; d < 4; d++) {
							for (int i = 1; i <= 2; i++) {
								if (r + dr[d] * i >= 0 && r + dr[d] * i < N && c + dc[d] * i >= 0 && c + dc[d] * i < N)
									house[r + dr[d] * i][c + dc[d] * i] = 0;
							}
						}
					} else if (map[r][c] == 'C'){ // 동서남북 3개 커버하는 기지국
						for (int d = 0; d < 4; d++) {
							for (int i = 1; i <= 3; i++) {
								if (r + dr[d] * i >= 0 && r + dr[d] * i < N && c + dc[d] * i >= 0 && c + dc[d] * i < N)
									house[r + dr[d] * i][c + dc[d] * i] = 0;
							}
						}
					}
				}
			}

			int cnt = 0; // 기지국에 cover가 되지 않는 집의 수
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (house[r][c] == 1) {
						cnt++;
					}
				}
			}

			System.out.printf("#%d %d\n", tc, cnt);
		} // testcase
	}
}
