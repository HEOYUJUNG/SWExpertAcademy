package D4;

import java.util.Scanner;
import java.util.StringTokenizer;

public class P1232_사칙연산 {
	static String[][] arr;
	static String result;
	static int N;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			N = sc.nextInt(); // 정점의 개수, 1<=N<=1000

			// 트리 구현할 배열
			arr = new String[N + 1][3]; // 첫번째 열에 들어있는 것이 연산자이면 두번째, 세번째에 왼쪽 자식, 오른쪽 자식 번호 넣기

			// 정점 정보 받아서 배열에 저장하기
			for (int i = 1; i <= N; i++) {
				int num = sc.nextInt(); // 정점 번호
				StringTokenizer st = new StringTokenizer(sc.nextLine());
				while (st.hasMoreTokens()) {
					String s = st.nextToken();
					arr[i][0] = s; // 일단 저장
					// 연산자인 경우는 왼쪽 자식, 오른쪽 자식도 저장하기
					if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
						arr[i][1] = st.nextToken();
						arr[i][2] = st.nextToken();
					}
				}
			}

			// 후위 순회 (LRV)
			traverse(1);

			// 출력 형식 맞추려면 int로 해야함.
			// 근데 그동안 double로 계산했기 때문에 바로 parseInt 하면 형식 에러남. 일단 double로 바꾸고 int로!
			System.out.printf("#%d %d\n", tc, (int) Double.parseDouble(arr[1][0]));

		} // testcase
	} // main

	// x번 노드부터 시작해서 순회하는 메서드 (후위)
	public static void traverse(int x) {
//		System.out.println(x + " " + arr[x]);
		// 연산자에 해당하는 노드일 때만 순회
		// 숫자 들어있는 노드는 어차피 리프 노드들이기 때문에 순회할 필요 X
		if (arr[x][0].equals("+") || arr[x][0].equals("-") || arr[x][0].equals("*") || arr[x][0].equals("/")) {
			// L 
			traverse(Integer.parseInt(arr[x][1]));  // 1번 열에 있는 왼쪽 자식 노드 번호 int 형으로 넣어서 재귀
			// R
			traverse(Integer.parseInt(arr[x][2]));
			// V (방문처리 = 연산해서 계산한 값 저장)
			// 계산 중간 과정에서의 연산은 모두 실수 연산으로 한다.
			// 1번 열에 왼쪽 자식노드의 번호가 들어있음. 번호를 int로 가져와서 그 번호를 인덱스로 가지는 행의 0번 열에 있는 값을 가져와서 계산해야 함.
			// 오른쪽 자식노드도 마찬가지.
			if (arr[x][0].equals("+")) {
				arr[x][0] = String.valueOf(Double.parseDouble(arr[Integer.parseInt(arr[x][1])][0])
						+ Double.parseDouble(arr[Integer.parseInt(arr[x][2])][0]));
			} else if (arr[x][0].equals("-")) {
				arr[x][0] = String.valueOf(Double.parseDouble(arr[Integer.parseInt(arr[x][1])][0])
						- Double.parseDouble(arr[Integer.parseInt(arr[x][2])][0]));
			} else if (arr[x][0].equals("*")) {
				arr[x][0] = String.valueOf(Double.parseDouble(arr[Integer.parseInt(arr[x][1])][0])
						* Double.parseDouble(arr[Integer.parseInt(arr[x][2])][0]));
			} else if (arr[x][0].equals("/")) {
				arr[x][0] = String.valueOf(Double.parseDouble(arr[Integer.parseInt(arr[x][1])][0])
						/ Double.parseDouble(arr[Integer.parseInt(arr[x][2])][0]));
			}
		}
	}
}