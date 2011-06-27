// USACO Section 1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/*
ID: leonluc1
LANG: JAVA
PROG: crypt1
*/

public class crypt1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"crypt1.out")));
		int cnt = Integer.parseInt(in.readLine());
		int[] digits = new int[cnt];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < cnt; i++){
			digits[i] = Integer.parseInt(st.nextToken());
		}
		int[] firstNums = new int [cnt * cnt * cnt];
		int idx = 0;
		for(int i = 0; i < cnt; i++)
			for(int j = 0; j < cnt; j++)
				for(int k = 0; k < cnt; k++)
					firstNums[idx++] = digits[i]*100 + digits[j] * 10 + digits[k];
		idx = 0;
		int[] secondNums = new int [cnt * cnt];
		for(int i = 0; i < cnt; i++)
			for(int j = 0; j < cnt; j++)
				secondNums[idx++] = digits[i]*10 + digits[j];
		int resCount = 0;
		for(int i = 0; i < firstNums.length; i++)
			for(int j = 0; j < secondNums.length; j++){
				int firstProduct = firstNums[i] * (secondNums[j] %10);
				if(!isResultInDigits(digits,firstProduct,3)) continue;
				int secondProduct = firstNums[i] * (secondNums[j] /10);
				if(!isResultInDigits(digits,secondProduct,3)) continue;
				int res = firstNums[i] * secondNums[j];
				if(!isResultInDigits(digits,res,4))	continue;
				resCount++;
			}
		
		out.println(resCount);
		out.close();
		System.exit(0);
	}
	
	private static boolean isResultInDigits(int[] digits, int num, int length){
		for(int k = 0; k < length; k++){
			if(!isValidDigit(digits,num % 10)) return false;
			num /= 10;
		}
		if(num != 0) return false;
		return true;
	}
	
	private static boolean isValidDigit(int[] digits, int d){
		for(int i = 0; i < digits.length; i++){
			if(digits[i] == d) return true;
		}
		return false;
	}
}