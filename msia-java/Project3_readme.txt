Team Members: 
	Luis Steven Lin
	Xiang Shawn Li

]Error handling:

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