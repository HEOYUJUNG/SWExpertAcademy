package D4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1251_하나로 {
	static int N, i;
	static double E;
	static Island[] island, sel;
	static Edge[] edges;
	static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 섬 개수
			island = new Island[N]; // 각 섬의 x좌표, y좌표 저장
			// 섬들의 x좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i] = new Island(sc.nextInt(), 0, i); // y는 뒤에서 입력받을 것임!
			}
			// 섬들의 y좌표 입력받기
			for (int i = 0; i < N; i++) {
				island[i].y = sc.nextInt();
			}

			E = sc.nextDouble(); // 환경 부담 세율

			// 간선 생성. 시작 정점, 끝 정점, 가중치(E*두 정점 사이 거리^2)
			edges = new Edge[N * (N - 1) / 2]; // 무방향 그래프에서 간선 개수는 N * (N - 1) / 2
			sel = new Island[2]; // 터널 연결할 섬 2개 선택
			int idx = 0;
//			i = 0;
//			comb(0, 0);
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					Island a = island[i];
					Island b = island[j];
					edges[idx++] = new Edge(a, b, Math.pow(getDistance(a, b), 2) * E); // 간선 추가해주기
				}
			}

			// 출력해보기
//			for(Edge e : edges) {
//				System.out.println(e.toString());
//			}

			// 크로스칼 1단계 : 가중치 오름차순 정렬
			Arrays.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					if (o1.w >= o2.w) {
						return 1;
					}
					return -1;
				}
			});

			// 크로스칼 2단계 : 최소신장트리 만들 간선 뽑기. 가중치 적은 것부터 & 사이클 안 생기도록!
			double cost = 0;
			idx = 0; // 몇번째 간선 볼 것인가!
			int pick = 0; // 내가 뽑은 간선 개수
			p = new int[N];
			for (int i = 0; i < N; i++) {
				p[i] = i;
			}
			while (pick < N - 1) { // 크로스칼 3단계 : 간선 개수 N-1개되면 종료!
				int ai = edges[idx].a.i;
				int bi = edges[idx].b.i;
				// 사이클 안 생기면, 즉 a와 b의 루트가 다르면 간선 뽑기!
				if (findSet(ai) != findSet(bi)) {
					union(ai, bi);
					pick++;
					cost += edges[idx].w;
				}
				idx++; // 다음 간선 보러가기
			}

			System.out.println("#" + tc + " " + (int) cost);
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

//	// N개의 섬 중에서 터널 연결할 섬 2개 뽑기 nC2
//	private static void comb(int idx, int sidx) {
//		// 기저 조건
//		if (sidx == 2) {
//			// 섬 2개 선택 완료
//			edges[i++] = new Edge(sel[0], sel[1], Math.pow(getDistance(sel[0], sel[1]), 2) * E); // 간선 추가해주기
//			return;
//		}
//
//		// 유도 조건
//		for (int i = idx; i <= N - 2 + sidx; i++) {
//			sel[sidx] = island[idx];
//			comb(idx + 1, sidx + 1);
//		}
//	}

	// 두 섬 사이 거리 계산
	private static double getDistance(Island a, Island b) {
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	private static class Island {
		int x, y; // 섬의 위치
		int i;

		public Island(int x, int y, int i) {
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

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
	}
}
