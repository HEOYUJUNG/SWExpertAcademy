package B형특강02;

import java.io.*;
import java.util.*;

public class 공통조상 {
	static public class Node {
		List<Integer> children;
		int parent;

		public Node() {
			this.children = new ArrayList<>();
			this.parent = 0;
		}
	}

	static Node[] nodes;
	static ArrayList<Integer> ancestorA, ancestorB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점 개수
			int E = Integer.parseInt(st.nextToken()); // 간선 개수
			int A = Integer.parseInt(st.nextToken()); // 공통 조상을 찾는 2개의 정점 번호
			int B = Integer.parseInt(st.nextToken());
			nodes = new Node[V + 1];
			for (int i = 1; i <= V; i++) {
				nodes[i] = new Node();
			}
			ancestorA = new ArrayList<Integer>();
			ancestorB = new ArrayList<Integer>();

			// 간선 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				nodes[parent].children.add(child);
				nodes[child].parent = parent;
			}

			// A, B 각각의 전체 조상 찾기
			traverse(A, ancestorA);
			traverse(B, ancestorB);

			// 가장 가까운 공통 조상 찾기
			int CLA = 1;
			for (int i = 0; i < V; i++) {
				// ★ Intger끼리의 비교는 equals 메소드를 사용해야 함!!!!!
				if (!ancestorA.get(i).equals(ancestorB.get(i))) { // 달라지기 전까지가 공통조상
					break;
				}
				CLA = ancestorA.get(i);
			}

			// 서브 트리 크기 계산
			sb.append("#" + tc + " " + CLA + " " + dfs(CLA) + "\n");
		} // testcase
		System.out.println(sb);
	}

	public static void traverse(int idx, ArrayList<Integer> ancestor) {
		int parent = nodes[idx].parent;
		if (parent != 0) {
			traverse(parent, ancestor);
		}
		ancestor.add(idx); // 위쪽 공통 조상부터 들어감
	}

	public static int dfs(int idx) {
		int res = 1;
		for (int child : nodes[idx].children) {
			res += dfs(child);
		}
		return res;
	}
}
