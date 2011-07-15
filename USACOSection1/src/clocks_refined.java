// Section 1.4.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 ID: leonluc1
 PROG: clocks
 LANG: JAVA
 */

// Improvement:
// 1. apply smaller moves first by repeating 3..0 instead of 0...3
// 2. when t == 9, just validate it and check the length. If a sequence of smaller length found, replace foundSequence with it.
// 3. make the moved clocks a state with dfs, rather than calculate it every time 

public class clocks_refined {
	private static int[] clock;
	private static int[][] moves;
	private static int[] foundSequence;
	private static int sequenceLength = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"clocks.out")), true);

		// read in
		StringTokenizer st = new StringTokenizer(in.readLine());
		clock = new int[9];
		for (int i = 0; i < clock.length; i++) {
			if (!st.hasMoreTokens())
				st = new StringTokenizer(in.readLine());
			clock[i] = Integer.parseInt(st.nextToken());
		}

		// use char-'A' to generate the moves
		moves = new int[][] { { 'A', 'B', 'D', 'E' }, { 'A', 'B', 'C' },
				{ 'B', 'C', 'E', 'F' }, { 'A', 'D', 'G' },
				{ 'B', 'D', 'E', 'F', 'H' }, { 'C', 'F', 'I' },
				{ 'D', 'E', 'G', 'H' }, { 'G', 'H', 'I' },
				{ 'E', 'F', 'H', 'I' } };
		for (int i = 0; i < moves.length; i++)
			for (int j = 0; j < moves[i].length; j++)
				moves[i][j] -= 'A';

		// dfs
		int[] moveSequence = new int[9];
		dfs(0, moveSequence);

		// output
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < foundSequence.length; i++) {
			int times = foundSequence[i];
			for (int j = 0; j < times; j++) {
				sb.append(i + 1);
				sb.append(" ");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		out.println(sb);
		System.exit(0);
	}

	private static void dfs(int t, int[] moveSequence) {
		if (t == moveSequence.length) {
			if(successful()){
				int length = 0;
				for(int rep : moveSequence)
					length += rep;
				if(length < sequenceLength){
					sequenceLength = length;
					foundSequence = moveSequence.clone();
				}
			}

		} else
			for (int rep = 3; rep >= 0; rep--) {
				for (int i = 0; i < rep; i++)
					moveClock(moves[t]);
				moveSequence[t] = rep;
				dfs(t + 1, moveSequence);
				for (int i = 0; i < rep; i++)
					moveClockBack(moves[t]);
			}
	}

	private static int clockwise(int n) {
		return (n+3==15) ? 3 : n+3;
	}
	
	private static int antiClockwise(int n){
		return (n-3==0) ? 12 : n-3;
	}

	private static void moveClock(int[] move) {
		for (int i : move)
			clock[i] = clockwise(clock[i]);
	}
	
	private static void moveClockBack(int[] move) {
		for (int i : move)
			clock[i] = antiClockwise(clock[i]);
	}

	private static boolean successful() {
		for (int i : clock) {
			if (i != 12)
				return false;
		}
		return true;
	}
}