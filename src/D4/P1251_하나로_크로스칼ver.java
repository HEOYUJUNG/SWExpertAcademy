package D4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1251_하나로 {
	static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 섬 개수
			Island[] island = new Island[N]; // 각 섬의 x좌표, y좌표 저장
			// 섬들의 x좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i] = new Island(sc.nextDouble(), 0, i); // y는 뒤에서 입력받을 것임!
			}
			// 섬들의 y좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i].y = sc.nextDouble();
			}

			double E = sc.nextDouble(); // 환경 부담 세율

			// 간선 생성. 시작 정점, 끝 정점, 가중치(E*두 정점 사이 거리^2)
			Edge[] edges = new Edge[N * (N - 1) / 2]; // 무방향 그래프에서 간선 개수는 N * (N - 1) / 2
			int idx = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					Island a = island[i];
					Island b = island[j];
					double L = getDistance(a, b);
					edges[idx++] = new Edge(a, b, L * L * E); // 간선 추가해주기
				}
			}

			// 크로스칼 1단계 : 가중치 오름차순 정렬
			Arrays.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					if (o1.w < o2.w)
						return -1;
					return 1;
//					return Double.compare(o1.w, o2.w);
				}
			});

			// 크로스칼 2단계 : 최소신장트리 만들 간선 뽑기. 가중치 적은 것부터 & 사이클 안 생기도록!
			double cost = 0;
			int pick = 0; // 내가 뽑은 간선 개수
			p = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i;
			}

			for (int i = 0; i < N * (N - 1) / 2; i++) {
				int ai = edges[i].a.i;
				int bi = edges[i].b.i;
				// 사이클 안 생기면, 즉 a와 b의 루트가 다르면 간선 뽑기!
				if (findSet(ai) != findSet(bi)) {
					union(ai, bi);
					pick++;
					cost += edges[i].w;
				}

				if (pick == N - 1) { // 크로스칼 3단계 : 간선 개수 N-1개되면 종료!
					break;
				}
			}

			System.out.println("#" + tc + " " + Math.round(cost));
		} // testcase
	} // main

	private static int findSet(int x) {
		if (p[x] != x) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}

	private static void union(int a, int b) {
		p[findSet(b)] = findSet(a);
	}

	// 두 섬 사이 거리 계산
	private static double getDistance(Island a, Island b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	private static class Island {
		double x, y; // 섬의 위치
		int i; // 배열에서의 인덱스

		public Island(double x, double y, int i) {
			this.x = x;
			this.y = y;
			this.i = i;
		}
	}

	private static class Edge {
		Island a, b; // 시작, 끝 정점
		double w; // 두 정점 사이 가중치 (E*두 정점 사이 거리^2)

		public Edge(Island a, Island b, double w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
}
