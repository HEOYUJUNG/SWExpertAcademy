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


			// 제일 신입 사원이 많은 분반과 가장 적은 분반의 차의 최소값
			int minD = 1000;
			// 분반 별 최소 인원, 최대 인원을 만족하는 T1,T2가 있는지 여부. 일단 없다고 가정 -> 있으면 true로 바꿔주기
			boolean flag = false; 

			// 가능한 모든 T1, T2 따져보기. C분반이 T1미만이니까 C분반에 사람 있으려면 T1은 cut 리스트에서 2번째, 즉 1번 인덱스부터 탐색 시작해야 함.
			for (int i = 1; i < cut.size() - 1; i++) {
				for (int j = i + 1; j < cut.size(); j++) {  // T2는 T1보다 적어도 1 커야 함
					int T1 = cut.get(i);
					int T2 = cut.get(j);
					// 분반별 최소 및 최대 인원 조건 만족하는지 확인

					// A, B, C 반의 인원
					int A = 0;
					int B = 0;
					int C = 0;
					// A,B,C반 인원 세보기
					for (int s = 0; s < N; s++) {
						if (score[s] < T1) {
							C++;
						} else if (score[s] >= T2) {
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
						// 조건 만족하는 T1, T2 있음을 표시
						flag = true;
					}
				}
			}

			if (flag) { // 조건 만족하는 T1, T2 있으면 가장 많은 분반과 가장 적은 분반 사람 차이 출력
				System.out.printf("#%d %d\n", tc, minD);
			} else { // 조건 만족하는 T1, T2 없으면 -1 출력
				System.out.printf("#%d %d\n", tc, -1);
			}

		} // testcase
	} // main
}