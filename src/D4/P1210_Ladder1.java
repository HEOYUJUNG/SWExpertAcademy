package D4;

import java.util.Scanner;

public class P1210_Ladder1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 일단 1개만 테스트
		for (int tc = 1; tc <= 10; tc++) {
			int tcN = sc.nextInt();
			int[][] ladder = new int[100][100]; // ladder[c][r] 문제에서 c 변하면 좌우로 이동
			// 목표지점부터 찾기
			int X = 0; // X표시의 c 좌표
			for (int r = 0; r < 100; r++) {
				for (int c = 0; c < 100; c++) {
					ladder[c][r] = sc.nextInt();
					if (ladder[c][99] == 2) {
						X = c;
					}
				}
			}


			// 목표지점부터 거슬러 올라가서 시작 지점 찾기
			int r = 99;
			int c = X;
			while (r > 0) {
				// 위로 이동
				r--;
				// 위로 이동했을 때 왼쪽 or 오른쪽이 1이면 옆으로 이동
				// 단, 양 끝 벗어나지 않도록!!
				if (c > 0 && c < 99) { // 양 끝이 아닌 경우 -> 오른쪽 왼쪽 전부 확인
					if (ladder[c + 1][r] == 1 || ladder[c - 1][r] == 1) {
						if (ladder[c + 1][r] == 1) { // 오른쪽 이동
							c++;
							while (ladder[c][r - 1] == 0) {
								c++;
							}
						} else if (ladder[c - 1][r] == 1) { // 왼쪽 이동
							c--;
							while (ladder[c][r - 1] == 0) {
								c--;
							}
						}
					}
				} else if (c == 0) { // 왼쪽 끝인 경우 -> 오른쪽만 확인
					if (ladder[c + 1][r] == 1) {
						c++;
						while (ladder[c][r - 1] == 0) { // 오른쪽 이동
							c++;
						}
					}
				} else { // 오른쪽 끝인 경우 -> 왼쪽만 확인
					if (ladder[c - 1][r] == 1) { // 왼쪽 이동
						c--;
						while (ladder[c][r - 1] == 0) {
							c--;
						}
					}
				}
			} // while
			System.out.printf("#%d %d\n", tc, c);
		} // test-case

	} // main
}