package B형특강06;

import java.io.*;
import java.util.*;

public class K번째접미어 {
	static StringBuilder sb;
	static char[] result;
	static int K;

	static class Trie {
		char alphabet;
		boolean isEnd;
		Map<Character, Trie> children = new HashMap<>();
		int cnt = 0; // 해당 정점 밑으로 완성된 단어가 몇 개 존재하는지 (현재 정점도 포함)

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
			K = Integer.parseInt(br.readLine()); // 사전순서로 K번째에 해당하는 접미어 찾기
			String word = br.readLine();
			if (word.length() < K) {
				sb.append("none").append("\n");
				continue;
			}
			Trie head = new Trie(); // 루트 노드
			for (int i = 0; i < word.length(); i++) { // i번째 알파벳으로 시작하는 접미어
				Trie indexTrie = head;
				for (int j = i; j < word.length(); j++) { // 현재 접미어에서 각각의 알파벳 보기
					char alphabet = word.charAt(j);
					if (!indexTrie.children.containsKey(alphabet)) { // 새로운 문자라면 정점 추가해주기
						Trie newTrie = new Trie(alphabet);
						indexTrie.children.put(alphabet, newTrie);
					}
					// 알파벳에 해당하는 자식 정점으로 이동
					indexTrie = indexTrie.children.get(alphabet);
					indexTrie.cnt++; // 하위에 완성된 문자열이 있다는 것이기 때문에 cnt 증가!
				}
				indexTrie.isEnd = true;
			}
			result = new char[word.length()];
			dfs(head, 0);
		} // testcase
		System.out.println(sb.toString());
	}

	public static void dfs(Trie trie, int depth) {
		// 해당 정점에서 끝나는 단어가 있는 경우
		if (trie.isEnd) {
			K--;
			if (K == 0) { // K번째 단어인 경우!
				String res = "";
				for (int i = 0; i < depth; i++) {
					res += result[i];
				}
				sb.append(res).append("\n");
			}
		}

		for (char c = 'a'; c <= 'z'; c++) {
			if (trie.children.containsKey(c)) {
				Trie child = trie.children.get(c);
				if (child.cnt < K) { // 해당 방향으로 있는 단어 개수가 남은 K보다 작으면 탐색할 필요x
					K -= child.cnt; // 단어 개수만큼만 빼주기
					continue;
				}
				// 해당 자식 방향으로 단어가 있으면 탐색해 나가기
				result[depth] = c;
				dfs(child, depth + 1);
			}
		}
	}
}
