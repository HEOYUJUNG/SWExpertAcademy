package D2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1966_숫자를정렬하자_삽입정렬ver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt(); // 테케 개수

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 숫자 개수

			// 정렬된 부분집합
			List<Integer> Sorted = new ArrayList<>();
			// 정렬되지 않은 부분집합
			List<Integer> Unsorted = new ArrayList<>();

			// 처음에는 첫번째 원소만 정렬된 부분집합으로 넣기
			Sorted.add(sc.nextInt());
			// 나머지 원소들은 정렬되지 않은 부분집합.
			for (int i = 1; i < N; i++) {
				Unsorted.add(sc.nextInt());
			}

			for (int j = 0; j < N - 1; j++) { // 정렬된 부분집합 원소 개수가 1개~N-1개일 때까지
				for (int i = j; i >= 0; i--) { // 정렬된 부분집합 뒤에서부터 탐색
					if (Sorted.get(i) <= Unsorted.get(0)) {
						// 정렬되지 않은 부분집합에서 가져온 수가 정렬된 부분집합의 i번째 원소보다 크거나 같으면 i+1번째 원소로 넣어주기
						Sorted.add(i + 1, Unsorted.remove(0));
						break;
					}
					// 아니면 계속 진행
					if (i == 0) {
						// 정렬되지 않은 부분집합에서 가져온 수가 가장 작은 수였으면 위에서 걸리지 않고 여기까지 온다. -> 0번째에 추가해주기
						Sorted.add(0, Unsorted.remove(0));
					}
				}
			}

			System.out.print("#" + tc);
			for (int i = 0; i < N; i++) {
				System.out.print(" " + Sorted.get(i));
			}
			System.out.println();
		} // testcase
	} // main
}
