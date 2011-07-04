// USACO Section 1.1.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 LANG: JAVA
 PROG: gift1
 */

public class gift1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"gift1.out")), true);

		// initialization
		int cnt = Integer.parseInt(in.readLine());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] names = new String[cnt];
		for (int i = 0; i < cnt; i++) {
			names[i] = in.readLine();
			map.put(names[i], 0);
		}
		
		// process
		for (int i = 0; i < cnt; i++) {
			String name = in.readLine();
			StringTokenizer st = new StringTokenizer(in.readLine());
			int money = Integer.parseInt(st.nextToken());
			int giveCount = Integer.parseInt(st.nextToken());
			if (giveCount == 0)
				continue;
			
			// giver
			int remainMoney = money % giveCount;
			int givePerMoney = money / giveCount;
			map.put(name, map.get(name).intValue() - (money - remainMoney));
			
			// receiver
			for (int j = 0; j < giveCount; j++) {
				String receiver = in.readLine();
				map.put(receiver, map.get(receiver).intValue() + givePerMoney);
			}
		}

		// output
		for (String s : names)
			out.println(s + " " + map.get(s));

		System.exit(0);
	}
}
