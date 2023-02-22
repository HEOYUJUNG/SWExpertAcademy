package D4;

import java.util.Scanner;

public class P4613_러시아국기같은깃발 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 깃발 크기 N행
			int N = sc.nextInt();
			// 깃발 크기 M열
			int M = sc.nextInt();
			// 깃발 2차월 배열
			char[][] flag = new char[N][M];

			// 색깔 입력받기
			for (int r = 0; r < N; r++) {
				String line = sc.next();
				for (int c = 0; c < M; c++) {
					flag[r][c] = line.charAt(c);
				}
			}

			// N을 1이상의 3개의 수로 나눌 수 있는 경우 따져보기
			int min = M * N; // 새로 칠해야 하는 칸의 최솟값 담을 변수, 최대인 M*N으로 초기화
			for (int white = 1; white <= N - 2; white++) {
				for (int blue = 1; blue <= (N - white - 1); blue++) {
					// N개의 행 중 흰색 w줄, 파란색 b줄
					// 나머지는 자동으로 빨간색. r줄
					int red = N - white - blue;

					// 새로 칠해야 하는 칸 카운트하기 위한 변수
					int cnt = 0;

					// 흰색으로 색칠되어야 하는 행
					for (int r = 0; r < white; r++) {
						// 흰색이 아니면 새로 칠해야 하는 칸 카운트
						for (int c = 0; c < M; c++) {
							if (flag[r][c] != 'W') {
								cnt++;
							}
						}
					}
					// 파란색으로 색칠되어야 하는 행
					for (int r = white; r < white + blue; r++) {
						for (int c = 0; c < M; c++) {
							if (flag[r][c] != 'B') {
								cnt++;
							}
						}
					}
					// 빨간색으로 색칠되어야 하는 행
					for (int r = white + blue; r < N; r++) {
						for (int c = 0; c < M; c++) {
							if (flag[r][c] != 'R') {
								cnt++;
							}
						}
					}

					// 최소값 갱신
					if (cnt < min) {
						min = cnt;
					}

				}
			}

			// 현재 테케에서 최소값 출력
			System.out.printf("#%d %d\n", tc, min);
		} // testcase
	} // main
}
