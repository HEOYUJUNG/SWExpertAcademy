package B형특강03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중간값구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			PriorityQueue<Integer> L = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙
			PriorityQueue<Integer> R = new PriorityQueue<>(); // 최소힙
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 반복 횟수
			int A = Integer.parseInt(st.nextToken()); // 처음 수
			L.add(A);
			int res = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				// 양쪽 힙에 개수 맞춰서 넣어주기
				L.add(Math.min(X, Y));
				R.add(Math.max(X, Y));
				// 왼쪽, 오른쪽 힙이 중간값보다 작은수, 큰수로 잘 유지되고 있는지 확인
				if (L.peek() > R.peek()) {
					R.add(L.poll());
					L.add(R.poll());
				}
				// 왼쪽 최대힙에서 꺼내면 나오는 수가 중간값!
				res = (res + L.peek()) % 20171109;
			}
			sb.append(res + "\n");
		} // testcase
		System.out.println(sb);
	}
}
