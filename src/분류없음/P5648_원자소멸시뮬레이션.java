// 골드4

package 분류없음;

import java.util.ArrayList;
import java.util.Scanner;

public class P5648_원자소멸시뮬레이션 {
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int sum, N;
	static ArrayList<Atom> atoms;

	private static class Atom {
		int r, c, d, K; // 위치, 방향, 에너지

		public Atom(int r, int c, int d, int k) {
			this.r = r;
			this.c = c;
			this.d = d;
			K = k;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 원자 개수 1 ~ 1000
			atoms = new ArrayList<>(); // 원자들 (N개) 저장할 리스트

			// -1000 ~ 1000이면 총 2001칸. 그런데 충돌이 정수 칸에서만 일어나지 않음. 0.5 인 곳에서도 일어날 수 있으니까 이걸 위해서
			// 2배씩 늘려주자
			// => -2000 ~ 2000
			// => 양수로 밀면 0 ~ 4000
			int[][] map = new int[4001][4001];

			// 원자 정보 입력받기
			for (int i = 0; i < N; i++) {
				int c = sc.nextInt(); // x 위치 -1000 ~ 1000
				int r = sc.nextInt(); // y 위치
				int d = sc.nextInt(); // 이동 방향 (0, 1, 2, 3)
				int K = sc.nextInt(); // 보유 에너지 1 ~ 100
				atoms.add(new Atom(r * 2, c * 2, d, K)); // 배열을 2배씩 늘려놨으니까 위치도 2배 해서 넣기!
				map[r][c]++; // (r,c) 위치에 원자가 하나 있음!
			}

			sum = 0; // 방출되는 에너지 총합

			while (!atoms.isEmpty()) { // 원자들 다 나갈 때까지 반복
				// 1초 지난 상황. 원자들 다 이동시키자
				int size = atoms.size(); // 반복 횟수
				int idx = 0; // 탐색 중인 인덱스
				for (int i = 0; i < size; i++) {
					Atom a = atoms.get(idx);
					// 일단 지금 위치에서 없애기
					map[a.r][a.c]--;

					// 방향대로 이동 시키기
					switch (a.d) {
					case 0: // 상
						a.r -= 1;
						break;
					case 1: // 하
						a.r += 1;
						break;
					case 2: // 좌
						a.c -= 1;
						break;
					default: // 우
						a.c += 1;
					}

					// 나갔으면 리스트에서 제거하자
					if (a.r < 0 || a.r > 4000 || a.c < 0 || a.c > 4000) {
						atoms.remove(idx);
						idx--;
					} else {
						map[a.r][a.c]++; // 위치에 다시 넣어주기
					}

					idx++;
				}

				// 이동 끝!
				// 각 원자가 있는 자리들 다 검사해보기. 2개 이상 있는 곳이라면 해당 원소들 다 없애기 + 에너지 방출
				size = atoms.size();
				idx = 0; // 탐색 중인 인덱스
				for (int i = 0; i < size; i++) {
					Atom a = atoms.get(idx);
					if (map[a.r][a.c] >= 2) {
						
					}
				}
			}

			// 방출되는 에너지의 총합을 출력
			System.out.println("#" + tc + " " + sum);
		} // testcase

	}
}
