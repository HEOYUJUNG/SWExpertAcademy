package D4;

import java.util.Scanner;
import java.util.Stack;

public class P1222_계산기1 {
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

			// 1. 중위 표기식 -> 후위 표기식 변환
			int i = 0;
			for (char c : infix) {
				if (c == '+' && Operator.isEmpty()) {
					// 연산자(+)이면 연산자 스택에 넣기
					Operator.push(c);
				} else if (c == '+' && !Operator.isEmpty()) {
					// 연산자(+)인데 스택에 + 이미 있으면
					postfix[i++] = Operator.pop();
					Operator.push(c);
				} else {
					postfix[i++] = c;
				}
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
					// 결과 다시 넣어주기
					Operand.push(A + B);
				}
			}

			int result = Operand.pop();
			System.out.printf("#%d %d\n", tc, result);
		} // testcase
	} // main
}
