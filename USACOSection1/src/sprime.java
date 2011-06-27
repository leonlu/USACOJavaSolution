// Section 1.5

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: leonluc1
PROG: sprime
LANG: JAVA
*/


public class sprime {
	private static int N;
	private static int[] init = new int[]{2,3,5,7};
	private static BufferedReader in;
	private static PrintWriter out;
	
	public static void main(String[] args) throws Exception{
		in = new BufferedReader(new FileReader("sprime.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		
		N = Integer.parseInt(in.readLine());
		for(int i : init)
			dfs(i);
		
		out.close();
		System.exit(0);
	}
	
	private static void dfs(int n){
		// if not prime return
		if(n != 2 && n % 2 == 0) return;
		for(int i = 3; i * i <=n; i+=2){
			if(n % i == 0) return;
		}
		
		// if the length, then output
		if(Integer.toString(n).length() == N)
			out.println(n);
		
		for(int i = 0; i <=9; i++)
			dfs(10 * n + i);
	}
}