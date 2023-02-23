package D3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P7102_준홍이의카드놀이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 첫 번째 카드 세트의 카드 개수
			int N = sc.nextInt();
			// 두 번째 카드 세트의 카드 개수
			int M = sc.nextInt();

			// 첫 번째 카드 세트
			Queue<Integer> cards1 = new LinkedList<>();
			for (int i = 1; i <= N; i++) {
				cards1.add(i);
			}
			// 두 번째 카드 세트
			Queue<Integer> cards2 = new LinkedList<>();
			for (int i = 1; i <= M; i++) {
				cards2.add(i);
			}

			// 숫자 합 셀 카운팅 배열 만들기
			// 2 ~ M+N 인덱스 사용 (배열 크기가 M+N+1이어야 M+N 인덱스 사용 가능)
			int[] counting = new int[M + N + 1];
			// 가장 많이 등장한 합이 몇 번 등장했는가!
			int max = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 첫 번째 카드 세트의 가장 앞에 있는 카드 + 두 번쨰 카드들 돌아가면서 하나씩 합 구하기
					int card = cards2.poll();
					counting[card + cards1.peek()]++;
					if (counting[card + cards1.peek()] > max) {
						max = counting[card + cards1.peek()];
					}
					cards2.add(card);
				}
				// 첫 번째 카드 세트의 가장 앞에 있는 카드를 다음 카드로 바꿔주기
				cards1.add(cards1.poll());
			}

			System.out.print("#" + tc);
			for (int i = 2; i < M + N + 1; i++) {
				if (counting[i] == max) {
					System.out.print(" " + i);
				}
			}
			System.out.println();

		}
	}
}
