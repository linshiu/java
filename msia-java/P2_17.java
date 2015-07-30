import java.util.Scanner;

public class P2_17 {
	
	/**
	 * Reads two times in military format (0900, 1730) and prints the
	 * number of hours and minutes between the two times.
	 * @param args
	 */
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
	    int hour_diff;
	    int min_diff;
		
	    // Prompt the user input
		System.out.print("Please enter the first time: ");
		String time1 = in.next();
		System.out.print("Please enter the second time: ");
		String time2 = in.next();
		
		// Extract hours and times as integers for each time
		int hr1 = Integer.parseInt(time1.substring(0, 2));
		int hr2 = Integer.parseInt(time2.substring(0, 2));
		
		int min1 = Integer.parseInt(time1.substring(2, 4));
		int min2 = Integer.parseInt(time2.substring(2, 4));
		
		// if time1 <= time 2 true, time1 and time 2 are same day
		// depending on the minutes of the times, 
		// compute appropriately hours and minutes
		if (time1.compareTo(time2) <= 0)
		{
			if (min1<=min2)
			{
				hour_diff = hr2 - hr1;
			    min_diff = min2 - min1;
			}
			else
			{
				hour_diff = hr2 - hr1 -1;
			    min_diff = 60 + (min2 - min1);	
			}
		}
		
		// if time1 < time 2 false, time2 is next day time, so adjust
		else
			if (min1<=min2)
			{
				hour_diff = 24 + (hr2 - hr1);
			    min_diff = min2- min1;
			}
			else
			{
				hour_diff = 24 + (hr2 - hr1) -1 ; 
			    min_diff = 60 + (min2 - min1);	
			}
		
		// Display results
		System.out.println(hour_diff + " hours "+
				           min_diff + " minutes ");
				
	}
}

