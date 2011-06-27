// USACO Section 1.2

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


/*
ID: leonluc1
LANG: JAVA
PROG: transform
*/

public class transform {
	  public static void main (String [] args) throws IOException {
		  //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
		  BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		  PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		  int length = Integer.parseInt(in.readLine());
		  char[][] pic = new char[length][length];
		  for(int i = 0 ; i < length; i++){
			  String tmp = in.readLine();
			  for(int j = 0; j < length; j++){
				  pic[i][j] = tmp.charAt(j);
			  }
		  }
		  char[][] target = new char[length][length];
		  for(int i = 0 ; i < length; i++){
			  String tmp = in.readLine();
			  for(int j = 0; j < length; j++){
				  target[i][j] = tmp.charAt(j);
			  }
		  }
		  
		  out.println(tryPattern(pic,target));
		  		 
		  out.close();
		  System.exit(0);
	  }
	  
	  private static int tryPattern(char[][] pic, char[][] target){
		  char[][] cpy = copy(pic);
		  rotateClockwise90(cpy);
		  if(equal(target,cpy)) return 1;
		  
		  cpy = copy(pic);
		  rotateClockwise180(cpy);
		  if(equal(target,cpy)) return 2;
		  
		  cpy = copy(pic);
		  rotateClockwise270(cpy);
		  if(equal(target,cpy)) return 3;
		  
		  cpy = copy(pic);
		  reflection(cpy);
		  if(equal(target,cpy)) return 4;
		  
		  cpy = copy(pic);
		  combination1(cpy);
		  if(equal(target,cpy)) return 5;
		  
		  cpy = copy(pic);
		  combination2(cpy);
		  if(equal(target,cpy)) return 5;
		  
		  cpy = copy(pic);
		  combination3(cpy);
		  if(equal(target,cpy)) return 5;
		  
		  if(equal(target,pic)) return 6;
		  
		  return 7;
	  }
	  
	  // #1: 90 Degree Rotation: The pattern was rotated clockwise 90 degrees
	  private static void rotateClockwise90(char[][] pic){
		  int length = pic.length;
		  char[][] tmp = new char[length][length];
		  for(int i = 0 ; i < length; i++){
			  for(int j = 0 ; j < length; j++){
				  tmp[j][length-i-1] = pic[i][j];
			  }
		  }
		  for(int i = 0 ; i < length; i++){
			  for(int j = 0 ; j < length; j++){
				  pic[i][j] = tmp[i][j];
			  }
		  }
	  }
	  
	  // #2: 180 Degree Rotation: The pattern was rotated clockwise 180 degrees.
	  private static void rotateClockwise180(char[][] pic){
		  rotateClockwise90(pic);
		  rotateClockwise90(pic);
	  }
	  
	  // #3: 270 Degree Rotation: The pattern was rotated clockwise 270 degrees.
	  private static void rotateClockwise270(char[][] pic){
		  rotateClockwise90(pic);
		  rotateClockwise90(pic);
		  rotateClockwise90(pic);
	  }
	  
	  // #4: Reflection: The pattern was reflected horizontally
	  private static void reflection(char[][] pic){
		  int length = pic.length;
		  char tmp;
		  for(int i = 0; i < length; i++){
			  int low = 0;
			  int high = length-1;
			  while(low <= high){
				  tmp = pic[i][low];
				  pic[i][low] = pic[i][high];
				  pic[i][high] = tmp;
				  low++;high--;
			  }
		  }
	  }

	  // #5.1
	  private static void combination1(char[][] pic){
		  reflection(pic);
		  rotateClockwise90(pic);
	  }
	  
	  // #5.2
	  private static void combination2(char[][] pic){
		  reflection(pic);
		  rotateClockwise180(pic);
	  }
	  
	  // #5.3
	  private static void combination3(char[][] pic){
		  reflection(pic);
		  rotateClockwise270(pic);
	  }
	  
	  private static boolean equal(char[][] pic1, char[][] pic2){
		  for(int i = 0 ; i < pic1.length; i++){
			  if(!Arrays.equals(pic1[i], pic2[i]))
				  return false;
		  }
		  return true;
	  }
	  
	  private static char[][] copy(char[][] src){
		  char[][] newpic = new char[src.length][src.length];
		  for(int i = 0 ; i < src.length; i++){
			  for(int j = 0; j < src.length; j++){
				  newpic[i][j] = src[i][j];
			  }
		  }
		  return newpic;
	  }
}