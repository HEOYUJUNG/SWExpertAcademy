package D4;

import java.util.LinkedList;
import java.util.Scanner;

public class P5120_암호 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테케 개수

		for (int tc = 1; tc <= T; tc++) {
			LinkedList<Integer> list = new LinkedList<>();

			int N = sc.nextInt(); // 숫자 개수
			int M = sc.nextInt(); // M번째에 칸 추가
			int K = sc.nextInt(); // 작업 반복 횟수
			// 숫자 N개 list에 입력받기
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}

			int idx = 0;
			for (int i = 0; i < K; i++) { // 작업 K번 반복
				idx += M; // M칸 이동
				if (idx == list.size()) {
					// 밀려난 칸 없으면 시작 숫자와 더하기
					list.add(idx, list.get(idx - 1) + list.get(0));
				} else {
					// 밀려난 칸 있으면 앞칸 숫자와 더하기
					idx %= list.size(); // 리스트 크기 벗어나면 다시 앞(=0)으로 돌아감
					list.add(idx, list.get(idx-1) + list.get(idx));
				}
			}

			System.out.print("#" + tc);
			for (int i = list.size() - 1; i >= 0; i--) {
				System.out.print(" " + list.get(i));
				if (i == list.size() - 10) // 숫자 10개 이상이면 10개까지만 출력
					break;
			}
			System.out.println();
		} // testcase
	}
}
