// Section 1.5

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: leonluc1
LANG: JAVA
PROG: numtri
*/

// DP Problem
// f(i,j) = max{f(i+1,j),f(i+1,j+1)} + f(i,j)
// from bottom to top
public class numtri {
	private static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"numtri.out")));
		
		int N = Integer.parseInt(in.readLine());

		// only about a half space is saved, so if the space is not an issue, we can directly use a 2-dimension array eg. this problem
		nums = new int[(1+N) * N / 2];
		int idx = 0;
		for(int i = 1; i <= N; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= i; j++)
				nums[idx++] = Integer.parseInt(st.nextToken());
		}
		
		idx = 0;
		for(int i = N-1; i >= 1; i--)
			for(int j = 1; j <=i; j++)
				setF(i,j, Math.max(getF(i+1,j), getF(i+1,j+1)) + getF(i,j));
			

		out.println(getF(1,1));
		out.close();
		System.exit(0);
	}
	
	private static int getF(int i, int j){
		return nums[i * (i-1) / 2 + j -1];
	}
	private static void setF(int i, int j, int v){
		nums[i * (i-1) / 2 + j -1] = v;
	}
}
