package D1;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution2071 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 정수 한 개 입력받기

		for (int tc = 0; tc < T; tc++) {
			double ans = 0;
			for (int i = 0; i < 10; i++) {
				int num = sc.nextInt();

				ans += num;

			}
			System.out.println("#"+ (tc + 1) +" "+ Math.round(ans / 10));
		} // test case for문
	} // main

}
