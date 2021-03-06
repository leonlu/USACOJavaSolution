// Section 2.3.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 PROG: prefix
 LANG: JAVA
 */

// DP: f(x) = longest substring length beginning at x.
// for every prefix that matches substring beginning at x
// f(x) = max(prefixlength + f(x+prefixlength))
public class prefix {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"prefix.out")), true);
		List<String> prefix = new ArrayList<String>();

		// read in
		String tmpStr;
		while (!(tmpStr = in.readLine()).equals(".")) {
			StringTokenizer st = new StringTokenizer(tmpStr);
			while (st.hasMoreTokens())
				prefix.add(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		while ((tmpStr = in.readLine()) != null) {
			sb.append(tmpStr);
		}
		String s = sb.toString();
		int[] length = new int[s.length()];

		// process
		for (int i = s.length() - 1; i >= 0; i--) {
			label: 
			for (String p : prefix) {
				for (int j = 0; j < p.length() && i + j < s.length(); j++) {
					if (p.charAt(j) != s.charAt(i + j))
						continue label;
					if (j == p.length() - 1) {
						int pLen = p.length();
						int t = (i + pLen) < s.length() ? pLen + length[i + pLen] : pLen;
						length[i] = t > length[i] ? t : length[i];
					}
				}
			}
		}

		// output
		out.println(length[0]);

		System.exit(0);
	}
}
