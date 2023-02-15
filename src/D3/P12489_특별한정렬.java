package D3;

import java.util.Scanner;

public class P12489_특별한정렬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 정수의 개수
			int N = sc.nextInt();

			// 배열 입력받기
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			// 선택정렬 (내림차순 - 이유는 없음. just 연습)
			for (int i = 0; i < N - 1; i++) {
				// i번째가 가장 큰 수라고 가정
				int max = arr[i];
				int maxIdx = i;
				for (int j = i + 1; j < N; j++) {
					// i+1 ~ 끝 중에서 최대값 찾기
					if (arr[j] > max) {
						max = arr[j];
						maxIdx = j;
					}
				}
				// 최대값 맨 앞으로!
				int temp = arr[i];
				arr[i] = arr[maxIdx];
				arr[maxIdx] = temp;
			}


			// 특별한 정렬
			// 가장 큰 수, 가장 작은 수, 2번째 큰 수, 2번째 작은 수 ...
			// 큰 수와 작은 수를 번갈아 정렬
			int[] special = new int[N];
			
			// 내림차순 정렬 돼있으니까 맨 앞이 가장 큰 수, 맨 뒤가 가장 작은 수
			int maxIdx = 0;
			int minIdx = N - 1;
			System.out.print("#"+tc+" ");
			
			// 10개까지 출력
			for (int i = 0; i < 10; i++) {
				if (i % 2 == 0) { // 큰 수 들어갈 차례
					special[i] = arr[maxIdx++];
				} else { // 작은 수 들어갈 차례
					special[i] = arr[minIdx--];
				}
				System.out.print(special[i]+" ");
			}
			System.out.println();
		} // testcase
	} // main
}
