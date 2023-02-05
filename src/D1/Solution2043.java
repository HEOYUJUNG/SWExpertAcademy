package D1;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.FileInputStream;

public class Solution2043 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int P = sc.nextInt(); // 정수 한 개 입력받기
		int K = sc.nextInt(); // 정수 한 개 입력받기

		if (P >= K) {
			System.out.println(P - K + 1);
		} else {
			System.out.println((999 - K + 1) + (P + 1));
		}

	} // main
}
