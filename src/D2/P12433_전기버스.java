package D2;

import java.util.Scanner;

public class P12433_전기버스 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테케 수
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int K = sc.nextInt(); // 한 번 충전 후 최대 이동 가능한 정류장 수
			int N = sc.nextInt(); // 종점 번호
			int M = sc.nextInt(); // 충전기 있는 정류장 개수

			// 충전기가 설치된 정류장을 index로 같는 요소 값 = 1
			int[] BusStop = new int[N+1];
			for (int i = 0; i < M; i++) {
				BusStop[sc.nextInt()]++;
			}

			int charge = 0; // 마지막으로 충전한 정류장 번호
			int cnt = 0; // 충전 횟수
			// 버스가 종점에 도착할 때까지 반복
			// 종점 도착 = charge+K >= N
			while (charge + K < N) {
				int chargerIdx = 0;
				if (BusStop[charge + K] == 1) { // 최대로 이동, 충전기 있으면 충전
					cnt++;
					charge = charge + K;
				} else { // 최대로 이동, 충전기 없는 경우
					// 그 전에 있었던 마지막 충전기 찾기
					for (int i = charge + 1; i < charge + K; i++) {
						if (BusStop[i] == 1) {
							chargerIdx = i;
						}
					}
					if (chargerIdx == 0) {
						// 충전기 없음. 충전기 설치가 잘못됨. 0출력!
						cnt = 0;
						break;
					} else {
						// 최대 이동 전에 충전기 있었다면 마지막 충전기에서 충전
						cnt++;
						charge = chargerIdx;
					}
				}
			} // while

			System.out.printf("#%d %d\n", tc, cnt);
		} // testcase
	} // main
}
