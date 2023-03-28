package 분류없음;

import java.util.Scanner;

public class P7733_치즈도둑 {
	static int N;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean[][] visited;
	static int[][] cheese;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 치즈 한 변 길이
			cheese = new int[N][N];

			int maxDay = 0; // X번째날에는 맛있는 정도가 X인 칸을 먹으니까, 값이 최대인 칸을 찾으면 그 날만큼 먹을 수 있다!
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					cheese[r][c] = sc.nextInt();
					maxDay = Math.max(cheese[r][c], maxDay);
				}
			}

			int max = 0;
			for (int day = 1; day <= maxDay; day++) {
				int cnt = 0; // 오늘의 치즈 덩어리 개수
				visited = new boolean[N][N];
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] == day) {
							cheese[r][c] = -1;
						}
						System.out.print(cheese[r][c] + " ");
					}
					System.out.println();
				}
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] > 0) {
							// 치즈 덩어리 만나면 dfs 하면서 어디까지가 한 덩어리인지 구하고
							DFS(r, c);
							// 치즈 덩어리 개수 카운트
							cnt++;
						}
					}
				}
				System.out.println("day : " + day + " / cnt : " + cnt);
				max = Math.max(max, cnt);
			}

			System.out.println("#" + tc + " " + max);
		} // testcase
	} // main

	private static void DFS(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			// 다음 이동한 위치
			int rr = r + dr[d];
			int cc = c + dc[d];
			if (check(rr, cc) && !visited[rr][cc] && cheese[rr][cc] > 0) { // 갈 수 있으면
				DFS(rr, cc);
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}
