package D3;

import java.util.LinkedList;
import java.util.Scanner;

public class P5108_숫자추가 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테케 개수

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 수열 길이
			int M = sc.nextInt(); // 추가 횟수
			int L = sc.nextInt(); // 출력할 인덱스 번호

			LinkedList<Integer> l = new LinkedList<>();

			// 수열 입력받기
			for (int i = 0; i < N; i++) {
				l.add(sc.nextInt());
			}

			// 추가 횟수
			for (int i = 0; i < M; i++) {
				l.add(sc.nextInt(), sc.nextInt());
			}

			System.out.printf("#%d %d\n", tc, l.get(L));
		}
	}
}
