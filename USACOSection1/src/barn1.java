// USACO Section 1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
ID: leonluc1
LANG: JAVA
PROG: barn1
*/

public class barn1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"barn1.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		/*int S =*/ Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] stallNos = new int[C];
		for(int i = 0; i < stallNos.length; i++){
			stallNos[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(stallNos);
		int[] distances = new int[C-1];
		for(int i = 0; i < distances.length; i++){
			distances[i]= stallNos[i+1] - stallNos[i] - 1;
		}
		Arrays.sort(distances);
		int index = distances.length-1;
		index -= (M-1);
		int res = C;
		while(index >=0){
			res+= distances[index];
			index --;
		}
		out.println(res);
		out.close();
		System.exit(0);
	}
}