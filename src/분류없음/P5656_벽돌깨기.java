package 분류없음;

import java.util.Scanner;

public class P5656_벽돌깨기 {
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, W, H, min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 구슬 개수
			W = sc.nextInt(); // 가로
			H = sc.nextInt(); // 세로

			int[][] map = new int[H][W];

			// 벽돌 정보 입력받기
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					map[r][c] = sc.nextInt();
				}
			}

			min = H * W; // 최대한 많은 벽돌을 제거했을 때의 남은 벽돌 개수

			trial(0, map);


			int cnt = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (map[r][c] > 0) {
						cnt++;
					}
				}
			}
			min = Math.min(cnt, min);
			// 남은 벽돌 개수 출력
			System.out.println("#" + tc + " " + min);
		} // testcase
	} // main

	// idx번째 구슬
	private static void trial(int idx, int[][] map) {
		// 기저 조건
		if (idx == N || isEmpty(map)) { // 구슬 N개 다 쓰면 끝!
			int cnt = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (map[r][c] > 0) {
						cnt++;
					}
				}
			}

//			if (cnt < min) {
//				System.out.println("idx : " + idx);
//				System.out.println("cnt : " + cnt);
//				for (int r = 0; r < H; r++) {
//					System.out.println(Arrays.toString(map[r]));
//				}
//				System.out.println();
//			}

			min = Math.min(cnt, min);
			return;
		}

		int[][] copy = new int[H][W];
		for (int r = 0; r < H; r++) {
			copy[r] = map[r].clone();
		}
		// 유도 조건
		for (int c = 0; c < W; c++) {
			if (copy[H - 1][c] > 0) { // 아직 벽돌이 남아 있는 열 골라서 (가장 밑바닥이 남아있으면 남아있는 것임!)
				int r = findTop(c, copy);
				remove(r, c, copy); // 구슬 던지기
				copy = fill(copy); // 벽돌 깨고 나서 빈 공간들 재정비 해주기

				trial(idx + 1, copy); // 다음 시도 하러 가기

				for (int i = 0; i < H; i++) { // 원상복구
					copy[i] = map[i].clone();
				}
			}
		} // for
	}

	// (r,c) 벽돌이 제거될 때, 범위 내의 벽돌들 제거하기
	private static void remove(int r, int c, int[][] map) {
		int range = map[r][c]; // 범위 받아오고
		map[r][c] = 0; // (r,c) 칸 제거
		for (int x = 1; x < range; x++) { // (range-1) 칸만큼 같이 제거됨 (1이면 그냥 패쓰)
			for (int d = 0; d < 4; d++) { // 방향별로 아래, 왼쪽, 오른쪽 제거하러 가기 (위에는 어차피 없음!)
				int rr = r + dr[d] * x;
				int cc = c + dc[d] * x;
				if (check(rr, cc) && map[rr][cc] > 0) { // 배열 범위 벗어나지 않으면
					remove(rr, cc, map); // 제거하러 가기
				}
			}
		}
	}

	private static int[][] fill(int[][] map) {
		for (int c = 0; c < W; c++) { // 모든 열 한번씩 확인하기
			// 두 포인터 모두 밑바닥에서 시작
			int start = H - 1;
			int end = H - 1;
			while (end >= 0) { // 그 열의 끝까지 다 볼 때까지 반복
				if (map[end][c] > 0) { // 뭔가 있다면
					map[start][c] = map[end][c]; // 내려주고
					if (start != end) {
						map[end][c] = 0; // 빈칸 만들기
					}
					start--; // 포인터 둘 다 이동
					end--;
				} else { // 빈 칸이면
					end--; // 끝 포인터만 이동
				}
			}
		}
		return map;
	}

	// 범위 체크
	private static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

	// 어떤 열에서 가장 위쪽 벽돌이 무슨 행에 있는지 반환
	private static int findTop(int c, int[][] map) {
		for (int r = 0; r < H; r++) {
			if (map[r][c] > 0) {
				return r;
			}
		}
		return -1; // 그 열이 비어있으면 -1 반환
	}

	private static boolean isEmpty(int[][] map) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
