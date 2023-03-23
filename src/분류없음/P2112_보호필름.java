package 분류없음;

import java.util.Scanner;

public class P2112_보호필름 {
	static int D;
	static int W;
	static int K;
	static int[][] cell;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt(); // 보호 필름 두께 3<=D<=13
			W = sc.nextInt(); // 보호 필름 가로크기 1<=W<=20
			K = sc.nextInt(); // 합격 기준 (같은 특성의 cell이 깊이 방향으로 K개 이상 연속적으로 있어야 함) 1<=K<=D
			cell = new int[D][W];

			for (int r = 0; r < D; r++) {
				for (int c = 0; c < W; c++) {
					// 0이면 A, 1이면 B
					cell[r][c] = sc.nextInt();
				}
			}

			int min = 0; // 약품 투입 최소 횟수

			int testResult = test();
			while (testResult != -1) { // 테스트 통과 할 때까지 반복
				
				
				
				testResult = test(); // 검사 결과 갱신
			}

			System.out.println("#" + tc + " " + min);

		} // testcase
	} // main

	// 투입하는 막의 모든 셀들은 하나의 특성으로 변경
	public static void drug(char type, int r) {
		if (type == 'A') { // A약품이면 현재 막, 즉 r행을 전부 0으로 바꾸기
			for (int c = 0; c < W; c++) {
				cell[r][c] = 0;
			}
		} else { // B약품이면 현재 막, 즉 r행을 전부 1로 바꾸기
			for (int c = 0; c < W; c++) {
				cell[r][c] = 1;
			}
		}
	}

	// 성능검사. 단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는지!!
	public static int test() {
		for (int c = 0; c < W; c++) {
			int prev = cell[0][c]; // 이전 값 기억하기
			int cnt = 1; // 몇 개 연속인지
			for (int r = 1; r < D; r++) {
				if (prev == cell[r][c]) { // 이전 것과 같다면
					cnt++;
				} else { // 이전 것과 다르다면
					prev = 1 - prev; // 0 아니면 1이니까 반전시켜주기
					cnt = 1;
				}

				if (cnt == K) { // K개 연속인거 확인됐으면
					break; // 현재 열 그만 탐색
				}
			}

			if (cnt < K) { // 탐색 끝났는데 K개 연속인 거 없었으면
				return c; // 검사 통과하지 못한 첫번째 열 반환
			}
		}
		return -1; // 한 번도 K개 연속 아닌 적이 없으면, 즉 검사 통과면 -1 반환
	}
}
