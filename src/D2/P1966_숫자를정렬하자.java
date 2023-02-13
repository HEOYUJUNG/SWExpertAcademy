package SWEA_LIST;

import java.util.Scanner;

public class P1966_숫자를정렬하자 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();		

		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = sc.nextInt();
			}

			// 버블 정렬
			for (int s = 0; s < N; s++) { // pass 개수(N-1)
				for (int t = 0; t < N - 1 - s; t++) {
					if (arr[t] > arr[t + 1]) {
						int temp = arr[t];
						arr[t] = arr[t + 1];
						arr[t + 1] = temp;
					}
				}
			}

			System.out.printf("#%d ", i + 1);
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", arr[j]);
			}
			System.out.println();

		} // testcase
	} // main
}
