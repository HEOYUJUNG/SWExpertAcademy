package SWEA_LIST;

import java.util.Scanner;

public class P13598_gravity {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int d = 0; // 낙차
		for (int i = 0; i < arr.length; i++) {
			int cnt = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] >= arr[i]) {
					cnt++;
				}
			}
			if (d < arr.length - 1 - i - cnt) {
				d = arr.length - 1 - i - cnt;
			}
		}

		System.out.println(d);
	}
}
