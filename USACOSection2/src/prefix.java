// Section 2.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



/*
ID: leonluc1
PROG: prefix
LANG: JAVA
*/

// backward dp, like the triangle

// f(x) = longest substring to the end at x,
// for every prefix that matches substring beginning at x, figure out the f(x) = max(lengthOfSub + f(x+lengthOfSub))
public class prefix {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		List<String> prefix = new ArrayList<String>();

		String tmpStr;
		while(!(tmpStr = in.readLine()).equals(".")){
			StringTokenizer st = new StringTokenizer(tmpStr);
			while(st.hasMoreTokens())
				prefix.add(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		while((tmpStr = in.readLine()) != null){
			sb.append(tmpStr);
		}
		String s = sb.toString();
		int[] length = new int[s.length()];
		
		for(int i = s.length()-1; i>=0;i--){
			int tmpMaxLen = 0;
			label:
			for(String p : prefix){
				// don't use if(s.substring(i).indexOf(p)==0): TLE!
				boolean flag = false;
				for(int j = 0; j < p.length() && i + j < s.length(); j++){
					if(p.charAt(j) != s.charAt(i+j)){
						continue label;
					}
					if(j == p.length()-1) flag = true;
				}
				if(flag){
					int pLen = p.length();
					int t = (i + pLen) < s.length() ? pLen + length[i + pLen] : pLen;
					tmpMaxLen = t > tmpMaxLen ? t: tmpMaxLen;
				}
			}
			length[i] = tmpMaxLen;
		}
		
		out.println(length[0]);

		out.close();
		System.exit(0);
	}
}
