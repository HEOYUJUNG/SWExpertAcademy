package D5;

import java.util.Scanner;

public class P1247_최적경로 {
	static int N, min;
	static Position[] customers, sel;
	static Position office, home;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 고객의 수
			customers = new Position[N];
			sel = new Position[N + 2];
			visited = new boolean[N];
			office = new Position(sc.nextInt(), sc.nextInt()); // 회사의 좌표
			home = new Position(sc.nextInt(), sc.nextInt()); // 집의 좌표
			sel[0] = office;
			sel[N + 1] = home;
			for (int i = 0; i < N; i++) {
				customers[i] = new Position(sc.nextInt(), sc.nextInt()); // 고객의 좌표
			}

			min = Integer.MAX_VALUE; // 가장 짧은 경로의 이동거리

			perm(1, 0);

			System.out.println("#" + tc + " " + min);

		} // testcase
	} // main

	private static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + "]";
		}
	}

	// 좌표 사이 거리 구하기
	private static int distance(Position p1, Position p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	private static void perm(int idx, int dist) {
		if (dist > min) {
			return;
		}
		
		// 기저 조건
		if (idx == N + 1) {
			// 고객 방문 순서 결정 완료. 이동 거리 구하자.
			dist += distance(sel[idx - 1], sel[idx]);
			min = Math.min(min, dist);

			return;
		}

		// 유도 조건
		for (int i = 0; i < N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true; // 방문처리
			sel[idx] = customers[i];
			perm(idx + 1, dist + distance(sel[idx - 1], sel[idx]));
			visited[i] = false; // 원상복귀
		}
	}
}
