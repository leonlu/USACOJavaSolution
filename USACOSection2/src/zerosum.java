// Section 2.3.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 ID: leonluc1
 PROG: zerosum
 LANG: JAVA
 */

// dfs
public class zerosum {

	private static int N;
	private static char[] operators;
	private static PrintWriter out;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		out = new PrintWriter(
				new BufferedWriter(new FileWriter("zerosum.out")), true);

		N = Integer.parseInt(in.readLine());
		operators = new char[N - 1];
		dfs(0);

		System.exit(0);
	}

	private static void dfs(int t) {
		if (t == N - 1) {
			int[] nums = new int[N + 1];
			for (int i = 1; i <= N; i++)
				nums[i] = i;
			int res = 0;
			for (int i = N; i >= 2; i--)
				switch (operators[i - 2]) {
				case ' ':
					nums[i - 1] = Integer.parseInt(Integer
							.toString(nums[i - 1]) + Integer.toString(nums[i]));
					break;
				case '+':
					res += nums[i];
					break;
				case '-':
					res -= nums[i];
					break;
				}
			res += nums[1];

			if (res == 0) {
				for (int i = 1; i <= N; i++) {
					out.print(i);
					if (i <= N - 1)
						out.print(operators[i - 1]);
				}
				out.println();
			}
			return;
		}
		operators[t] = ' ';
		dfs(t + 1);
		operators[t] = '+';
		dfs(t + 1);
		operators[t] = '-';
		dfs(t + 1);
	}
}
