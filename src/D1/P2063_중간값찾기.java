package SWEA;

import java.util.Scanner;

public class P2063_중간값찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
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
		
		System.out.println(arr[N/2]);
		
	}
}
