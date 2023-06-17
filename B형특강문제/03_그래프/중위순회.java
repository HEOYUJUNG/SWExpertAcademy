package B형특강02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 중위순회 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());
			tree = new char[N + 1];
			for (int i = 1; i <= N; i++) {
				tree[i] = br.readLine().split(" ")[1].charAt(0);
			}
			traverse(1);
			sb.append("\n");
		} // testcase
		System.out.println(sb);
	}

	public static void traverse(int x) {
		if (x > N) {
			return;
		}
		// L
		traverse(x * 2);
		// V
		sb.append(tree[x]);
		// R
		traverse(x * 2 + 1);
	}
}
