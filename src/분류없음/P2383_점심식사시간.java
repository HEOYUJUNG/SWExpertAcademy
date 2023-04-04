package 분류없음;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class P2383_점심식사시간 {
	// 계단 2개. 각각의 계단으로 가야할 사람들의 남은 거리를 list에 넣을것임
	static PriorityQueue<Integer>[] stairs;

	private static class Person {
		int r, c; // 사람이 있는 위치

		public Person(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	private static class Stair {
		int r, c, length; // 계단 위치 & 길이

		public Stair(int r, int c, int length) {
			this.r = r;
			this.c = c;
			this.length = length;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 방의 한 변 길이
			int minOfTotalTime = Integer.MAX_VALUE;

			ArrayList<Person> p = new ArrayList<>(); // 사람들 저장할 리스트
			ArrayList<Stair> s = new ArrayList<>(); // 계단 저장할 리스트 (2개)

			// 입력 받으면서 사람, 계단 나눠서 저장하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int tmp = sc.nextInt();
					if (tmp == 1) { // 사람인 경우
						p.add(new Person(r, c));
					} else if (tmp >= 2) { // 계단인 경우
						s.add(new Stair(r, c, tmp));
					}
				}
			}

			int people = p.size(); // 전체 사람 수
			for (int i = 0; i < (1 << people); i++) { // 부분 집합 개수
				stairs = new PriorityQueue[2]; // 처음에 가까운 사람들이 앞에 오게 정렬해주고 싶으니까 우선순위큐 썼음
				// 초기화 해주기
				for (int m = 0; m < 2; m++) {
					stairs[m] = new PriorityQueue<>();
				}

				for (int j = 0; j < people; j++) {
					if ((i & (1 << j)) > 0) { // 부분집합 i가 j번째 원소(=사람)를 가지고 있음
						// 첫번째 계단으로 갈 사람 -> 서둘러 가세요~~
						stairs[0].offer(distance(p.get(j), s.get(0)));
					} else {
						// 두번째 계단으로 갈 사람 -> 서둘러 가세요~~
						stairs[1].offer(distance(p.get(j), s.get(1)));
					}
				}

				// 여기서 사람들 별로 어느 계단으로 갈 것인지 나뉘어 있음
				// 가까운 사람이 앞에 오도록 정렬
				int totalTime = 0;
				for (int t = 0; t < 2; t++) {
					Queue<Integer> tempQ = new LinkedList<>();
					while (!stairs[t].isEmpty()) {
						tempQ.add(stairs[t].poll());
					}
					totalTime = Math.max(totalTime, move(tempQ, s.get(t).length));
				}
				minOfTotalTime = Math.min(totalTime, minOfTotalTime); // 최소 시간 갱신
			}
			System.out.println("#" + tc + " " + minOfTotalTime);
		} // tc 완 ㅋ
	}

	// 사람과 계단 사이 거리 계산해서 반환
	private static int distance(Person p, Stair s) {
		return Math.abs(p.r - s.r) + Math.abs(p.c - s.c);
	}

	// 특정 계단으로 내려가야하는 사람들의 거리가 들어 있는 리스트를 받아서
	// 그 사람들이 전부 내려가는 데 걸리는 시간을 반환
	// length : 현재 계단의 길이
	private static int move(Queue<Integer> q, int length) {
		length = (-1) * length;
		int minute = 0; // 걸린 시간
		int stairCnt = 0; // 계단에 있는 사람 수 -> 핸드폰 하지 말고 빨리빨리 갈 것 !!

		while (!q.isEmpty()) { // 우큐 빌 때까지 돌아 ~~
			minute++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int tmp = q.poll();
				if (tmp == 0 && stairCnt == 3) { // 이제 계단을 내려갈 차례인데, 계단에 이미 3명이 있으면 내려갈 수 없음 -> 빨리 가세요!!
					q.offer(tmp);
					continue; // ㄴㄴ 돌아가 ~~
				} else if (tmp == 0) { // 계단 내려갈 수 있으면 -> 무릎 조심 !!(연골 브륄레,,)
					stairCnt++;
				}
				tmp -= 1;
				if (tmp < length) {
					stairCnt--;
					continue; // ㄴㄴ 돌아가 ~~
				}
				q.offer(tmp); // !!!!!!
			}
		}
		return minute; // 걸린 시간 리턴 ㅋ
	}
}
