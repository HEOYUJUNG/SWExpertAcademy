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
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}

			for (int i = 1; i <= K; i++) {
				list.add(K * i % N, list.get((K * i - 1) % N) + list.get((K * i) % N));
				System.out.println(list);
			}
			
			System.out.print("#" + tc);
			for (int i = list.size() - 1; i >= list.size() - 11; i--) {
				System.out.print(" " + list.get(i));
			}
			System.out.println();
		} // testcase
	}
}
