package D3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P2817_부분순열의합 {
	static int cnt;
	static int[] A;
	static int N;
	static int K;
	static List<Integer> sel = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 수열에 있는 원소 개수
			K = sc.nextInt(); // 부분 수열의 합이 K가 되는 경우의 수 구하기
			A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = sc.nextInt();
				sel.add(0); // 원소 개수만큼 0 넣어서 sel 초기화
			}

			// nCn 중에서 합이 K인 경우
			// nCn-1 중에서 합이 K인 경우
			// ...
			// nC1 중에서 합이 K인 경우
			cnt = 0; // 경우의 수 0으로 초기화F
			for (int i = 1; i <= N; i++) {
				comb(0, 0, i);
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

	// N개 중에서 r개를 선택
	// N개의 수 중 idx번째 수를 r개의 수 중 sidx번째 수로 선택
	public static void comb(int idx, int sidx, int r) {
		// 기저 조건
		if (sidx == r) { // r개 다 뽑은 경우
			// 뽑은 원소들 합이 K인지 보기
			int sum = 0;
			for (int i = 0; i < r; i++) {
				sum += sel.get(i); // r개 뽑았으니까 거기까지만 보기!! 그 이후로는 0으로 초기화된 수들 들어있음..
			}
			if (sum == K) {
				cnt++; // K이면 카운트!
			}
			return;
		}

		if (idx == N) { // n개의 수 다 고려한 경우
			return; // 그냥 종료
		}

		// 유도 조건
		sel.set(sidx, A[idx]); // 일단 sidx번째 수로 idx번재 수 선택
		comb(idx + 1, sidx + 1, r); // 이번 원소 포함함
		comb(idx + 1, sidx, r); // 이번 원소 포함하지 않음 (sidx번째 수 다시 뽑아줄 거니까)
	}
}
