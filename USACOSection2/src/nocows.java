// Section 2.3.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 PROG: nocows
 LANG: JAVA
 */

// dp: f(n,k) = Sigma(f(i,k-1)*f(n-1-i,k-1)), i = 1,2,...n-2
public class nocows {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"nocows.out")), true);
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][K + 1];

		for (int k = 1; k <= K; k++) {
			dp[1][k] = 1;
			// optimization: since n must be an odd number, n++ -> n+=2
			for (int n = 1; n <= N; n += 2)
				for (int i = 1; i <= n - 2; i++)
					dp[n][k] = (dp[n][k] + dp[i][k - 1] * dp[n - 1 - i][k - 1]) % 9901;
		}

		int res = (dp[N][K] - dp[N][K - 1] + 9901) % 9901;
		out.println(res);

		System.exit(0);
	}
}
