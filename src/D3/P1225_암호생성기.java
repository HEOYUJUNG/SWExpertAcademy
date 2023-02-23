package D3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1225_암호생성기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			// 테스트 케이스 번호
			int T = sc.nextInt();

			// 8개의 데이터 큐에 입력받기
			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < 8; i++) {
				q.add(sc.nextInt());
			}

			// 감소시킬 수
			int dec = 1;
			// 사이클 돌리기
			while (true) {
				// 숫자가 감소할 때 0보다 작아지거나 0일 경우 (프로그램 종료 조건)
				if (q.element() - dec <= 0) {
					// 지금 숫자는 빼고
					q.remove();
					// 맨 뒤에 0으로 저장
					q.add(0);
					// 프로그램은 종료
					break;
				} else {
					// dec만큼 감소시키고 뒤로 이동
					q.add(q.remove() - dec);
					// 다음에는 dec에서 1만큼 증가시킨 값을 감소시켜야 함
					dec++;
					// 사이클은 1~5 사이에서만 도니까 6 되면 다시 1로 바꿔주기
					if (dec == 6) {
						dec = 1;
					}
				}
			}

			System.out.print("#" + tc);
			for (int n : q) {
				System.out.print(" " + n);
			}
			System.out.println();
		} // testcase
	} // main
}
