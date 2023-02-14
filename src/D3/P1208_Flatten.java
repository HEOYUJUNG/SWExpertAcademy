package SWEA;

import java.util.Scanner;

public class P1208_Flatten {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int tc = 0; tc < 10; tc++) {
			int dumps = sc.nextInt();

			int[] box = new int[100];
			int high = 1; // 최고점
			int low = 100; // 최저점
			for (int i = 0; i < 100; i++) {
				box[i] = sc.nextInt();
			}


			// 평탄화 작업
			int dump = 0;
			while (true) {						
				// 최고점에 있는 상자 -1 , 최저점에 있는 상자 +1
				int min = 101;
				int max = 0;
				int minIdx = 0;
				int maxIdx = 0;
				for (int i = 0; i < 100; i++) {
					if (box[i] >= max) {
						max = box[i];
						maxIdx = i;
					}
					if (box[i] <= min) {
						min = box[i];
						minIdx = i;
					}					
				}
				
				
				
				// 주어진 덤프 횟수 이내에 평탄화가 완료 or 덤프 횟수 되면 종료
				if (box[maxIdx]-box[minIdx] <=1 || dump == dumps) {
					break;
				}
				
				// 덤프 횟수 증가
				dump++;		
				
				// 덤프
				box[maxIdx]--;
				box[minIdx]++;		

			} // while

			int min = 101;
			int max = 0;
			int minIdx = 0;
			int maxIdx = 0;
			for (int i = 0; i < 100; i++) {
				if (box[i] >= max) {
					max = box[i];
					maxIdx = i;
				} else if (box[i] <= min) {
					min = box[i];
					minIdx = i;
				}					
			}
			
			
			System.out.printf("#%d %d\n",tc+1, box[maxIdx]-box[minIdx]);
			
		} //testcase
	} //main
}