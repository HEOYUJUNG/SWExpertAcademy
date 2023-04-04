package 분류없음;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P5650_핀볼게임 {
	// 상하좌우 (0, 1, 2, 3)
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// 반대방향
	static int[] reverseDir = { 1, 0, 3, 2 };

	// 순서대로 1~5번을 만났을 때 바뀌는 방향
	static int[][] block = { null, { 1, 3, 0, 2 }, { 3, 0, 1, 2 }, { 2, 0, 3, 1 }, { 1, 2, 3, 0 }, { 1, 0, 3, 2 } };

	static class Wormhole {
		int r, c, num; // 위치, 웜홀 번호

		public Wormhole(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] map = new int[N + 2][N + 2];
			List<Wormhole> wh = new ArrayList<>();

			// 바깥 테두리는 5번 블록으로 채우기 (5번 처리만 해주면 따로 범위 check 할 필요 없이 테두리도 처리 가능해짐!!)
			for (int i = 0; i < N + 2; i++) {
				// 위쪽 변, 오른쪾 변, 아래쪽 변, 왼쪾 변
				map[0][i] = map[i][0] = map[N + 1][i] = map[i][N + 1] = 5;
			}

			// 핀볼 게임판 입력받기
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					map[r][c] = sc.nextInt();
					// 웜홀은 따로 관리해주자 (6 ~ 10)
					if (map[r][c] >= 6) {
						wh.add(new Wormhole(r, c, map[r][c]));
					}
				}
			}


			int maxScore = 0;

			// 다 돌면서 빈 공간을 출발 위치로 고르기
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (map[r][c] == 0) {
						// 각각의 출발위치에서 4가지 방향으로 출발해보기
						for (int startD = 0; startD < 4; startD++) {
							int score = 0;
							int R = r; // 밑에서 쓸 변수 (위에서 for문 돌리는 변수는 손대면 안됨!)
							int C = c;
							int d = startD;
							here: while (true) {
								// d : 현재 진행 중인 방향
								int rr = R + dr[d]; // 이동할 곳
								int cc = C + dc[d];
								if (map[rr][cc] == -1 || (rr == r && cc == c)) { // 블랙홀인 경우 or 제자리로 돌아온 경우
									maxScore = (maxScore < score) ? score : maxScore; // 점수 갱신하고
									break here; // 종료
								} else if (map[rr][cc] == 0) { // 빈공간인 경우는 따로 고려해줄 거 없이 그냥 진행하면 됨.
									R = rr;
									C = cc;
									continue;
								} else if (map[rr][cc] >= 1 && map[rr][cc] <= 5) { // 블록인 경우
									if (block[map[rr][cc]][d] == reverseDir[d]) { // 블록에 맞고 나서 바뀐 방향이 지금까지 오던 방향과 반대인 경우
										// 어차피 똑같은 길로 돌아갈 거니까 점수 갱신해주고
										maxScore = (maxScore < score * 2 + 1) ? score * 2 + 1 : maxScore;
										break here; // 종료
									}
									d = block[map[rr][cc]][d]; // 방향 바꿔주고
									score++; // 점수 증가
								} else if (map[rr][cc] >= 6) { // 웜홀인 경우
									// 같은 번호의 다른 웜홀 찾아서 거기로 이동!
									for (Wormhole h : wh) {
										if (h.num == map[rr][cc] && (h.r != rr || h.c != cc)) { // 같은 번호이면서 위치 다른 웜홀 찾으면
											rr = h.r;
											cc = h.c;
											// 방향은 안바뀜
											break;
										}
									}
								}

								R = rr;
								C = cc;
							} // while
						}
					}
				}
			}
			System.out.println("#" + tc + " " + maxScore);
		} // testcase
	} // main
}
