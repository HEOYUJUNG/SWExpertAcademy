package B형특강04;

import java.io.*;
import java.util.*;

public class 문자열교집합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 두 집합의 원소 개수 (1~10만)
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 두 집합에 같은 문자열이 몇 개 있는지 세려면 완전 탐색으로 해야 할 것 같다.
			// 그러기 위해 배열 2개에 문자열들을 저장해두고 하나씩 비교하려면 N^M만큼의 시간이 소요된다.
			// 따라서 삽입과 색인 처리가 가능하면서 빠르게 탐색이 가능한 
			// HashSet(혹은 HashMap)을 사용했습니다.
			// 그 결과 N*M의 시간복잡도로 문제 해결이 가능했습니다.
			HashMap<String, Integer> map = new HashMap<>();
			for (int i = 0; i < N; i++) {
				map.put(st.nextToken(), 1);
			}
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				if (map.containsKey(st.nextToken()))
					cnt++;
			}
			bw.write("#" + tc + " " + cnt + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
