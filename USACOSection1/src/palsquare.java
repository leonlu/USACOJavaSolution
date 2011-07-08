// USACO Section 1.2.4

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: leonluc1
 LANG: JAVA
 PROG: palsquare
 */

public class palsquare {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"palsquare.out")),true);
		int base = Integer.parseInt(in.readLine());
		for (int i = 1; i <= 300; i++) {
			String square = toBaseForm(i * i, base);
			if (isPalsquare(square)) {
				out.print(toBaseForm(i, base));
				out.print(" ");
				out.println(square);
			}
		}
		System.exit(0);
	}

	private static char digitToChar(int digit) {
		if (digit < 10)
			return (char) (digit + '0');
		else
			return (char) ('A' - 10 + digit);
	}

	private static String toBaseForm(int num, int base) {
		StringBuilder sb = new StringBuilder();
		do {
			char curChar = digitToChar(num % base);
			sb.append(curChar);
		} while ((num /= base) != 0);
		sb.reverse();
		return sb.toString();
	}

	private static boolean isPalsquare(String s) {
		int low = 0, high = s.length() - 1;
		while (low < high) {
			if (s.charAt(low) != s.charAt(high))
				return false;
			low++;
			high--;
		}
		return true;
	}
}