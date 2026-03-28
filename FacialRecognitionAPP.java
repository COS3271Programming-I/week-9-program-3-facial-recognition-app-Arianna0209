package week9;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class FacialRecognitionAPP {

	// This program inputs from a text file six measurements from each of five different different faces. It then uses
	// the ratios these measurements make to tell the user which face most closely resembles the measurements they 
	// input for the sixth "mystery face."
	
	static Scanner userinput = new Scanner(System.in);
	static Scanner datainput = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		// Tell the user what the program does:
		System.out.println("This program will have you input six measurements from a face of your choice. Then it will"
				+ "\ntell you which of the five stored faces the one you input most closely resembles.\n");
		
		// Set up scanner for the file:
		String fileName = "C:\\Users\\arian_a5ouxb2\\eclipse-workspace\\myproject\\src\\week9\\FacialRecognitionDataFile";
		File measurementFile = new File(fileName);
		Scanner input = new Scanner(measurementFile);
		
		// Define the array that will hold the measurements of the six faces (including the mystery one):
		double[][] measurements = new double[6][6];
		
		// Fill that array:
		String line;
		while (input.hasNextLine()) {
			// Find the person number in the first line:
			line = input.nextLine();
			line = line.trim();
			Thread.sleep(200);
			System.out.println(line);
			int personNumber = Character.getNumericValue(line.charAt(7));
			
			// Iterate through each measurement, strip the letter and white spaces,
			// convert it to a double, and add it to the array:
			for (int count = 0; count <= 5; count++) {
				String measurementLine = input.nextLine();
				Thread.sleep(200);
				System.out.println(measurementLine);
				measurementLine = measurementLine.substring(2);
				measurementLine = measurementLine.trim();
				double measurementData = Double.parseDouble(measurementLine);
				measurements[personNumber-1][count] = measurementData;
			}
			// Add a next line for even spacing if needed, and avoid making the line variable above empty.
			if (input.hasNextLine()) {
				input.nextLine();
				System.out.println();}
			
		
		}
		// Close the scanner:
		input.close();
		
		// Have the user input the sixth person's measurements:
		Thread.sleep(200);
		System.out.println("\nEnter Person 6\'s measurements.");
		for (int count = 0; count <= 5; count ++) {
			System.out.print((char)(count+65) + ": ");
			measurements[5][count] = userinput.nextDouble();
			}
			
		// Create the array to hold the ratios:
		double [][] ratios = new double [6][15];
		
		// Calculate the ratios. "Person" represents the person whose measurements ratios are being calculated,
		// letter1 represents the dividend of the ratio, and letter2 represents the divisor. Lastly, letter3
		// is used to keep track of what position in the ratio array needs to be filled next.
		int person, letter1;
		int letter2 = 1;
		int letter3 = 0;
		for (person = 0; person <= 5; person++) {
			for (letter1 = 0; letter1 <= 5; letter1++) {
				while (letter2 <= 5) {
					double ratio = measurements[person][letter1]/measurements[person][letter2];
					ratios[person][letter3] = ratio;
					letter2++;
					letter3++;
				}
				letter2 = letter1 + 2;
			}
			letter3 = 0;
			letter2 = 1;
		}
		
		// Print the ratios:
		Thread.sleep(1000);
		System.out.println("\n\nCalculated Ratios:");
		for (person = 0; person <= 5; person++) {
			for (int count = 0; count <= 14; count++) {
				Thread.sleep(100);
				System.out.format("\nPerson %d ratio %d: %.2f", (person + 1), (count + 1), ratios[person][count]);
			}
			System.out.println();
		}
		
		// Create an array for the sum of squares % differences:
		double [] squaresPercentDiffs = new double[5];
		
		// Calculate the sum of squares % differences:
		for (person = 0; person <= 4; person++) {
			double totalDiffs = 0;
			for (int ratio = 0; ratio <= 14; ratio++) {
				totalDiffs = totalDiffs + Math.pow(((ratios[5][ratio]-ratios[person][ratio])/ratios[person][ratio]), 2);
			}
			squaresPercentDiffs[person] = totalDiffs;
		}
		
		// Create a sorted version of the squaresPercentDiffs array:
		double[] sortedSquaresPercentDiffs = new double [5];
		sortedSquaresPercentDiffs = Arrays.copyOf(squaresPercentDiffs, squaresPercentDiffs.length);
		Arrays.sort(sortedSquaresPercentDiffs);
		
		// Print the sorted and unsorted versions:
		Thread.sleep(1000);
		System.out.print("\nSum of squares % differences for each person compared to person 6:");
		for (person = 0; person <=4; person++) {
			Thread.sleep(200);
			System.out.format("\nPerson %d: %f", (person+1), squaresPercentDiffs[person]);
		}
		
		Thread.sleep(1000);
		System.out.print("\n\nSorted sum of squares % differences for each person compared to person 6:");
		for (person = 0; person <=4; person++) {
			Thread.sleep(200);
			System.out.format("\n%.6f", sortedSquaresPercentDiffs[person]);
		}
		
		// Find out which index number in the original array matches the first item in the sorted one:
		int lowestSumIndex = 0;
		for (int sum = 0; sum <= 4; sum++) {
			if (squaresPercentDiffs[sum] == sortedSquaresPercentDiffs[0]) {
				lowestSumIndex = sum;
			}
		}
		
		// Tell the user who their person is:
		Thread.sleep(2000);
		System.out.println("\n\nYour person is number " + (lowestSumIndex+1) + "!");
		
		// Tell the user the program has ended:
		Thread.sleep(1000);
		System.out.println("\nProgram Ended.");
		
	}

}
