// Section 2.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: leonluc1
PROG: lamps
LANG: JAVA
*/

public class lamps {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int N = Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		boolean[] lampsOn = new boolean[N+1];
		boolean[] lampsOff = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		int idx;
		while((idx = Integer.parseInt(st.nextToken()))!=-1)
			lampsOn[idx] = true;
		st = new StringTokenizer(in.readLine());		
		while((idx = Integer.parseInt(st.nextToken()))!=-1)
			lampsOff[idx] = true;
		
		
		List<boolean[]> res = new ArrayList<boolean[]>();
		
		for(int a = 0; a < 2; a++){
			for(int b = 0; b < 2; b++)
				for(int c = 0; c < 2; c++)
					label:
					for(int d = 0; d < 2; d++){
						boolean[] lamps = new boolean[N+1];
						for(int i = 1; i < lamps.length; i++)
							lamps[i] = true;
						int count = C;
						if(a == 0 && count-->0)
							for(int i = 1; i < lamps.length; i++)
								lamps[i] = !lamps[i];
						if(b == 0 && count-->0)
							for(int i = 1; i < lamps.length; i+=2)
								lamps[i] = !lamps[i];
						if(c == 0 && count-->0)
							for(int i = 2; i < lamps.length; i+=2)
								lamps[i] = !lamps[i];						
						if(d == 0 && count-->0)
							for(int i = 1; i < lamps.length; i+=3)
								lamps[i] = !lamps[i];
						
						for(int i = 1; i < lamps.length; i++){
							if(lampsOn[i] && !lamps[i]) continue label;
							if(lampsOff[i] && lamps[i]) continue label;
						}
						res.add(lamps);
					}
		}
		Collections.sort(res, new LampComparator());
		
		if (res.size() == 0)
			out.println("IMPOSSIBLE");
		else {
			boolean[] last = null;
			for (boolean[] lamp : res) {
				boolean flag = true;
				if (last != null) {
					flag = false;
					for (int i = 0; i < lamp.length; i++) {
						if (last[i] != lamp[i]) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					for (int i = 1; i < lamp.length; i++)
						out.print(lamp[i] ? "1" : "0");
					out.println();
				}
				last = lamp;
			}
		}
		
		out.close();
		System.exit(0);
	}
}
class LampComparator implements Comparator<boolean[]>{
	@Override
	public int compare(boolean[] o1, boolean[] o2) {
		for(int i = 1; i < o1.length; i++){
			if(o1[i] != o2[i]){
				if(o1[i] && !o2[i]) return 1;
				if(!o1[i] && o2[i]) return -1;
			}
		}
		return 0;
	}
}