package B형특강;

import java.util.Scanner;

public class 새로운불면증치료법_시프트연산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int total = (1 << 10) - 1; // 1023. 모든 수가 체크되었다면 이 값이 되어야 함.
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int k = 0;
			int check = 0;
			for (k = 1;; k++) {
				char[] arr = String.valueOf(N * k).toCharArray();
				for (char c : arr) {
					int num = c - '0';
					check = check | (1 << num);
				}
				if (check == total)
					break;
			}
			System.out.println("#" + tc + " " + k * N);
		}
	}
}
