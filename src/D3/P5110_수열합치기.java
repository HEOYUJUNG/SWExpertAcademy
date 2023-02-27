package D3;

import java.util.LinkedList;
import java.util.Scanner;

public class P5110_수열합치기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테케 개수

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 수열 길이
			int M = sc.nextInt(); // 수열 개수

			// 길이가 N인 수열이 M개 담긴 배열
			LinkedList<Integer>[] listArr = new LinkedList[M];

			for (int i = 0; i < M; i++) {
				// 객체 생성해주기 (안하면 null인 상태)
				listArr[i] = new LinkedList<>();
				for (int j = 0; j < N; j++) {
					listArr[i].add(sc.nextInt());
				}
			}

			for (int i = 1; i < M; i++) { // 첫번째 수열과 두번째~네번째 수열 비교 -> 첫번째 수열에 결과 쌓기
				for (int j = 0; j < N * i; j++) { // (i+1)번째 수열에서의 탐색
					if (listArr[0].get(j) > listArr[i].get(0)) {
						// listArr[i]의 첫 숫자 보다 큰 수자를 listArr[0]에서 찾음.
						for (int idx = 0; idx < N; idx++) {
							listArr[0].add(j + idx, listArr[i].get(idx));
						}
						break;
					}
					// 끝까지 탐색했는데 안 들어가졌으면 끝에 넣어주기
					if (j == N * i - 1) {
						for (int idx = 0; idx < N; idx++) {
							listArr[0].add(listArr[i].get(idx));
						}
					}
				}
			}

			// 맨 뒤부터 10개의 숫자를 역순으로 출력한다.
			System.out.print("#" + tc);
			for (int i = listArr[0].size() - 1; i > listArr[0].size() - 11; i--) {
				System.out.print(" " + listArr[0].get(i));
			}
			System.out.println();

		} // testcase
	} // main
}
