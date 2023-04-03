package D6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1267_작업순서_Queue {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			StringBuilder sb = new StringBuilder();
			int V = sc.nextInt();
			int E = sc.nextInt();
			ArrayList<Integer>[] graph = new ArrayList[V + 1]; // 1 ~ V
			int[] inDegree = new int[V + 1]; // 1 ~ V 진입차수 배열
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>(); // 초기화
			}
			// 간선 정보 입력받기
			for (int i = 0; i < E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				graph[a].add(b);
				inDegree[b]++; // 진입차수 1 증가
			}

			// 위상정렬 (Topological Sort)
			Queue<Integer> q = new LinkedList<>();

			// 우선, 진입 차수가 0인 정점들 큐에 넣기
			for (int i = 1; i <= V; i++) {
				if (inDegree[i] == 0) {
					q.add(i);
				}
			}

			while (!q.isEmpty()) { // 큐 빌 때까지 반복!! (모든 정점 방문 전에 큐가 비면 사이클이 있다는 뜻)
				int n = q.poll(); // 정점 하나 뽑기
				sb.append(n + " "); // 큐에서 뽑은 순서가 작업 순서
				// 현재 뽑은 정점에서 갈 수 있는 곳들의 진입 차수 감소시키기
				for (int x : graph[n]) {
					inDegree[x]--;
					if (inDegree[x] == 0) { // 진입차수가 0인 된 정점이 생겼다면!
						q.add(x); // 큐에 또 넣어주기
					}
				}
			}
			System.out.println("#" + tc + " " + sb);
		} // testcase
	} // main
}
