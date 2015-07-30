/** 
 * Author: Luis Steven Lin, Xiang Shawn Li
 * 
 * Purpose: Implements a simple flight reservation system
 *          
 * General Design:  Main method calls functions to read files, store in arrays, and run
 *                  a loop to get input and process inputs from user until it quits. 
 *                  The program is mainly broken down by methods for each menu option
 *                  Option S = Show all reservations
 *                  Option B = Show names for a flight
 *                  Option U = Show reservations for a user
 *                  Option N = Create Reservations
 *                  Option Q = To quit.
 *                  
 *                  The input files used are reservations.txt and allFlights.txt.
 *                  The program reads all reservations from the file at the beginning
 *                  and stores it into arrays (same for reference information in allFlights).
 *                  When the program quits, any information in the array is stored back
 *                  and overwritten in the reservations.txt file. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {
	
	// Error handling:
	// 1) Trim to correctly read input from user in case white space is typed
	// 2) FlightID valid integer (assumes allFlights.txt has valid inputs): exception
	// 3) Missing files: exception
	// 4) Blank File (using array list this is handled)
	// 5) Out of bound in arrays or empty array (using array list this is handled)
	// 6) N = No match Origin, Destination --> flightID
	// 7) S = No data to show
	// 8) U = If user is not found
	// 9) B = IF flightID does not exist or input invalid
	// 10) Check names do not contain numbers (use isLetter() method)
	// 11) Check duplicate (user, flight) combination
	// 12) Upper/lower case (use methods toUpperCase() or toLowerCase())
	
	
	// define global variables to hold data so passing arguments and returning
	// values is avoided simplifying the coding
	
	// note array lists were chosen instead of arrays because 
	// of its flexibility and no need to keep track of the size of the array
	// another advantage is that removal of observations can be easily implemented if needed
	
	// reservations.txt
	public static ArrayList <String > name = new ArrayList<String>();
	public static ArrayList <String > origin = new ArrayList<String>();
	public static ArrayList <String > destination = new ArrayList<String>();
	public static ArrayList <Integer> flightID = new ArrayList<Integer>();
	
	// allFlights.txt (note: arrays were created in case future 
	// flights need to be added and also easier to work in java for look up
	// information based on flight ID)
	public static ArrayList <String > origin_ref = new ArrayList<String>();
	public static ArrayList <String > destination_ref = new ArrayList<String>();
	public static ArrayList <Integer> flightID_ref = new ArrayList<Integer>();
	
	/**
	 * Main method calls functions to read files, store in arrays, and run
	 * a loop to get input and process inputs from user until it quits.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		
		//load data, terminate program if missing files
		loadData();
		
		String choice = "";
		Scanner in_choice = new Scanner(System.in);
		
		// display menu and ask for input
		showMenu();
		System.out.print("> Select Menu Option: " );
		choice = in_choice.next().trim().toUpperCase();
		
		// Run program until User quits
		while(!choice.equals("Q")){
			
			Scanner in = new Scanner(System.in);
			
			// Option N = Create Reservations
			if (choice.equals("N")){
				
				// Ask for inputs for new reservation
				System.out.print("> Enter the name: ");
				String newName = in.nextLine().trim();
				System.out.print("> Enter the departure city: ");
				String newDeparture = in.nextLine().trim();
				System.out.print("> Enter the destination city: ");
				String newDestination= in.nextLine().trim();
				
				// Check that names/cities are a valid entry
				if (isValidName(newName) && isValidName(newDeparture) && isValidName(newDestination)   ){
					
					// Create New Reservation
					createReservation(newName,newDeparture,newDestination);	
				}
				
				else{ System.out.println("**Error: Invalid Name/City (contains invalid character)");}

			}
			
			// Option S = Show all reservations
			else if (choice.equals("S")){
				
				showAllReservations();		
			}
			
			// Option U = Show reservations for a user
			else if (choice.equals("U")){
				
				// Ask for inputs for user to search for
				System.out.print("> Enter the name: ");
				String newName = in.nextLine().trim();
				
				// Check valid entry
				if (isValidName(newName)){
					showUserReservations(newName);
				}
				
				else{System.out.println("**Error: Invalid Name (contains invalid character)");
				}	
			}
			
			// Option B = Show names for a flight
			else if (choice.equals("B")){
				
				// Ask for inputs for flight to search for
				System.out.print("Enter FlightID: ");
				String newFlightID = in.nextLine().trim();
				
				// Check valid flightID input
				if (validFlightID(newFlightID)){
					showNamesFlightID(Integer.parseInt(newFlightID));	
				}		
			}
			// Other Option = Invalid Input
			
			else{
				System.out.println("**Invalid Input, please enter one of the menu options");		
			}
			
			// display menu and ask for input
			showMenu();
			System.out.print("> Select Menu Option: " );
			choice = in_choice.next().trim().toUpperCase();
		}
		
		// Option Q = User quits, update reservations file by overwriting
		// file with all information from array
		writeFile("reservations.txt");
		
		System.out.println("**Exiting Progam....");

	}
	
	/**
	 * loadData
	 * 
	 * Call read methods to to read the reservation and allFlights files
	 */
	public static void loadData(){
		
		// reservations file
		try{
			// prompt the user for filename
			Scanner console = new Scanner(System.in);
			System.out.print("Reservation text file (e.g. reservations.txt): ");
			String fileName = console.next();

			
			readFile(fileName);
			
		}
		
		catch(IOException exception){
			System.out.println("**Error: reservations.txt is missing");
			System.exit(0);
			
		}
		
		// all fligts file
		try{
			// prompt the user for filename
			Scanner console = new Scanner(System.in);
			System.out.print("All flights text file (e.g. allFlights.txt): ");
			String fileName = console.next();

			readFile(fileName);
			
		}
		
		catch(IOException exception){
			System.out.println("**Error: allFlights.txt is missing");
			System.exit(0);
			
		}
		
		
	}
	
	/**
	 * readFile
	 * The function reads txt files and stores the information
	 * in the appropriate array lists
	 * @param fileName
	 * @throws IOException
	 */
	public static void readFile(String fileName) throws IOException{
		
		File inputFile = new File(fileName);
		Scanner in = new Scanner(inputFile);
		
		// read line of files as string
		while(in.hasNextLine()){
			
			String line = in.nextLine();
			
			// break different fields in the line by ;
			Scanner in_line = new Scanner(line);
			in_line.useDelimiter(";");
			
			// keep track of column number to decide what array to store value to
			int column = 0;
			
			// iterate each column (separated by ;) of the line
			while(in_line.hasNext()){
				column++;
				
				// get value of the column
				String value = in_line.next().trim() ;
				
				// add to appropriate array depending of filename and column number
				if (fileName.equals("reservations.txt"))
				{
					if (column ==1){ name.add(value);}
					else if (column ==2){origin.add(value);}
					else if (column ==3){destination.add(value);}
					else {flightID.add(Integer.parseInt(value));}
				}
				
				else if (fileName.equals("allFlights.txt"))
				{
					if (column ==1){ origin_ref.add(value);}
					else if (column ==2){destination_ref.add(value);}
					else {flightID_ref.add(Integer.parseInt(value));}
				}
					
			}
			
			in_line.close();
		}
		
		in.close();
		
		
	}
	
	/**
	 * writeFile
	 * The function writes the information stored in the array to
	 * a txt file
	 * @param fileName
	 * @throws IOException
	 */
	public static void writeFile(String fileName) throws IOException {
		
		PrintWriter out = new PrintWriter(fileName);
		
		// write out in different lines for every customer
		// the reservation information separated by ;
		for (int i=0; i < name.size(); i++){
			out.printf("%s; %s; %s; %d%n",
					name.get(i),
					origin.get(i),
					destination.get(i),
					flightID.get(i));
		}
		
		
		out.close();	
		
	}
	
	/**
	 * createReservation
	 * 
	 * Method asks user input for name, departure and destination
	 * Then looks up the FlightID, and if found adds it to the
	 * arrays lists. If not found, message is displayed
	 */
	
	public static void createReservation(String newName,String newDeparture,String newDestination){
	
		
		// lookup flight
		int newFlightID = findFlight(newDeparture,newDestination);
		
		// if not found display message and go back to main menu
		if (newFlightID == -1){
			System.out.println("**Fail: No Flight ID found for given departure and destination "
					+ "city combination");
		}
		
		// if found, but duplicate
		else if (hasDuplicateUserFlight(newName, newFlightID))
		{
			
			System.out.println("**Fail: Duplicate name, flightID combination");	
				
		}
		
		// found, no duplicate, then add
		else {
			
			name.add(newName);
			origin.add(newDeparture);
			destination.add(newDestination);
			flightID.add(newFlightID);
				
			System.out.println("**Success: Reservation Created");	
				
			}
			
		}
			
	
	
	/**
	 * findFlight
	 * 
	 * Function returns the FlightID (or -1 if not found) for a given departure and destination 
	 * city combination. Assumes a unique FlightID
	 * @param departureCity
	 * @param destinationCity
	 * @return FlightID if found, -1 if not
	 */
	
	public static int findFlight(String departureCity,String destinationCity){
			
		for (int i=0; i<flightID_ref.size();i++){
			
			// if origin, destination pair match return flightID
			if(departureCity.toLowerCase().equals(origin_ref.get(i).toLowerCase()) && 
			   destinationCity.toLowerCase().equals(destination_ref.get(i).toLowerCase()))
			{	
				
				// stop since found
				 return flightID_ref.get(i);
			}
			
		}
		
		// else return -1 if no matches
		return -1;
		
	}
	
	/**
	 * hasDuplicateUsersFlights
	 * 
	 * Check whether name,flighID combination is unique in reservations table
	 * @param newName
	 * @param newFlightID
	 * @return
	 */
	public static boolean hasDuplicateUserFlight(String newName, int newFlightID){
		
		// iterate through all reservations
		for (int i = 0; i < flightID.size(); i++ ){
			
			// if duplicate found
			if(newName.toLowerCase().equals(name.get(i).toLowerCase()) &&
			   newFlightID == flightID.get(i)){			
				return true;
			}	
		}
		
		return false;
	}
	
	/**
	 * showAlleservations
	 * 
	 * Print out all reservations in the database
	 */
	public static void showAllReservations(){
		
		/// print table header
		printHeader ();
		
		// if there are no reservations display message
		if (flightID.size() ==0){
			
			System.out.println("**No Reservations in database");
		}
		
		else{
			// loop through every reservation
			for (int i =0; i<flightID.size(); i++){
						
				System.out.printf("%-20s%-20s%-20s%-20s\n",
						name.get(i), origin.get(i), destination.get(i), flightID.get(i));
			}
			
		}
		
	}
	
	/**
	 * showUserReservations
	 * 
	 * Method prints all reservations for a given name if there is any
	 * @param user_name
	 */
	public static void showUserReservations(String user_name){
		
		// print table header
		System.out.printf("%-20s%-20s%-20s\n",
				"Origin","Destination","FlighID");
		System.out.print("-----------------------------------"
				+ "-------------------------------------------\n");	
				
		boolean found = false;
		// loop through every reservation
		for (int i =0; i<flightID.size(); i++){
			
			// output only matching user
			if (user_name.toLowerCase().equals(name.get(i).toLowerCase())){
				
				found = true;
				System.out.printf("%-20s%-20s%-20s\n",
					 origin.get(i), destination.get(i), flightID.get(i));
			}
		
		}
		
		if (found == false) { System.out.println("**No reservantions under that name");}
		
	}
		
	
	/**
	 *  showNamesFlightID
	 *  
	 *  Method prints all reservations for a given flightIFD if there is any
	 * @param newflightID
	 */
	public static void showNamesFlightID(int newflightID){
		
		// print table header
		System.out.printf("%-20s\n","Name");
		System.out.print("--------------------\n");	
				
		boolean found = false;
		// loop through every reservation
		for (int i =0; i<flightID.size(); i++){
			
			// output only matching flight
			if ( newflightID == flightID.get(i)){
				
				found = true;
				System.out.printf("%-20s\n",
					name.get(i));
			}
		
		}
		
		if (found == false) { System.out.println("**No names under that flight");}
		
		
	}
	

	/**
	 *  showMenu
	 *  
	 *  Displays the main menu
	 */
	public static void showMenu(){
		System.out.printf("\n\n%55s \n%60s \n\n%s \n%s \n%s \n%s \n%s\n\n",
			"Welcome to the Flight Planner",
			"Please Select From One of the Following:",
			"N – in order to create a new flight reservation;",
			"S – in order to show all the data for the existing reservations for all the users;",
			"U – in order to show all the reservations for a particular user;",
			"B – in order to show the names of the customers who have bookings for a particular flight;",
			"Q – in order to quit this application");

	}
	
	/**
	 * printHeader
	 * 
	 * Prints the header when showing information to the user
	 */
	
	public static void printHeader (){
		
		// print table header
		System.out.printf("%-20s%-20s%-20s%-20s\n",
				"Name", "Origin","Destination","FlighID");
		System.out.print("-----------------------------------"
				+ "-------------------------------------------\n");		
	}
	
	public static boolean validFlightID(String newFlightID_s){
		
		boolean isValid = false;
		
		// try to convert element to integer
		try
		{
		  int newflightID = Integer.parseInt(newFlightID_s);
		  
		  // if positive integer
		  if (newflightID >0)
		  {	  
			  isValid = true;
			  return isValid;
		  }
		  
		  // if negative
		  else
		  {	
			  System.out.println("**Fail: Invalid flightID (needs to be integer > 0) ");
			  return isValid;
		  }
		  	  
		}
		
		// if integer conversion fails
		catch (NumberFormatException e) 
		{
			System.out.println("**Fail: Invalid flightID (needs to be integer > 0) ");
			return isValid;	
		}
				
	}
	
	/**
	 * isValidName
	 * 
	 * Takes a string and check whether string has a digit
	 * 
	 * @param newName
	 * @return
	 */
	public static boolean isValidName(String newName){
		
		// iterate through every character of string
		for (int i = 0; i<newName.length(); i++){
			
			// check if character is not letter and not a space
			if (!Character.isLetter(newName.charAt(i)) && !Character.isWhitespace(newName.charAt(i))){
				return false;		
			}
		}
			
		return true;
	}
	
}
	


