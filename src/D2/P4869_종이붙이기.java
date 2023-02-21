package D2;

import java.util.Scanner;

public class P4869_종이붙이기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 테이프로 표시한 영역의 크기 20(세로)*N(가로)
			int N = sc.nextInt();
			int region = 0;

			System.out.printf("#%d %d\n", tc, region(N/10));
		}
	} // main

	
	// 10≤N≤300 -> 인덱스 1~30 필요. -> 근데 배열은 0부터 시작하니까 0은 안쓴다 생각. 
	static int[] r = new int[31];
	// 클래스 변수인 r 초기화 해주기
	static {
		// 20*10인 영역을 채울 수 있는 경우의 수는 1이다.
		r[1] = 1;
		// 20*20인 영역을 채울 수 있는 경우의 수는 3이다.
		r[2] = 3;
	}

	// 교실 바닥의 가로 길이(n)가 주어졌을 때,
	// 10*20, 20*20인 종이로 영역을 채울 수 있는 경우의 수를 반환하는 메서드
	public static int region(int n) {
		// 이전에 계산한 적이 없는 경우만 다시 재귀 계산 해줌
		if (n>=3 && r[n] == 0) {
			r[n] = region(n - 2) * 2 + region(n - 1);
		}

		// 30 이후로는 규칙 있음
		// 1. n-20인 경우에서 오른쪽에 20*20이나 20*10 위아래로 붙인 것 2장을 놓는 것
		// 2. n-10인 경우에서 오른쪽에 10*20 1장을 놓는 것
		// 위의 두가지 경우를 더한 것이 n인 경우의 수
		return r[n];
	}
}
