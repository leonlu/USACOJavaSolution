// Section 2.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: leonluc1
PROG: castle
LANG: JAVA
*/

// simple recursive floodfill is fine here
// check every two rooms' sum of size
// only need to break walls to the north and to the east
public class castle {
	
	private static int M;
	private static int N;
	private static int[][] rooms;
	private static int[][] roomscolor;
	private static int maxcolor = 1;
	private static int[] colorcnt;
	
	public static void main(String[] args) throws Exception{
		long old = System.currentTimeMillis();

		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		rooms = new int[N][M];
		roomscolor = new int[N][M];
		colorcnt = new int[M * N + 1];
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++)
				rooms[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(roomscolor[i][j] == 0)
					dfs(i,j,maxcolor++);
		
		out.println(maxcolor-1);
		
		int maxCount = 0;
		for(int i : colorcnt)
			maxCount = i > maxCount ? i : maxCount;
			
		out.println(maxCount);
		
		maxCount = 0;
		int wallI = 0;
		int wallJ = 0;
		char direction = 0;
		for(int j = 0; j < M; j++)
			for(int i = N-1; i >=0; i--){
				if( i-1 >=0 && roomscolor[i-1][j] != roomscolor[i][j]){
					int tmp = colorcnt[roomscolor[i-1][j]] + colorcnt[roomscolor[i][j]];
					if(maxCount < tmp){
						maxCount = tmp;
						wallJ = j;
						wallI = i;
						direction = 'N';
					}
				}
				if( j+1 <=M-1 && roomscolor[i][j+1] != roomscolor[i][j]){
					int tmp = colorcnt[roomscolor[i][j+1]] + colorcnt[roomscolor[i][j]];
					if(maxCount < tmp){
						maxCount = tmp;
						wallJ = j;
						wallI = i;
						direction = 'E';
					}
				}				
			}
		
		out.println(maxCount);
		out.println(wallI+1 + " " + (wallJ+1) +" " + direction);
		
		
		System.out.println("Time elapsed: " +(System.currentTimeMillis() - old)/1000.0);

		out.close();
		System.exit(0);
	}
	
	private static void dfs(int i, int j, int color){
		if(roomscolor[i][j] != 0)
			return;
		
		roomscolor[i][j] = color;
		colorcnt[color]++;
		
		// Pay attention to the priority of operator &
		
		// To West
		if( (rooms[i][j] & 1) == 0 && j-1 >= 0)
			dfs(i,j-1,color);
		// To North
		if( (rooms[i][j] & 2) == 0 && i-1 >= 0)
			dfs(i-1,j,color);
		// To East
		if( (rooms[i][j] & 4) == 0 && j+1 <= M-1)
			dfs(i,j+1,color);
		// To South
		if( (rooms[i][j] & 8) == 0 && i+1 <= N-1)
			dfs(i+1,j,color);
		
	}
}