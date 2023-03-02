package D3;

import java.util.Scanner;

public class P2930_힙 {
	static int[] heap; // 힙(완전 이진트리)을 나타낼 배열
	static int last; // 배열에서 마지막 원소의 인덱스

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.print("#" + tc);
			int N = sc.nextInt(); // 수행해야 하는 연산의 수
			heap = new int[N + 2];
			last = 0; // 새로 삽입되는 수는 last+1에 들어와야 함.
			// N개의 연산 수행
			for (int i = 0; i < N; i++) {
				int cmd = sc.nextInt();
				if (cmd == 1) { // 연산1. 삽입 연산.
					heap[++last] = sc.nextInt(); // 마지막 노드로 삽입
					// 리프 -> 루트 올라가면서 최대힙 확인
					int cur = last;
					while (cur > 1 && heap[cur / 2] < heap[cur]) { // cur이 1이면 더 이상 비교할 부모가 없으니까 종료!
						swap(cur, cur / 2);
						cur /= 2; // 위치 갱신
					}
				} else { // 연산2. 삭제 연산.
					if (last == 0) { // 삭제할 거 없으면 -1 출력하고 종료.
						System.out.print(" " + -1);
						continue;
					}
					System.out.print(" " + heap[1]); // 루트 노드의 원소 출력 (=삭제)
					heap[1] = heap[last]; // 마지막 노드 삭제 -> 루트 노드 자리로
					heap[last--] = 0;
					// 루트 -> 리프 내려가면서 최대힙 확인
					int cur = 1;
					// 왼쪽, 오른쪽 자식 비교해서 더 큰 쪽으로 내려감 (오른쪽 자식이 없을 수도 있음!) -> 일단 true로 돌다가 조건 만나면 break
					// 하자
					while (true) { // cur는 las/2일 때까지만 자식이 있음
						int child = cur * 2; // 일단 왼쪽 자식이라고 가정
						if (child + 1 <= last && heap[child] < heap[child + 1]) { // 오른쪽 자식이 있고, 오른쪽 자식이 왼쪽 자식보다 크다면
							child++; // 오른쪽 자식을 선택
						}

						// 부모가 자식보다 크거나 같아서 힙의 조건을 만족하면 종료
						// 더 이상 자식이 없는 리프 노드에 가면 종료
						if (child > last || heap[child] <= heap[cur]) {
							break;
						}

						swap(cur, child);
						cur = child;
					}
				}
			}
			System.out.println();
		} // testcase
	} // main

	public static void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
}
