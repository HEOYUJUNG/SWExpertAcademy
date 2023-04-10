package D2;

import java.util.Scanner;

public class P1970_쉬운거스름돈 {

	static int[] money = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			int[] count = new int[8];
			int N = sc.nextInt(); // 손님에게 거슬러 주어야 할 금액

			for (int i = 0; i < 8; i++) {
				if (N >= money[i]) {
					count[i] += N / money[i];
					N %= money[i];
				}
			}

			sb.append("#" + tc + "\n");
			for (int i = 0; i < 8; i++) {
				sb.append(count[i] + " ");
			}
			System.out.println(sb);
		} // testcase
	}
}
