// Section 2.2.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: leonluc1
PROG: preface
LANG: JAVA
*/

public class preface {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")),true);
		int oneCount[]  = new int[4]; // 1 10 100 1000
		int fiveCount[] = new int[4]; // 5 50 500
		int N = Integer.parseInt(in.readLine());
		for(int i = 1; i <= N; i++){
			int num = i;
			for(int j = 3; j >=0; j--){
				int t = num /(int)Math.pow(10,j);
				if(t >=1 && t <=3){
					oneCount[j] +=t;
				}else if(t>=4 && t<=8){ //4..8
					fiveCount[j] += 1;
					oneCount[j]  += Math.abs(t-5);
				}else if(t== 9){
					oneCount[j+1] += 1;
					oneCount[j]   += 1;
				}
				num = i%(int)Math.pow(10,j);
			}
		}
		
		if(oneCount[0] != 0)
			out.println("I " + oneCount[0]);
		if(fiveCount[0] != 0)
			out.println("V " + fiveCount[0]);	
		if(oneCount[1] != 0)
			out.println("X " + oneCount[1]);
		if(fiveCount[1] != 0)
			out.println("L " + fiveCount[1]);			
		if(oneCount[2] != 0)
			out.println("C " + oneCount[2]);
		if(fiveCount[2] != 0)
			out.println("D " + fiveCount[2]);
		if(oneCount[3] != 0)
			out.println("M " + oneCount[3]);		
		
		System.exit(0);
	}
}
