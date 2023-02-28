package D4;

import java.util.LinkedList;
import java.util.Scanner;

public class P5122_수열편집 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 수열 길이
			int M = sc.nextInt(); // 추가 횟수
			int L = sc.nextInt(); // 출력할 인덱스 번호
			LinkedList<Integer> nums = new LinkedList<>();
			// 수열 입력 받기
			for (int i = 0; i < N; i++) {
				nums.add(sc.nextInt());
			}

			// 편집 M회 수행
			for (int i = 0; i < M; i++) {
				String cmd = sc.next();
				if (cmd.equals("I")) { // 삽입
					nums.add(sc.nextInt(), sc.nextInt());
				} else if (cmd.equals("D")) { // 삭제
					nums.remove(sc.nextInt());
				} else { // 값 바꾸기
					nums.set(sc.nextInt(), sc.nextInt());
				}
			}

			int result = -1; // 기본 -1로 초기화
			if (L >= 0 && L < nums.size()) { // 범위 안에 들어오면
				result = nums.get(L);
			}
			System.out.printf("#%d %d\n", tc, result);
		} // testcase
	}
}
