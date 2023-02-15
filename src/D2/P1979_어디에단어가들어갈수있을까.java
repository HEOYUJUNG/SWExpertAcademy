package D2;

import java.util.Scanner;

public class P1979_어디에단어가들어갈수있을까 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테케 수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// N*N 단어퍼즐
			int N = sc.nextInt();
			// 단어 길이
			int K = sc.nextInt();

			// 단어 퍼즐 모양 입력받기
			// 오른쪽and아래쪽에 검은색, 즉 0인 부분 한 줄씩 추가
			// 1: 흰색, 0: 검은색
			int[][] puzzle = new int[N+1][N+1];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					puzzle[r][c] = sc.nextInt();
				}
			}
			

			// 단어의 길이를 인덱스, 해당 길이의 단어 수를 값으로 갖는 배열
			// 단어의 최대 길이 = N
			int[] WordLength = new int[N + 1];

			// 가로 방향 길이 탐색
			for (int r = 0; r < N; r++) {
				int length = 1;
				for (int c = 1; c < N+1; c++) {
					if(puzzle[r][c-1]==1&&puzzle[r][c]==1) {
						length++;
					} else if(puzzle[r][c-1]==1 &&puzzle[r][c]==0) {
						// 이렇게 구하면 한 칸만 있는 곳도 길이 1로 잡아버리긴 하지만
						// 주어지는 단어 길이(K)가 2 이상이므로 ㄱㅊ
						WordLength[length]++;
						length=1; // 초기화
					}
				}				
			}

			// 세로 방향 길이 탐색
			for (int c = 0; c < N; c++) {
				int length = 1;
				for (int r = 1; r < N+1; r++) {
					if(puzzle[r-1][c]==1&&puzzle[r][c]==1) {
						length++;
					} else if(puzzle[r-1][c]==1 &&puzzle[r][c]==0) {
						// 이렇게 구하면 한 칸만 있는 곳도 길이 1로 잡아버리긴 하지만
						// 주어지는 단어 길이(K)가 2 이상이므로 ㄱㅊ
						WordLength[length]++;
						length=1; // 초기화
					}
				}				
			}

			// K 길이 단어가 들어갈 수 있는 자리의 수 출력
			System.out.printf("#%d %d\n", tc, WordLength[K]);
			
		} // testcase
	} // main
}
