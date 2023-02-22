package D2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P5099_피자굽기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 화덕의 크기, 3<=N<=20
			int N = sc.nextInt();
			// 피자 개수, N<=M<=100
			int M = sc.nextInt();
			// 화덕에서 치즈의 양 추적하는 큐
			Queue<Integer> cQ = new LinkedList<>();
			// 화덕에서 피자 번호 추적하는 큐
			Queue<Integer> nQ = new LinkedList<>();

			// M개의 피자에 뿌려진 치즈의 양 입력받기
			// 단, 화덕의 크기가 N이니까 처음에는 N개만 들어갈 수 있음.
			// 1<=Ci<=20
			int pizza = 1;
			while (pizza <= N) {
				cQ.add(sc.nextInt());
				nQ.add(pizza++);
			}

			int num = 0;
			// 모든 피자를 빼서 화덕이 빌 때까지 반복
			while (!cQ.isEmpty()) {
				if (cQ.peek() == 1) { // 앞에 있는 피자의 치즈 양이 1이면 아예 빼기
					cQ.poll();
					num = nQ.poll(); // 피자 번호는 계속 추적해주기
					if (pizza <= M) {
						// 아직 넣어줘야 하는 피자 남아있으면 빈 자리에 넣어주기
						cQ.add(sc.nextInt());
						nQ.add(pizza++);
					}					
				} else {
					// 아직 치즈가 남아있으면 치즈 양 반으로 줄이고 화덕에 다시 넣기
					cQ.add(cQ.poll() / 2);
					nQ.add(nQ.poll());
				}
			}

			// 마지막까지 남아있는, 즉 가장 마지막에 나간 피자의 번호 출력
			System.out.printf("#%d %d\n", tc, num);
		}
	}
}
