package D4;

import java.util.Scanner;
import java.util.Stack;

public class P1223_계산기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			// 테케 길이
			int L = sc.nextInt();
			// 계산식(중위 표기식) 문자 배열로 입력받기
			char[] infix = sc.next().toCharArray();
			// 후위 표기식 저장할 배열 만들어두기
			char[] postfix = new char[L];
			// 연산자 스택 (중위 -> 후위 바꿀 때 사용)
			Stack<Character> Operator = new Stack<>();
			// 피연산자 스택 (후위 -> 계산할 때 사용)
			Stack<Integer> Operand = new Stack<>();
			// 새로운 연산자의 우선순위
			int icp = 0;
			// 스택의 top에 있는 연산자의 우선순위
			int isp = 4;

			// 1. 중위 표기식 -> 후위 표기식 변환
			/* 연산자 우선순위!! - 숫자 클수록 높은 우선순위 */
			// ( : (isp일 경우) 0 or (icp일 경우)3
			// * , / : 2
			// +, - : 1
			int i = 0;
			for (char c : infix) {
				// icp 업데이트 해주기
				if (c == '*') {
					icp = 2;
				} else {
					// +인 경우
					icp = 1;
				}

				if (!Character.isDigit(c)) {
					if (!Operator.isEmpty()) {
						// 연산자 스택 비어있지 않으면 연산자들 우선순위 비교 해줘야 함.
						// 스택 top에 있는 연사자가 나보다 우선순위가 높거나 같으면, pop
						// 스택 top에 있는 연사자가 나보다 작을 때가지!
						while (isp >= icp) {
							postfix[i++] = Operator.pop();
							if (Operator.isEmpty()) {
								break;
							} else if (Operator.peek() == '*') {
								isp = 2;
							} else {
								// +인 경우
								isp = 1;
							}
						}
					}

					// 1. 스택 top에 있는 연사자가 나보다 우선순위 낮으면 or
					// 2. 스택이 비어있으면
					// push
					Operator.push(c);
					// isp 업데이트 해주기
					if (c == '*') {
						isp = 2;
					} else {
						// +인 경우
						isp = 1;
					}
				} else {
					// 피연산자, 즉 숫자인 경우는 스택에 넣지 않고 바로 후위 표기식에 넣기
					postfix[i++] = c;
				}
			} // for-each
			
			// 연산자 stack에 남아있는 연산자 있으면 다 꺼내주기
			while (!Operator.isEmpty()) {
				postfix[i++] = Operator.pop();
			}
			
			

			// 2. 후기 표기식 -> 계산
			for (char c : postfix) {
				if (Character.isDigit(c)) {
					// 피연산자면 피연산자 스택에 넣기
					Operand.push((int) c - '0');
				} else if (!Character.isDigit(c)) {
					// 연산자면 피연산자 스택에서 위의 2개 꺼내서 연산 후
					int B = Operand.pop();
					int A = Operand.pop();
					if (c == '*') {
						// 결과 다시 넣어주기
						Operand.push(A * B);
					} else {
						// +인 경우
						Operand.push(A + B);
					}

				}
			}

			int result = Operand.pop();
			System.out.printf("#%d %d\n", tc, result);
		} // testcase
	} // main
}
