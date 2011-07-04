// USACO Section 1.1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 ID: leonluc1
 LANG: JAVA
 PROG: friday
 */

public class friday {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"friday.out")), true);

		int N = Integer.parseInt(in.readLine());
		// 0:Sunday 1:Monday ... 5:Friday 6:Saturday
		int[] day13thCount = new int[7];
		int current13th = 0;

		// for every year, every month
		for (int year = 1900; year < 1900 + N; year++) {
			for (int month = 1; month <= 12; month++) {
				if (year == 1900 && month == 1)
					current13th = 6;
				else 
					current13th = (next13thNeedAddDays(month, year) + current13th) % 7;
				day13thCount[current13th]++;
			}
		}

		// output 6:Saturday 0:Sunday ... 5:Friday
		out.print(day13thCount[6] + " ");
		for (int i = 0; i <= 4; i++)
			out.print(day13thCount[i] + " ");
		out.println(day13thCount[5]);

		System.exit(0);
	}

	private static boolean leapYear(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 100 == 0)
			if (year % 400 == 0)
				return true;
			else
				return false;
		return true;
	}

	private static int next13thNeedAddDays(int month, int year) {
		switch (month) {
		case 2:
			return 31;
		case 3:
			return leapYear(year) ? 29 : 28;
		case 4:
			return 31;
		case 5:
			return 30;
		case 6:
			return 31;
		case 7:
			return 30;
		case 8:
			return 31;
		case 9:
			return 31;
		case 10:
			return 30;
		case 11:
			return 31;
		case 12:
			return 30;
		case 1:
			return 31;
		}
		// can't reach here
		return 0;
	}
}
