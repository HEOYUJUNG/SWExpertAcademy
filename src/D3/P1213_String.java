package D3;

import java.util.Scanner;

public class P1213_String {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			// 찾을 문자열
			int T = sc.nextInt();
			char[] p = sc.next().toCharArray();
			int M = p.length;
			// 검색할 문장
			char[] t = sc.next().toCharArray();
			int N = t.length;

			// t 문장 안에 있는 p 문자열 개수
			int cnt = 0;
			
			int i = 0; // t에서 탐색
			int j = 0; // p에서 탐색
			
			// 문장 끝까지 탐색 하면 while문 종료
			while (i < N) {
				if (t[i] != p[j]) { // 다음 케이스 탐색하려면 i는 +1, j는 0으로 되어야 함!
					i -= j; // 한 케이스 내에서 탐색하면서 i와 j 똑같이 증가했다는 사실 이용
					j = -1;
				}
				i++;
				j++;


				if (j == M) { // 패턴 찾은 경우!				
					cnt++;
					// 다음 케이스 탐색하려면 i는 +1, j는 0으로 되어야 함!
					i = i - j + 1;
					j = 0;
				}
			}

			System.out.printf("#%d %d\n", tc, cnt);
		} // testcase
	} // main
}
