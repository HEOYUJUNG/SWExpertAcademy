package D3;

import java.util.Arrays;
import java.util.Scanner;

public class P9480_민정이와광직이의알파벳공부_재귀트라이 {
	static String[] words;
	static int[] wordsIdx;
	static int num; // N개의 단어 중 세트를 만들기 위해 고른 단어 개수
	static int N;
	static int set;
	static String[] Alpha = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 민정이가 현재 알고 있는 영어 단어 개수
			words = new String[N];
			int[] length = new int[N];
			for (int i = 0; i < N; i++) {
				words[i] = sc.next();
				length[i] = words[i].length();
			}
			Arrays.sort(length);
			int sum = 0;
			int minW = 0; // 알파벳 26개가 다 있어야 한다고 할 때의 최소한 필요한 단어 개수.
			for (int i = N - 1; i >= 0; i--) {
				sum += length[i];
				minW++;
				if (sum >= 26) {
					break;
				}
			}

			// N개의 단어들 중 몇 번 인덱스에 있는 단어를 고를 것인지 그 인덱스를 저장하는 배열
			// 한 세트에 최대 N개의 단어가 들어갈 수 있으니까 배열 크기는 N
			wordsIdx = new int[N];
			set = 0;
			// N개의 단어 중에서 minW개 이상의 단어를 고르고
			for (num = minW; num <= N; num++) {
				for (int i = 0; i <= N - num; i++) {
					pickWord(0, i);
				}
			}
			// 알파벳 26개 다 포함되어 있는지 확인
			// 조건 만족하면 set++

			System.out.printf("#%d %d\n", tc, set + 1);
		} // testcase
	} // main

	// 몇 번 인덱스의 단어를 골라야 할지 정하는 함수
	public static void pickWord(int x, int y) { // x : wordsIdx의 인덱스 , y : words의 인덱스
		// 기저 조건
		if (y == N - num + x + 1) { // 가능한 범위 벗어나면 종료.
			return;
		}
		// 기저 조건
		if (x == num) { // 단어 개수만큼 선택 완료

			System.out.println(Arrays.toString(wordsIdx));

			// 선택 완료되었으니까 알파벳 26개 다 포함되어 있는지 확인
			// wordsIdx에 몇 번 단어들이 선택되었는지 들어있음.
			int[] alphabet = new int[26]; // 알파벳 포함되었는지 확인할 배열
			for (int i = 0; i < num; i++) {
				String s = words[wordsIdx[i]]; // 선택된 단어
				for (int j = 0; j < 26; j++) {
					if (s.contains(Alpha[j])) {
						alphabet[j]++;
					}
				}
			}

			set++; // 일단 알파벳 다 포함한 세트라고 가정
			for (int i = 0; i < 26; i++) {
				if (alphabet[i] == 0) {
					set--;
					break; // 알파벳 빠진거 발견되면 취소.
				}
			}
//			System.out.println(set);
			return;

		}

		// 유도 조건
		wordsIdx[x] = y;
		for (int i = y + 1; i < N; i++) {
			// 고른 단어 중 x 번째가 y 인덱스 였으면 x+1번째 단어의 인덱스는 y보다 커야 함
			pickWord(x + 1, i);
		}
	}
}
