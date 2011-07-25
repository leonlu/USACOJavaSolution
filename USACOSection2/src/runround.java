// Section 2.2.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
ID: leonluc1
PROG: runround
LANG: JAVA
*/

// runround numbers have unique digits, so the max length is 9
// a position can't be accessed for a second time by following the rule
public class runround {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")),true);
		int M = Integer.parseInt(in.readLine());
		
		label:
		while(true){
			M++;
			char[] nums = Integer.toString(M).toCharArray();
			boolean[] usedDigits = new boolean[10]; //1..9
			int index = 0;
			int markedCnt = 0;
			while(true){
				if(usedDigits[nums[index] -'0']){
					if(index !=0 || markedCnt != nums.length)
						continue label;
						else{
							out.println(M);
							System.exit(0);
							break label;
						}
				}
				usedDigits[nums[index] -'0'] = true;
				markedCnt++;
				index = (index +nums[index] -'0') % nums.length;
			}
		}
	}
}