/** 
 * Author: Luis Steven Lin, MS Analytics, ID: 2873812
 * 
 * Purpose: Computes the square root of an integer number
 *          using an algorithm (Babylonian)
 *          
 * General Design: Main method prompts user for inputs
 *                 and calls function findSqrt to find the 
 *                 square root using the algorithm. The 
 *                 program first check if it is a perfect 
 *                 square and calls function findPerfectSquare.
 *                 The program prompts user new inputs until
 *                 flag for quit is selected by user to exit
 *                 program.  
 */

import java.util.Scanner;

public class Project1 {
	
	/**
	 * Main method prompts user inputs and calls functions to find
	 * the square root
	 * 
	 */
	
	public static void main(String[] args){
		
		System.out.println("**The program finds the square root of an integer number**");
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter positive integer, Q to quit: ");

		while (in.hasNextInt()){
			
			// prompt user inputs
			int number = in.nextInt();
			
			// Determine first if perfect square and if so don't run algorithm
			int result = findPerfectSquare(number);
			
			// perfect square
			if(result != -1)
			{
				System.out.println("The number " + number +  " is a perfect square, " +
								   "the square root is: " + result);
				
			}
			
			// not perfect square so run algorithm
			else
			{
				System.out.print("Enter tolerance (error bound) for algorithm: ");
				double tolerance = in.nextDouble();
				
				findSqrt(number, tolerance);	
			}
			
			// prompt user new inputs
			System.out.println("**********************************");
			System.out.print("Enter positive integer, Q to quit: ");
		}
		
		System.out.println("Program terminated");
		
		

		
	}
	
	/**
	 * The function computes the square root of integer numbers
	 * using the babylonian method
	 * 
	 * @param S = number to compute square root of
	 * @param tolerance = error bound of estimate
	 * @return = estimated sqrt, prints also error bound and iterations
	 */
	
	public static double findSqrt(int S, double tolerance ){
		
		double x = initialGuess(S);  // initialize with initial guess		
		int i =0; // counter of number of iterations
		
		// iterate until error within specified user tolerance
		// this formula was used to avoid using Math.sqrt() to 
		// compute the error bound with respect to the true
		// value of the sqrt
		while (x*x/S>(tolerance+1)*(tolerance+1)){
			i++; // iteration
			x = 0.5*(x + S/x);	  // compute estimate of sqrt
					
		}
		
		System.out.println("Calculated Square root of " + S + ": " + x);
		System.out.println("Number of Iterations: " + i);		
		return x;
			
	}
	
	/**
	 * Function returns initial guess for Babylonian algorithm
	 * 
	 * @param S: number to find square root of
	 * @return: initial guess depending of even or odd number of digits of S
	 */
	private static double initialGuess(int S){
		int d = findNDigits(S);
		int k;
		
		// number of digits is even
		if(isEven(d))
		{
			k = (d-2)/2;
			return 6*Math.pow(10, k);
		}
		
		// otherwise odd
		k = (d-1)/2; 
		return 2*Math.pow(10, k);
		
	}
	
	
	
	/**
	 * Function checks if integer number is odd or even
	 * @param number (integer)
	 * @return true if even, false if odd
	 */
	public static boolean isEven(int number){
		
		if (number%2 == 0) return true; // even is divisible by 2
		
		return false; // otherwise is odd
			
	}
	
	/**
	 * Function computes the number of digits of an integer
	 * @param number (integer)
	 * @return number of digits
	 */
	
	public static int findNDigits(int number){
		String s = Integer.toString(number); // convert to string
		int ndigits= s.length(); // count number of characters = digits
		return ndigits;
		
		
	}
	
	/**
	 * The method finds the square root of a perfect square
	 * 
	 * @param number: integer to find if perfect square
	 * @return: square root, or -1 if not perfect square
	 */
	public static int findPerfectSquare(int number){
		
		int i;
		for (i=1; i*i<=number; i++){
			if (i*i == number){
				return i;	// if perfect square
			}
		}
		return -1; // -1 if not perfect square
	
	}
	

}
