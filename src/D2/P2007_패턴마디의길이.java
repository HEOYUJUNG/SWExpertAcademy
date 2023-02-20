package D2;

import java.util.Scanner;
import java.util.Stack;

public class P2007_패턴마디의길이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int length = 0;
			// 스택 안에는 마디에 들어가는 후보들 넣기
			Stack<Character> stack = new Stack<>();
			int top = -1;
			
			String input = sc.next();
			char[] cArr = input.toCharArray();
			stack.push(cArr[0]);
			top++;
			
			// 마디의 최대 길이 10
			for(int i = 1;i<10;i++) {
				if(cArr[i] == cArr[0]) {
					// 일단 지금까지 stack에 들어가 있는 게 마디라고 가정
					boolean flag = true;
					int j = top;
					while(!stack.isEmpty()) {
						if(stack.pop() != cArr[i+j--]) {
							// 지금까지 찾은 마디가 다음 패턴과 일치하지 않으면 false
							flag = false;
							while(j<top) {
								stack.push(cArr[++j]);
							}
							break;
						}						
					}
					if(flag) {
						break;
					}					
				}
				// 지금까지 stack에 찾아둔 것이 마디가 아니면 마디 찾을 때까지 추가
				stack.push(cArr[i]);
				top++;
			}
			System.out.printf("#%d %d\n",tc, top+1);
		}
	}
}
