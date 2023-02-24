package D2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P15706_일회용 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 신입사원 수
			int N = sc.nextInt();
			// 각 분반별 최소 인원
			int K_MIN = sc.nextInt();
			// 각 분반별 최대 인원
			int K_MAX = sc.nextInt();

			// 성적 저장할 배열
			int[] score = new int[N];
			// N명의 어학 성적 입력받기
			for (int i = 0; i < N; i++) {
				score[i] = sc.nextInt();

			}

			// 성적 오름차순으로 정렬
			Arrays.sort(score);

			// 성적에서 중복 제거한 배열 새로 만들기 -> 점수컷 따질 때 사용
			List<Integer> cut = new ArrayList<>();
			cut.add(score[0]);
			for (int i = 1; i < N; i++) {
				if (score[i] != cut.get(cut.size() - 1)) {
					cut.add(score[i]);
				}
			}

			// 처음에는 T1을 전체 성적 중 최소값, T2를 전체 성적 중 최대값으로 설정.
			// 단, C분반 나눌 때 T1미만이 아닌, T1 이하로 생각하자.
			// B반은 T1초과 T2 미만
			int T1 = cut.get(0);
			int T2 = cut.get(cut.size() - 1);
			// 제일 신입 사원이 많은 분반과 가장 적은 분반의 차의 최소값
			int minD = 0;

			// 분반별 최소 및 최대 인원 조건 만족하는지 확인
			while (true) {
				// A, B, C 반의 인원
				int A = 0;
				int B = 0;
				int C = 0;
				// A,B,C반 인원 세보기
				for (int i = 0; i < N; i++) {
					if (score[i] <= T1) {
						C++;
					} else if (score[i] >= T2) {
						A++;
					} else {
						B++;
					}
				}

				// 최소, 최대 인원 만족하면 가장 가장 많은 분반과 적은 분반 차이 구하기 + 최소값 갱신
				if (A >= K_MIN && A <= K_MAX && B >= K_MIN && B <= K_MAX && C >= K_MIN && C <= K_MAX) {
					int max = Math.max(Math.max(A, B), C);
					int min = Math.min(Math.min(A, B), C);
					if (max - min < minD) {
						minD = max - min;
					}
				}

				// T1, T2 바꿔주기
				T1++;
				T2--;

			}

//			System.out.printf("#%d %d", tc, minD);
		} // testcase
	} // main
}
