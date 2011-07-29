// Section 2.3.4

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 PROG: money
 LANG: JAVA
 */

//dp : f(i,j) = f(i-1,j) + f(i, j-coin(i))
public class money {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"money.out")), true);

		StringTokenizer st = new StringTokenizer(in.readLine());
		int V = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] coins = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			if (!st.hasMoreTokens())
				st = new StringTokenizer(in.readLine());
			coins[i] = Integer.parseInt(st.nextToken());
		}

		long[][] dp = new long[V + 1][N + 1]; // long is a must

		for (int i = 0; i <= V; i++)
			dp[i][0] = 1;

		for (int i = 1; i <= V; i++)
			for (int j = 1; j <= N; j++)
				dp[i][j] = dp[i - 1][j]
						+ (j - coins[i] >= 0 ? dp[i][j - coins[i]] : 0);

		out.println(dp[V][N]);

		System.exit(0);
	}
}
