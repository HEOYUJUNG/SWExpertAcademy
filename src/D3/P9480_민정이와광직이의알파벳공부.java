package D3;

import java.util.Arrays;
import java.util.Scanner;

public class P9480_민정이와광직이의알파벳공부 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 민정이가 현재 알고 있는 영어 단어 개수
			String[] words = new String[N];
			int[] length = new int[N];
			for (int i = 0; i < N; i++) {
				words[i] = sc.next();
				length[i] = words[i].length();
			}
			Arrays.sort(length);
			int sum = 0;
			int minW = 0; // 알파벳 26개가 다 있어야 한다고 할 때의 최소한 필요한 단어 개수.
			for (int i = N - 1; i >= 0; i++) {
				sum += length[i];
				if (sum >= 26)
					break;
			}

			// N개의 단어들 중 몇 번 인덱스에 있는 단어를 고를 것인지 그 인덱스를 저장하는 배열
			// 한 세트에 최대 N개의 단어가 들어갈 수 있으니까 배열 크기는 N
			int[] wordsIdx = new int[N];
			int set = 0;
			// N개의 단어 중에서 minW개 이상의 단어를 고르고
			for (int num = minW; num <= N; num++) { // num : N개의 단어 중 세트를 만들기 위해 고른 단어 개수
				for (int i = 0; i <= num; i++) {
					
				}
			}
			// 알파벳 26개 다 포함되어 있는지 확인
			// 조건 만족하면 set++

			System.out.printf("#%d %d", tc, set);
		} // testcase
	}
}
