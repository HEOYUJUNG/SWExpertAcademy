package D3;

import java.util.Scanner;

public class P1216_회문2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			// 찾아야 하는 회문의 길이
			int T = sc.nextInt();

			// 글자판 입력받기
			char[][] board = new char[100][100];
			for (int r = 0; r < 100; r++) {
				String temp = sc.next();
				for (int c = 0; c < 100; c++) {
					board[r][c] = temp.charAt(c);
				}
			}

			// 회문 최대 길이
			int max = 0;

			// 가로 탐색
			for (int r = 0; r < 100; r++) {
				// 회문 길이 홀수 or 짝수 관계X => index : 0 ~ (length/2-1)
				for (int c = 0; c < 100; c++) {
					// 길이 홀수인 회문 찾기
					// c가 가운데 인덱스
					// 일단 회문이라고 가정, 양 옆으로 나가면서 같은지 확인
					// 대칭 아닌 것이 나오면 종료 => 길이 계산
					boolean flag = true;
					int i = 0;
					while (flag) {
						i++;
						if (c - i < 0 || c + i >= 100 || board[r][c + i] != board[r][c - i]) {
							flag = false;
						}
					}
					if ((i - 1) * 2 + 1 > max) {
						max = (i - 1) * 2 + 1;
					}
					// 길이 짝수인 회문 찾기
					// c가 가운데 중 왼쪽 인덱스
					// 일단 회문이라고 가정, 양 옆으로 나가면서 같은지 확인
					// 대칭 아닌 것이 나오면 종료 => 길이 계산
					flag = true;
					i = 0;
					while (flag) {
						i++;
						if (c - i + 1 < 0 || c + i >= 100 || board[r][c + i] != board[r][c - i + 1]) {
							flag = false;
						}
					}
					if ((i - 1) * 2 > max) {
						max = (i - 1) * 2;
					}
				}
			}

			// 세로 탐색
			for (int r = 0; r < 100; r++) {
				// 회문 길이 홀수 or 짝수 관계X => index : 0 ~ (length/2-1)
				for (int c = 0; c < 100; c++) {
					// 길이 홀수인 회문 찾기
					boolean flag = true;
					int i = 0;
					while (flag) {
						i++;
						if (c - i < 0 || c + i >= 100 || board[c + i][r] != board[c - i][r]) {
							flag = false;
						}
					}
					if ((i - 1) * 2 + 1 > max) {
						max = (i - 1) * 2 + 1;
					}
					// 길이 짝수인 회문 찾기
					flag = true;
					i = 0;
					while (flag) {
						i++;
						if (c - i + 1 < 0 || c + i >= 100 || board[c + i][r] != board[c - i + 1][r]) {
							flag = false;
						}
					}
					if ((i - 1) * 2 > max) {
						max = (i - 1) * 2;
					}
				}
			}

			System.out.printf("#%d %d\n", T, max);
		} // testcase
	} // main
}