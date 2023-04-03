package D6;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class P1267_작업순서_Stack {
	static ArrayList<Integer>[] graph;
	static int[] inDegree;
	static Stack<Integer> stack;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			StringBuilder sb = new StringBuilder();
			int V = sc.nextInt();
			int E = sc.nextInt();
			graph = new ArrayList[V + 1]; // 1 ~ V
			inDegree = new int[V + 1]; // 1 ~ V 진입차수 배열
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
			stack = new Stack<>();
			visited = new boolean[V + 1];
			// 진입차수가 0인 정점들에서 출발하기
			for (int i = 1; i <= V; i++) {
				if (inDegree[i] == 0) {
					topological(i);
				}
			}

			// 스택에는 처음 처음에 가야할 정점들이 위에 쌓여있음. 순서대로 빼서 스트링 빌더에 쌓아주자
			while (!stack.isEmpty()) {
				sb.append(stack.pop() + " ");
			}
			System.out.println("#" + tc + " " + sb);
		} // testcase
	} // main

	private static void topological(int n) {
		// 현재 정점 방문처리!
		visited[n] = true;
		for (int x : graph[n]) { // 현재 정점에서 갈 수 있는 정점들 중에서
			if (!visited[x]) { // 아직 방문한 적 없으면
				topological(x); // DFS로 탐색해주기
			}
		}
		stack.add(n); // DFS 탐색 끝나고 오면, 즉 나보다 뒤에 들어가야 할 정점들 다 들어가고 나면 이제 나 들어가기
	}
}
