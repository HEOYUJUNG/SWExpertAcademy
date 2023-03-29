package D4;

import java.util.Scanner;

public class P3289_서로소집합 {
	static int[] p; // 대표자 저장

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			
			int n = sc.nextInt(); // 집합 개수
			int m = sc.nextInt(); // 연산 개수

			p = new int[n + 1]; // 각각의 집합마다 대표자 저장해주기. 1부터 시작해서 n개
			// makeset 따로 안 만들고 반복문으로 초기화해주기
			for (int i = 0; i <= n; i++) { // 초기에 {1}, {2}, ... {n} 이 각각 n개의 집합을 이루고 있다.
				p[i] = i;
			}

			// m개의 연산 입력받기
			for (int i = 0; i < m; i++) {
				int cal = sc.nextInt();
				if (cal == 0) { // 합집합
					union(sc.nextInt(), sc.nextInt());
				} else { // 두 원소가 같은 집합인지 확인
					if (sameSet(sc.nextInt(), sc.nextInt())) { // 같은 집합에 속해있는 경우
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			System.out.println("#" + tc + " " + sb);
		} // testcase
	} // main

	// a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다
	private static void union(int a, int b) {
		p[findset(b)] = findset(a); // a 집합이 대표가 되도록 합치기!
	}

	// 두 원소가 같은 집합에 포함되어 있는지를 확인
	private static boolean sameSet(int a, int b) {
		return findset(a) == findset(b);
	}

	// 경로 압축으로! 즉, 모든 정점들이 본인의 루트 노드 가리키도록. x가 포함된 집합의 대표자 반환
	private static int findset(int x) {
		if (p[x] != x) {
			p[x] = findset(p[x]); // 이렇게 저장해주면 다음에 다시 findset 할 때 바로 루트 노드로 가버리니까 훨씬 효율적!
		}
		return p[x];
	}
}
