// Section 2.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
ID: leonluc1
PROG: frac1
LANG: JAVA
*/

// a common way to solve this, gcd
// other way please refer to the analysis
// it's not a bigger chanlenger
public class frac1 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		
		List<Fraction> fractions = new ArrayList<Fraction>();
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 1; i <N; i++)
			for(int j = i+1; j <=N; j++){
				if(gcd(i,j)!=1 && i != 1) continue;
				fractions.add(new Fraction(i,j,1.0 * i/j));
			}
		
		Collections.sort(fractions, new FractionComparator());
		out.println("0/1");
		for(Fraction f : fractions)
			out.println(f.i +"/" + f.j);
		out.println("1/1");

		out.close();
		System.exit(0);
	}
	
	private static int gcd(int a, int b){
	    if(b == 0)
	        return a;
	    return gcd(b, a % b);
	}
}

class Fraction{
	public int i;
	public int j;
	public double value;
	
	public Fraction(int i, int j, double value){
		this.i = i;
		this.j = j;
		this.value = value;
	}
}

class FractionComparator implements Comparator<Fraction>{

	public int compare(Fraction arg0, Fraction arg1) {
		if(arg0.value < arg1.value) return -1;
		if(arg0.value > arg1.value) return 1;
		return 0;
	}
	
}