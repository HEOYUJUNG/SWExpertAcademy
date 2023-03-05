package D3;

import java.util.Scanner;

public class P6485_삼성시의버스노선 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int[] BusStop = new int[5001]; // 버스 정류장 번호. 1~5000
			int N = sc.nextInt(); // 버스 노선 개수
			int[] A = new int[N + 1];
			int[] B = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				A[i] = sc.nextInt();
				B[i] = sc.nextInt();
				for (int j = A[i]; j <= B[i]; j++) {
					BusStop[j]++;
				}
			}

			int P = sc.nextInt();
			System.out.print("#" + tc);
			for (int i = 0; i < P; i++) {
				System.out.print(" " + BusStop[sc.nextInt()]);
			}
			System.out.println();
		}
	}
}
