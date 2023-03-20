import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class P5658_보물상자비밀번호 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 숫자 개수
			int K = sc.nextInt(); // 크기 순서. K번째로 큰 수 찾기
			Deque<Integer> q = new LinkedList<>(); // N개의 숫자 넣어두는 큐
			Set<Integer> set = new HashSet<>(); // 생성된 숫자들 저장하는 곳. 중복 허용하지 않음!!
			String input = sc.next();

			// 문자열로 입력받은 숫자를 int로 변환해서 q에 넣기
			for (int i = 0; i < N; i++) {
				if (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
					q.addLast(input.charAt(i) - '0');
				} else {
					q.addLast(input.charAt(i) - 'A' + 10);
				}
			}
			// 한 변에 들어가는 16진수 숫자 개수는 N/4개 (N은 4의 배수, 8이상 28이하의 정수니까 생성된 숫자가 int 범위를 넘어가지
			// 않는다!)
			int cnt = 0;
			while (cnt < N) { // 모든 경우의 수 찾아야 함. N번 회전할 수 있음!
				for (int i = 0; i < 4; i++) { // 현재 상태에서 숫자 4개 만들 수 있음
					int num = 0; // 한 변에서 만들어진 숫자
					for (int j = 1; j <= N / 4; j++) {
						num += q.peekFirst() * Math.pow(16, N / 4 - j);
						q.addLast(q.pollFirst()); // 숫자 뒤로 보내주기
					}
					set.add(num); // 생성된 숫자 set에 넣어주기 (중복된 값 있으면 추가 안 될 것임!)
				}
				// 현재 상태에서 만들 수 있는 수 4개 다 찾았으면 다시 처음 큐 상태로 돌아와 있을 것임
				// 한 번 회전 시켜줘야 하니까 마지막에 있는 원소를 맨 앞으로 가져오기
				q.addFirst(q.pollLast());
				cnt++;
			}

			int[] arr = new int[set.size()];
			Iterator<Integer> it = set.iterator(); // 반복자 생성
			int idx = 0;
			while (it.hasNext()) {
				arr[idx++] = it.next();
			}

			Arrays.sort(arr);
			System.out.println("#" + tc + " " + arr[arr.length - K]); // 오름차순 정렬했기 때문에 arr.length-K 번째에 있는 수가 K번째로 큰 수

		} // testcase
	} // main
}
