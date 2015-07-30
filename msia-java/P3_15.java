import java.util.Scanner;


public class P3_15 {
	
	/**
	 * Write a program that reads in three floating-point 
	 * numbers and prints the largest of the three inputs. 
	 *
	 */
	public static void main(String[] args){
	
	Scanner in = new Scanner(System.in);
	System.out.print("Please enter three numbers: ");
	
	double n1 = in.nextDouble();
	double n2 = in.nextDouble();
	double n3 = in.nextDouble();
	
	double max = n1;
	if (n2>max){
		max = n2;
	}
	if (n3>max){
		max = n3;
	}
	
	System.out.println("The largest number is " + max);
		
	}

}
