// Section 1.5.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import static java.lang.Math.max;

/*
ID: leonluc1
LANG: JAVA
PROG: numtri
*/

// DP Problem
// newRow[j]+= max(oldRow[j-1], oldRow[j]);

public class numtri {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"numtri.out")),true);
		
		int[] oldRow = null;
		int[] newRow = null;
		
		// initialize
		int N = Integer.parseInt(in.readLine());
		for(int i = 1; i <= N; i++){
			// exchange oldRow with newRow
			if(i != 1) oldRow = newRow; 
			// read in newRow
			newRow = new int[N+2];
			newRow[0] = 0;
			newRow[N+1] = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= i; j++)
				newRow[j] = Integer.parseInt(st.nextToken());
			// calculate
			if(i != 1)
				for(int j = 1; j <=i; j++)
					newRow[j]+= max(oldRow[j-1], oldRow[j]);
		}
			
		// output
		int maxValue = 0;
		for(int i = 0; i < newRow.length; i++)
			maxValue = max(newRow[i],maxValue);
		out.println(maxValue);
		
		System.exit(0);
	}
	
	
}
