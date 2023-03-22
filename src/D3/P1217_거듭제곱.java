package D3;

import java.util.Scanner;

public class P1217_거듭제곱 {
	static int M;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			int T = sc.nextInt();
			int N = sc.nextInt();
			M = sc.nextInt();
			arr = new int[M + 1];
			arr[0] = 1;
			System.out.println("#" + tc + " " + pow(N, M));
		} // testcase
	}

	private static int pow(int C, int N) {
		// 기저 조건
		if (arr[N] != 0) {
			return arr[N];
		}

		// 재귀 조건
		// 1. 짝수일 때
		if (N % 2 == 0) {
			arr[N] = pow(C, N / 2) * pow(C, N / 2);
			return arr[N];
		} else { // 2. 홀수일 때
			arr[N] = pow(C, N / 2) * pow(C, N / 2) * C;
			return arr[N];
		}
	}
}
