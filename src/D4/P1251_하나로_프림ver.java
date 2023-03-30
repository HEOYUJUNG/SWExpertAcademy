package D4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1251_하나로_프림ver {
	static final double INF = Double.MAX_VALUE;
	static double[] dist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 섬 개수
			Island[] island = new Island[N]; // 각 섬의 x좌표, y좌표 저장
			// 섬들의 x좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i] = new Island(sc.nextDouble(), 0); // y는 뒤에서 입력받을 것임!
			}
			// 섬들의 y좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i].y = sc.nextDouble();
			}

			double E = sc.nextDouble(); // 환경 부담 세율

			double cost = 0; // 환경 부담금
			dist = new double[N];
			boolean[] visited = new boolean[N];
			Arrays.fill(dist, INF); // 전부 무한대로 초기화.

			// 임의의 정점 하나 선택! (그냥 출발 지점을 임의로 선택해준 것. 아직 방문처리는 X)
			dist[0] = 0;

			// 프림!!
			for (int i = 0; i < N; i++) { // 간선 N-1개 뽑으면 되니까!
				// 1. 아직 안 뽑힌 애들 중에서 가중치 가장 작은 정점을 뽑자!
				double min = INF;
				int idx = -1; // 가중치 가장 작은 정점을 뽑았을 때, 그 정점의 인덱스 저장!

				for (int j = 0; j < N; j++) { // 전체 정점 돌면서
					if (!visited[j] && dist[j] < min) { // 아직 방문 안했고 & 가중치 가장 작은 점 고르기
						min = dist[j];
						idx = j;
					}
				}

				visited[idx] = true; // 뽑힌 정점 방문처리!
				cost += dist[idx] * dist[idx] * E;

				// 2. 뽑힌 정점에서 갈 수 있는 정점들에 대해 가중치 작으면 업데이트 해주기!
				for (int j = 0; j < N; j++) {
					if (!visited[j] && getDistance(island[j], island[idx]) < dist[j]) {
						dist[j] = getDistance(island[j], island[idx]);
					}
				}
			}

			System.out.println("#" + tc + " " + Math.round(cost));
		} // testcase
	} // main

	// 두 섬 사이 거리 계산
	private static double getDistance(Island a, Island b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	private static class Island {
		double x, y; // 섬의 위치

		public Island(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}