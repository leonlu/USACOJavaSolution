// Section 2.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: leonluc1
PROG: hamming
LANG: JAVA
*/
public class hamming {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int MAX = (int)Math.pow(2, B);
		int[] res = new int[N];
		int curCount = 0;
		
		label:
		for(int i = 0; i < MAX; i++){
			if(curCount >= N) break;
			for(int j = 0; j < curCount; j++){
				if(distance(i,res[j]) < D)
					continue label;
			}
			res[curCount++] = i;
		}
		
		// output 10 numbers a line
		StringBuilder sb = null;
		for(int i = 0; i < curCount; i++){
			if( i % 10 == 0) 
				sb = new StringBuilder();
			sb.append(res[i]);
			if( i % 10 != 9)
				sb.append(" ");
			if( i % 10 == 9){
				out.println(sb.toString());
				sb = null;
			}
		}
		if(sb != null){
			sb.deleteCharAt(sb.length()-1);
			out.println(sb.toString());
		}

		out.close();
		System.exit(0);
	}
	
	private static int distance(int a, int b){
		int tmp = a ^ b;
		int cnt = 0;
		while(tmp != 0){
			if(tmp % 2 == 1) cnt ++;
			tmp >>= 1;
		}
		return cnt;
	}
	
}
