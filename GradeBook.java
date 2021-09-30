/*
 * Author: Augsutus Crosby
 * Filename: GradeBook.java
 * Specification: Class Gradebook that has several methods that reads a text file and creates a Gradebook 
 * and creates an average while dropping the lowest score, and assigns grades and turns it into a nice table.
 * TIME SPENT: 2.5 hours
 */
/* Part 1: Written Exercises
 * 1. a)1st: 3, 18, 16, 19, 14, 6
 * 		2nd: 3, 6, 18, 16, 19, 14
 * 		3rd: 3, 6, 14, 18, 16, 19
 * 		4th: 3, 6, 14, 16, 18, 19 (DONE)
 * 	  b)1st: 16, 18, 19, 3, 14, 6
 * 		2nd: 16, 18, 19, 3, 14, 6
 * 		3rd: 3, 16, 18, 19, 14, 6
 * 		4th: 3, 14, 16, 18, 19, 6
 * 		5th: 3, 6, 14, 16, 18, 19 (DONE)
 * 2. a)First 3, 5, 6, 8, 12, 13, 16, 17, THEN 18
 * 	  b)First 12, 17, THEN 18
 * 	  c)Using Linear Search and searching for 2, All 10 elements are compared. When using binary search, it looks like 3 elements are compared (12, 5, then 3).
 * 3. a) 4 rows
 * 	  b) 3 columns
 * 	  c) 1
 * 	  d) 2
 */


import java.io.*;
import java.util.*;

public class GradeBook {
	
	private String[] names;
	private char[] letterGrades;
	private double[][] testScores;
	public final int NUM_TEST = 4;
	
	private int size;
	
	public GradeBook (int size) {
		this.size = size;
		this.names = new String[size];
		this.letterGrades = new char[size];
		this.testScores = new double[size][NUM_TEST];
	}
	
	public void fillGradeBook(String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		Scanner fileScanner = new Scanner(fileReader);
		
		int index = 0;
		
		while (fileScanner.hasNext()) {
			String name = fileScanner.next();
			double grade1 = fileScanner.nextDouble();
			double grade2 = fileScanner.nextDouble();
			double grade3 = fileScanner.nextDouble();
			double grade4 = fileScanner.nextDouble();
			
			this.names[index] = name;
			this.testScores[index][0] = grade1;
			this.testScores[index][1] = grade2;
			this.testScores[index][2] = grade3;
			this.testScores[index][3] = grade4;
			
			index++;
			
		}
		
		fileScanner.close();
		fileReader.close();
		
	}
	
	private double getStudentAverage(int index) {
		if (index < 0 || index >= this.size)
			return -1;
		
		//int grade1 = this.testScores[index][0];
		//int grade2 = this.testScores[index][1];
		//int grade3 = this.testScores[index][2];
		//int grade4 = this.testScores[index][3];
		
		double smallest = 1000;
		double sum = 0;
		
		for(int i = 0; i < 4; i++) {
			sum += this.testScores[index][i];
			if(this.testScores[index][i] < smallest)
				smallest = this.testScores[index][i];
		}
		
		double sumOfThreeLargest = sum - smallest;
		
		return sumOfThreeLargest/3.0;
	}
	
	public void assignGrades() {
		for(int index = 0; index < this.size; index++) {
			double average = getStudentAverage(index);
			if(average >= 90)
				this.letterGrades[index] = 'A';
			else if(average >= 80 && average < 90)
				this.letterGrades[index] = 'B';
			else if(average >= 70 && average < 80)
				this.letterGrades[index] = 'C';
			else if(average >= 60 && average < 70)
				this.letterGrades[index] = 'D';
			else
				this.letterGrades[index] = 'E';
			
		}
	
	}
	
	public String getStudentName(int index) {
		if (index < 0 || index >= this.size)
			return null;
		
		return this.names[index];
	}
	
	public char getLetterGrade(int index) {
		if (index < 0 || index >= this.size)
			return ' ';
		return this.letterGrades[index];
	}
	
	
	public int search(String target) {
		for(int index = 0; index < size; index++) {
			if(this.names[index].equals(target))
				return index;
		}
		
		return -1;
	}
	
	public String toString() {
		String[] gradeBookString = new String [size];
		String gradeBookString1 = "";
		for(int index = 0; index < size; index++) {
			gradeBookString[index] = this.names[index] + "	" + this.testScores[index][0] + "	" + this.testScores[index][1] 
					+ "	" + this.testScores[index][2] + "	" + this.testScores[index][3] + "	" + (Math.round(getStudentAverage(index)*10.0)/10.0) + "	" + this.letterGrades[index];
		}
		gradeBookString1 = "Name	T1	T2	T3	T4	Avg	Letter \n=======================================================\n"
				+ gradeBookString[0] + "\n" + gradeBookString[1]+ "\n" + gradeBookString[2]+ "\n" + gradeBookString[3]+ "\n" + gradeBookString[4];
		return gradeBookString1;
		
	}
		
		
		
}
	
	

	
	
	

