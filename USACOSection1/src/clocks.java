// Section 1.4

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

// BFS 8^(3*9) -> can't achieve
// Todo: try another method: use BFS and prunning, record status which's searched, using an array of 36 length

// list all the possible sequences 4(enum 0...3)^9 lesser checks before longer 
public class clocks {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"clocks.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] clock = new int[9];
		for (int i = 0; i < clock.length; i++) {
			if (!st.hasMoreTokens())
				st = new StringTokenizer(in.readLine());
			clock[i] = Integer.parseInt(st.nextToken());
		}
		// Todo use char-'A' to generate the moves
		int[][] moves = new int[][] { { 0, 1, 3, 4 }, { 0, 1, 2 },
				{ 1, 2, 4, 5 }, { 0, 3, 6 }, { 1, 3, 4, 5, 7 }, { 2, 5, 8 },
				{ 3, 4, 6, 7 }, { 6, 7, 8 }, { 4, 5, 7, 8 } };

		int[] ms = new int[9];
		List<int[]> validSequence = new ArrayList<int[]>();
		for (int a = 0; a <= 3; a++)
			for (int b = 0; b <= 3; b++)
				for (int c = 0; c <= 3; c++)
					for (int d = 0; d <= 3; d++)
						for (int e = 0; e <= 3; e++)
							for (int f = 0; f <= 3; f++)
								for (int g = 0; g <= 3; g++)
									for (int h = 0; h <= 3; h++)
										for (int i = 0; i <= 3; i++) {
											ms[0] = a;
											ms[1] = b;
											ms[2] = c;
											ms[3] = d;
											ms[4] = e;
											ms[5] = f;
											ms[6] = g;
											ms[7] = h;
											ms[8] = i;
											if (moveSequenceCheck(clock, moves,
													ms))
												validSequence.add(ms.clone());
										}
		Collections.sort(validSequence, new ValidSequenceComparator());
		int[] res = validSequence.get(0);
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
		out.close();
		System.exit(0);
	}

	private static int clockwise(int n) {
		switch (n) {
		case 9:
			return 12;
		case 12:
			return 3;
		case 3:
			return 6;
		case 6:
			return 9;
		default:
			throw new RuntimeException();
		}
	}

	private static void moveClock(int[] clock, int[] move) {
		for (int i : move) {
			clock[i] = clockwise(clock[i]);
		}
	}

	private static boolean moveSequenceCheck(int[] clock, int[][] moves,
			int[] ms) {
		clock = clock.clone();
		for (int i = 0; i < ms.length; i++) {
			int[] move = moves[i];
			int cnt = ms[i];
			while (cnt-- != 0) {
				moveClock(clock, move);
			}
		}
		if (successful(clock))
			return true;
		else
			return false;
	}

	private static boolean successful(int[] clock) {
		for (int i : clock) {
			if (i != 12)
				return false;
		}
		return true;
	}
}

class ValidSequenceComparator implements Comparator<int[]> {
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
}