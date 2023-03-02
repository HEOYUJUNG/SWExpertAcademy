package D4;

import java.util.Arrays;
import java.util.Scanner;

public class P1232_사칙연산 {
	static String[] arr;
	static String result;
	static int N;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 9; tc++) {
			N = sc.nextInt(); // 정점의 개수, 1<=N<=1000
			// 트리 구현할 배열
			arr = new String[10000];
			max = 0;
			// 정점 정보 받아서 배열에 저장하기
			for (int i = 0; i < N; i++) {
				int num = sc.nextInt(); // 정점 번호
				if (num > max) {
					max = num;
				}
				String cmd = sc.nextLine(); // 그 뒤 정보들
				cmd = cmd.substring(1); // 공백 한 칸 버리기
				// 연산자인 경우
				if (cmd.substring(0, 1).equals("+") || cmd.substring(0, 1).equals("-")
						|| cmd.substring(0, 1).equals("*") || cmd.substring(0, 1).equals("/")) {
					// 해당 위치에 연산자 넣어주기
					arr[num] = cmd.substring(0, 1);
				} else { // 연산자 아닌 경우는
					// 숫자를 String형으로 저장
					arr[num] = cmd;
				}
			}

//			System.out.println(Arrays.toString(arr));
//			System.out.println(arr.length);

			// 후위 순회 (LRV)
			traverse(1);

			System.out.printf("#%d %d\n", tc, (int) Double.parseDouble(arr[1]));

		} // testcase
	} // main

	// x번 노드부터 시작해서 순회하는 메서드 (후위)
	public static void traverse(int x) {
//		System.out.println(x + " " + arr[x]);
		// 연산자에 해당하는 노드일 때만 순회
		// 숫자 들어있는 노드는 어차피 리프 노드들이기 때문에 순회할 필요 X
		if (x <= max && (arr[x].equals("+") || arr[x].equals("-") || arr[x].equals("*") || arr[x].equals("/"))) {
			// L
			traverse(x * 2);
			// R
			traverse(x * 2 + 1);
			// V (방문처리 = 연산해서 계산한 값 저장)
			// 계산 중간 과정에서의 연산은 모두 실수 연산으로 한다.
			if (arr[x].equals("+")) {
				arr[x] = String.valueOf(Double.parseDouble(arr[x * 2]) + Double.parseDouble(arr[x * 2 + 1]));
			} else if (arr[x].equals("-")) {
				arr[x] = String.valueOf(Double.parseDouble(arr[x * 2]) - Double.parseDouble(arr[x * 2 + 1]));
			} else if (arr[x].equals("*")) {
				arr[x] = String.valueOf(Double.parseDouble(arr[x * 2]) * Double.parseDouble(arr[x * 2 + 1]));
			} else if (arr[x].equals("/")) {
				arr[x] = String.valueOf(Double.parseDouble(arr[x * 2]) / Double.parseDouble(arr[x * 2 + 1]));
			}
		}
	}
}
