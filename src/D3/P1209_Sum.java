package D3;

import java.util.Scanner;

public class P1209_Sum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테케 10개
		for (int tc = 0; tc < 10; tc++) {
			// 테케 번호
			int T = sc.nextInt();

			// 배열 입력받기
			// 배열 크기 100*100
			int[][] arr = new int[100][100];
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					arr[r][c] = sc.nextInt();
				}
			}

			int cSum = 0; // 각 열의 합 중 최댓값
			int rSum = 0; // 각 행의 합 중 최댓값
			int dSum = 0; // 각 대각선의 합 중 최댓값
			// 각 열 합의 최댓값 구하기
			for (int c = 0; c < 100; c++) {
				int sum = 0; // 현재 열의 합
				for (int r = 0; r < 100; r++) {
					sum += arr[r][c];
				}
				// 최댓값 갱신
				if (sum > cSum) {
					cSum = sum;
				}
			}

			// 각 행 합의 최댓값 구하기
			for (int r = 0; r < 100; r++) {
				int sum = 0; // 현재 행의 합
				for (int c = 0; c < 100; c++) {
					sum += arr[r][c];
				}
				// 최댓값 갱신
				if (sum > rSum) {
					rSum = sum;
				}
			}

			// 각 대각선 합의 최댓값 구하기
			for (int r = 0; r < 100; r++) {
				int sum1 = 0;
				int sum2 =0;
				sum1 += arr[r][r];
				sum2 += arr[r][99-r];
				dSum = Math.max(sum1, sum2);
			}

			System.out.printf("#%d %d\n",T,Math.max(dSum, Math.max(rSum, cSum)));
		} // testcase
	} // main
}
