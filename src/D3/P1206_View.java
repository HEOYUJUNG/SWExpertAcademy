package SWEA_LIST;

import java.util.Scanner;

public class P1206_View {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10; // 테스트케이스
		
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt(); // 건물 개수
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = sc.nextInt();
			}

			int cnt = 0; // 조망권이 확보된 세대 수

			// 맨 왼쪽 두 칸과 맨 오른쪽 두 칸에는 건물이 지어지지 않는다.
			for (int s = 2; s < N - 2; s++) {
				int maxL = Math.max(arr[s - 2], arr[s - 1]);
				int maxR = Math.max(arr[s + 1], arr[s + 2]);
				if (maxL < arr[s]) {
					maxL = arr[s] - maxL;
				} else {
					maxL = 0;
				}
				
				if (maxR < arr[s]) {
					maxR = arr[s] - maxR;
				} else{
					maxR = 0;
				}
				
				cnt += Math.min(maxL, maxR);
			}
			System.out.printf("#%d %d\n", i + 1, cnt);
		} // testcase
	} // main
}
