/********미해결 코드************
package D3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P3307_최장증가부분수열 {
	public static void main(String[] args) {
		// 각 수열의 원소는 1 이상 2^31-1 이하의 자연수 => int로 커버 가능
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();		
		
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt(); // 수열의 길이
			int[] A = new int[N];
			for (int j = 0; j < N; j++) {
				A[j] = sc.nextInt();
			}
			
			
//			System.out.println(Arrays.toString(A));  // 배열 잘 들어갔나 확인
			int lenMax = 0; // 최장 부분 증가 수열의 길이 담을 변수
			int idx = 0;			
			while(idx+lenMax < N) {   // 뒷쪽에 있는 것부터 탐색하기 시작할 경우, 이미 이전에 탐색한 최장 길이가 나올 수 없으면 굳이 탐색하지 않음
				List<Integer> B = new ArrayList<>();
				B.add(A[idx]);
				int temp_idx = idx; // B에 마지막을 들어가 있는 요소의 A에서의 인덱스
				System.out.println(B.toString());
				
				for(int j = idx+1;j<N;j++) {
					if(A[j]>A[temp_idx]) {
						B.add(A[j]);
						temp_idx = j;
					}
				}
				
				idx++;
				int len = B.size(); 
				lenMax= Math.max(lenMax, len);
				System.out.println(B.toString());
				System.out.println(len);
			}
			
			System.out.printf("#%d %d\n", i + 1, lenMax);
		} // test-case
	} // main
}
