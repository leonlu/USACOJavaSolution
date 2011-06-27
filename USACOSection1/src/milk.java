// USACO Section 1.3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


/*
ID: leonluc1
LANG: JAVA
PROG: milk
*/

public class milk {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"milk.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int need = Integer.parseInt(st.nextToken());
		MilkInfo[] milks = new MilkInfo[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < milks.length; i++){
			st = new StringTokenizer(in.readLine());
			milks[i] = new MilkInfo(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(milks,new MilkComparator());
		int totalPrice = 0;
		for(int i = 0; i < milks.length; i++){
			if(need == 0) break;
			if(need >= milks[i].num){
				need -= milks[i].num;
				totalPrice+= milks[i].num * milks[i].price;
			}
			else{
				totalPrice+= milks[i].price * need;
				break;
			}
		}
		out.println(totalPrice);
		out.close();
		System.exit(0);
	}
}

class MilkInfo{
	public int price;
	public int num;
	public MilkInfo(int price, int num){
		this.price = price;
		this.num = num;
	}
}

class MilkComparator implements Comparator<MilkInfo>{
	public int compare(MilkInfo a, MilkInfo b) {
		if(a.price < b.price) return -1;
		if(a.price > b.price) return 1;
		return 0;
	}
}