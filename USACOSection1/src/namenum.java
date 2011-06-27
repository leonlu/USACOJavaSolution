// USACO Section 1.2

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
				"namenum.out")));
		String input = in.readLine();
		List<String> dict = readDict();
		List<String> res = new ArrayList<String>();
		
		for(String s : dict)
			if(convert(s).equals(input))
				res.add(s);
		
		if(res.size() == 0) out.println("NONE");
		else{
			Collections.sort(res);
			for(String s: res)
				out.println(s);
		}
		
		out.close();
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



// data 26678268463 -> tle: 1.7s Map GenString to Dict
//public class namenum {
//	  public static void main (String [] args) throws IOException {
//		  BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
//		  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
//		  String input = in.readLine();
//
//		  String[] genStrings = inputToStrings(input);
//		  
//		  if(genStrings == null) out.println("NONE");
//		  else{
//			  List<String> names = new ArrayList<String>();
//			  HashSet<String> set = readDict();
//			  for(int i = 0; i < genStrings.length; i++)
//				  if(set.contains(genStrings[i]))
//					  names.add(genStrings[i]);
//			  if(names.size() == 0) out.println("NONE");
//			  else{
//				  Collections.sort(names);
//				  for(String s : names)
//					  out.println(s);
//			  }
//		  }
//		  out.close();
//		  System.exit(0);
//	  }
//	  
//	  private static char[] digitToChars(int digit){
//		  switch(digit){
//			  case 2: return new char[]{'A','B','C'};
//			  case 3: return new char[]{'D','E','F'};
//			  case 4: return new char[]{'G','H','I'};
//			  case 5: return new char[]{'J','K','L'};
//			  case 6: return new char[]{'M','N','O'};
//			  case 7: return new char[]{'P','R','S'};
//			  case 8: return new char[]{'T','U','V'};
//			  case 9: return new char[]{'W','X','Y'};
//		  }
//		  return null;
//	  }
//	  
//	  private static String[] inputToStrings(String input){
//		  int length = input.length();
//		  String[] ret = new String[(int)Math.pow(3, length)];
//		  for(int i = 0; i < ret.length; i++){
//			  int count = i;
//			  char[] tmp = new char[length];
//			  for(int j = length - 1; j >= 0; j--){
//				  char[] chartmp = digitToChars(input.charAt(j) - '0');
//				  if(chartmp == null) return null;
//				  tmp[j] = chartmp[(int)(count / Math.pow(3, j))];
//				  count %= Math.pow(3, j);
//			  }
//			  ret[i] = new String(tmp);
//		  }
//		  return ret;
//	  }
//	  
//	  private static HashSet<String> readDict() throws IOException{
//		  HashSet<String> ret = new HashSet<String>();
//		  BufferedReader in = new BufferedReader(new FileReader("dict.txt"));
//		  String tmp;
//		  while( (tmp = in.readLine()) != null)
//			  ret.add(tmp);
//	  
//		  return ret;
//	  }
//}