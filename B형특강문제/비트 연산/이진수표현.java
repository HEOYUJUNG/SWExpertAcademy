package B형특강;

import java.util.Scanner;

public class 이진수표현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int N = sc.nextInt();
			int M = sc.nextInt();
			int check = (1 << N) - 1;
			if ((M & check) == check) {
				sb.append("ON\n");
			} else {
				sb.append("OFF\n");
			}
		}
		System.out.println(sb);
	}
}
