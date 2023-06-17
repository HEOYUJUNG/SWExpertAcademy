package B형특강;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 암호문3_LinkedList구현 {
	static int NODE_MAX = 5000; // 삽입할 때마다 동적으로 추가해주는 것이 아니라, 정적으로 구현하기 위함!
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class Node {
		int data; // 데이터
		Node next; // 다음 노드 주소값

		// 생성자
		public Node(int data) {
			this.data = data;
			this.next = null; // 이거 안 해도 기본 null 들어감!
		}
	}

	static class LinkedList {
		Node head;
		Node tail;
		Node[] nodeArr = new Node[NODE_MAX];
		int nodeCnt = 0;

		// 생성자
		public LinkedList() {

		}

		// 새 node 생성
		Node getNewNode(int data) {
			nodeArr[nodeCnt] = new Node(data);
			return nodeArr[nodeCnt++];
		}

		// I (삽입) - idx번 인덱스 이후에 nums 추가
		void insert(int idx, int[] nums) {
			int start = 0;
			// head 바뀌는 경우 => 먼저 한 개만 추가해서 head 재조정
			if (idx == 0) {
				if (head != null) {
					Node newNode = getNewNode(nums[0]);
					newNode.next = head;
					head = newNode;
				} else {
					head = getNewNode(nums[0]);
				}
				// 남은 수들은 head 뒤에 붙이기
				idx = 1;
				start = 1;
			}

			Node cur = head;
			// idx-1 번째 노드까지 이동 (삽입되기 직전 노드)
			for (int i = 0; i < idx - 1; i++) {
				cur = cur.next;
			}
			for (int i = start; i < nums.length; i++) {
				Node newNode = getNewNode(nums[i]);
				newNode.next = cur.next;
				cur.next = newNode;
				cur = newNode; // 다음 입력되는 수는 뒤로 이어붙여야 함!
			}

			// tail 바뀌는 경우
			if (cur.next == null) {
				tail = cur;
			}
		}

		// D (삭제) - idx번 인덱스부터 cnt개 만큼 삭제
		void delete(int idx, int cnt) {
			Node cur = head;
			// head 바뀌는 경우
			if (idx == 0) {
				for (int i = 0; i < cnt; i++) {
					cur = cur.next;
				}
				head = cur;
				return;
			}

			// idx-1 번째 노드까지 이동 (삭제되기 직전 노드)
			for (int i = 0; i < idx - 1; i++) {
				cur = cur.next;
			}
			Node anchor = cur;
			for (int i = 0; i < cnt; i++) {
				cur = cur.next;
			}
			anchor.next = cur.next; // 삭제 이전과 이후 이어주기

			// tail 바뀌는 경우
			if (anchor.next == null) {
				tail = anchor;
			}
		}

		// A (추가) - 제일 뒤에 data 추가하기
		void add(int data) {
			Node cur = tail;
			Node newNode = getNewNode(data);
			cur.next = newNode;
			tail = newNode;
		}

		// 출력
		void print() throws IOException {
			int cnt = 10;
			Node cur = head;
			while (cnt-- > 0) {
				bw.write(cur.data + " ");
				cur = cur.next;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		for (int tc = 1; tc <= 10; tc++) {
			LinkedList list = new LinkedList();

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] init = new int[N];
			for (int i = 0; i < N; i++) {
				init[i] = Integer.parseInt(st.nextToken());
			}
			list.insert(0, init);

			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String cmd = st.nextToken();
				int x, y;
				if (cmd.equals("I")) {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					int[] nums = new int[y];
					for (int j = 0; j < y; j++) {
						nums[j] = Integer.parseInt(st.nextToken());
					}
					list.insert(x++, nums);
				} else if (cmd.equals("D")) {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					list.delete(x, y);
				} else {
					y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						int s = Integer.parseInt(st.nextToken());
						list.add(s);
					}
				}
			}

			bw.write("#" + tc + " ");
			list.print();
			bw.write("\n");
		} // 테케
		
		bw.flush();
        br.close();
        bw.close();
	}
}
