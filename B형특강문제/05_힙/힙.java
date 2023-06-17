package B형특강03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 힙 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int N = Integer.parseInt(br.readLine()); // 연산 개수
//			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//				@Override
//				public int compare(Integer o1, Integer o2) {
//					return o2 - o1;
//				}
//			});
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				if (Integer.parseInt(st.nextToken()) == 1) { // 삽입
					pq.add(Integer.parseInt(st.nextToken()));
				} else { // 삭제
					int res = -1;
					if (!pq.isEmpty()) {
						res = pq.poll();
					}
					sb.append(res + " ");
				}
			}
			sb.append("\n");
		} // testcase
		System.out.println(sb);
	}
}
