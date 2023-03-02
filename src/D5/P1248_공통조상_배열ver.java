package D5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1248_공통조상_배열ver {
	static int V;
	static int[][] tree;
	static int size;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			V = sc.nextInt(); // 정점의 개수
			int E = sc.nextInt(); // 간선의 개수 (간선의 개수 = 노드 개수-1 이니까 사실 필요없는 정보인듯..)
			// 공통 조상을 찾는 두 개의 노드 번호
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();

			tree = new int[V + 1][2];

			// 부모 노드 번호가 행, 첫번 째 열에는 왼쪽 자식, 두번 째 열에는 오른쪽 자식
			for (int i = 0; i < E; i++) {
				int parent = sc.nextInt();
				if (tree[parent][0] == 0) { // 왼쪽 자식 없으면
					tree[parent][0] = sc.nextInt(); // 왼쪽 자식으로 추가
				} else { // 왼쪽 자식 있으면
					tree[parent][1] = sc.nextInt(); // 오른쪽 자식으로 추가
				}
			}

			// 공통 조상 찾기
			int ancestor = 1; // 일단 루트 노드라고 가정
			// 우선 num1의 조상 먼저 찾기
			List<Integer> ac = new ArrayList<>();
			while (num1 != 1) { // 루트 노드일 때까지 부모 노드 찾아서 리스트에 넣어주기
				num1 = findRoot(num1);
				ac.add(num1);
			}
			here: while (num2 != 2) {
				num2 = findRoot(num2); // num2의 조상 찾았으면
				for (int i = 0; i < ac.size(); i++) { // num1의 조상 중 같은 조상 있는지 비교
					if (ac.get(i) == num2) {
						ancestor = num2; // 1 외에도 공통 조상 있으면 그걸로 바꿔줌
						break here;
					}
				}
			}

			// 가장 가까운 공통 조상의 번호와 그것을 루트로 하는 서브 트리의 크기
			size = 0;
			traverse(ancestor);

			System.out.printf("#%d %d %d\n", tc, ancestor, size);
		} // testcase
	}

	// x라는 번호를 갖는 노드의 부모 노드 번호 구하기
	public static int findRoot(int x) {
		for (int i = 1; i < V + 1; i++) {
			if (tree[i][0] == x || tree[i][1] == x) {
				return i;
			}
		}
		return -1;
	}

	// x를 루트로 하는 서브 트리 순회하기, 전위 순회
	public static void traverse(int x) {
		// V
		size++;

		// L
		if (tree[x][0] != 0) {
			traverse(tree[x][0]);
		}

		// R
		if (tree[x][1] != 0) {
			traverse(tree[x][1]);
		}
	}
}