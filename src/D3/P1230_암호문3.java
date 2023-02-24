package D3;

import java.util.LinkedList;
import java.util.Scanner;

public class P1230_암호문3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 1; tc <= 10; tc++) {
			// 원본 암호문의 길이 (2000 <= N <= 4000, 정수)
			int N = sc.nextInt();
			// 암호문 저장할 리스트. 삽입, 삭제 자유자재로 하려면 링크드리스트가 짱짱맨
			LinkedList<Integer> list = new LinkedList<>();
			// 암호문 입력받기
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}
			// 명령어의 개수 (250 <= M <= 500, 정수)
			int M = sc.nextInt();
			// 명령어 입력 받기
			for (int i = 0; i < M; i++) {
				String command = sc.next();
				// 명령어는 3가지
				if (command.equals("I")) { // 삽입
					// 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입
					int x = sc.nextInt();
					int y = sc.nextInt();

					// y개 숫자 입력 받아서 삽입하기!
					for (int j = 0; j < y; j++) {
						list.add(x + j, sc.nextInt());
					}
				} else if (command.equals("D")) { // 삭제
					// 앞에서부터 x의 위치 바로 다음부터 y개의 숫자를 삭제
					int x = sc.nextInt();
					int y = sc.nextInt();

					for (int j = 0; j < y; j++) {
						list.remove(x + 1);
					}
				} else { // 추가
					// 암호문의 맨 뒤에 y개의 숫자를 덧붙인다.
					int y = sc.nextInt();
					for (int j = 0; j < y; j++) {
						list.add(sc.nextInt());
					}
				}
			}

			// 수정된 결과의 처음 10개 숫자를 출력
			System.out.print("#" + tc);
			for (int i = 0; i < 10; i++) {
				System.out.print(" " + list.get(i));
			}
			// 줄 바꾸기
			System.out.println();

		} // testcase
	} // main
}
