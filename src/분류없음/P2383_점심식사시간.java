package 분류없음;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P2383_점심식사시간 {
	static ArrayList<Integer>[] stairs;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 개수
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 방의 한 변 길이
			int minOfTotalTime = Integer.MAX_VALUE;
//			int[][] map = new int[N][N]; // 지도
//			int[][] stairInfo = new int[2][3]; // 계단 2개의 1.길이 2.행 3.열 저장해 둘 배열

			ArrayList<Person> p = new ArrayList<>();
			ArrayList<Stair> s = new ArrayList<>();

			// 계단 2개. 각각의 계단으로 가야할 사람들의 남은 거리를 list에 넣을것임. 남은 거리 오름차순으로 정렬!
			stairs = new ArrayList[2];
			
			// 지도 정보 입력받으면서 사람인 경우와 계단인 경우만 저장하기
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

			// 사람마다 어떤 계단으로 가야하는지 달아놓기
			for (int i = 0; i < p.size(); i++) {
				
				for (int m = 0; m < 2; m++) {
					stairs[m] = new ArrayList<>(); // 초기화 해주기
				}

				
				for (int j = 0; j < i; i++) {
					stairs[0].add(distance(p.get(j), s.get(0)));
				}
				for (int j = i; j < p.size(); j++) {
					stairs[1].add(distance(p.get(j), s.get(1)));
				}
				System.out.println("여기?");
//				System.out.println(stairs[0]);
//				System.out.println(stairs[1]);
				int totalTime = 0;
				// 가까운 사람이 앞에 오도록 정렬
				for (int t = 0; t < 2; t++) {
					Collections.sort(stairs[t]);
					System.out.println(stairs[t]);
					totalTime = Math.max(totalTime, move(stairs[t], s.get(t).length));
				}
				minOfTotalTime = Math.min(minOfTotalTime, totalTime);
				System.out.println(minOfTotalTime);
			}

			System.out.println("#" + tc + " " + minOfTotalTime);
		} // testcase
	} // main

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

	// 두 점 사이 거리 계산해서 반환
	private static int distance(Person p, Stair s) {
		return Math.abs(p.r - s.r) + Math.abs(p.c - s.c);
	}

	// 특정 계단으로 내려가야하는 사람들의 거리가 들어 있는 리스트를 받아서
	// 그 사람들이 전부 내려가는 데 걸리는 시간을 반환
	// length : 현재 계단의 길이
	private static int move(ArrayList<Integer> list, int length) {
		length = (-1) * length;
		int cnt = 0;
		int stairCnt = 0;
		while (!list.isEmpty()) {
//			System.out.println(list);
//			int size = list.size();
			cnt++;
			for (int i = 0; i < list.size(); i++) {
				int tmp = list.get(i);
				if (tmp == 0 && stairCnt == 3) { // 이제 계단을 내려갈 차례인데, 계단에 이미 3명이 있으면 내려갈 수 없음
					continue;
				} else {
					stairCnt++;
				}
				list.set(i, list.get(i) - 1);
				if (list.get(i) < length) {
					list.remove(i--);
					stairCnt--;
				}
			}
		}
		return cnt;
	}
}
