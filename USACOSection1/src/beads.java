// USACO Section 1.1

/*
ID: leonluc1
LANG: JAVA
PROG: beads
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



// 1.enumerate every break point, O(n^2)
// 2.Using DP, there is an algorithm O(n) 

//public class beads {
//	 public static void main (String [] args) throws IOException {
//		 //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		 BufferedReader in = new BufferedReader(new FileReader("beads.in"));
//		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
//		 
//		 in.readLine(); // characters count, just ignore it
//		 String line = in.readLine();
//		 String beads = line + line;
//		 // find the first bead not white
//		 int indexNotWhite = NotWhiteIndex(0, beads);
//		 // beads all white
//		 if(indexNotWhite == -1) {
//			 out.println(line.length());
//			 out.close();
//			 return;
//		 }
//		 
//		 int max = 0;
//		 do{
//			 char left = beads.charAt(indexNotWhite);
//			 int leftCount = leftCount(left, indexNotWhite, beads);
//			 char right = (left == 'r' ? 'b' : 'r');
//			 int rightCount = rightCount(right,indexNotWhite+1, beads);
//			 if(leftCount + rightCount > max)
//				 max = leftCount + rightCount;
//		 }while((indexNotWhite = NotWhiteIndex(indexNotWhite+1, beads))!=-1);
//		 
//		 if(max > line.length()) 
//			 out.println(line.length());
//		 else
//			 out.println(max);
//		 
//		 out.close();
//		 System.exit(0);
//	 }
//	 
//	 private static int NotWhiteIndex(int beg, String beads){
//		 for(int i = beg; i < beads.length(); i++){
//			 if(beads.charAt(i) != 'w') {
//				return i;
//			}
//		 }
//		 return -1;
//	 }
//	 
//	 private static int leftCount(char color, int beg, String beads){
//		 int cnt = 0;
//		 for(int i = beg; i >= 0; i--){
//			 if(beads.charAt(i) == color || beads.charAt(i) == 'w') 
//				 cnt ++;
//			 else return cnt;
//		 }
//		 return cnt;
//	 }
//	 
//	 private static int rightCount(char color, int beg, String beads){
//		 int cnt = 0;
//		 for(int i = beg; i < beads.length(); i++){
//			 if(beads.charAt(i) == color || beads.charAt(i) == 'w') 
//				 cnt ++;
//			 else return cnt;
//		 }
//		 return cnt;
//	 }
//}

//Using DP, there is an algorithm O(n) 
public class beads {
	 public static void main (String [] args) throws IOException {
		 //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		 
		 in.readLine();
		 String line = in.readLine();
		 String beads = line + line;
		 
		 int[] rLeft = new int[beads.length()];
		 int[] bLeft = new int[beads.length()];
		 rLeft[0] = bLeft[0] = 0;
		 
		 for(int i = 0; i < beads.length()-1; i++){
			 if(beads.charAt(i) == 'r'){
				 rLeft[i+1] = rLeft[i] +1;
				 bLeft[i+1] = 0;
			 }
			 else if(beads.charAt(i) == 'b'){
				 rLeft[i+1] = 0;
				 bLeft[i+1] = bLeft[i] + 1;
			 }
			 else{
				 rLeft[i+1] = rLeft[i] + 1;
				 bLeft[i+1] = bLeft[i] + 1;
			 }
		 }
		 
		 int[] rRight = new int[beads.length() + 1];
		 int[] bRight = new int[beads.length() + 1];
		 rRight[beads.length()] = 0;
		 bRight[beads.length()] = 0;
		 
		 for(int i = beads.length() - 1; i >= 0; i --){
			 if(beads.charAt(i) == 'r'){
				 rRight[i] = rRight[i+1] + 1;
				 bRight[i] = 0;
			 }
			 else if (beads.charAt(i) == 'b'){
				 rRight[i] = 0;
				 bRight[i] = bRight[i+1] + 1;
			 }
			 else{
				 rRight[i] = rRight[i+1] + 1;
				 bRight[i] = bRight[i+1] + 1;
			 }
		 }
		 
		 int max = 0;
		 for(int i = 0; i < beads.length(); i ++){
			 if(rRight[i] + bLeft[i] > max)
				 max = rRight[i] + bLeft[i];
			 if(rLeft[i] + bRight[i] > max)
				 max = rLeft[i] + bRight[i];
		 }
		 
		 if( max > line.length())
			 max = line.length();
		 
		 out.println(max);
	 
		 
		 out.close();
		 System.exit(0);
	 }
}

