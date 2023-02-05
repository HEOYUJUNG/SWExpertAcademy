package D1;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.FileInputStream;

public class Solution1933 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // 정수 한 개 입력받기

		for (int i = 1; i <= num; i++) {
			if (num % i == 0)
				System.out.printf("%d ", i);
		}

	} // main
}
