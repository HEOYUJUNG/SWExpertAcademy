package D1;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.FileInputStream;

public class Solution2019 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // 정수 한 개 입력받기

		for (int i = 0; i <= num; i++) {
			int result = 1;
			for (int j = 0; j < i; j++) {
				result *= 2;
			}
			System.out.printf("%d ", result);
		}

	} // main
}
