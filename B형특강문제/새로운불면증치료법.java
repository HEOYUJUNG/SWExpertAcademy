package B형특강;

import java.util.Scanner;

public class 새로운불면증치료법 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int k = 1;
			int[] check = new int[10];
			int cnt = 0;
			while (true) {  // 하나의 수에 대해 소요되는 시간은 자리수만큼! => 총 최대k * 자리수만큼 소요
				int num = N * k++;
				while (num > 0) {  // 자리수만큼 반복
					if (check[num % 10] == 0) {  // 이 if문은 상수 (if문이 늘어나는 것을 두려워하지 말고 전체적인 시간복잡도를 줄일 수 있도록!!)
						check[num % 10] = 1;
						cnt++;
					}
					num /= 10;
				}
				if (cnt == 10)  // 모든 숫자를 봤는지 매번 확인할 필요 없이 cnt로 추적 => 시간 복잡도 많이 낮출 수 있다!!
					break;
			}
			System.out.println("#" + tc + " " + (k - 1) * N);
		}
	}
}
