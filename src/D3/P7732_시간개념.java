package D3;

import java.util.Scanner;

public class P7732_시간개념 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			String now = sc.next();
			String schedule = sc.next();
			int nowH = Integer.parseInt(now.split(":")[0]);
			int nowM = Integer.parseInt(now.split(":")[1]);
			int nowS = Integer.parseInt(now.split(":")[2]);
			int scheH = Integer.parseInt(schedule.split(":")[0]);
			int scheM = Integer.parseInt(schedule.split(":")[1]);
			int scheS = Integer.parseInt(schedule.split(":")[2]);
			int n = (nowH * 60 + nowM) * 60 + nowS; // 지금 시각 sec 단위로 표현
			int s = (scheH * 60 + scheM) * 60 + scheS; // 약속 시각 sec 단위로 표현
			
			// 남은 시간 저장할 변수
			int remain = 0;
			// 현재 시각이 약속 시각보다 빠른 경우 -> 약속 시간 - 현재 시각
			if (n <= s) {
				remain = s - n;
			} else { // 약속 시간이 현재 시각보다 빠른 경우 -> 약속 시간 + (24-현재 시각)
				remain = 24 * 60 * 60 - n + s;
			}

			int S = remain % 60 % 60;
			int M = remain / 60 % 60;
			int H = remain / 60 / 60;

			System.out.printf("#%d %02d:%02d:%02d\n", tc, H, M, S);
		}
	}
}
