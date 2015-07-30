import java.util.Scanner;

public class P2_6 {
	
	/**
	 * Prompts the user for a measurement in meters and 
	 * then converts it to miles, feet, and inches.
	 * 
	 * @author Steven
	 *
	 */
	
	public static void main(String[] args){
		
		System.out.print("Please enter a number in meters: ");
		
		Scanner in = new Scanner(System.in);
		double meters = in.nextDouble();
		
		System.out.println("Miles: " + meters*0.000621371);
		System.out.println("Feet: " + meters*3.28084);
		System.out.println("Inches: " + meters*39.3701);
	}
	

}
