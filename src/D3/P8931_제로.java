package D3;

import java.util.Scanner;
import java.util.Stack;

public class P8931_제로 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			// 입력받을 정수의 개수
			int K = sc.nextInt();
			// 가장 최근에 재민이가 쓴 수를 지우게 시킨다. => LIFO => 스택!!
			Stack<Integer> money = new Stack<>();

			for (int nums = 0; nums < K; nums++) {
				int input = sc.nextInt();
				// 0이면 가장 최근, 즉 스택 가장 위에 있는 수 지우기 (pop)
				if (input == 0) {
					money.pop();
				} else { // 0이 아니면 쓰기 (push)
					money.push(input);
				}
			}

			// 입력이 끝난 후, 적은 수들의 합 구하기
			int sum = 0;
			for (int num : money) {
				sum += num;
			}
			System.out.printf("#%d %d\n",tc,sum);
		}
		
	}
}
