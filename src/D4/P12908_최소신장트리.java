package D4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P12908_최소신장트리 {

	static int[] p; // 대표를 저장할 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {

			int V = sc.nextInt(); // 정점 개수
			int E = sc.nextInt(); // 간선 개수

			// 간선을 저장하기 위해서 클래스를 사용할 수도 있지만
			// 배열을 이용해서 저장을 하겠다. 0 : 시작정점 / 1 : 끝정점 / 2 : 가중치
			int[][] edges = new int[E][3];
			for (int i = 0; i < E; i++) {
				edges[i][0] = sc.nextInt();
				edges[i][1] = sc.nextInt();
				edges[i][2] = sc.nextInt();
			} // 입력 끝

			// 크루스칼 1단계 : 간선을 정렬한다. (오름차순)
			Arrays.sort(edges, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2] - o2[2];
				}
			});

			// 크루스칼 2단계 : V개의 간선을 뽑는데, 사이클이 발생 안 하는 애들만!!
			p = new int[V + 1];

			// make-set (나 자신을 대표로 초기화하자!)
			for (int i = 0; i <= V; i++) {
				makeset(i); // 정석
			}

			int ans = 0; // 최소 비용

			int pick = 0; // 내가 뽑은 간선 개수

			// 모든 간선 순회하면서 체크하자!

//		 정석적인 방법 -> while
			int i = 0;
			while (pick < V) {
				// i번째 간선을 뽑아서 두 정점의 대표 확인하자 -> 두 정점의 대표가 같다면 사이클 발생!
				int x = edges[i][0];
				int y = edges[i][1];

				if (findset(x) != findset(y)) { // 부모가 같지 않다! = 사이클 발생하지 않는다! = 연결 가능~
					union(x, y);
					ans += edges[i][2];
					pick++;
				}
				i++;
			}

			System.out.println("#" + tc + " " + ans);
		}
	} // main

	static void makeset(int x) {
		p[x] = x;
		// rank는 따로 하지 않았음!
	}

	static int findset(int x) {
		// 경로 압축 (path compression)
		if (x != p[x])
			p[x] = findset(p[x]); // 이렇게 저장해주면 다음에 다시 findset 할 때 바로 루트 노드로 가버리니까 훨씬 효율적!
		return p[x];
	}

	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}
}
