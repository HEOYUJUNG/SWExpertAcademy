package D2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P5097_회전 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 숫자 개수, 3<=N<=20
			int N = sc.nextInt();
			// 작업 반복 횟수, N<=M<=1000
			int M = sc.nextInt();
			Queue<Integer> q = new LinkedList<>();

			// N개 숫자 입력받기
			for (int i = 0; i < N; i++) {
				q.add(sc.nextInt());
			}

			for (int i = 0; i < M; i++) {
				q.offer(q.poll());
			}

			System.out.printf("#%d %d\n", tc, q.peek());
		}
	}
}
