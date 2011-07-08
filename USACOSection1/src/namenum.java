// USACO Section 1.2.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
ID: leonluc1
LANG: JAVA
PROG: namenum
*/

public class namenum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"namenum.out")),true);
		String input = in.readLine();
		List<String> dict = readDict();
		List<String> res = new ArrayList<String>();
		
		// converted names of dictionary to match input string
		for(String s : dict)
			if(convert(s).equals(input))
				res.add(s);
		
		// output
		if(res.size() == 0) out.println("NONE");
		else{
			Collections.sort(res);
			for(String s: res)
				out.println(s);
		}
		
		System.exit(0);
	}
	
	private static String convert(String s){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			sb.append(map(s.charAt(i)));
		}
		return sb.toString();
	}
	
	private static char map(char c){
		switch(c){
			case 'A': case 'B':	case 'C': return '2';
			case 'D': case 'E': case 'F': return '3';
			case 'G': case 'H': case 'I': return '4';
			case 'J': case 'K': case 'L': return '5';
			case 'M': case 'N': case 'O': return '6';
			case 'P': case 'R': case 'S': return '7';
			case 'T': case 'U': case 'V': return '8';
			case 'W': case 'X': case 'Y': return '9';
		}
		return 0;
	}
	
	private static List<String> readDict() throws IOException{
		List<String> ret = new ArrayList<String>();
		BufferedReader in = new BufferedReader(new FileReader("dict.txt"));
		String tmp;
		while( (tmp = in.readLine()) != null)
			ret.add(tmp);
		return ret;
	}
}