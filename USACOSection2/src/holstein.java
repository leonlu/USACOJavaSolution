// Section 2.1.4

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: leonluc1
PROG: holstein
LANG: JAVA
*/

// 2^15 subset problem
public class holstein {
	
	private static int vCount;
	private static int[] vitamin;
	private static int feedCount;
	private static int[][] feed;
	
	private static boolean[] feedToUse;
	private static boolean[] resFeedToUse;

	private static int toUseCount = 0;
	private static int resToUseCount = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")),true);

		// read data
		vCount = Integer.parseInt(in.readLine());
		vitamin = new int[vCount];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < vCount; i++)
			vitamin[i] = Integer.parseInt(st.nextToken());
		
		feedCount = Integer.parseInt(in.readLine());
		feed = new int[feedCount][vCount];
		for(int i = 0; i < feedCount; i++){
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < vCount; j++){
				feed[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		feedToUse = new boolean[feedCount];
		resFeedToUse = new boolean[feedCount];
		
		dfs(0);
		
		// output
		StringBuilder sb = new StringBuilder();
		sb.append(resToUseCount + " ");
		for(int i = 0; i < resFeedToUse.length; i++){
			if(resFeedToUse[i])
				sb.append(i + 1 + " ");
		}
		sb.deleteCharAt(sb.length()-1);
	
		out.println(sb.toString());
		
		System.exit(0);
	}
	
	private static void dfs(int t){
		if(toUseCount > resToUseCount) return; // pruning
		
		if (meet() && less()){
			resToUseCount = toUseCount;
			resFeedToUse = feedToUse.clone();
		}
		
		if(t == feedCount)
			return;
		
		feedToUse[t] = true;
		toUseCount++;
		dfs(t+1);
		
		feedToUse[t] = false;
		toUseCount--;
		dfs(t+1);
	}
	
	private static boolean meet(){
		int[] tmpV = new int[vCount];
		for(int i = 0; i < feedToUse.length; i++){
			if(feedToUse[i])
				for(int j = 0; j <vCount; j++)
					tmpV[j] += feed[i][j];
		}
		
		for(int j = 0; j < vCount; j++)
			if(tmpV[j] < vitamin[j])
				return false;
		
		return true;
	}
	
	private static boolean less(){
		if(toUseCount > resToUseCount) return false;
		if(toUseCount < resToUseCount) return true;
		
		for(int i = 0; i < feedCount; i++){
			if(!feedToUse[i] && resFeedToUse[i])
				return false;
			if(feedToUse[i] && !resFeedToUse[i])
				return true;
		}
		// can't reach here
		return false;
	}
}
