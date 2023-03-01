package D3;

import java.util.Scanner;

public class P13772_그는내가아는전설의폭탄마였어 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 델타 - 순서대로 상,우,하,좌,우상,우하,좌하,좌상
		int[] dr = { -1, 0, 1, 0, -1, 1, 1, -1 };
		int[] dc = { 0, 1, 0, -1, 1, 1, -1, -1 };

		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 배열의 크기
			int P = sc.nextInt(); // 폭탄마의 폭탄 파워
			int happy = 0; // 기쁨지수, 최대값 갱신하면서 저장

			// 맵에 기쁨지수 입력 받기
			int[][] map = new int[N + P * 2 + 1][N + P * 2 + 1];
			for (int r = P; r < N + P; r++) {
				for (int c = P; c < N + P; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			for (int r = P; r < N + P; r++) {
				for (int c = P; c < N + P; c++) {
					// 폭탄 설치 위치 : (r,c)

					// 1. +폭발하는 경우
					int sum1 = map[r][c]; // 일단 폭탄 설치한 자리의 기쁨 지수 더하기
					// 2. x폭발하는 경우
					int sum2 = map[r][c];
					for (int d = 0; d < 8; d++) {
						if (d >= 0 && d < 4) { // 1. +폭발하는 경우 (상,우,하,좌)
							for (int p = 1; p <= P; p++) { // 각각의 방향으로 폭탄 타워만큼 이동
								// 합 구하기
								sum1 += map[r + dr[d] * p][c + dc[d] * p];
							}
						} else { // 2. x폭발하는 경우
							for (int p = 1; p <= P; p++) { // 각각의 방향으로 폭탄 타워만큼 이동
								// 합 구하기
								sum2 += map[r + dr[d] * p][c + dc[d] * p];
							}
						}
					}

					// 최댓값 갱신
					if (Math.max(sum1, sum2) > happy) {
						happy = Math.max(sum1, sum2);
					}
				}
			}

			System.out.printf("#%d %d\n", tc, happy);
		} // testcase
	} // main
}
