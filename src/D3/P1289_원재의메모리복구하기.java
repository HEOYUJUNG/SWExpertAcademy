package D3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P1289_원재의메모리복구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int cnt = 0; // 고치는 횟수 카운팅

			// memory 비트별로 배열에 저장
			String[] memory = sc.next().split("");
			int len = memory.length;
			// 초기값 배열 (전부 0으로 초기화)
			String[] init = new String[len];
			for (int j = 0; j < len; j++) {
				init[j] = "0";
			}

//			System.out.println("memory" + Arrays.toString(memory));   // 배열 잘 들어갔는지 확인
//			System.out.println("init" + Arrays.toString(init));
			// 초기값 : 전부 0
			// 앞에서부터 탐색 -> 원래 값이랑 비트 다른 지점 있으면 바꾸기
			for (int idx = 0; idx < len; idx++) {
//				System.out.println("인덱스 "+idx);
				if (!(init[idx].equals(memory[idx])) && init[idx].equals("0")) {
//					System.out.println("if");
					cnt++;
					for (int j = idx; j < len; j++) {
						init[j] = "1";

//						System.out.println("1로 바꿈");
					}
				} else if (!(init[idx].equals(memory[idx])) && init[idx].equals("1")) {
//					System.out.println("else if");
					cnt++;
					for (int j = idx; j < len; j++) {
						init[j] = "0";

//						System.out.println("0으로 바꿈");
					}
				}
			}

			System.out.printf("#%d %d\n", i + 1, cnt);
		} // test-case
	} // main
}
