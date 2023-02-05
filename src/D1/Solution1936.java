package D1;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.FileInputStream;

public class Solution1936 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt(); // 정수 한 개 입력받기
		int B = sc.nextInt(); // 정수 한 개 입력받기

		if ((A == 1 && B == 3) || (A == 2 && B == 1) || (A == 3 && B == 2)) {
			System.out.println('A');
		} else {
			System.out.println('B');
		}

	} // main
}
