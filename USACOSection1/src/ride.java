// USACO Section 1.1.1

/*
ID: leonluc1
LANG: JAVA
PROG: ride
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ride {
	  public static void main (String [] args) throws IOException {
		    BufferedReader in = new BufferedReader(new FileReader("ride.in"));
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")),true);
		    String comet = in.readLine();
		    String group = in.readLine();
		    
		    if(mod47(comet) == mod47(group))
		    	out.println("GO");
		    else
		    	out.println("STAY");

		    System.exit(0);
	  }
	  
	  private static int mod47(String s){
		  int res = 1;
		  for(int i = 0; i < s.length(); i++)
			  res *= (s.charAt(i)-'A'+1);
		  return res % 47;
	  }
}
