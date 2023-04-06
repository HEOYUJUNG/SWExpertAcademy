package 분류없음;

import java.util.*;

public class P1949_등산로조성 {
	static int K, N, maxD;
	static int[][] map;
	static List<Pos> peaks;

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static class Pos {
		int r, c, dist;

		public Pos(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", dist=" + dist + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 지도 한 변 길이
			K = sc.nextInt(); // 최대 공사 가능 깊이
			map = new int[N][N];
			peaks = new ArrayList<>(); // 가장 높은 봉우리들 저장할 리스트
			int maxH = 0; // 가장 높은 봉우리

			// 지도 정보 입력받기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = sc.nextInt();
					maxH = (maxH < map[r][c]) ? map[r][c] : maxH; // 가장 높은 봉우리의 높이 구하기
//					System.out.print(map[r][c] + " ");
				}
//				System.out.println();
			}

			// 가장 높은 봉우리 위치 찾기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == maxH) {
						peaks.add(new Pos(r, c, 1));
					}
				}
			}

//			for (Pos p : peaks) { 
//				System.out.println(p.toString());
//			}

			maxD = 0; // 만들 수 있는 가장 긴 등산로 길이
			for (Pos p : peaks) { // 가장 높은 봉우리들 중 하나씩 뽑아서 만들 수 있는 등산로 길이 확인하기
				System.out.println(p.toString());
				BFS(p);
				// 현재 뽑은 봉우리가 있는 곳 말고 다른 곳들 K씩 깎아보기
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						System.out.println("깎을 곳   r : " + r + " / c :" + c);
						if (r == p.r && c == p.c) { // 현재 봉우리 있는 곳이면
							continue; // 탐색 안함
						}
						// 다른 곳들은 K씩 깎아서
						map[r][c] -= K;
						// 다시 BFS 해보기
						BFS(p);
						// BFS 끝나면 다시 원상복구
						map[r][c] += K;
					}
				}
			}
			System.out.println("#" + tc + " " + maxD);
		} // testcase
	} // main

	private static void BFS(Pos p) {
		int max = 0; // 출발지점이 p일 때의 가장 긴 경로 길이
		int[][] visited = new int[N][N]; // 방문처리할 배열 (출발 위치 ~ 현재 위치까지의 거리 저장)
		Queue<Pos> q = new LinkedList<>();

		q.add(p); // 큐에 넣기

		while (!q.isEmpty()) {
			Pos cur = q.poll(); // 제일 앞에 있는거 꺼내서

//			if(cur.dist == 4) {
//				return;
//			}
			if (cur.dist > visited[cur.r][cur.c]) { // 다른 방법으로 갔을 때보다 거리가 짧거나 같은 경우는 안 봐도 됨. 패쓰~

				System.out.println(p.toString());
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						System.out.print(visited[r][c] + " ");
					}
					System.out.println();
				}
				System.out.println();

				// 아직 방문한 적 없거나, 방문한 적이 있어도 이번 방문이 더 긴 경로인 경우
				visited[cur.r][cur.c] = cur.dist; // 방문처리 해주고
				max = (max < cur.dist) ? cur.dist : max; // 최대 거리 갱신
				for (int d = 0; d < 4; d++) { // 상하좌우에서
					int rr = cur.r + dr[d];
					int cc = cur.c + dc[d];
					// 갈수 있는 곳 (범위 안 벗어나고, 수가 작거나 같은 곳)
					if (check(rr, cc) && map[rr][cc] <= map[cur.r][cur.c]) {
						q.add(new Pos(rr, cc, cur.dist + 1));
					}
				}
			}
		}

		maxD = (maxD < max) ? max : maxD; // 전체 경우에서의 최대 길이 갱신해주기
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
