package D4;

import java.util.Scanner;

public class P1231_중위순회 {
	static String[] tree; // traverse 메서드에서도 사용해야하는 것들 static으로 메모리에 올려두기
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			N = sc.nextInt(); // 트리가 갖는 노드의 총 개수
			sc.nextLine(); // 밑에서 nextLine으로 받을 거니까 첫번째 줄의 개행 문자 버려주기!!
			tree = new String[N + 1]; // 트리. 1차원 배열로 구현.인덱스 번호 1번부터 사용하니까 배열 크기 N+1!

			for (int i = 0; i < N; i++) {
				String[] input = sc.nextLine().split(" "); // 공백 빼고 입력받기
				tree[Integer.parseInt(input[0])] = input[1]; // 트리의 해당 번호에 알파벳 넣기
			}

			System.out.printf("#%d ", tc); // traverse 안에서 출력 되니까 여기는 테케만 출력
			// 1번 노드부터 시작해서 중위순회 실행
			traverse(1);
			System.out.println();
		} // testcase
	} // main

	// 중위순회 (LVR)
	public static void traverse(int i) {
		if (i <= N) {
			// L: 왼쪽 트리로 탐색을 이어나감
			traverse(i * 2);

			// V: 자기 자신을 방문처리
			if (tree[i] != null)
				System.out.print(tree[i]); // 여기서 출력!

			// R: 오른쪽으로 탐색을 이어나감
			traverse(i * 2 + 1);
		}
	}
}
