// Section 2.1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: leonluc1
PROG: sort3
LANG: JAVA
*/

// Max data is 1000, so it's easy to solve the problem
public class sort3 {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")),true);
		
		int lines = Integer.parseInt(in.readLine());
		int[] nums = new int[lines];
		for(int i = 0; i < lines; i++)
			nums[i] = Integer.parseInt(in.readLine());

		int[] sorted = nums.clone();
		Arrays.sort(sorted);
		
		int pair12=0, pair21=0, pair13=0, pair31=0,pair23=0,pair32=0;
		for(int i = 0; i < nums.length; i++){
			int n = nums[i], s = sorted[i];
			if( n == 1 && s == 2) pair12++;
			else if(n == 2 && s == 1) pair21++;
			else if(n == 1 && s == 3) pair13++;
			else if(n == 3 && s == 1) pair31++;
			else if(n == 2 && s == 3) pair23++;
			else if(n == 3 && s == 2) pair32++;
		}
		
		int cnt = Math.min(pair12, pair21) + Math.min(pair13, pair31) + Math.min(pair23, pair32)
					+ Math.abs(pair12 - pair21) * 2;
		
		out.println(cnt);

		System.exit(0);
	}
}