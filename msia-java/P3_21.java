import java.util.Scanner;

public class P3_21 {
	/**
	 * compute income tax according to this schedule.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		// Define tiers
		final double TIER1 = 50000;
		final double TIER2 = 75000;
		final double TIER3 = 100000;
		final double TIER4 = 250000;
		final double TIER5 = 500000;
		
		// Define rates for tiers
		final double TIER1_rate = 0.01;
		final double TIER2_rate = 0.02;
		final double TIER3_rate = 0.03;
		final double TIER4_rate = 0.04;
		final double TIER5_rate = 0.05;
		final double TIER6_rate = 0.06;
		
		System.out.print("Please enter income (positive integer): ");
		double income = in.nextDouble();
		double tax;
		
		if (income <= TIER1)      tax = income * TIER1_rate;
		else if (income <= TIER2) tax = TIER1 * TIER1_rate + 
				                        (income-TIER1)  * TIER2_rate;
		else if (income <= TIER3) tax = TIER1 * TIER1_rate + 
										(TIER2 - TIER1) * TIER2_rate +
                                        (income-TIER2)  * TIER3_rate;
      
		else if (income <= TIER4) tax = TIER1 * TIER1_rate + 
				                        (TIER2 - TIER1) * TIER2_rate +
				                        (TIER3 - TIER2) * TIER3_rate +
				                        (income-TIER3)  * TIER4_rate;
		else if (income <= TIER5) tax = TIER1 * TIER1_rate + 
										(TIER2 - TIER1)  * TIER2_rate +
										(TIER3 - TIER2)  * TIER3_rate +
										(TIER4 - TIER3)  * TIER4_rate +
										(income-TIER4)   * TIER5_rate;
		else tax = TIER1 * TIER1_rate + 
				   (TIER2 - TIER1) * TIER2_rate +
				   (TIER3 - TIER2) * TIER3_rate +
				   (TIER4 - TIER3) * TIER4_rate +
				   (TIER5 - TIER4) * TIER5_rate +
			       (income-TIER5)  * TIER6_rate;		
		
		System.out.printf("%s%.2f", "Income tax: $ ", tax);
		
	}
}
