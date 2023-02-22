package D3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P3499_퍼펙트셔플 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 덱에 놓인 카드 개수, 1 ≤ N ≤ 1,000
			int N = sc.nextInt();
			// 카드가 쌓인 덱
			Queue<String> cards1 = new LinkedList<>();
			Queue<String> cards2 = new LinkedList<>();

			// 전체 카드 중 앞의 반은 cards1에 쌓기
			// 카드가 홀수개인 경우 cards1에 한 장 더 많게 쌓음.
			for (int i = 0; i < (N + 1) / 2; i++) {
				cards1.offer(sc.next());
			}
			// 전체 카드 중 뒤의 반은 cards2에 쌓기
			for (int i = (N + 1) / 2; i < N; i++) {
				cards2.offer(sc.next());
			}

			// 셔플한 결과는 바로 스트링 빌더에 쌓기
			StringBuilder sb = new StringBuilder();
			// 카드 덱이 다 빌 때까지 sb에 쌓기
			// cards1이 cards1과 같거나 한 장 많기 대문에, cards1 비었으면 cards2도 빈 것임.
			while (!cards1.isEmpty()) {
				sb.append(cards1.poll() + " ");
				// 전체 카드가 홀수장이면 cards1은 남았지만 cards2는 없을 수도 있음.
				// 없으면 여기 건너뛰기.
				if(!cards2.isEmpty()) {
					sb.append(cards2.poll() + " ");					
				}
			}

			// 셔플 결과 출력
			System.out.printf("#%d %s\n", tc, sb);
		} // testcase
	} // main
}
