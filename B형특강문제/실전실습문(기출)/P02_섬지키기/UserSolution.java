package P02_섬지키기;

import java.util.*;

class UserSolution {
	public final int MAX_N = 20;
	public final int MAX_HASH = 9999; // 구조물 최대 크기 5 => 해시값은 최대 4자리

	public int n;
	// 바깥쪽에 0으로 padding
	public int[][] initMap = new int[MAX_N + 2][MAX_N + 2];
	public int[][] modifiedMap = new int[MAX_N + 2][MAX_N + 2];

	// 특정 해시값을 가질 수 있는 후보들, 시뮬레이션을 위해서는 위치 정보 필요
	public class Candidate {
		int r, c;
		boolean isHorizontal, isReverse;

		public Candidate(int r, int c, boolean isHorizontal, boolean isReverse) {
			this.r = r;
			this.c = c;
			this.isHorizontal = isHorizontal;
			this.isReverse = isReverse;
		}
	}

	public List<Candidate>[] candidates = new List[MAX_HASH + 1];

	public void init(int N, int mMap[][]) {
		n = N;
		// 지도 입력받기
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				modifiedMap[r + 1][c + 1] = initMap[r + 1][c + 1] = mMap[r][c];
			}
		}
		// candidates 배열 초기화
		for (int i = 1; i <= MAX_HASH; i++) {
			candidates[i] = new ArrayList<>();
		}
		// 전처리 과정 (주어진 지도에 대해 구조물을 놓는 모든 경우들에 대해 계산해두기, cache)
		// 오른쪽에서 왼쪽 뺀 값들 -> 이어붙여서 hash값 계산
		for (int length = 2; length <= 5; length++) {
			// 구조물이 가로로 놓일 경우
			for (int r = 1; r <= n; r++) {
				for (int c = 1; c <= n - length + 1; c++) {
					int hash = 0;
					for (int i = 0; i < length - 1; i++) {
						hash = 10 * hash + (initMap[r][c + i + 1] - initMap[r][c + i] + 5);
					}
					candidates[hash].add(new Candidate(r, c, true, false));
					int reverseHash = 0;
					for (int i = length - 1; i > 0; i--) {
						reverseHash = 10 * reverseHash + (initMap[r][c + i - 1] - initMap[r][c + i] + 5);
					}
					// 설치 지역이 동일하다면 같은 경우로 취급. 즉, 대칭인 경우는 하나만 후보로 넣어야 함
					if (hash != reverseHash) {
						candidates[reverseHash].add(new Candidate(r, c, true, true));
					}
				}
			}
			// 구조물이 세로로 놓일 경우
			for (int c = 1; c <= n; c++) {
				for (int r = 1; r <= n - length + 1; r++) {
					int hash = 0;
					for (int i = 0; i < length - 1; i++) {
						hash = 10 * hash + (initMap[r + i + 1][c] - initMap[r + i][c] + 5);
					}
					candidates[hash].add(new Candidate(r, c, false, false));
					int reverseHash = 0;
					for (int i = length - 1; i > 0; i--) {
						reverseHash = 10 * reverseHash + (initMap[r + i - 1][c] - initMap[r + i][c] + 5);
					}
					if (hash != reverseHash) {
						candidates[reverseHash].add(new Candidate(r, c, false, true));
					}
				}
			}
		}
	}

	public int numberOfCandidate(int M, int mStructure[]) {
		if (M == 1) {
			return n * n;
		}

		// 구조물에서는 map에서와는 반대로, 왼쪽에서 오른쪽 뺀 값들 이어붙여서 hash값 계산!

		// 맨 앞자리가 0인 경우가 있다면 자리수가 달라도 같은 hash값이 나올 것임..
		// 그러나 앞뒤값의 차이에 5를 더하기 때문에 1~9의 범위를 가진다.
		// 서로 다른 경우에서 같은 hash값이 나오는 경우가 발생하지 않기 때문에 괜찮다!!
		int hash = 0;
		for (int i = 0; i < M - 1; i++) {
			hash = 10 * hash + (mStructure[i] - mStructure[i + 1] + 5);
		}
		return candidates[hash].size();
	}

	public int maxArea(int M, int mStructure[], int mSeaLevel) {
		int ret = -1; // 설치할 수 없다고 가정
		if (M == 1) {
			for (int r = 1; r <= n; r++) {
				for (int c = 1; c <= n; c++) {
					modifiedMap[r][c] = initMap[r][c] + mStructure[0];
					ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
					modifiedMap[r][c] = initMap[r][c]; // 원상복구
				}
			}
			return ret;
		}

		int hash = 0;
		for (int i = 0; i < M - 1; i++) {
			hash = hash * 10 + (mStructure[i] - mStructure[i + 1] + 5);
		}
		for (Candidate wall : candidates[hash]) {
			if (wall.isHorizontal) { // 가로로 있는 경우
				int height = mStructure[0]
						+ (wall.isReverse ? initMap[wall.r][wall.c + M - 1] : initMap[wall.r][wall.c]);
				for (int i = 0; i < M; i++) {
					modifiedMap[wall.r][wall.c + i] = height;
				}
				ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
				for (int i = 0; i < M; i++) { // 원상복구
					modifiedMap[wall.r][wall.c + i] = initMap[wall.r][wall.c + i];
				}
			} else {
				int height = mStructure[0]
						+ (wall.isReverse ? initMap[wall.r + M - 1][wall.c] : initMap[wall.r][wall.c]);
				for (int i = 0; i < M; i++)
					modifiedMap[wall.r + i][wall.c] = height;
				ret = Math.max(ret, unsubmergedArea(modifiedMap, mSeaLevel));
				for (int i = 0; i < M; i++)
					modifiedMap[wall.r + i][wall.c] = initMap[wall.r + i][wall.c];
			}
		}

		return ret;
	}

	public boolean[][] check = new boolean[MAX_N + 2][MAX_N + 2];
	public int[] dr = { -1, 1, 0, 0 };
	public int[] dc = { 0, 0, -1, 1 };

	public int unsubmergedArea(int[][] mMap, int mSeaLevel) {
		Queue<int[]> q = new LinkedList<>();
		// 우선 바깥 테두리들 큐에 넣고, 방문처리
		for (int r = 0; r <= n + 1; r++) {
			for (int c = 0; c <= n + 1; c++) {
				if (r == 0 || r == n + 1 || c == 0 || c == n + 1) {
					q.add(new int[] { r, c });
					check[r][c] = true;
				} else {
					check[r][c] = false;
				}
			}
		}
		// bfs
		while (!q.isEmpty()) {
			int[] front = q.poll();
			for (int d = 0; d < 4; d++) {
				int[] rear = { front[0] + dr[d], front[1] + dc[d] };
				if (rear[0] >= 1 && rear[0] <= n && rear[1] >= 1 && rear[1] <= n) {
					if (!check[rear[0]][rear[1]] && mMap[rear[0]][rear[1]] < mSeaLevel) {
						q.add(rear);
						check[rear[0]][rear[1]] = true;
					}
				}
			}
		}
		int ret = 0;
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (!check[r][c])
					ret++;
			}
		}
		return ret;
	}
}