package D2;

import java.util.Scanner;

public class P12457_베이비진을만들어보자_counting배열버전 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int input = sc.nextInt();
			//counting 배열 인덱스 = 카드 번호, 요소 = 인덱스를 번호로 가지는 카드 개수
			int[] counting = new int[10];
			for (int i = 0; i < 6; i++) {
				counting[input % 10]++;
				input = input / 10;
			}

			// 베이비진 체크
			int BabyGin = 0;
			for (int i = 0; i < counting.length; i++) {
				// triplet 체크! (같은 수가 6장 있을 수도 있으니까 2번 체크)
				if (counting[i] >= 3) {
					counting[i] -= 3;
					BabyGin++;
				}
				if (counting[i] >= 3) {
					counting[i] -= 3;
					BabyGin++;
				}
				
				// run 체크! (같은 run 세트가 2개 있을 수도 있으니까 2번 체크)
				if (counting[i] >= 1 && counting[i + 1] >= 1 && counting[i + 2] >= 1) {
					counting[i]--; counting[i + 1]--; counting[i + 2]--;					
					BabyGin++;
				}
				
				if (counting[i] >= 1 && counting[i + 1] >= 1 && counting[i + 2] >= 1) {
					counting[i]--; counting[i + 1]--; counting[i + 2]--;					
					BabyGin++;
				}
			}
			
			// BabyGin이 2가 되어야 진짜 베이비진!
			if(BabyGin==2) {
				System.out.printf("#%d 1\n",tc);
			} else {
				System.out.printf("#%d 0\n",tc);
			}
		} // testcase
	} // main
}
