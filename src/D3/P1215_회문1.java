package D3;

import java.util.Scanner;

public class P1215_회문1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			// 찾아야 하는 회문의 길이
			int length = sc.nextInt();

			// 글자판 입력받기
			char[][] board = new char[8][8];
			for (int r = 0; r < 8; r++) {
				String temp = sc.next();
				for (int c = 0; c < 8; c++) {
					board[r][c] = temp.charAt(c);
				}
			}

			// 회문 개수 카운트
			int cnt = 0;

			// 가로 탐색
			for (int r = 0; r < 8; r++) {
				// 회문 길이 홀수 or 짝수 관계X => index : 0 ~ (length/2-1)
				for (int c = 0; c < 9 - length; c++) {
					// 일단, 회문이라고 가정
					boolean flag = true;
					for (int i = 0; i < (length / 2); i++) {
						if (board[r][c + i] != board[r][c + length - 1 - i]) {
							// 회문 아니면 false로 바꿈
							flag = false;
						}
					}
					// 회문이면 카운트!
					if(flag) cnt++;
				}
			}

			// 세로 탐색
			for (int r = 0; r < 8; r++) {
				// 회문 길이 홀수 or 짝수 관계X => index : 0 ~ (length/2-1)
				for (int c = 0; c < 9 - length; c++) {
					boolean flag = true;
					for (int i = 0; i < (length / 2); i++) {
						if (board[c+i][r] != board[c + length - 1 - i][r]) {
							flag = false;
						}
					}
					if(flag) cnt++;
				}
			}

			System.out.printf("#%d %d\n", tc, cnt);
		}
	} // main
}