import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
ID: leonluc1
PROG: concom
LANG: JAVA
*/

public class concom {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

		int[][] per = new int[101][101];
		int n = Integer.parseInt(in.readLine());
		Queue<IJP> q = new LinkedList<IJP>();
		while(n-->0){
			StringTokenizer st = new StringTokenizer(in.readLine());
			q.add(new IJP(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		while(!q.isEmpty()){
			IJP curIJP = q.poll();
			
			if(curIJP.i == curIJP.j)
				continue;
			
			// if i is controled
			for(int a = 1; a <= 100; a++){
				if(per[a][curIJP.i] > 50)
					q.add(new IJP(a,curIJP.i,curIJP.p));
			}
			
			if(per[curIJP.i][curIJP.j] <= 50)
				per[curIJP.i][curIJP.j] += curIJP.p;
			

			// check j controled something?
			if(per[curIJP.i][curIJP.j] > 50)
				for(int k = 1; k <=100; k++){
					if(per[curIJP.j][k]>0)
						q.add(new IJP(curIJP.i,k,per[curIJP.j][k]));
				}
		}
		
		boolean empty = true;
		for(int i = 1; i <=100; i++)
			for(int j = 1; j <=100; j++)
				if(per[i][j] >50){
					empty = false;
					out.println(i+" "+j);
				}
		
		if(empty)
			out.println(0);
		
		out.close();
		System.exit(0);
	}
}

class IJP{
	public int i;
	public int j;
	public int p;
	public IJP(int i, int j, int p){
		this.i = i;
		this.j = j;
		this.p = p;
	}
}
