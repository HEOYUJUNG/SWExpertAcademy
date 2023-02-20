package D4;

import java.util.Scanner;
import java.util.Stack;

public class P5432_쇠막대기자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 괄호들 저장할 문자 배열
			char[] cArr = sc.next().toCharArray();
			Stack<Character> stack = new Stack<>();
			int result = 0;
			int lastIndex = 0;
			int top = -1;
			for (int i = 0; i < cArr.length; i++) {
				if (cArr[i] == '(') {
					stack.push(cArr[i]);
					lastIndex = i;
					top++;
				} else {
					stack.pop();
					top--;					
					if (i == lastIndex + 1) {
						// 레이저 괄호 닫힌 순간 => 스택에 쌓여 있던 괄호 수 = 레이저 치면서 잘린 조각 수. result에 더하기
						result += (top + 1);
					} else {
						// 레이저 아닌 괄호 닫힌 순간 => 한 조각 더 생김 result++
						result++;
					}					
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
