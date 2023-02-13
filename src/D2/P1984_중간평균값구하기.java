package SWEA_LIST;

import java.util.Scanner;

public class P1984_중간평균값구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int max = 0;
			int min = 10000;
			double sum = 0;
			for (int j = 0; j < 10; j++) {
				int num = sc.nextInt();
				if (num > max)
					max = num;
				if (num < min)
					min = num;
				sum += num;
			}
			System.out.printf("#%d %d\n", i + 1, Math.round((sum - max - min) / 8));
		}
	} // main
}
