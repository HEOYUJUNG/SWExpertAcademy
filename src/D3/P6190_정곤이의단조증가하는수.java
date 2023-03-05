package D3;

import java.util.Scanner;

public class P6190_정곤이의단조증가하는수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 양의 정수의 개수
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
			}

			int max = -1;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					int result = A[i] * A[j];
					int temp = result / 10;
					int inc = result % 10;
					boolean flag = true;
					while (flag && temp > 0) {
						flag = (temp % 10 <= inc) ? true : false;
						inc = temp % 10;
						temp /= 10;
					}
					if (flag) {
						max = (max < result) ? result : max;
					}
				}
			}

			System.out.printf("#%d %d\n", tc, max);

		}
	}
}
