package SWEA;

import java.util.Scanner;

public class P12487_이진탐색 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			int P = sc.nextInt(); // 전체 쪽수
			int Pa = sc.nextInt(); // A가 찾을 쪽 번호
			int Pb = sc.nextInt(); // B가 찾을 쪽 번호

			
			// A
			int start = 1;
			int end = P;
			int Acnt = 0;
			
			while (start <= end) {
				Acnt++;
				int c = (start + end) / 2;  // 중간 페이지
				if (c == Pa) {
					break;
				} else if (c > Pa) {
					end = c ;
				} else {
					start = c ;
				}
			}
			
			// B
			start = 1;
			end = P;
			int Bcnt = 0;
			
			while (start <= end) {
				Bcnt++;
				int c = (start + end) / 2;  // 중간 페이지
				if (c == Pb) {
					break;
				} else if (c > Pb) {
					end = c ;
				} else {
					start = c ;
				}
			}

			if (Acnt>Bcnt) {
				System.out.printf("#%d %c\n",tc+1,'B');
			} else if(Acnt<Bcnt) {
				System.out.printf("#%d %c\n",tc+1,'A');
			} else {
				System.out.printf("#%d %d\n",tc+1,0);
			}

		} // testcase
	}// main
}
