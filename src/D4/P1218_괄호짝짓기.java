package D4;

import java.util.Scanner;
import java.util.Stack;

public class P1218_괄호짝짓기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			// 테스트케이스의 길이
			int L = sc.nextInt();
			// 괄호들 저장할 문자 배열
			char[] cArr = sc.next().toCharArray();
			// 스택에 괄호 넣어서 검사

			Stack<Character> stack = new Stack<>();

			int result = 0;
			for (int i = 0; i < L; i++) {
				if (cArr[i] == '(' || cArr[i] == '{' || cArr[i] == '[') {
					// 왼쪽 괄호 -> 스택에 넣기
					stack.push(cArr[i]);
				} else if (cArr[i] == ')' || cArr[i] == '}' || cArr[i] == ']') {
					// 오른쪽 괄호 -> 스택에서 top에 있는 괄호 검사(peek)
					if (stack.isEmpty()) {
						// stack 비었으면 유효X(-1)
						break;
					} else if (cArr[i] == ')' && stack.peek() == '(' || cArr[i] == '}' && stack.peek() == '{'
							|| cArr[i] == ']' && stack.peek() == '[') {
						// 같은 종류 쌍이면 pop
						stack.pop();
					} else {
						// 다르면 유효하지 않음!!(-1)
						break;
					}
				}
			}
			// 전체 다 돌았을 때 스택에 괄호 남아있으면 => 유효하지 않음(-1)
			if (stack.isEmpty()) {
				result = 1;
			}
			System.out.printf("#%d %d\n", tc, result);
		} // test-case
	} // main
}
