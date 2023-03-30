package D4;

import java.util.ArrayList;
import java.util.Scanner;

public class P1238_Contact {
	static ArrayList<Integer>[] contact;
	static int[] visited;
	static int maxDepth;

	public static void main(String[] args) {
		for (int tc = 1; tc <= 1; tc++) {
			Scanner sc = new Scanner(System.in);
			int L = sc.nextInt(); // 입력 받는 데이터의 길이
			int start = sc.nextInt(); // 시작점

			contact = new ArrayList[101]; // 부여될 수 있는 번호는 1이상, 100이하
			visited = new int[101];
			for (int i = 1; i <= 100; i++) {
				contact[i] = new ArrayList<>(); // 전부 초기화 해주기
			}

			// 비상연락망 정보 입력받기
			for (int i = 0; i < L; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				contact[from].add(to);
			} // 입력 끝

			BFS(start, 0);

			int max = 0;
			for (int i = 1; i <= 100; i++) {
				if (visited[i] == maxDepth) {
					max = (max < i) ? i : max;
				}
			}
			
			System.out.println(max);
		} // testcase
	} // main

	private static void BFS(int x, int depth) {
		visited[x] = depth;

		maxDepth = (maxDepth < depth) ? depth : maxDepth;

		for (int n : contact[x]) {
			if (visited[n] == 0) // 방문한 적 없으면!
				BFS(x, depth + 1);
		}

	}
}
