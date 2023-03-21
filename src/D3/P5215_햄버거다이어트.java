package D3;

import java.util.Scanner;

public class P5215_햄버거다이어트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			int N = sc.nextInt(); // 재료 개수
			int L = sc.nextInt(); // 제한 칼로리
			int[] T = new int[N]; // 맛 점수
			int[] K = new int[N]; // 칼로리
			for (int i = 0; i < N; i++) {
				T[i] = sc.nextInt();
				K[i] = sc.nextInt();
			}

			int maxT = 0;
			for (int i = 0; i < (1 << N); i++) {
				int sumK = 0;
				int sumT = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) > 0) {
						sumK += K[j];
						sumT += T[j];
					}
				}
				if (sumK <= L) {
					maxT = (maxT < sumT) ? sumT : maxT;
				}
			}

			System.out.println("#" + tc + " " + maxT);
		} // testcase
	} // main
}
