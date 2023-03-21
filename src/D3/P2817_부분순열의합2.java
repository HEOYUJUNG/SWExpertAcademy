package D3;

import java.util.Scanner;

public class P2817_부분순열의합2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 수열에 있는 원소 개수
			int K = sc.nextInt(); // 부분 수열의 합이 K가 되는 경우의 수 구하기
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
			}

			int cnt = 0;  // 부분 수열의 합이 K가 되는 경우의 수
			// 부분 집합 개수는 2^N = 1<<N 개
			for (int i = 0; i < (1 << N); i++) {
				// i번째 부분 집합에 포함된 원소 확인
				int sum = 0;  // i번째 부분 집합에 포함된 원소들의 합
				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) > 0) { // i번쨰 부분 집합에 j번째 원소가 포함되어 있다면
						sum += A[j];
					}
				}
				if (sum == K)
					cnt++;
			}
			System.out.println("#" + tc + " " + cnt);
		} // testcase
	}
}
