// Section 1.4

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
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"packrec.out")));
		
		Rectangle[] recs = new Rectangle[4];
		for(int i = 0; i < 4; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			recs[i] = new Rectangle(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		List<int[]> perms = new ArrayList<int[]>(24);
		genPermutation( 0, new int[]{0,1,2,3}, perms);
		List<boolean[]> subset = new ArrayList<boolean[]>(16);
		genSubset(0,new boolean[4], subset);
		
		List<Rectangle> res = new ArrayList<Rectangle>();
		// improve it to call in function ,not to return permuatation.
		for(int[] p : perms){
			Rectangle r1 = recs[p[0]];
			Rectangle r2 = recs[p[1]];
			Rectangle r3 = recs[p[2]];
			Rectangle r4 = recs[p[3]];
			for(boolean[] s : subset){
				int w1,w2,w3,w4;
				int h1,h2,h3,h4;
				if(s[0]) {w1 = r1.w; h1=r1.h;}
				else     {w1 = r1.h; h1=r1.w;}
				if(s[1]) {w2 = r2.w; h2=r2.h;}
				else     {w2 = r2.h; h2=r2.w;}
				if(s[2]) {w3 = r3.w; h3=r3.h;}
				else     {w3 = r3.h; h3=r3.w;}
				if(s[3]) {w4 = r4.w; h4=r4.h;}
				else     {w4 = r4.h; h4=r4.w;}
				Collections.addAll(res, calculate(w1,w2,w3,w4,h1,h2,h3,h4));
			}
		}
		Collections.sort(res, new RectangleComparator());
		int size = res.get(0).size();
		int w = res.get(0).w;
		int h = res.get(0).h;
		out.println(size);
		out.println(w + " " + h);
		for(int i = 1; i < res.size(); i ++){
			Rectangle r = res.get(i);
			if(r.size() != size)
				break;
			if(r.w == w && r.h == h)
				continue;
			out.println(r.w + " " + r.h);
			w = r.w;
			h = r.h;
		}
		out.close();
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
		else // h4== h3
			width = max(w1+w2, w3+w4);
		a[4] = new Rectangle(width,height);

		return a;
	}
	
	private static void genPermutation(int t, int[] a, List<int[]> perms){
		int tmp;
		if(t == a.length) {
			perms.add(a.clone()); // a.length !
		}
		else
			for(int i = t; i < a.length; i++){
				tmp = a[t]; a[t] = a[i]; a[i] = tmp;
				genPermutation(t+1, a, perms);
				tmp = a[t]; a[t] = a[i]; a[i] = tmp;
			}
	}
	
	private static void genSubset(int t, boolean[] a, List<boolean[]> subset){
		if(t == a.length){
			subset.add(a.clone());	// 2^ a.length
		}
		else{
			a[t] = false;
			genSubset(t+1, a, subset);
			a[t] = true;
			genSubset(t+1, a, subset);
		}
	}
}

class Rectangle{
	public int h;
	public int w;
	public Rectangle(int x, int y){
		if(x < y){
			int tmp = x;
			x = y;
			y = tmp;
		}
		h = x;
		w = y;
	}
	
	public int size(){
		return h * w;
	}
}

class RectangleComparator implements Comparator<Rectangle>{
	public int compare(Rectangle arg0, Rectangle arg1) {
		if(arg0.size() < arg1.size()) return -1;
		if(arg0.size() > arg1.size()) return 1;
		if(arg0.w < arg1.w) return -1;
		if(arg0.w > arg1.w) return 1;
		return 0;
	}
}
	