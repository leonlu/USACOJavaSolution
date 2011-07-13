// Section 1.4.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.Math.max;


/*
ID: leonluc1
LANG: JAVA
PROG: packrec
*/

// possible solutions: 4! * 2^4 * 5 = 1920
public class packrec {
	
	private static List<Rectangle> results = new ArrayList<Rectangle>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"packrec.out")),true);
		
		// read in
		Rectangle[] recs = new Rectangle[4];
		for(int i = 0; i < 4; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			recs[i] = new Rectangle(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		// backtrack with permutation and subset
		permutate(0,recs);

		// sort by area and w
		Collections.sort(results, new Comparator<Rectangle>(){
			public int compare(Rectangle arg0, Rectangle arg1) {
				if(arg0.size() < arg1.size()) return -1;
				if(arg0.size() > arg1.size()) return 1;
				if(arg0.w < arg1.w) return -1;
				if(arg0.w > arg1.w) return 1;
				return 0;
			}});
		
		// output
		int size = results.get(0).size();
		out.println(size);
		int w = results.get(0).w;
		int h = results.get(0).h;
		out.println(w + " " + h);
		for(int i = 1; i < results.size(); i ++){
			Rectangle r = results.get(i);
			if(r.size() != size)
				break;
			if(r.w == w && r.h == h)
				continue;
			out.println(r.w + " " + r.h);
			w = r.w;
			h = r.h;
		}
		System.exit(0);
	}
	
	private static Rectangle[] calculate(int w1, int w2, int w3, int w4,
			int h1, int h2, int h3, int h4){
		Rectangle[] a = new Rectangle[5];
		
		//1: 
		int width = w1 + w2 + w3 + w4;
		int height = max(max(h1, h2),max(h3, h4));
		a[0] = new Rectangle(width, height);
		
		//2:
		height = w1 + max(max(h2, h3),h4);
		width = max(h1, w2 + w3 + w4);
		a[1] = new Rectangle(width, height);
		
		//3:
		height = max(h1, w2 + max(h3, h4));
		width = w1 + max(h2, w3 + w4);
		a[2] = new Rectangle(width, height);
		
		//4,5:
		width = max(w2,w4) + w1 + w3;
		height= max(max(h1, h3),h2 + h4);
		a[3] = new Rectangle(width, height);
		
		//6
		height = max(h1+h3, h2+h4);
		if(h3 >= h2 + h4)
			width = max(w1, max(w3+w2,w3+w4));
		else if (h3 > h4 && h3 < h2+h4)
			width = max(max(w1+w2, w2+w3),w3+w4);
		else if( h3 < h4 && h4 < h1+h3)
			width = max(max(w1+w2, w1+w4), w3+w4);
		else if (h4 >=h1+h3)
			width = max(max(w1+w4, w3+w4), w2);
		else // h4 == h3
			width = max(w1+w2, w3+w4);
		a[4] = new Rectangle(width,height);

		return a;
	}
	
	// length!
	private static void permutate(int t, Rectangle[] recs){
		if(t == recs.length)
			subset(0,recs);// subset
		else
			for(int i = t; i < recs.length; i++){
				swap(recs,t,i);
				permutate(t+1,recs);
				swap(recs,t,i);
			}
	}
	
	private static void swap(Rectangle[] a, int p1, int p2){
		Rectangle tmp = a[p1];
		a[p1] = a[p2];
		a[p2] = tmp;
	}
	
	// 2^length
	private static void subset(int t, Rectangle[] recs){
		if(t == recs.length)
			Collections.addAll(results,calculate(recs[0].w,recs[1].w,recs[2].w,recs[3].w,
					recs[0].h,recs[1].h,recs[2].h,recs[3].h));
		else{
			// do not exchange h with w
			subset(t+1,recs);
			// exchange h with w
			int tmp = recs[t].h; recs[t].h = recs[t].w; recs[t].w = tmp; 
			subset(t+1,recs);
		}
	}
}

class Rectangle{
	public int h;
	public int w;
	public Rectangle(int x, int y){
		w = (x < y ? x : y);
		h = (x > y ? x : y);
	}
	public int size(){
		return h * w;
	}
}
	