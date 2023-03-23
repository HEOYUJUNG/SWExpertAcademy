package D3;

import java.util.Arrays;
import java.util.Scanner;

public class P6808_규영이와인영이의카드게임 {
	static int[] card1; // 규영이 카드
	static int[] card2; // 인영이 카드
	static boolean[] cards; // 전체 카드
	static boolean[] visited; // 인영이 낼 카드 구할 때 방문체크
	static int[] result; // 인영이 낼 카드 (이 순서대로)
	static int cntWin;
	static int cntLose;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			// 초기화
			card1 = new int[9];
			card2 = new int[9];
			cards = new boolean[19]; // 1 ~ 18
			visited = new boolean[9];
			cntWin = 0;
			cntLose = 0;

			// 규영이 카드 입력받기 (규영이가 카드 내는 순서는 고정!)
			for (int i = 0; i < 9; i++) {
				card1[i] = sc.nextInt();
				cards[card1[i]] = true;
			}

			// 남은 카드는 인영이 카드로 입력받기
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (!cards[i]) {
					card2[idx++] = i;
				}
			}

//			System.out.println(Arrays.toString(card1));
//			System.out.println(Arrays.toString(card2));

			cardSort(0, 0, 0);

			System.out.println("#" + tc + " " + cntWin + " " + cntLose);
		} // testcase
	} // main

	// 몇 번째 카드를 내는지
	// idx => result에 어디에 들어갈건지
	// sidx => card2 어디서 뽑아올건지
	// sum1 => 규영이 점수 (card1이 클 경우)
	// sum2 => 인영이 점수 (card2가 클 경우)
	private static void cardSort(int idx, int sum1, int sum2) {
		// 기저 조건
		if (idx == 9) {
			// 인영이가 낼 카드 다 정했으면
			// 승패 계산하기
			if (sum1 > sum2) {
				cntWin++;
			} else if (sum1 < sum2) {
				cntLose++;
			}
			return;
		}

		// 1~18 총합이 171인데 반이 넘는 86을 가져갔다면 더 볼 필요 없이 그 사람이 이긴 것!
		if (sum1 >= 86) {
			// idx-1까지는 카드가 정해졌고 idx ~ 8까지 카드가 들어올 수 있는 경우의 수만큼 더해줘야 한다.
			cntWin += factorial(9 - idx);
			return;
		}

		if (sum2 >= 86) {
			cntLose += factorial(9 - idx);
			return;
		}

		// 유도 조건
		for (int i = 0; i < 9; i++) {
			if (visited[i]) { // 이미 뽑은 카드면
				continue;
			}

			visited[i] = true;

			// 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻는다!
			if (card1[idx] > card2[i]) { // 규영이가 이김
				cardSort(idx + 1, sum1 + (card1[idx] + card2[i]), sum2);
			} else { // 인영이가 이김
				cardSort(idx + 1, sum1, sum2 + (card1[idx] + card2[i]));
			}

			// 원상복구
			visited[i] = false;
		}
	}

	private static int factorial(int x) {
		int result = 1;
		for (int i = x; i >= 1; i--) {
			result *= i;
		}
		return result;
	}
}
