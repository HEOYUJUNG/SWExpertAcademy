package D3;

import java.util.Arrays;
import java.util.Scanner;

public class P1860_진기의최고급붕어빵 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 사람 수
			int M = sc.nextInt(); // M초 시간 동안
			int K = sc.nextInt(); // K개 붕어빵 만듦
			boolean flag = true;

			int[] time = new int[N];
			// N명의 사람이 붕어빵을 사러 옴
			for (int i = 0; i < N; i++) {
				time[i] = sc.nextInt(); // 현재 사람이 도착한 시간
			}
			// 먼저 도착한 사람이 앞에 오도록 정렬
			Arrays.sort(time);
			for (int i = 0; i < N; i++) {
				int boong = (time[i] / M * K - i);
				if (boong <= 0) {
					flag = false;
					break;
				}
			}

			if (flag) { // 모든 손님에 대해 기다리는 시간이 없이 붕어빵을 제공할 수 있으면
				System.out.printf("#%d Possible\n", tc);
			} else { // 기다려야 하는 손님이 생기면
				System.out.printf("#%d Impossible\n", tc);
			}
		} // testcase
	}
}
