// Section 2.1.5

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
 PROG: hamming
 LANG: JAVA
 */
public class hamming {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"hamming.out")), true);

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int MAX = (int) Math.pow(2, B);
		List<Integer> codewords = new ArrayList<Integer>();

		label: 
		for (int i = 0; i < MAX && codewords.size() < N; i++) {
			for (int c : codewords)
				if (distance(i, c) < D)
					continue label;
			codewords.add(i);
		}

		// output 10 numbers a line
		for(int i=0; i < codewords.size(); i++){
			out.print(codewords.get(i));
			if(i % 10 == 9 || i == codewords.size()-1) 
				out.println();
			else
				out.print(' ');
		}

		System.exit(0);
	}

	private static int distance(int a, int b) {
		int tmp = a ^ b;
		int cnt = 0;
		while (tmp != 0) {
			if (tmp % 2 == 1)
				cnt++;
			tmp >>= 1;
		}
		return cnt;
	}

}
