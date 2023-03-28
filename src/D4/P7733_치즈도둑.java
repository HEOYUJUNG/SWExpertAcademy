package D4;

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
			cheese = new int[N][N]; // 치즈 맛있는 정도 저장할 배열

			int maxDay = 0; // X번째날에는 맛있는 정도가 X인 칸을 먹으니까, 값이 최대인 칸을 찾으면 그 날만큼 먹을 수 있다!
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					cheese[r][c] = sc.nextInt();
					maxDay = Math.max(cheese[r][c], maxDay);
				}
			}
			
			
			// 3
			// 1 1 1
			// 1 1 1
			// 1 1 1
			// => 첫째 날에 전부 먹어버리기 때문에 치즈 덩어리를 카운트 할 일이 없어서 바로 종료되지만
			// 어쨌든 한 덩어리는 있기 때문에 1이 출력돼야 함. 따라서 1로 초기화!! (0으로 초기화하면 테케 11개 중에 10개 맞음..)
			int max = 1; // 치즈덩어리가 가장 많을 때의 덩어리 개수 (1로 초기화)
			for (int day = 1; day <= maxDay; day++) {
				int cnt = 0; // 오늘의 치즈 덩어리 개수
				visited = new boolean[N][N]; // 오늘 방문처리할 배열
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] == day) {  // X번째날에는 맛있는 정도가 X인 칸을 먹어버린다
							cheese[r][c] = -1; // 치즈 먹어버렸으면 -1로 표시
						}
//						System.out.print(cheese[r][c] + " ");
					}
//					System.out.println();
				}

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] > 0 && !visited[r][c]) {
							// 아직 방문하지 않은 치즈 덩어리 만나면 dfs 하면서 같은 덩어리인 부분 전부 방문처리 해주기!
							DFS(r, c);
							// 치즈 덩어리 개수 카운트
							cnt++;
						}
					}
				}
//				System.out.println("day : " + day + " / cnt : " + cnt);
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
		return;
	}

	private static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}