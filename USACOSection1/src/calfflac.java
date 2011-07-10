// USACO Section 1.3.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: leonluc1
LANG: JAVA
PROG: calfflac
*/

/* O(n) = 20000 * 2000 * 2 */
public class calfflac {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"calfflac.out")),true);

		// read this way to read in everything
		char[] text = new char[20000];
		int textLength = 0;
		while(in.ready())
			text[textLength++] = (char)in.read();
		String s = new String(text, 0, textLength);
		
		int resLen = 0;
		String res = null;
		String tmp = null;
		
		/* AABAA*/
		for(int i = 0; i < s.length(); i++){
			if(isChar(s.charAt(i))){
				tmp = res(s,i,i);
				if(tmp != null && alphabetCharCount(tmp) > resLen){
					resLen = alphabetCharCount(tmp);
					res = tmp;
				}
			}
		}
		
		/* AABBAA*/
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(isChar(c)){
				int ncpos = nextChar(s,c,i+1);
				if(ncpos != -1){
					tmp = res(s,i,ncpos);
					if(tmp != null && alphabetCharCount(tmp) > resLen){
						resLen = alphabetCharCount(tmp);
						res = tmp;
					}
				}
			}
		}
		
		out.println(resLen);
		out.println(res);
		System.exit(0);
	}
	
	private static int nextChar(String s, char c, int beg){
		for(int i = beg; i < s.length(); i++){
			if(s.charAt(i) == c)
				return i;
			else if(isChar(s.charAt(i)))
				return -1;
		}
		return -1;
	}
	
	private static int alphabetCharCount(String s){
		int cnt = 0;
		for(int i = 0; i < s.length(); i++){
			if(isChar(s.charAt(i)))
				cnt ++;
		}
		return cnt;
	}
	
	private static String res(String s, int low, int high){
		int first = low,last = high;
		while(low >=0 && high <= s.length()-1){
			if(!isChar(s.charAt(low))){
				low--; 
				continue;
			}
			if(!isChar(s.charAt(high))){
				high++;
				continue;
			}
			if(!isCharEqual(s.charAt(low),s.charAt(high)))
				return s.substring(first,last+1);
			else{
				first = low; last = high;
				low--; high++;
			}
		}
		return s.substring(first,last+1);
	}
	
	private static boolean isChar(char c){
		if( c >= 'a' && c <= 'z') return true;
		if( c >= 'A' && c <= 'Z') return true;
		return false;
	}
	
	private static boolean isCharEqual(char c1, char c2){
		return Character.toLowerCase(c1) == Character.toLowerCase(c2);
	}
}