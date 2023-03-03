package D2;

import java.util.Scanner;

public class P13705_파동파동 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 배열의 크기
			int M = sc.nextInt(); // 파동 시작값
			int R = sc.nextInt(); // 행
			int C = sc.nextInt(); // 열
			int D = sc.nextInt(); // 증감

			// 문제에서 (1,1) 시작하니까 배열 사이즈 1 크게 만들고 행이나 열이 0인 곳은 쓰지 않기
			int[][] arr = new int[N + 1][N + 1];
			// 계속 덮어씌우니까 가장 바깥쪽 범위부터 시작해서 안쪽으로 들어오기
			int g = Math.max(Math.max(R - 1, C - 1), Math.max(N - R, N - C));
			while (g >= 0) {
				for (int r = R - g; r <= R + g; r++) {
					for (int c = C - g; c <= C + g; c++) {
						// 배열 범위 내에 들어오는 부분들만 값 넣어주기
						if (r >= 0 && r <= N && c >= 0 && c <= N) {
							if (M + D * g <= 255 && M + D * g >= 0) {
								arr[r][c] = M + D * g;
							} else if (M + D * g < 0) { // 최소 0보다 작아지면 0 저장
								arr[r][c] = 0;
							} else { // 최대 255 넘어가면 255로 저장
								arr[r][c] = 255;
							}

						}
					}
				}
				g--;
			}

			// 테케 번호 + 행 별 합 출력
			System.out.print("#" + tc);
			for (int r = 1; r <= N; r++) {
				int sum = 0;
				for (int c = 1; c <= N; c++) {
					sum += arr[r][c];
				}
				System.out.print(" " + sum);
			}
			System.out.println();
		} // testcase
	}
}
