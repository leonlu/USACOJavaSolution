// Section 2.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: leonluc1
PROG: subset
LANG: JAVA
*/

// dfs can't do, 2^39
// dp a[i][j], i: num, j: sum of nums <=i, a[i][j] = count
// a[i][j]= a[i-1][j-i](use i)+a[i-1][j](don't use i) 
public class subset {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int N = Integer.parseInt(in.readLine());
		int sum = (1+N) * N /2;
		
		if(sum % 2 != 0) 
			out.println(0);
		else{
			int partitionSum = sum /2;
			long[][] a = new long[N+1][partitionSum + 1];
			for(int i = 1; i <=N; i++) a[i][0] = 1;
			a[1][1] = 1;
			for(int i = 2; i <= N; i++)
				for(int j = 1; j <= partitionSum; j++)
					a[i][j] = a[i-1][j] + (j>=i ? a[i-1][j-i] : 0);
			out.println(a[N][partitionSum] /2);
		}
		out.close();
		System.exit(0);
	}
}
