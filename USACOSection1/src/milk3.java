// Section 1.4

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import static java.lang.Math.max;
import static java.lang.Math.min;

/*
ID: leonluc1
PROG: milk3
LANG: JAVA
*/


public class milk3 {
	
	private static boolean[][] searched = new boolean[21][21];
	private static boolean[] amount = new boolean[21];
	private static int a,b,c;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		
		//read data
		StringTokenizer st = new StringTokenizer(in.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dfs(0,0,c);
		for (int i = 0;i < c;i++) 
			if (amount[i]) 
				out.print(i + " ");
		out.println(c);

		out.close();
		System.exit(0);
	}
	
	private static void dfs(int x, int y, int z){
		if(searched[x][y]) return;
		searched[x][y] = true;
		if(x == 0) amount[z] = true;
		if (x>0 && y<b) 
			dfs(max(0,x+y-b),min(b,x+y),z);
		if (x>0 && z<c) 
			dfs(max(0,x+z-c),y,min(c,x+z));
		if (y>0 && x<a) 
			dfs(min(a,y+x),max(0,y+x-a),z);
		if (y>0 && z<c) 
			dfs(x,max(0,y+z-c),	min(c,y+z));
		if (z>0 && x<a) 
			dfs(min(a,z+x),y,max(0,z+x-a));
		if (z>0 && y<b) 
			dfs(x,min(b,z+y),max(0,z+y-b));
	}
}