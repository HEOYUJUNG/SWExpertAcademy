package D3;

import java.util.Scanner;

public class P2817_부분순열의합3 {
	static int N;
	static int K;
	static int[] A;
	static int cnt;
	static boolean[] sel;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 수열에 있는 원소 개수
			K = sc.nextInt(); // 부분 수열의 합이 K가 되는 경우의 수 구하기
			A = new int[N];
			sel = new boolean[N]; // 해당 원소를 선택했는지 안했는지
			for (int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
			}

			cnt = 0;  // 카운트 초기화!!
			powerset(0);
			System.out.println("#" + tc + " " + cnt);
		} // testcase
	}

	public static void powerset(int k) {
		// 기저 조건
		if (k == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (sel[i]) {
					sum += A[i];
				}
			}
			if (sum == K) {
				cnt++;
			}
			return;
		}

		sel[k] = true; // k번재 원소 포함하는 경우
		powerset(k + 1);

		sel[k] = false; // k번재 원소 포함하지 않는 경우
		powerset(k + 1);
	}
}
