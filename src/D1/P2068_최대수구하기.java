package SWEA_LIST;

import java.util.Scanner;

public class P2068_최대수구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int max = 0;
			for (int j = 0; j < 10; j++) {
				int num = sc.nextInt();
				if (num > max)
					max = num;
			}
			System.out.printf("#%d %d\n", i + 1, max);
		}
	}
}
