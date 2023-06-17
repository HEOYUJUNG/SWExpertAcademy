package B형특강;

import java.io.*;
import java.util.*;

public class 수열편집_배열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 수열 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수
			int L = Integer.parseInt(st.nextToken()); // 출력 인덱스

			st = new StringTokenizer(br.readLine());
			int[] arr = new int[2000];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				int x, y;
				if (cmd.equals("I")) {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for (int idx = N - 1; idx >= x; idx--) {
						arr[idx + 1] = arr[idx];
					}
					arr[x] = y;
					N++; // 길이 증가
				} else if (cmd.equals("D")) {
					x = Integer.parseInt(st.nextToken());
					for (int idx = x; idx < N; idx++) {
						arr[idx] = arr[idx + 1];
					}
					N--; // 길이 감소
				} else {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					arr[x] = y;
				}
			}

			System.out.print("#" + tc + " ");
			if (L < N) {
				System.out.println(arr[L]);
			} else {
				System.out.println(-1);
			}
		}
	}
}
