package 분류없음;

import java.util.Arrays;
import java.util.Scanner;

public class P4012_요리사 {
	static int N;
	static int[][] S;
	static boolean[] sel;
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 식재료 수. A, B 음식에 N/2개씩 나누어 넣음
			S = new int[N][N];
			sel = new boolean[N]; // 해당 식재료를 A 음식에 넣었으면 true, B 음식에 넣었으면 false
			min = Integer.MAX_VALUE; // 두 음식 맛 차이의 최소값
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					S[i][j] = sc.nextInt();
				}
			}

			// 일단 N개의 식재료 중에 A에 넣을 식재료 N/2개 고르기
			comb(0, 0);

			System.out.println("#" + tc + " " + min);

		} // testcase
	} // main

	public static void comb(int idx, int select) {
		// 기저 조건
		if (select == N / 2) { // A 음식에 들어갈 재료 N/2개 다 골랐다!
			// A음식의 맛, B음식의 맛 구해서 맛 차이 구하기
			int A = 0; // A음식 맛
			int B = 0; // B음식 맛
			for (int i = 0; i < N; i++) {
				if (sel[i]) {
					// 이 재료는 A 음식에 들어간다
					for (int j = 0; j < N; j++) {
						if (sel[j] && j != i) {
							// A 음식에 들어가면서 i번째 재료와 다른 재료 j
							A += S[i][j];
						}
					}
				} else {
					// 이 재료는 B 음식에 들어간다
					for (int j = 0; j < N; j++) {
						if (!sel[j] && j != i) {
							// B 음식에 들어가면서 i번째 재료와 다른 재료 j
							B += S[i][j];
						}
					}
				}
			}
//			System.out.println(Arrays.toString(sel));
//			System.out.println(Math.abs(A - B));
			// 최소값 갱신
			min = (Math.abs(A - B) < min) ? Math.abs(A - B) : min;
		}

		if (idx == N) { // 다 안 골랐어도 식재료 탐색 다 했으면 종료
			return;
		}

		// 유도 조건
		sel[idx] = true; // idx번째 재료 A에 넣는다
		comb(idx + 1, select + 1); // idx+1번재 재료 뽑을건지 말건지 보러가자
		sel[idx] = false; // idx번재 재료 A에 안 넣는다 (=B에 넣는다)
		comb(idx + 1, select); // idx+1번재 재료 뽑을건지 말건지 보러가자
	}
}
