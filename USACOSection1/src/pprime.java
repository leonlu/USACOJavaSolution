// Section 1.5

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: leonluc1
LANG: JAVA
PROG: pprime
*/

// Genearate palindromic to match prime
public class pprime {
	
	private static List<Integer> res = new ArrayList<Integer>();
	private static String[] init = new String[]{
		"0","1","2","3","4","5","6","7","8","9",
		"00","11","22","33","44","55","66","77","88","99"
		};
	private static int a;
	private static int b;
	private static String bstr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"pprime.out")));
		
		// read data
		StringTokenizer st = new StringTokenizer(in.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		bstr = Integer.toString(b);
		
		// solve
		for(String s : init)
			dfs(s);
		Collections.sort(res);

		// output
		for(Integer num : res)
			out.println(num);
		out.close();
		System.exit(0);
	}
	
	private static void dfs(String n){
		// check and record
		checkAndRecord(n);

		// no further
		if(n.length() + 2 > bstr.length())
			return;
		
		for(int i = 0; i <= 9; i ++){
			String tmp = i + n + i;
			dfs(tmp);
		}
	}
	
	private static void checkAndRecord(String n){
		int tmp = Integer.parseInt(n);
		
		// min a = 5, eliminate some numbers obviously not prime
		if(tmp % 2 ==0 || tmp %3 == 0) return;
		
		// out of range
		if(tmp < a || tmp > b)
			return;
		// has leading zero
		if(Integer.toString(tmp).length() != n.length())
			return;
		
		// is not prime
		for(int i = 5; i*i <=tmp; i+=2){
			if(tmp %i == 0)
				return;
		}
		res.add(tmp);
	}
}
