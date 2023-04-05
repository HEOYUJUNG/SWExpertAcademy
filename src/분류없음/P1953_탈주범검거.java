package 분류없음;

import java.util.Arrays;
import java.util.Scanner;

public class P1953_탈주범검거 {
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 현재 위치의 타입을 알고, 상하좌우로 이동했을 때 어떤 타입이 있으면 이동할 있는지!
	static int[][][] go = { {}, { { 1, 2, 5, 6 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, { 1, 3, 6, 7 } },
			{ { 1, 2, 5, 6 }, { 1, 2, 4, 7 }, {}, {} }, { {}, {}, { 1, 3, 4, 5 }, { 1, 3, 6, 7 } },
			{ { 1, 2, 5, 6 }, {}, {}, { 1, 3, 6, 7 } }, { {}, { 1, 2, 4, 7 }, {}, { 1, 3, 6, 7 } },
			{ {}, { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, {} }, { { 1, 2, 5, 6 }, {}, { 1, 3, 4, 5 }, {} } };

	static int L;
	static int[][] map;
	static int[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 지도의 세로 크기
			int M = sc.nextInt(); // 지도의 가로 크기
			int R = sc.nextInt(); // 맨홀 세로 위치
			int C = sc.nextInt(); // 맨홀 가로 위치
			L = sc.nextInt(); // 탈출 후 소요된 시간
			map = new int[N + 2][M + 2]; // 1 ~ N, 1 ~ M (범위 체크 안하려고 테두리에는 0으로 padding)
			visited = new int[N + 2][M + 2]; // 방문 처리할 배열. 단, boolean 값이 아니라 해당 칸까지 걸린 시간 저장

			for (int r = 0; r < N + 2; r++) {
				Arrays.fill(visited[r], Integer.MAX_VALUE); // 최대값으로 초기화
			}
			
			// 지하 터널 지도 정보 입력받기
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			int cnt = 0;
			DFS(R + 1, C + 1, 0); // 한칸씩 패딩 해줬으니까 맨홀 위치도 수정해줘야 함!!

			// 탈주범이 위치할 수 있는 장소의 개수 세기
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (visited[i][j] < Integer.MAX_VALUE) {
						cnt++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}

	// (r,c) : 현재 위치
	// time : 소요된 시간
	private static void DFS(int r, int c, int time) {
		// 기저 조건
		if (time == L) {
			return;
		}

		// 유도 조건
		visited[r][c] = time; // 방문처리 (여기까지 오는 데 얼마나 걸렸는지 저장!)
		int type = map[r][c]; // 현재 위치에 있는 구조물 타입

		for (int d = 0; d < 4; d++) { // 상하좌우 이동해보기
			// 이동했을 때의 위치 (rr, cc)
			int rr = r + dr[d];
			int cc = c + dc[d];

			for (int i = 0; i < go[type][d].length; i++) {
				// 갈 수 있고, 더 짧은 시간에 갈 수 있다면! (전보다 더 시간이 오래 걸리는 경우에는 굳이 볼 필요 X)
				if (go[type][d][i] == map[rr][cc] && time + 1 < visited[rr][cc]) {
					DFS(rr, cc, time + 1);
				}
			}
		}
	}
}
