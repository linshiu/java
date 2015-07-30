/** 
 * Author: Luis Steven Lin, Xiang Shawn Li
 * 
 * Purpose: Flight object stores origin, destination and flight ID
 *          for each flight
 *          
 * General Design:  includes a constructor, get and set methods
 */


public class Flight {
	
	private String origin;
	private String destination;
	private int flightID;
	
	public Flight(String Ori, String Des, int FID)
	{
		origin = Ori;
		destination = Des;
		flightID = FID;
	}
	
	public String getOrigin(){
		
		return origin;
	}
	
	public String getDestination(){
		
		return destination;
	}

	public int getFlightID(){
	
		return flightID;
	}
	
	
	public void setOrigin(String Ori)
	{
		origin = Ori;
	}	
	
	public void setDestination(String Des)
	{	
		destination = Des;
	}
	
	
	public void setFlightID(int FID)
	{
		flightID = FID;
	}

}
