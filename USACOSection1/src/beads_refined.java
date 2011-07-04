// USACO Section 1.1.4

/*
ID: leonluc1
LANG: JAVA
PROG: beads
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//Using DP, it is O(n)
public class beads_refined {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"beads.out")));

		in.readLine();
		String line = in.readLine();
		String beads = line + line;

		int[] rLeft = new int[beads.length()];
		int[] bLeft = new int[beads.length()];
		rLeft[0] = bLeft[0] = 0;

		for (int i = 0; i < beads.length() - 1; i++) {
			if (beads.charAt(i) == 'r') {
				rLeft[i + 1] = rLeft[i] + 1;
				bLeft[i + 1] = 0;
			} else if (beads.charAt(i) == 'b') {
				rLeft[i + 1] = 0;
				bLeft[i + 1] = bLeft[i] + 1;
			} else {
				rLeft[i + 1] = rLeft[i] + 1;
				bLeft[i + 1] = bLeft[i] + 1;
			}
		}

		int[] rRight = new int[beads.length() + 1];
		int[] bRight = new int[beads.length() + 1];
		rRight[beads.length()] = 0;
		bRight[beads.length()] = 0;

		for (int i = beads.length() - 1; i >= 0; i--) {
			if (beads.charAt(i) == 'r') {
				rRight[i] = rRight[i + 1] + 1;
				bRight[i] = 0;
			} else if (beads.charAt(i) == 'b') {
				rRight[i] = 0;
				bRight[i] = bRight[i + 1] + 1;
			} else {
				rRight[i] = rRight[i + 1] + 1;
				bRight[i] = bRight[i + 1] + 1;
			}
		}

		int max = 0;
		for (int i = 0; i < beads.length(); i++) {
			if (rRight[i] + bLeft[i] > max)
				max = rRight[i] + bLeft[i];
			if (rLeft[i] + bRight[i] > max)
				max = rLeft[i] + bRight[i];
		}

		if (max > line.length())
			max = line.length();

		out.println(max);

		out.close();
		System.exit(0);
	}
}
