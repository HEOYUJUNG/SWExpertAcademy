package 분류없음;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P2105_디저트카페 {
	static int N;
	static int[][] cafe;
	static List<Integer> path; // 투어 경로 저장해둘 배열
	static boolean[][] visited; // 카페 방문했는지 체크
	// 순서대로 좌상, 우상, 우하, 좌하
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { -1, 1, 1, -1 };
	static int[] direction; // 한 투어에서 방향 4가지 중에 몇 번 갔는지 체크 (1이면 간 적 있는 방향, 2면 지금 진행중인 방향)
	static int startR;
	static int startC;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 지역의 한 변 길이
			cafe = new int[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					cafe[r][c] = sc.nextInt();
				}
			}

			max = -1; // 가능한 경우 중 디저트를 가장 많이 먹을 때의 디저트 수

			// 재귀 (행, 열 제한 가능할듯!)
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j <= N - 2; j++) {
					// 모든 칸들 돌면서 출발해보기
					path = new ArrayList<>();
					visited = new boolean[N][N];
					direction = new int[N];
					startR = i; // 시작 지점 저장
					startC = j;
					cafeTour(0, i, j, -1, false);
				}
			}

			System.out.println("#" + tc + " " + max);
		} // testcase
	} // main

	// 현재 위치 (r,c)에서 갈 수 있는 방향 찾기
	// cnt : 투어 경로(path) 중 몇 번째 카페인지!
	// dir : 진행중인 방향
	// change : 이전 진행 방향이랑 바뀌었는지 여부 (바뀌었으면 true, 같으면 false)
	public static void cafeTour(int cnt, int r, int c, int dir, boolean change) {

		// 기저 조건
		// 원점으로 돌아왔을 경우(처음 시작했을 때는 말고!), 종료하고 max값 갱신
		if (r == startR && c == startC && path.size() >= 4) {
			max = (max < cnt) ? cnt : max;
			return;
		}

		// 원점으로 돌아온건 아니지만 종료해줘야 하는 조건들
		// 범위 벗어나거나 or 방문한 적 있거나 or 디저트 중복이거나 or 진행중인 방향이 아닌데 간 적 있는 방향이면
		if (dir != -1) {
			if (!check(r, c) || visited[r][c] || path.contains(cafe[r][c]) || (direction[dir] != 0 && change)) {
				return;
			}
		}

		// 중복되는 디저트 없으면
		path.add(cafe[r][c]); // 현재 위치를 경로에 추가하기
		visited[r][c] = true; // 방문체크
		if (dir != -1) {
			direction[dir]++;
		}

		// 유도 조건
		if (dir == -1) {
			// 처음 진행하는 거는 우하 방향으로 가기!
			cafeTour(cnt + 1, r + dr[2], c + dc[2], 2, true);

		} else {
			// 처음이 아니면 4가지 방향 다 보기!
			for (int d = 0; d < 4; d++) { // 대각선 4개 방향 순서대로 가보기
				// 범위 벗어나지 않고 && 방문한 적 없으면
				// 다음 카페 가기
				if (dir == d) {
					cafeTour(cnt + 1, r + dr[d], c + dc[d], d, false);
				} else {
					cafeTour(cnt + 1, r + dr[d], c + dc[d], d, true);
				}
			}
		}

		// 원상복구
		path.remove(cnt);
		visited[r][c] = false;
		if (dir != -1) {
			direction[dir]--;
		}
	}

	// 인덱스 범위 체크
	public static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}