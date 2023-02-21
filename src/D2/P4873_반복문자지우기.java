package D2;

import java.util.Scanner;
import java.util.Stack;

public class P4873_반복문자지우기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 테케 개수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 문자열을 문자 배열로 입력받기
			char[] s = sc.next().toCharArray();
			// 반복문자를 지운 문자열 저장할 스택
			Stack<Character> stack = new Stack<>();

			for(char c : s) {
				if(stack.isEmpty()||stack.peek()!=c) {
					// 스택이 비어있거나
					// 반복이 아닐 때, 즉 지금 문자와 이전 문자가 같지 않으면
					// 스택에 넣기 (PUSH)
					stack.push(c);
				} else{
					// 스택이 비어있지 않으면서
					// 반복일 때
					// 이전 문자도 빼고(POP), 지금 문자도 넣지 않음 -> 반복 제거
					stack.pop();
				}
			}
			
			// 반복문자를 지운 후 남은 문자열의 길이, 즉 스택 사이즈 출력
			System.out.printf("#%d %d\n", tc, stack.size());
		}
	}
}
