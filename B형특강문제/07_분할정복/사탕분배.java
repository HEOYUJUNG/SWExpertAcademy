package B형특강04;

import java.io.*;
import java.util.*;

public class 사탕분배 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 나연이 사탕 개수
			int B = Integer.parseInt(st.nextToken()); // 다현이 사탕 개수
			int K = Integer.parseInt(st.nextToken()); // 작업 반복 횟수
			long a = calPower(K, A + B) * A % (A + B);
			sb.append("#").append(tc).append(" ").append(Math.min(a, A + B - a)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static int calPower(int n, int sum) {
		long res = 1;
		long num = 2;
		while (n > 0) {
			if (n % 2 == 1) {
				res = (res * num) % sum;
			}
			num = (num * num) % sum;
			n /= 2;
		}
		return (int) res;
	}
}
