package B형특강04;

import java.io.*;
import java.util.*;

public class 단어가등장하는횟수 {
	private static final int EXPONENT1 = 31;
	private static final int EXPONENT2 = 37;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String B = br.readLine(); // 책 내용
			String S = br.readLine(); // 찾고자 하는 단어
			sb.append("#").append(tc).append(" ").append(getCount(B, S)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static int getCount(String string, String pattern) {
		int cnt = 0;
		
		// 해시값 하나만 사용하면 collision이 발생한다.
		// 멀티해싱 해주면 해싱의 정확도를 높일 수 있음!
		// 멀티해싱을 몇 개 써야하는지는 그냥 제출해보고, 틀리면 해싱 더 추가해서 해보고.. 보통 4개정도까지 하면 웬만하면 맞는다!
		int sHash = 0;
		int pHash = 0;
		int sHash2 = 0;
		int pHash2 = 0;
		
		int power1 = 1;
		int power2 = 1;
		for (int j = 0; j < pattern.length(); j++) {
			sHash += hash(string.charAt(pattern.length() - 1 - j), power1);
			pHash += hash(pattern.charAt(pattern.length() - 1 - j), power1);
			sHash2 += hash(string.charAt(pattern.length() - 1 - j), power2);
			pHash2 += hash(pattern.charAt(pattern.length() - 1 - j), power2);
			if (j < pattern.length() - 1) {
				power1 *= EXPONENT1;
				power2 *= EXPONENT2;
			}
		}
		if (sHash == pHash && sHash2 == pHash2)
			cnt++;
		for (int i = 1; i < string.length() - pattern.length() + 1; i++) {
			sHash = EXPONENT1 * (sHash - hash(string.charAt(i - 1), power1)) + string.charAt(pattern.length() - 1 + i);
			sHash2 = EXPONENT2 * (sHash2 - hash(string.charAt(i - 1), power2)) + string.charAt(pattern.length() - 1 + i);
			if (sHash == pHash && sHash2 == pHash2)
				cnt++;
		}
		return cnt;
	}

	static int hash(int value, int power) {
		return value * power;
	}
}
