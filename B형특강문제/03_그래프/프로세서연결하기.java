package B형특강02;

import java.io.*;
import java.util.*;

public class 프로세서연결하기 {
	static int[][] map;
	static List<int[]> coreList;
	static int N, maxCnt, minL;
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>(); // 전선 연결할 코어 후보들 (끝에 붙어있는 코어들은 제외)
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (r > 0 && r < N - 1 && c > 0 && c < N - 1 && map[r][c] == 1) {
						coreList.add(new int[] { r, c });
					}
				}
			}

			// coreList에 있는 코어들을 순서대로 연결 시작 (각각 상하좌우무 5가지 경우)
			maxCnt = 0;
			minL = 0;
			backtracking(0, 0, 0);
			sb.append("#" + tc + " " + minL + "\n");
		} // testcase
		System.out.println(sb);
	}

	// idx : 몇 번째 코어를 연결할 차례인지
	// length : 이때까지 사용한 전선의 길이
	// cnt : 이때까지 연결에 성공한 코어 개수
	// idx번 코어부터 시작해서 남은 모든 코어를 연결하는 모든 경우를 탐색해주는 함수
	static void backtracking(int idx, int length, int cnt) {
		// 갱신
		if (cnt > maxCnt) {
			maxCnt = cnt;
			minL = length;
		} else if (cnt == maxCnt) { // 같은 개수가 연결되어 있으면 전선 길이 최소가 되어야 함
			minL = (length < minL) ? length : minL;
		}
		// 종료
		if (idx == coreList.size()) {
			return;
		}

		// 유도조건
		for (int d = 0; d < 4; d++) {
			// 해당 방향으로 전선 잇기 시도
			int wireLen = extend(coreList.get(idx), d);
			// 불가능하면 skip
			if (wireLen == -1) {
				continue;
			}
			// 연결 가능하면 재귀
			backtracking(idx + 1, length + wireLen, cnt + 1);
			rollback(coreList.get(idx), d); // 원상복구
		}
		// 전선을 깔지 않는 방법
		backtracking(idx + 1, length, cnt);
	}

	// 지정된 방향으로 전선 잇는 메서드
	static int extend(int[] point, int d) {
		// 지정된 방향으로 끝까지 이을 수 있는지 판단 먼저
		int r = point[0] + dr[d];
		int c = point[1] + dc[d];
		while (inRange(r, c)) {
			if (map[r][c] != 0) // 중간에 막혀있는 경우
				return -1;
			r += dr[d];
			c += dc[d];
		}
		// 이을 수 있다면 길이 반환, 이은 곳 2로 표시
		int res = 0;
		r = point[0] + dr[d];
		c = point[1] + dc[d];
		while (inRange(r, c)) {
			map[r][c] = 2;
			res++;
			r += dr[d];
			c += dc[d];
		}
		return res;
	}

	static boolean inRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static void rollback(int[] point, int d) {
		int r = point[0] + dr[d];
		int c = point[1] + dc[d];
		while (inRange(r, c)) {
			map[r][c] = 0;
			r += dr[d];
			c += dc[d];
		}
	}
}
