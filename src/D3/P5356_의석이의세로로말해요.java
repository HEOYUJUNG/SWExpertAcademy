package D3;

import java.util.Scanner;
import java.util.Stack;

public class P5356_의석이의세로로말해요 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			// stack 5개 필요함
			// 반복문 쓸 수 있도록 stack 들어가는 배열을 만들자
			Stack[] stArr = new Stack[5];
			for (int i = 0; i < 5; i++) {
				stArr[i] = new Stack();
			}

			// 문자열들 스택에 쌓기. 가장 오른쪽 글자가 스택 가장 밑으로 가도록.
			for (int r = 0; r < 5; r++) {
				String input = sc.next();
				for (int i = input.length() - 1; i >= 0; i--) {
					stArr[r].push(input.charAt(i));
				}
			}
			// "각 줄에는 길이가 1이상 15이하인 문자열이 주어진다"
			// 5줄에 최대 75개의 문자가 있을 수 있음
			// 가장 왼쪽 글자가 스택 가장 위에 올라와 있으니까
			// 스택 5개 돌면서 위에 있는거 하나씩 뽑아오면 됨
			// 스택에 남은 게 없으면 넘어가기!
			for (int i = 0; i < 75; i++) {
				if (!stArr[i % 5].isEmpty()) {
					sb.append(stArr[i % 5].pop());
				}
			}

			System.out.printf("#%d %s\n", tc, sb);
		}
	}
}
