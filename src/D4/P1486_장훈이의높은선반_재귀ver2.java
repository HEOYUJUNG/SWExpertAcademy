package D4;

import java.util.Scanner;

public class P1486_장훈이의높은선반_재귀ver2 {
	static int N, B, min;
	static int[] H;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 점원 인수
			B = sc.nextInt(); // 선반 높이
			H = new int[N];

			int S = 0; // 점원들 키의 합
			min = Integer.MAX_VALUE; // 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것
			for (int i = 0; i < N; i++) {
				H[i] = sc.nextInt();
				S += H[i];
			}

			powerset(0, 0, S);

			System.out.println("#" + tc + " " + min);
		} // testcase
	}

	// sum : 지금까지의 합
	// rsum : 앞으로 나한테 더해질 수 있는 합
	public static void powerset(int idx, int sum, int rsum) {
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

		// 중간에 쳐내기 222
		if (sum + rsum < B)
			// 앞으로 나한테 더해질 수 있는 거 더해도 B를 넘을 수 없다면..!! 안해
			return;

		powerset(idx + 1, sum + H[idx], rsum - H[idx]); // idx번째 점원 뽑음
		powerset(idx + 1, sum, rsum - H[idx]); // idx번째 점원 안뽑음

	}
}