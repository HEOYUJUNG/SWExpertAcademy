package P01_병사관리;

public class UserSolution {
	public class Node {
		int id; // 고유번호
		int v; // 버전
		Node nxt;

		public Node() {
		}

		public Node(int id, int v) {
			this.id = id;
			this.v = v;
		}
	}

	public Node[] nodes = new Node[200025]; // hire 10만 + update 10만 + 각 링크드리스트별 더미 1개씩 5*5
	public int cnt = 0; // 노드 번호
	public int[] version = new int[100001];
	public int[] team = new int[100001];

	public Node getNewNode(int id, Node nxt) {
		Node ret = nodes[cnt++];
		ret.id = id;
		ret.nxt = nxt;
		ret.v = ++version[id]; // 새로운 노드 가져올 때 자동으로 버전 관리가 된다!!
		return ret;
	}

	public class Team {
		// 팀별로 1~5점의 head, tail만 저장
		Node[] head = new Node[6];
		Node[] tail = new Node[6];
	}

	public Team[] teams = new Team[6]; // 1~5팀

	public void init() {
		cnt = 0;
		for (int i = 0; i < 200025; i++) {
			nodes[i] = new Node();
		}
		for (int i = 1; i <= 5; i++) {
			teams[i] = new Team();
			for (int j = 1; j <= 5; j++) {
				teams[i].tail[j] = teams[i].head[j] = getNewNode(0, null); // 더미
			}
		}
		for (int i = 1; i <= 100000; i++) {
			version[i] = 0;
			team[i] = 0;
		}
	}

	public void hire(int mID, int mTeam, int mScore) {
		Node newNode = getNewNode(mID, null);
		teams[mTeam].tail[mScore].nxt = newNode; // 해당팀, 해당점수의 연결리스트에 이어 붙여주고
		teams[mTeam].tail[mScore] = newNode; // 해당 연결리스트의 tail 정보 업데이트
		team[mID] = mTeam; // 병사의 팀 정보 저장
	}

	public void fire(int mID) {
		version[mID] = -1;
	}

	public void updateSoldier(int mID, int mScore) {
		hire(mID, team[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		if (mChangeScore < 0) { // 음수면 2->1, 3->2 ... 순서로 연결리스트 붙여줘야 함!
			for (int before = 1; before <= 5; before++) {
				int after = before + mChangeScore;
				after = (after < 1) ? 1 : after;
				if (before == after)
					continue;
				if (teams[mTeam].head[before].nxt == null)
					continue; // 바꾸기 전 점수에 해당하는 병사들이 없으면 패쓰~
				// 1) 옮기고
				teams[mTeam].tail[after].nxt = teams[mTeam].head[before].nxt;
				teams[mTeam].tail[after] = teams[mTeam].tail[before];
				// 2) 지우기
				teams[mTeam].head[before].nxt = null;
				teams[mTeam].tail[before] = teams[mTeam].head[before];
			}
		} else {
			for (int before = 5; before >= 1; before--) {
				int after = before + mChangeScore;
				after = (after > 5) ? 5 : after;
				if (before == after)
					continue;
				if (teams[mTeam].head[before].nxt == null)
					continue; // 바꾸기 전 점수에 해당하는 병사들이 없으면 패쓰~
				// 1) 옮기고
				teams[mTeam].tail[after].nxt = teams[mTeam].head[before].nxt;
				teams[mTeam].tail[after] = teams[mTeam].tail[before];
				// 2) 지우기
				teams[mTeam].head[before].nxt = null;
				teams[mTeam].tail[before] = teams[mTeam].head[before];
			}
		}
	}

	public int bestSoldier(int mTeam) {
		for (int i = 5; i >= 1; i--) { // 점수5인 연결리스트부터 탐색
			Node node = teams[mTeam].head[i].nxt; // 없으면 다음으로 높은 점수 탐색
			if (node == null)
				continue;
			int ID = 0;
			while (node != null) {
				if (node.v == version[node.id]) { // 가장 최신 버전인지 (중간에 업데이트가 있어서 버려진 노드면 고려하면 안된다!)
					ID = ID < node.id ? node.id : ID;
				}
				node = node.nxt;
			}
			if (ID != 0) // 노드가 있긴 한데 최신버전이 아닌 경우는 다음 점수 탐색해야 함
				return ID;
		}
		return 0;
	}
}
