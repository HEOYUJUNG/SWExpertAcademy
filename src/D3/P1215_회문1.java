package D3;

import java.util.Arrays;
import java.util.Scanner;

public class P1215_회문1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 1; tc++) {
			// 찾아야 하는 회문의 길이
			int length = sc.nextInt();

			// 글자판 입력받기
			char[][] board = new char[8][8];
			for (int r = 0; r < 8; r++) {
				String temp = sc.next();
				for (int c = 0; c < 8; c++) {
					board[r][c] = temp.charAt(c);
				}
				System.out.println(Arrays.toString(board[r]));
			}

			// 회문 개수 카운트
			int cnt = 0;

			// 가로 탐색
			for (int r = 0; r < 8; r++) {
				// 회문 길이 홀수 or 짝수 관계X => index : 0 ~ (length/2-1)

				// 패턴 비교할 배열 생성
				char[] pattern = new char[length / 2];
				for (int idx = 0; idx < length / 2; idx++) {
					pattern[idx] = board[r][idx];
				}

				int i = 0;// board의 행에서 탐색
				int j = 0;// pattern에서 탐색

				while (i < 8) {
					System.out.println(Arrays.toString(pattern));
					if (board[r][i+length-1-j] != pattern[j]) {						
						i -= j;
						j = -1;
						for (int idx = 0; idx < length / 2; idx++) {
							pattern[idx] = board[r][i+1+idx];
						}
					}
					i++;
					j++;
					if (j == length / 2) {
						System.out.print("r : " + r +"\n");
						i = i - j + 1;
						j = 0;
						for (int idx = 0; idx < length / 2; idx++) {
							pattern[idx] = board[r][i+idx];
						}
					}
				}
			}

			// 세로 탐색

			System.out.printf("#%d %d", tc, cnt);
		}
	} // main
}
