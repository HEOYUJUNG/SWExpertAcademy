package B형특강;

import java.io.*;
import java.util.*;

public class 수열편집_LinkedList {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 수열 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수
			int L = Integer.parseInt(st.nextToken()); // 출력 인덱스

			st = new StringTokenizer(br.readLine());
			List<Integer> list = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				if (cmd.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.add(x, y);
				} else if (cmd.equals("D")) {
					list.remove(Integer.parseInt(st.nextToken()));
				} else {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.set(x, y);
				}
			}

			System.out.print("#" + tc + " ");
			if (L < list.size()) {
				System.out.println(list.get(L));
			} else {
				System.out.println(-1);
			}
		}
	}
}
