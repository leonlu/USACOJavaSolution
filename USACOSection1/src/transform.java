// USACO Section 1.2.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
 ID: leonluc1
 LANG: JAVA
 PROG: transform
 */

public class transform {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"transform.out")), true);
		int length = Integer.parseInt(in.readLine());

		// initialize
		char[][] source = new char[length][length];
		for (int i = 0; i < length; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < length; j++) {
				source[i][j] = tmp.charAt(j);
			}
		}
		char[][] target = new char[length][length];
		for (int i = 0; i < length; i++) {
			String tmp = in.readLine();
			for (int j = 0; j < length; j++) {
				target[i][j] = tmp.charAt(j);
			}
		}

		// try one by one
		if (equal(target, rotate(source)))
			out.println(1);
		else if (equal(target, rotate(rotate(source))))
			out.println(2);
		else if (equal(target, rotate(rotate(rotate(source)))))
			out.println(3);
		else if (equal(target, reflect(source)))
			out.println(4);
		else if (equal(target, rotate(reflect(source))))
			out.println(5);
		else if (equal(target, rotate(rotate(reflect(source)))))
			out.println(5);
		else if (equal(target, rotate(rotate(rotate(reflect(source))))))
			out.println(5);
		else if (equal(target, source))
			out.println(6);
		else
			out.println(7);

		System.exit(0);
	}

	// return pattern rotated clockwise 90 degrees
	private static char[][] rotate(char[][] pic) {
		int length = pic.length;
		char[][] ret = new char[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				ret[j][length - i - 1] = pic[i][j];
			}
		}
		return ret;
	}

	// return pattern reflected horizontally
	private static char[][] reflect(char[][] pic) {
		int length = pic.length;
		char[][] ret = new char[length][length];
		for (int i = 0; i < length; i++) {
			int low = 0;
			int high = length - 1;
			while (low <= high) {
				ret[i][low] = pic[i][high];
				ret[i][high] = pic[i][low];
				low++;
				high--;
			}
		}
		return ret;
	}

	// check if equal
	private static boolean equal(char[][] pic1, char[][] pic2) {
		for (int i = 0; i < pic1.length; i++) {
			if (!Arrays.equals(pic1[i], pic2[i]))
				return false;
		}
		return true;
	}
}