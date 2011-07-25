// Section 2.2.4

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 PROG: lamps
 LANG: JAVA
 */

public class lamps {
	private static BufferedReader in;
	private static PrintWriter out;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("lamps.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")),
				true);
		int N = Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		boolean[] lampsOn = new boolean[N + 1];
		boolean[] lampsOff = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		int idx;
		while ((idx = Integer.parseInt(st.nextToken())) != -1)
			lampsOn[idx] = true;
		st = new StringTokenizer(in.readLine());
		while ((idx = Integer.parseInt(st.nextToken())) != -1)
			lampsOff[idx] = true;

		List<boolean[]> res = new ArrayList<boolean[]>();

		for (int a = 0; a < 2; a++) {
			for (int b = 0; b < 2; b++)
				for (int c = 0; c < 2; c++)
					label: for (int d = 0; d < 2; d++) {
						boolean[] lamps = new boolean[N + 1];
						for (int i = 1; i < lamps.length; i++)
							lamps[i] = true;
						int count = C;
						if (a == 0 && count-- > 0)
							for (int i = 1; i < lamps.length; i++)
								lamps[i] = !lamps[i];
						if (b == 0 && count-- > 0)
							for (int i = 1; i < lamps.length; i += 2)
								lamps[i] = !lamps[i];
						if (c == 0 && count-- > 0)
							for (int i = 2; i < lamps.length; i += 2)
								lamps[i] = !lamps[i];
						if (d == 0 && count-- > 0)
							for (int i = 1; i < lamps.length; i += 3)
								lamps[i] = !lamps[i];

						for (int i = 1; i < lamps.length; i++) {
							if (lampsOn[i] && !lamps[i])
								continue label;
							if (lampsOff[i] && lamps[i])
								continue label;
						}
						res.add(lamps);
					}
		}
		Collections.sort(res, new Comparator<boolean[]>() {
			public int compare(boolean[] o1, boolean[] o2) {
				for (int i = 1; i < o1.length; i++) {
					if (o1[i] != o2[i]) {
						if (o1[i] && !o2[i])
							return 1;
						if (!o1[i] && o2[i])
							return -1;
					}
				}
				return 0;
			}
		});

		if (res.size() == 0)
			out.println("IMPOSSIBLE");
		else {
			print(res.get(0));
			for (int i = 1; i < res.size(); i++) {
				for (int j = 1; j < N + 1; j++) {
					if (res.get(i)[j] != res.get(i - 1)[j]) {
						print(res.get(i));
						break;
					}
				}
			}
		}
		System.exit(0);
	}

	private static void print(boolean[] lamps) {
		for (int i = 1; i < lamps.length; i++)
			out.print(lamps[i] ? "1" : "0");
		out.println();
	}
}