package D1;

import java.util.Scanner;
import java.io.FileInputStream;

public class Solution2029 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 정수 한 개 입력받기

		for (int tc = 0; tc < T; tc++) {
			int a = sc.nextInt();
			int b = sc.nextInt();			
			
			System.out.println("#"+ (tc + 1) +" "+ (a/b) + " " + (a%b));
		} // test case for문
	} // main

}
