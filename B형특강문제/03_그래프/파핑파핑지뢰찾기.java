package B형특강02;

import java.io.*;
import java.util.*;

public class 파핑파핑지뢰찾기 {
	static int N;
	static char[][] map;
	// 오른쪽위부터 시계방향
	static int[] dr = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N]; // * 지뢰, . 지뢰 없는곳, c 지뢰 없는 곳 중 클릭한 곳
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 0인 칸들 먼저 클릭
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != '.')
						continue;
					if (isZero(r, c)) {
						click(r, c);
						cnt++;
					}
				}
			}

			// 남은 칸들 개수 세기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == '.')
						cnt++;
				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		} // testcase
		System.out.println(sb);
	}

	// 0인지 판단
	public static boolean isZero(int r, int c) {
		for (int d = 0; d < 8; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			// 지뢰 하나라도 있으면 false 반환
			if (rr >= 0 && rr < N && cc >= 0 && cc < N && map[rr][cc] == '*')
				return false;
		}
		return true;
	}

	public static void click(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			map[cur[0]][cur[1]] = 'c'; // 방문처리
			for (int d = 0; d < 8; d++) {
				int rr = cur[0] + dr[d];
				int cc = cur[1] + dc[d];
				if (rr < 0 || rr >= N || cc < 0 || cc >= N || map[rr][cc] != '.')
					continue;
				if (isZero(rr, cc))
					q.add(new int[] { rr, cc });
				map[rr][cc] = 'c'; // 0인 아니어도 숫자면 클릭
			}
		}
	}
}
