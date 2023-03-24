package D4;

import java.util.Scanner;

public class P1486_장훈이의높은선반 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 점원 인수
            int B = sc.nextInt(); // 선반 높이
            int[] H = new int[N];
            int S = 0; // 점원들 키의 합
            int min = Integer.MAX_VALUE; // 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것
            for (int i = 0; i < N; i++) {
                H[i] = sc.nextInt();
                S += H[i];
            }
 
            for (int i = 0; i < (1 << N); i++) { // 부분집합 개수 2^N개
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) > 0) { // j번째 원소를 포함하면
                        sum += H[j];
                    }
                }
 
//              System.out.println("i : " + i + " / sum : " + sum);
 
                // 현재 부분집합에서 탑의 높이는 sum
                if (B <= sum) {
                    min = (sum - B < min) ? sum - B : min;
                }
            }
 
            System.out.println("#" + tc + " " + min);
        }
    }
}