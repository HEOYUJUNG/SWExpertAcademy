package B형특강07;

import java.io.*;
import java.util.*;

public class SegmentTree연습1 {
	static int n;
	static int[] a, maxTree, minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc);
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			a = new int[n];
			maxTree = new int[4 * n]; // 트리의 길이가 4*n를 절대 넘을 수 없음 (넉넉하게 사용 가능)
			minTree = new int[4 * n];

			// a 배열 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			// 초기화.
			init(1, 0, n - 1);

			// 쿼리 실행
			for (int i = 0; i < q; i++) {
				st = new StringTokenizer(br.readLine());
				int query = Integer.parseInt(st.nextToken());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				if (query == 0) {
					// left번째를 right로 바꾸기
					update(1, 0, n - 1, left, right);
				} else if (query == 1) {
					// left ~ right-1 까지의 최댓값과 최소값 차이 출력
					int max = queryMax(1, 0, n - 1, left, right - 1);
					int min = queryMin(1, 0, n - 1, left, right - 1);
					sb.append(" ").append(max - min);
				}
			}
			sb.append("\n");
		} // testcase
		System.out.println(sb.toString());
	}

	/**
	 * 
	 * @param node      : 각 정점의 번호
	 * @param nodeLeft  : nodeLeft ~ nodeRight => 각 정점이 담당하는 범위
	 * @param nodeRight
	 */
	public static void init(int node, int nodeLeft, int nodeRight) {
		// 한 칸만 남은 경우!
		if (nodeLeft == nodeRight) {
			maxTree[node] = a[nodeLeft];
			minTree[node] = a[nodeLeft];
			return;
		}

		int mid = (nodeLeft + nodeRight) / 2;

		// 반으로 쪼개면서 내려가기
		init(node * 2, nodeLeft, mid);
		init(node * 2 + 1, mid + 1, nodeRight);

		maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
		minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
	}

	/*
	 * 
	 * @param node : 각 정점의 번호
	 * 
	 * @param nodeLeft : nodeLeft ~ nodeRight => 각 정점이 담당하는 범위
	 * 
	 * @param nodeRight
	 * 
	 * @param queryIdx : 쿼리문에서 입력한 값. queryIdx에 해당하는 숫자를 업데이트 해야함
	 * 
	 * @param value : 쿼리문에서 입력한 값. 이 값으로 업데이트 해야함
	 */
	public static void update(int node, int nodeLeft, int nodeRight, int queryIdx, int value) {
		// queryIdx가 a배열의 범위를 벗어나면 종료
		if (queryIdx < nodeLeft || nodeRight < queryIdx) {
			return;
		}

		// 한 칸만 남았을 때, 해당 칸을 value로 업데이트 & 종료
		if (nodeLeft == nodeRight) {
			maxTree[node] = value;
			minTree[node] = value;
			return;
		}

		int mid = (nodeLeft + nodeRight) / 2;

		// 하위 노드들 먼저 업데이트 하기
		update(node * 2, nodeLeft, mid, queryIdx, value);
		update(node * 2 + 1, mid + 1, nodeRight, queryIdx, value);

		// 하위 노드들 업데이트 된 사항을 반영해서 현재 노드도 업데이트 하기
		maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
		minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
	}

	/**
	 * 최대값 쿼리
	 * 
	 * @param node       : 각 정점의 번호
	 * @param nodeLeft   : nodeLeft ~ nodeRight => 각 정점이 담당하는 범위
	 * @param nodeRight
	 * @param queryLeft  : queryLeft ~ nodeRight => 이 범위에서의 최대값을 찾고싶음
	 * @param queryRight
	 * @return
	 */
	public static int queryMax(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		// 찾는 범위가 노드 볌위 벗어나면 0 반환
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return 0;
		}
		// 찾는 범위가 해당 node를 전부 포함하고 있으면 해당 노드의 최대값 반환하면 됨 (더 쪼갤 필요 x)
		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return maxTree[node];
		}

		int mid = (nodeLeft + nodeRight) / 2;
		int leftMax = queryMax(node * 2, nodeLeft, mid, queryLeft, queryRight);
		int rightMax = queryMax(node * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return Math.max(leftMax, rightMax);
	}

	/**
	 * 최소값 쿼리
	 * 
	 * @param node
	 * @param nodeLeft
	 * @param nodeRight
	 * @param queryLeft
	 * @param queryRight
	 * @return
	 */
	public static int queryMin(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
		// 찾는 범위가 노드 볌위 벗어나면 가능한 최대값 반환
		if (queryRight < nodeLeft || nodeRight < queryLeft) {
			return Integer.MAX_VALUE;
		}
		// 찾는 범위가 해당 node를 전부 포함하고 있으면 해당 노드의 최대값 반환하면 됨 (더 쪼갤 필요 x)
		if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
			return minTree[node];
		}

		int mid = (nodeLeft + nodeRight) / 2;
		int leftMin = queryMin(node * 2, nodeLeft, mid, queryLeft, queryRight);
		int rightMin = queryMin(node * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight);
		return Math.min(leftMin, rightMin);
	}
}
