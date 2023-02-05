package D1;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution2025 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 정수 한 개 입력받기
		int sum = 0;
		for (int i = 1; i <= T; i++) {
			sum += i;
		}
		System.out.println(sum);
	} // main

}
