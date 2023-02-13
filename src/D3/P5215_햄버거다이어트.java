package D3;

import java.util.Scanner;

public class P5215_햄버거다이어트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int i = 0; i < tc; i++) {
			int N = sc.nextInt(); // 재료 개수
			int L = sc.nextInt(); // 제한 칼로리

			int[] T = new int[N]; // 재료 맛 점수
			int[] K = new int[N]; // 재료 칼로리
			for (int j = 0; j < N; j++) {
				T[j] = sc.nextInt();
				K[j] = sc.nextInt();
			}

			// K(칼로리) 내림차순으로 정렬
			for (int k = 0; k < N - 1; k++) {
				for (int j = 0; j < N - 1 - k; j++) {
					if (K[j] < K[j + 1]) {
						int temp = K[j];
						K[j] = K[j + 1];
						K[j + 1] = temp;
						// T 순서도 같이 바꿔줘야 함
						temp = T[j];
						T[j] = T[j + 1];
						T[j + 1] = temp;
					}
				}
			}

			// 칼로리 높은 것부터 하나씩 조합에 넣기
			int max_sumT = 0;
			for (int j = 0; j < N; j++) {
				int sumT = T[j]; // 햄버거 조합의 맛점수 합
				int sumK = K[j];				
				
				// 제한 칼로리 이하면 더 추가할 수 있나 확인 (큰 것부터)
				for (int k = j + 1; k < N; k++) {
					if (sumK + K[k] <= L) {
						sumT += T[k];
						sumK += K[k];
					} 
				}				
//				System.out.println(sumT);
				max_sumT = Math.max(max_sumT, sumT);				
			}

		} // test-case
	} // main
}
