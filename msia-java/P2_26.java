import java.util.Scanner;

public class P2_26 {
	
	/**
	 * Reads initial balance and annual interest rate,
	 * and calculates the interest for the first 3 months
	 * 
	 * 
	 * @param args
	 */
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Initial balance: ");
		double balance = in.nextDouble();		
		
		System.out.print("Annual interest rate in percent: ");
		double interest_rate = in.nextDouble()/100;
		
		double month1 = balance*Math.pow((1+interest_rate/12), 1);
		double month2 = balance*Math.pow((1+interest_rate/12), 2);
		double month3 = balance*Math.pow((1+interest_rate/12), 3);	
		
		System.out.printf("%-23s%.2f\n", "After first month: ", month1);
		System.out.printf("%-23s%.2f\n", "After second month: ", month2);
		System.out.printf("%-23s%.2f\n", "After third month: ", month3);

	}

}
