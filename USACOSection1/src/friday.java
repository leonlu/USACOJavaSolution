// USACO Section 1.1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: leonluc1
LANG: JAVA
PROG: friday
*/

public class friday {
	 public static void main (String [] args) throws IOException {
		 BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		 PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		 
		 int N = Integer.parseInt(in.readLine());		 
		 int[] sum13thOnWeekday = new int[7];
		 int last13th = 0;
		 
		 for(int year = 1900; year < 1900 + N; year++){
			 for(int month = 1; month <= 12; month++){
				 if(year == 1900 && month == 1)
					 last13th = 6;
				 else{
					 last13th = (next13thDaysAdd(month,year) + last13th) %7;
				 }
				 sum13thOnWeekday[last13th]++;
			 }
		 }

		 out.print(sum13thOnWeekday[6]+ " ");
		 out.print(sum13thOnWeekday[0]+ " ");
		 out.print(sum13thOnWeekday[1]+ " ");
		 out.print(sum13thOnWeekday[2]+ " ");
		 out.print(sum13thOnWeekday[3]+ " ");
		 out.print(sum13thOnWeekday[4]+ " ");
		 out.println(sum13thOnWeekday[5]);
		 
		 out.close();
		 System.exit(0);
	 }
	 
	 private static boolean leapYear(int year){
		 if(year % 4 != 0) return false;
		 if(year % 100 == 0)
			 if(year % 400 == 0) return true;
			 else return false;
		 return true;
	 }
	 
	 private static int next13thDaysAdd(int month, int year){
		 switch(month){
		 case 2: return 31;
		 case 3: return leapYear(year) ? 29 :28;
		 case 4: return 31;
		 case 5: return 30;
		 case 6: return 31;
		 case 7: return 30;
		 case 8: return 31;
		 case 9: return 31;
		 case 10: return 30;
		 case 11: return 31;
		 case 12: return 30;
		 case 1: return 31;
		 }
		 // can't reach here
		 return 0;
	 }
}
