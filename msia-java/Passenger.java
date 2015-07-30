/** 
 * Author: Luis Steven Lin, Xiang Shawn Li
 * 
 * Purpose: Flight object stores name and flight object for each passenger
 *          
 * General Design:  includes a constructor, get and set methods
 */


public class Passenger {
	private String name;
	private Flight flight;
	
	public Passenger(String nm,Flight fl)
	{
		name= nm;
		flight = fl;
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public Flight getFlight()
	{
		return flight;
	}
	
	
	public void setName(String nm)
	{
		name = nm;
	}
	
	public void setFlight(Flight fl)
	{
		flight = fl;
	}
	
	
	
	
	
	
}
