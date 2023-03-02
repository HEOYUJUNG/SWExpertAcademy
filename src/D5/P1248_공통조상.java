package D5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class P1248_공통조상 {
	static int cnt; // 서브 루트에 있는 노드 개수
	static int V; // 총 노드 개수
	static LinkedList<Node> tree; // 트리도 메모리에 올려두기

	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		int T = sc.nextInt();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();

//			V = sc.nextInt(); // 정점의 개수
//			int E = sc.nextInt(); // 간선의 개수 (간선의 개수 = 노드 개수-1 이니까 사실 필요없는 정보인듯..)
//			// 공통 조상을 찾는 두 개의 노드 번호
//			int num1 = sc.nextInt();
//			int num2 = sc.nextInt();
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			tree = new LinkedList<>(); // 트리 초기화

			// 트리 만들기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
//				int p = sc.nextInt(); // 간선의 부모 노드 번호
//				int c = sc.nextInt(); // 간선의 자식 노드 번호
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				Node pNode = getNode(p);
				Node cNode = getNode(c);

				// "부모 - 자식" 순서로 표기되니까
				// 하나의 간선 -> 부모 노드, 자식 노드 2개 노드 만들기 or 기존 노드 수정 해야 함

				// 간선의 부모 노드 번호&자식 노드 번호현재 트리 내에 아무것도 없을 때
				if (pNode == null && cNode == null) { // 번호 찾아봤는데 없음
					Node child = new Node(c, null, null, null); // 일단 자식 노드 생성
					Node parent = new Node(p, null, child, null); // 부모 노드 생성. 루트 노드니까 얘의 부모 노드는 없음(null)
					child.Parent = parent; // 자식 노드에 부모 노드 정보 추가해주기
					tree.add(parent); // 트리에 두 노드 추가
					tree.add(child);
					continue; // 할 거 다 했으니 다음 간선 정보 받기
				}

				// 현재 트리 내에 부모 노드&자식 노드 번호 다 있을 때 -> 둘 다 찾아서 이어주기
				if (pNode != null && cNode != null) {
					Node parent = pNode; // 부모 노드 가져오기
					Node child = cNode; // 자식 노드 가져오기
					if (parent.LChild != null) { // 왼쪽 자식 있으면
						parent.RChild = child; // 오른쪽 자식으로 추가
						// 자식 노드 추가하는거 잠시 보류
					} else { // 왼쪽 자식 없으면
						parent.LChild = child; // 왼쪽 자식으로 추가
						// 자식 노드 추가하는거 잠시 보류
					}
					child.Parent = parent;
					continue;
				}

				// 경우 잘 나눠서 풀어보자...!!!
				// 부모, 자식 둘 다 트리 있거나 둘 다 트리에 없는 경우는 위에서 이미 처리함.
				// 남은건 둘 중 하나만 있는 경우 -> 부모든 자식이든 찾아서 붙여주기

				// 현재 트리 내에 부모 노드 번호만 있을 때
				if (pNode != null) {
					Node parent = pNode; // 부모 노드 가져오기
					Node child = new Node(c, parent, null, null); // 자식 노드 생성
					if (parent.LChild != null) { // 왼쪽 자식 있으면
						parent.RChild = child; // 오른쪽 자식으로 추가
						// 자식 노드 추가하는거 잠시 보류
					} else { // 왼쪽 자식 없으면
						parent.LChild = child; // 왼쪽 자식으로 추가
						// 자식 노드 추가하는거 잠시 보류
					}
					child.Parent = parent;
					tree.add(child); // 자식 노드 트리에 추가
				} else { // 현재 트리 내에 자식 노드 번호만 있을 때
					Node child = cNode;
					Node parent = new Node(p, null, child, null);
					child.Parent = parent;
					tree.add(parent);
				}
			} // 트리 완성

			// 이제 공통 조상 찾아야 함
			// 공통 조상 받을 변수 준비해두기. 일단 루트 노드라고 가정.(문제에서 루트 노드는 항상 1번이라고 함)
			Node cmAncestor = getNode(1);

			// num1, n2 번호를 갖는 노드 찾기
			Node node1 = getNode(num1);
			Node node2 = getNode(num2);

			// node1의 조상 리스트 (바로 위 부모 노드 ~ 루트 노드까지 조상 노드들 넣어주기)
			List<Node> ancestor = new ArrayList<>();
			Node current = node1; // node1에서 출발해서
			while (current.Parent != null) { // 루트 노드가 아닐 때까지
				current = current.Parent; // 부모 노드로 이동
				ancestor.add(current); // 조상 리스트에 추가해주기
			}

			current = node2; // 이번에는 node2의 조상 노드들 찾기
			here: while (current.Parent != null) {
				current = current.Parent; // 부모 노드로 이동
				// node1의 조상 노드 리스트에 (지금 current에 들어있는) node2의 조상 노드 있는지 확인
				// 있으면 그게 가장 가까운 공통 조상 노드
				for (int i = 0; i < ancestor.size(); i++) {
					if (ancestor.get(i) == current) {
						cmAncestor = current;
						break here; // 공통 조상 찾았으면 node2의 조상 그만 찾아도 됨. whil문 나가기
					}
				}
				// 없으면 다음 부모 찾기
			}

			// 공통 조상을 루트로 하는 서브 트리 순회하면서 노드 개수 카운트
			cnt = 0;
			traverse(cmAncestor);

			// 테케 번호 & 가장 가까운 공통 조상 번호 & 서브 트리 크기 출력
			sb.append("#" + tc + " " + cmAncestor.Me + " " + cnt);
			System.out.println(sb);
		}

	} // main

	// Node 클래스 생성.
	public static class Node {
		public int Me; // 현재 노드 번호
		Node Parent; // 부모 노드
		Node LChild; // 왼쪽 자식 노드
		Node RChild; // 오른쪽 자식 노드

		// 생성자
		public Node(int me, Node parent, Node lChild, Node rChild) {
			super();
			Me = me;
			Parent = parent;
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
		for (int i = 0; i < tree.size(); i++) { // 지금까지 완성된 트리 만큼만 탐색
			if (tree.get(i).Me == x) { // 번호가 x인 노드 찾아서
				return tree.get(i); // 반환
			}
		}
		return null;
	}
}
