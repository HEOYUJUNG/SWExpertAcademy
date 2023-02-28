package D2;

import java.util.LinkedList;
import java.util.Scanner;

public class P12511_subtree_node {
	static int cnt; // 서브 루트에 있는 노드 개수
	static int E;
	static LinkedList<Node> tree = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			E = sc.nextInt(); // 간선의 개수, 노드 개수는 E+1
			int N = sc.nextInt(); // 서브 트리의 루트 노드 번호

			tree = new LinkedList<>();

			for (int i = 0; i < E; i++) {
				int p = sc.nextInt(); // 부모 노드 번호
				int c = sc.nextInt(); // 자식 노드 번호
				// 트리에 아무것도 없으면 그냥 추가
				if (tree.size() == 0) {
					Node child = new Node(c, null, null);
					tree.add(new Node(p, child, null));
					tree.add(child);
					continue;
				}

				// 트리에 뭔가 있으면 부모 노드 찾아서
				Node parent = getNode(p);
				if (parent.LChild != null) {
					Node child = new Node(c, null, null);
					parent.RChild = child;
					tree.add(child);
				} else {
					Node child = new Node(c, null, null);
					parent.LChild = child;
					tree.add(child);
				}
			} // 트리 완성

			System.out.print("#" + tc + " ");
			// 번호가 N인 노드 찾아서 그 노드를 루트로 가지는 서브 트리의 노드 개수 찾기
			cnt = 0;
			traverse(getNode(N));
			System.out.println(cnt);
		} // testcase
	}

	// Node 클래스 생성.
	public static class Node {
		public int Me; // 현재 노드 번호
		Node LChild; // 왼쪽 자식 노드 번호
		Node RChild; // 오른쪽 자식 노드 번호

		// 생성자
		public Node(int me, Node lChild, Node rChild) {
			super();
			Me = me;
			LChild = lChild;
			RChild = rChild;
		}
	}

	// 전위 순회. VLR
	public static void traverse(Node n) {
		cnt++;
		if (n.LChild != null) {
			// 왼쪽 자식이 있으면 왼쪽 자식을 다시 부모로 보고 탐색
			traverse(n.LChild);
		}
		if (n.RChild != null) {
			// 오른쪽 자식이 있으면 오른쪽 자식을 다시 부모로 보고 탐색
			traverse(n.RChild);
		}
	}

	// 번호가 x인 노드 반환하는 메서드
	public static Node getNode(int x) {
		for (int i = 0; i < E + 1; i++) {
			if (tree.get(i).Me == x) { // N인 노드 찾기
				return tree.get(i);
			}
		}
		return null;
	}
}
