package D3;

import java.util.Scanner;

public class P10726_이진수표현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 마지막 N비트가 1인지
			int M = sc.nextInt(); // 정수
			String result = "OFF";

			int num = (int) (Math.pow(2, N) - 1);
			if ((num & M) == num) {
				result = "ON";
			}
			System.out.println("#" + tc + " " + result);
		}
	}
}
