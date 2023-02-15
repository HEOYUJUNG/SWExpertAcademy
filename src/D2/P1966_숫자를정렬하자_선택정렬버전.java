package SWEA;


import java.util.Scanner;

public class P1966_숫자를정렬하자_선택정렬버전 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			// 입력 받아서 배열로 저장
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = sc.nextInt();
			}

			// 선택정렬
			for (int j = 0; j < N - 1; j++) {
				int min = arr[j];
				int idx = j;
				for (int t = j + 1; t < N; t++) {
					if (arr[t] < min) {
						min = arr[t];
						idx = t;
					}
				}
				int temp = arr[idx];
				arr[idx] = arr[j];
				arr[j] = temp;
			}

			System.out.printf("#%d ", i + 1);
			for (int j = 0; j < N; j++) {
				System.out.printf("%d ", arr[j]);
			}
			System.out.println();
		}
	} // main
}
