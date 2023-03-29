package D4;

import java.util.Scanner;

public class P7465_창용마을무리의개수 {
	static int M;
	static int[] p;
	static int[] group;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 창용 마을에 사는 사람의 수 (정점 개수)
			M = sc.nextInt(); // 서로를 알고 있는 사람의 관계 수 (간선 개수)
			p = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			// M개의 관계 입력 받으면서, 서로 알고 있는 사람은 하나의 집합으로 묶어주기
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				union(a, b);
			}

			int cnt = 0; // 무리 개수 카운트
			group = new int[N + 1];
			// 무리로는 다 묶였고, 서로 다른 루트 노드의 개수 세기!
			for (int i = 1; i <= N; i++) {
				if (group[findset(i)] == 0) {  // 루트 노드 아직 센 적 없으면
					group[findset(i)] = 1; // 루트 노드 셌다고 표시해주고
					cnt++; // 개수 +1
				}
			}

			System.out.println("#" + tc + " " + cnt);
		} // testcase
	} // main

	// a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다
	private static void union(int a, int b) {
		p[findset(b)] = findset(a); // a 집합이 대표가 되도록 합치기!
	}

	// 경로 압축으로! 즉, 모든 정점들이 본인의 루트 노드 가리키도록. x가 포함된 집합의 대표자 반환
	private static int findset(int x) {
		if (p[x] != x) {
			p[x] = findset(p[x]); // 이렇게 저장해주면 다음에 다시 findset 할 때 바로 루트 노드로 가버리니까 훨씬 효율적!
		}
		return p[x];
	}
}
