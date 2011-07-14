// Section 1.4.2

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
 PROG: clocks
 LANG: JAVA
 */

// O(n) = 4^9 = 262144
public class clocks {
	private static int[] clock;
	private static int[][] moves;
	private static List<int[]> validSequences;

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

		// backtrack
		validSequences = new ArrayList<int[]>();
		int[] moveSequence = new int[9];
		backtrack(0, moveSequence);

		Collections.sort(validSequences, new Comparator<int[]>() {
			public int compare(int[] arg0, int[] arg1) {
				if (arg0.length < arg1.length)
					return -1;
				if (arg0.length > arg1.length)
					return 1;
				for (int i = 0; i < arg0.length; i++) {
					if (arg0[i] < arg1[i])
						return -1;
					if (arg0[i] > arg1[i])
						return 1;
				}
				return 0;
			}
		});
		int[] res = validSequences.get(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < res.length; i++) {
			int times = res[i];
			for (int j = 0; j < times; j++) {
				sb.append(i + 1);
				sb.append(" ");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		out.println(sb);
		System.exit(0);
	}

	private static void backtrack(int t, int[] moveSequence) {
		if (t == moveSequence.length) {
			if (applyMoveSequence(clock, moves, moveSequence))
				validSequences.add(moveSequence.clone());
		} else
			for (int i = 0; i < 4; i++) {
				moveSequence[t] = i;
				backtrack(t + 1, moveSequence);
			}
	}

	private static int clockwise(int n) {
		n += 3;
		if (n == 15)
			return 3;
		else
			return n;
	}

	private static void moveClock(int[] clock, int[] move) {
		for (int i : move)
			clock[i] = clockwise(clock[i]);
	}

	private static boolean applyMoveSequence(int[] clock, int[][] moves,
			int[] ms) {
		clock = clock.clone();
		for (int i = 0; i < ms.length; i++) {
			int[] move = moves[i];
			int cnt = ms[i];
			while (cnt-- != 0)
				moveClock(clock, move);
		}
		return successful(clock);
	}

	private static boolean successful(int[] clock) {
		for (int i : clock) {
			if (i != 12)
				return false;
		}
		return true;
	}
}