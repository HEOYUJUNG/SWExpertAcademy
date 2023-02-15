package SWEA;

import java.util.Scanner;

public class P1204_최빈수구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int Tnum = sc.nextInt();
			int[] scores = new int[101];

			// scores => j점이 몇 개 있는지
			int max = 0;
			for (int j = 0; j < 1000; j++) {
				int input = sc.nextInt();
				scores[input]++;
				if (scores[input] > max) {
					max = scores[input];
				}
			}

			// 빈도수 같은 게 있다면 가장 큰 점수만 출력
			for (int j = 100; j >= 0; j--) {
				if (scores[j] == max) {
					System.out.printf("#%d %d\n",Tnum,j);
					break;
				}
			}			
		}
	} // main
}
