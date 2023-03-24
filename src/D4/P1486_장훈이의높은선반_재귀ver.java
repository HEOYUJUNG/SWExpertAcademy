package D4;

import java.util.Scanner;

public class P1486_장훈이의높은선반_재귀ver {
	static int N, B, min;
	static int[] H;
//	static boolean[] sel;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 점원 인수
			B = sc.nextInt(); // 선반 높이
			H = new int[N];
//			sel = new boolean[N];
			int S = 0; // 점원들 키의 합
			min = Integer.MAX_VALUE; // 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것
			for (int i = 0; i < N; i++) {
				H[i] = sc.nextInt();
				S += H[i];
			}

			powerset(0, 0);

			System.out.println("#" + tc + " " + min);
		} // testcase
	}

	public static void powerset(int idx, int sum) {
		// 중간에 쳐내기
		if (sum - B > min) {
			return;
		}
		
		// sel 배열 필요 없는 ver
		if (idx == N) {
			if (sum >= B) {
				min = Math.min(sum - B, min);
			}
			return;
		}


		powerset(idx + 1, sum + H[idx]); // idx번째 점원 뽑음
		powerset(idx + 1, sum); // idx번째 점원 안뽑음
		
		
		// sel 배열 필요한 ver
//		if (idx == N) {
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				if (sel[i]) {
//					sum += H[i];
//				}
//			}
//			if (sum >= B) {
//				min = Math.min(sum - B, min);
//			}
//			return;
//		}
//
//		sel[idx] = true;
//		powerset(idx + 1);
//		sel[idx] = false;
//		powerset(idx + 1);
	}
}