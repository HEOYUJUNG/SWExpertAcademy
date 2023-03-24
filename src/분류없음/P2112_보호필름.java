package 분류없음;

import java.util.Arrays;
import java.util.Scanner;

public class P2112_보호필름 {
	static int D;
	static int W;
	static int K;
	static int[][] cell;
	static int min;
	static int[][] copy; // 약품 주입할 때 원본 복사해서 저장해둘 배열
	static int[] A;
	static int[] B;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt(); // 보호 필름 두께 3<=D<=13
			W = sc.nextInt(); // 보호 필름 가로크기 1<=W<=20
			K = sc.nextInt(); // 합격 기준 (같은 특성의 cell이 깊이 방향으로 K개 이상 연속적으로 있어야 함) 1<=K<=D
			cell = new int[D][W];
			copy = new int[D][W];
			A = new int[W]; // A약품 주입 결과. 모두 0.
			B = new int[W]; // B약품 주입 결과. 모두 1.
			for (int i = 0; i < W; i++) {
				B[i] = 1;
			}

			for (int r = 0; r < D; r++) {
				for (int c = 0; c < W; c++) {
					// 0이면 A, 1이면 B
					cell[r][c] = sc.nextInt();
				}
			}

			min = K; // 약품 투입 최소 횟수. K개 연속이어야 하니까 최대 K번 넣을 수 있음

			func(0, 0);
			
			System.out.println("#" + tc + " " + min);

		} // testcase
	} // main

	// idx : 행 인덱스. 지금 몇 번째 행에 대해서 판단 중인가!
	// cnt : 지금까지 약품 주입 몇 번 했나!
	public static void func(int idx, int cnt) {

//		System.out.println("idx : " + idx + " / cnt : " + cnt);

		// 기저 조건
		if (test()) { // 검사 통과할 수 있으면 종료!
			min = (cnt < min) ? cnt : min;
//			System.out.println("검사 통과");
			return;
		}

		if (min < cnt || idx == D) {
			// 1. 현재 최소값이 min인데 중간에 min보다 커졌으면 더 볼 필요 없음. 종료!
			// 2. 검사 통과는 못했지만 끝까지 탐색 했으면 종료!
			return;
		}

		copy[idx] = cell[idx]; // idx행 잠시 copy에 복사본 만들어놓고 (주소 연결)
		
		// 유도 조건
		// 1. 약품 주입 X
		func(idx + 1, cnt);

		// 2. A 약품 주입
		injection('A', idx);
		func(idx + 1, cnt + 1); // 약품 주입 횟수 늘리고, 다음 행 보러가기

		// 3. B 약품 주입
		injection('B', idx);
		func(idx + 1, cnt + 1); // 약품 주입 횟수 늘리고, 다음 행 보러가기

		// 원상복구!

		cell[idx] = copy[idx];
//		System.out.println(Arrays.toString(copy[idx]));
//		System.out.println(Arrays.toString(cell[idx]));
	}

	// 투입하는 막의 모든 셀들은 하나의 특성으로 변경
	public static void injection(char type, int r) {
		if (type == 'A') { // A약품이면 현재 막, 즉 r행을 전부 0으로 바꾸기
			cell[r] = A; // r행에는 A 넣기
			// 주소로 잠시 연결연결 해준 것. 부품을 갈아끼웠다!
		} else { // B약품이면 현재 막, 즉 r행을 전부 1로 바꾸기
			cell[r] = B;
		}
	}

	// 성능검사. 단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는지!!
	public static boolean test() {
		for (int c = 0; c < W; c++) {
			int prev = cell[0][c]; // 이전 값 기억하기
			int cnt = 1; // 몇 개 연속인지
			for (int r = 1; r < D; r++) {
				if (prev == cell[r][c]) { // 이전 것과 같다면
					cnt++;
				} else { // 이전 것과 다르다면
					prev = cell[r][c]; // prev 바꿔주고
					cnt = 1;
				}

				if (cnt == K) { // K개 연속인거 확인됐으면
					break; // 현재 열 그만 탐색
				}
			}

			if (cnt < K) { // 탐색 끝났는데 K개 연속인 거 없었으면
				return false; // false 반환
			}
		}
		return true; // 한 번도 K개 연속 아닌 적이 없으면, 즉 검사 통과면 true 반환
	}
}