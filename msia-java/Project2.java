/** 
 * Author: Luis Steven Lin, MS Analytics, ID: 2873812
 * 
 * Purpose: Computes the square root of an integer number
 *          using an algorithm (Babylonian)
 *          
 * General Design: Main method calls function runApplication that 
 *                 prompts user for inputs and gives options to read input.
 *                 (one at a time or sequence by space). The method then 
 *                 converts the array by taking each element separated 
 *                 by space using the method getInput. Then displaySqrt function 
 *                 is called, which iterates each valid user entry stored in the 
 *                 array returned from getInput, and displays the square 
 *                 root by calling findSqrt (use algorithm) and
 *                 findPerfectSquare. Different output options are prompted 
 *                 to the user. The program also includes a
 *                 loop to run another instance of the application until
 *                 user enters "Q" to quit program. 
 *                 
 */

import java.util.Scanner;
import java.util.Arrays;

public class Project2 {
	
	/**
	 * Main method prompts user inputs and calls functions to process
	 * inputs into an array and display the sqrt root
	 * 
	 */
	
	public static void main(String[] args){
		
		System.out.println("** This program computes the square root of integers");
		System.out.println("** Only positive integer numbers are processed");
	
		runApplication();
		
	}
	
	/**
	 * runApplication
	 * 
	 * The method prompts the user for an option of inputting values, processes
	 * the input by using getInput method, and displays the sqrt by 
	 * calling displaySqrt method. 
	 * 
	 * The function runs another instance of the application until the user
	 * selects to quit.
	 * 
	 * 
	 */
	public static void runApplication(){
		
		System.out.print("> Choose input method: <1> separated by space, "
				+ "<2> one at a time, <Q> to quit: " );
		
		Scanner in = new Scanner(System.in);
		String input_choice = in.nextLine();
		
		while (!input_choice.equals("Q"))
		{
			
			String input_line ="";
			
			if (input_choice.equals("2"))
			{	
				String flag = "done";
				String current_input = "";
				
				System.out.println("> Input numbers one at a time by pressing enter (type <done> to finish): ");
				
				// iterate until user inputs done
				while (!flag.equals(current_input))
				{
					// concatenate previous value in the loop to one string delimited by space
					// do this way instead because don't want "done" concatenated
					input_line = input_line + current_input + " " ;
					current_input = in.nextLine();
					
				}
				System.out.println(input_line);
					
			}
			
			// input separated by space
			else if (input_choice.equals("1"))
			
			{
				System.out.print("> Input Multiple integer numbers separated by space: ");
				
				// read all elements in the line as one string
				input_line = in.nextLine();
			
			}
			
			// invalid input so exit loop and termiante program
			else{
				
				break;
			}
			
			// get array with positive integers from user's inputs
			int[] input_int = getInput(input_line);
			
			if(input_int.length != 0){
				// print the sqrt roots from array
				displaySqrt(input_int);	
			}
			
			
			// Ask user to run another instance of the application
			System.out.print("> Choose input method: <1> separated by space, "
					+ "<2> one at a time, <Q> to quit: " );
			
			input_choice = in.nextLine();
			
		}
		
		System.out.println("***** Program terminated *****");
	
	}
	
	/**
	 * getInput
	 * Takes a string with entries delimited by space and 
	 * returns an array with positive integers. 
	 * Function also prints invalid and valid entries
	 * @param input_line: string with entries delimited by space
	 * @return: array with positive integers 
	 */
	
	
	public static int[] getInput(String input_line){
		
		/*NOTE: that there is no restriction on the number of values to 
		 * calculate the square root (project says 20). To handle the extra
		 * credit (the user can input any number of values), the input was
		 * read as a string, and then split by space so the maximum 
		 * number of values needed for an array can be determined, making 
		 * it equivalent to allowing an array to grow dynamically in size
		 * but is more robust as it can handle any type of invalid inputs,
		 * such as string, doubles, and negative numbers
		
		*/
		
		// split the string by space
		String[] input_split = input_line.split("\\s+");
		
		// create a temporary partially filled array to hold integer numbers
		int[] input_temp = new int[input_split.length];
		
		// index for integer array
		int currentSize = 0;
		String invalid_inputs = "";
		String valid_inputs = "";
		
		
		// extract only positive integers from string array
		for (String element:input_split){
			
			// try to convert element to integer
			try
			{
			  int number = Integer.parseInt(element);
			  
			  // if positive integer
			  if (number >0)
			  {	  
				  input_temp[currentSize] = number; // add element to array
				  valid_inputs = valid_inputs + " " + number;
				  currentSize++;
			  }
			  
			  // if negative
			  else
			  {
				  invalid_inputs = invalid_inputs + " " + element;		  
			  }
			  	  
			}
			
			// if integer conversion fails
			catch (NumberFormatException e) 
			{
				invalid_inputs = invalid_inputs + " " + element;	
			}
			
		}
		
		// print valid and invalid entries if any
		if (invalid_inputs.length()>1) // 1 because hitting enter adds " " in string
		{
			System.out.println("The invalid inputs are:" + invalid_inputs);
		}
		if (valid_inputs.length()!=0)
		{
			System.out.println("The valid inputs are:" + valid_inputs);
		}
		
		// create input array with size equal to number of valid inputs
		int[] input_int = new int[currentSize];
		
		// if no elements, don't process array
		if (currentSize == 0)
		{
			System.out.println("There a no valid inputs");
			return input_int;
		}
		
		// otherwise process array	
		for (int i=0; i < currentSize; i++)
		{
			input_int[i] = input_temp[i]; // add element to array
			
		}
		
		return input_int ;
		
	}
	
	/**
	 * displaySqrt
	 * Displays the sqrt root of integers from array. 
	 * Prompts user tolerance for algorithm and option
	 * to print either sequence of values or one value per row
	 * @param input_int = array with integers
	 */
	public static void displaySqrt(int[] input_int)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.print("Default output is sequence of values\n" +
						   "> Type <S> for sequence of values or <R> for one value per row (includes # iterations): ");
		String output_option = in.next();
		
		System.out.print("> Enter tolerance (error bound) for algorithm: ");
		double tolerance = in.nextDouble();
		
		// determine if print row by row (include iterations)
		boolean print = false;
		
		if (output_option.equals("R"))
		{
			print = true;
			System.out.printf("%-10s%-10s%s\n","Input","Sqrt","N Iterations");
		}
		
		// for option sequence of values
		String results_list = "";
		
		for (int element:input_int)
		{
			// Determine first if perfect square and if so don't run algorithm
			int result = findPerfectSquare(element);
			
			// perfect square
			if(result != -1)
			{
				if (print) 
				{	
					System.out.printf("%-10d%-10d%s\n",
							element,result, "none (perfect square)");
				}
				results_list = results_list + result + " ";
				
			}
			
			// not perfect square so run algorithm
			else
			{	
				double sqrt = findSqrt(element, tolerance, print);		
				results_list = results_list + sqrt + " ";
			}
			
		}
		
		// if option sequence of values 
		if (!print)
		{
			System.out.println(results_list);
		}
	}	
	
	/**
	 * findSqrt
	 * The function computes the square root of integer numbers
	 * using the babylonian method and has option to print result
	 * 
	 * @param S = number to compute square root of
	 * @param tolerance = error bound of estimate
	 * @param print = print results if true
	 * @return = estimated sqrt, prints also error bound and iterations
	 */
	
	public static double findSqrt(int S, double tolerance, boolean print){
		
		double x = initialGuess(S);  // initialize with initial guess		
		int i =0; // counter of number of iterations
		
		// iterate until error within specified user tolerance
		// this formula was used to avoid using Math.sqrt() to 
		// compute the error bound with respect to the true
		// value of the sqrt
		// note though this method does not provide very good results
		// for certain numbers as the criteria is met and loop does not start
		while (x*x/S>(tolerance+1)*(tolerance+1)){
			i++; // iteration
			x = 0.5*(x + S/x);	  // compute estimate of sqrt
					
		}		
		
		// display results only if flag print is true
		if (print)
		{	
			
			//System.out.printf("%s%-10d%s%-10.6f%s%d\n","Input: ",
				//	S,", Sqrt: ",x, ", N iterations: ",i);
			
			System.out.printf("%-10d%-10.6f%d\n",S,x,i);
		}
		
		return x;
			
	}
	
	/**
	 * initialGuess
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
	 * isEven
	 * Function checks if integer number is odd or even
	 * @param number (integer)
	 * @return true if even, false if odd
	 */
	public static boolean isEven(int number){
		
		if (number%2 == 0) return true; // even is divisible by 2
		
		return false; // otherwise is odd
			
	}
	
	/**
	 * findNDigits
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
	 * findPerfectSquare
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

/* References
 * 
 * http://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
 * http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
 * 
 */
