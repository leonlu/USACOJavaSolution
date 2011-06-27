// USACO Section 1.2

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
PROG: dualpal
*/

public class dualpal {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"dualpal.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		do{
			if(dualPal(++S)){
				out.println(S);
				if(--N == 0) break;
			}
		}
		while( S != Integer.MAX_VALUE);
		out.close();
		System.exit(0);
	}
	
	
	private static char digitToChar(int digit){
		return (char)(digit + '0');
	}
	
	private static boolean dualPal(int num){
		int count = 0;
		int base = 2;
		do{
			if(isPal(toBaseForm(num,base))){
				if(++count >= 2) return true;
			}
		}while( ++base <= 10);
		return false;
	}
	
	private static String toBaseForm(int num, int base){
		StringBuilder sb = new StringBuilder();
		do{
			char curChar = digitToChar(num % base);
			sb.append(curChar);
		}while( (num /= base) !=0);
		sb.reverse();
		return sb.toString();
	}
	
	private static boolean isPal(String s){
		int low = 0, high = s.length()-1;
		while(low < high){
			if(s.charAt(low)!= s.charAt(high))
				return false;
			low++;high--;
		}
		return true;
	}
}