package D4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1238_Contact {
	static ArrayList<Integer>[] contact;
	static int[] visited;
	static int maxDepth;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int L = sc.nextInt(); // 입력 받는 데이터의 길이
			int start = sc.nextInt(); // 시작점
			
			contact = new ArrayList[101]; // 부여될 수 있는 번호는 1이상, 100이하
			visited = new int[101];
			for (int i = 1; i <= 100; i++) {
				contact[i] = new ArrayList<Integer>(); // 전부 초기화 해주기
			}

			// 비상연락망 정보 입력받기
			for (int i = 0; i < (L / 2); i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				contact[from].add(to);
			} // 입력 끝

			maxDepth = 0;
			BFS(start, 1);

			int max = 0;
			for (int i = 1; i <= 100; i++) {
				if (visited[i] == maxDepth) {
					max = (max < i) ? i : max;
				}
			}

			System.out.println("#" + tc + " " + max);
		} // testcase
	} // main

	private static void BFS(int st, int depth) {
		visited[st] = depth;
		maxDepth = depth;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(st, depth));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int next : contact[cur.n]) {
				if (visited[next] == 0) {// 방문한 적 없으면!
					visited[next] = cur.depth + 1; // 방문처리
					maxDepth = (maxDepth < cur.depth + 1) ? cur.depth + 1 : maxDepth; // 최대 깊이 갱신
					q.add(new Node(next, cur.depth + 1)); // 큐에 들어갈 때가 방문한 경우!
				}
			}
		}
	}

	private static class Node {
		int n, depth;

		public Node(int n, int depth) {
			this.n = n;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Node [n=" + n + ", depth=" + depth + "]";
		}

	}
}
