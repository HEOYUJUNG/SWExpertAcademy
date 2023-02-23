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
				
			}

			// 처음에는 T1을 전체 성적 중 최소값, T2를 전체 성적 중 최대값으로 설정.
			// 단, C분반 나눌 때 T1미만이 아닌, T1 이하로 생각하자.
			// B반은 T1초과 T2 미만
			int T1 = score[0];
			int T2 = score[N - 1];

			// 분반별 최소 및 최대 인원 조건 만족하는지 확인
			while (true) {
				// 1. 일단 A, C반 확인 -> 만족 안하면 만족 안하는 쪽 변경(T1은 다음으로 높은 성적, T2는 다음으로 낮은 성적)
				for (int i = 0; i < N; i++) {

				}
				// 2. A, C반 만족하면 -> B반 확인
				// 3. B반 만족 안하면 T1변경 -> T1 == T2 되면
				// 4. 다시 처음 T1, T2로 바꾸고 T2변경하면서 확인
			}
		}
	}
}
