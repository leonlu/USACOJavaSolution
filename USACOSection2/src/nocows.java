// Section 2.3

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


public class nocows {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N+1][K+1];
		
		for(int k = 1; k <= K; k++){
			dp[1][k] = 1;
			for(int n = 1; n <= N; n+=2) //youhua: n++  ->  n+=2
				for(int i = 1; i <= n-2; i++)
					dp[n][k] = (dp[n][k] + dp[i][k-1] * dp[n-1-i][k-1]) % 9901;
		}
		
		int res = (dp[N][K] - dp[N][K-1] + 9901) % 9901;
		out.println(res);
		
		out.close();
		System.exit(0);
	}
}
