package B형특강06;

import java.io.*;
import java.util.*;

public class K번째문자열 {
	static StringBuilder sb;
	static char[] result;
	static int idx;
	static int K;

	static class Trie {
		char alphabet;
		Map<Character, Trie> children = new HashMap<>();
		int cnt = 0;

		public Trie(char alphabet) {
			this.alphabet = alphabet;
		}

		public Trie() {

		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			K = Integer.parseInt(br.readLine()) + 1;
			String word = br.readLine();
			Trie head = new Trie();
			for (int i = 0; i < word.length(); i++) { // 부분문자열의 시작 위치
				insert(word, i, head);
			}
			result = new char[word.length()];
			idx = 0;
			dfs(head, 0);
			if (K > 0) {
				sb.append("none").append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	// word 문자열의 idx번째 문자를 trie에 삽입한다!
	public static int insert(String word, int idx, Trie trie) {
		if (idx == word.length())
			return 0;
		char alphabet = word.charAt(idx);
		int subCnt = 0; // 새로 추가된 개수
		if (!trie.children.containsKey(alphabet)) { // 새로 추가되는 경우
			Trie newTrie = new Trie(alphabet);
			newTrie.cnt = 1;
			subCnt = 1;
			trie.children.put(alphabet, newTrie);
		}
		// 이전과 중복되는 경우 0이 return되기 때문에 중복 카운팅되지 않음!
		subCnt += insert(word, idx + 1, trie.children.get(alphabet));
		trie.cnt += subCnt;
		return subCnt;
	}

	public static void dfs(Trie trie, int depth) {
		K--;
		if (K == 0) {
			String res = "";
			for (int i = 0; i < depth; i++) {
				res += result[i];
			}
			sb.append(res).append("\n");
			return;
		}

		for (char i = 'a'; i <= 'z'; i++) { // 낮은 알파벳부터 하나씩 이동한다.
			if (trie.children.containsKey(i)) { // 해당 알파벳으로 이동할 수 있다면,
				Trie child = trie.children.get(i);
				if (child.cnt < K) { // 해당 정점으로 이동하더라도 K 개의 문자열보다 적은 개수의 문자열이 있다면,
					K -= child.cnt; // 빠르게 해당 개수만큼 skip 한다.
					continue;
				}
				result[depth] = i;
				dfs(trie.children.get(i), depth + 1);
			}
		}
	}
}
