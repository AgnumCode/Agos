import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

//Eric Agocs
//March 16 2015
//ITEC 360
//A program to run brute force and divide and conquer algorithms to solve the closest pair problem

public class Closest {
	
	// Filename of possible input file
	static String filename = "N/A";
	// Used to extract the coordinate point pair solution from the algorithms
	static String coordinatepairs = "";

	public static void main(String[] args) {
		
		// Number of points
		int numpoints = 0;
		// Method to be used
		String method = "";
		// the max_x
		double max_x = 0;
		// the max_y
		double max_y = 0;
		// Array used to keep points
		ArrayList<Point> point = new ArrayList<Point>();

		// If next arg is not a digit, its an input file; read it
		if (!Character.isDigit(args[1].charAt(0))) {
			
			//Get method
			method = args[0];
			
			//Get filename
			filename = args[1];	
			
			// Read the file
			BufferedReader br = null;
			try {
				
				//Hold read line
				String s = "";
				//Hold delmimited line in array
				String[] tok = {""};
				
				br = new BufferedReader(new FileReader(filename));
				numpoints = Integer.valueOf(br.readLine());

				for (int i = 0; i < numpoints; i++) {
					s = br.readLine();
					tok = s.split(" ");
					//Add point to array
					point.add(new Point(Double.valueOf(tok[0]), Double.valueOf(tok[1])));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		// If next arg is digit, all data should be in args
		else if (Character.isDigit(args[1].charAt(0))) {
			
			method = args[0];
			numpoints = Integer.valueOf(args[1]);
			max_x = Integer.valueOf(args[2]);
			max_y = Integer.valueOf(args[3]); 
		}

		// Populate array with points if it was not already
		if (filename.equalsIgnoreCase("N/A")) {
			createPoints(point, numpoints, max_x, max_y);
		}
	
		// If both methods requested, run twice
		if (method.equalsIgnoreCase("both")) {
					
			runAlgorithm("brute", numpoints, point, new File());
			runAlgorithm("divide", numpoints, point, new File());
		}
		// else run once
		else if (!method.equalsIgnoreCase("both")) {
			
			runAlgorithm(method, numpoints, point, new File());
		}
		
	}
	
	//Runs the algorithms
	public static void runAlgorithm(String method, int numpoints, ArrayList<Point> P, File file) {

		//The solution by the algorithm
		double dist = 0;
		//Start timer
		double startTime = System.currentTimeMillis();

		//If brute, do brute
		if (method.equalsIgnoreCase("brute")) {

			dist = BruteForceClosestPairs(P);
		}
		//If divide, do divide
		else if (method.equalsIgnoreCase("divide")) {
			
			dist = prepareDivideAndConquer(P);
		}
				
		//End timer
		double endtime = System.currentTimeMillis() - startTime;

		//Create a file
		file.createFile(method, filename, coordinatepairs, numpoints, dist, endtime);
	}

	//Brute force closest pairs
	public static double BruteForceClosestPairs(ArrayList<Point> P) {

		//Assume closest distance is very large
		double distance =  Double.POSITIVE_INFINITY;
		//Size of Arraylist
		int size = P.size();
		// New minimum 
		double distchange = 0.0;

		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {

				distchange = distance;
				// Calculate distance between two points
				distance = Math.min(Math.sqrt(Math.pow(P.get(i).getX() - P.get(j).getX(), 2) 
						          + Math.pow(P.get(i).getY() - P.get(j).getY(), 2)), distance);
				
				if (distchange != distance) {
					// If a new min was found, assign the new points 
					// used for output to get "coordinates of closest pair"
					coordinatepairs = "(" + P.get(i).getX() + ", " + P.get(i).getY()+  "), " +
								      "(" + P.get(j).getX() + ", " + P.get(j).getY() + ")";
				}
				}
			}

		return distance;			
	}
	
	// Divides arraylist by increasing X and Y respectively into two distinct
	// arrays and returns the D&C solution
	public static double prepareDivideAndConquer(ArrayList<Point> P) {

		//Sort by X
		ArrayList<Point> X = new ArrayList<Point>(P);
		sortX(X);
		//Sort by Y
		ArrayList<Point> Y = new ArrayList<Point>(P);
		sortY(Y);
		//Call divide and conquer closest pairs algorithm for solution
		return divideAndConquer(X, Y);

	}

	//Divide and conquer closest pairs
	private static double divideAndConquer(ArrayList<Point> X, ArrayList<Point> Y) {

		//Assigns size of array
		int size = X.size();

		//If less than 3 points, efficiency is better by brute force
		if (size <= 3) {
			return BruteForceClosestPairs(X);
		}
		
		//Ceiling of array size / 2
		int ceil = (int) Math.ceil(size / 2);
		//Floor of array size / 2
		int floor = (int) Math.floor(size / 2);
		
		//Array list for x & y values left of midpoint
		ArrayList<Point> xL = new ArrayList<Point>();	
		ArrayList<Point> yL = new ArrayList<Point>();
		
		//for [0 ... ceiling of array / 2]
		//add the points onto the new arrays
		for (int i = 0; i < ceil; i++) {
			
			xL.add(X.get(i));
			yL.add(Y.get(i));
		}
		
		// Array list for x & y values right of midpoint
		ArrayList<Point> xR = new ArrayList<Point>();
		ArrayList<Point> yR = new ArrayList<Point>();
		
		//for [floor of array / 2 ... size of array]
		//add the points onto the new arrays
		for (int i = floor; i < size - 1; i++) {

		    xR.add(X.get(i));
			yR.add(Y.get(i));
		}
		
		//Recursively find the shortest distance
		double distanceL = divideAndConquer(xL, yL);
		double distanceR = divideAndConquer(xR, xL);
		//Smaller of both distances
		double distance = Math.min(distanceL, distanceR);
		//Mid-line
		double mid = X.get(ceil - 1).getX();

		ArrayList<Point> S = new ArrayList<Point>();

		//copy all the points of Y for which |x - m| < d into S[0..num - 1]
		for (int i = 0; i < Y.size() - 1; i++) {

			if (Math.abs(X.get(i).getX() - mid) < distance) {
				S.add(Y.get(i));
			}
		}
		
		//Square minimum distance
		double dminsq = distance * distance;
		//Counter
		int k = 0;
		int num = S.size();

		for (int i = 0; i < num - 2; i++) {
			
			k = i + 1;
			
			while (k <= num - 1 && (Math.pow((S.get(k).getY() - S.get(i).getY()), 2) < dminsq)) {

				//Find distance between points and find the minimum compared to dminsq
				dminsq = Math.min(Math.pow(S.get(k).getX() - S.get(i).getX(), 2) 
								+ Math.pow(S.get(k).getY() - S.get(i).getY(), 2), dminsq);

				k = k + 1;
			}
		}

		return Math.sqrt(dminsq);
	}
	
	// Sorts the array by increasing X values
	public static void sortX(ArrayList<Point> points) {
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				if (p1.getX() < p2.getX())
					return -1;
				if (p1.getX() > p2.getX())
					return 1;
				return 0;
			}
		});
	}

	// Sorts the array by increasing X values
	public static void sortY(ArrayList<Point> points) {
		Collections.sort(points, new Comparator<Point>() {
			 public int compare(Point p1, Point p2) {
				if (p1.getY() < p2.getY())
					return -1;
				if (p1.getY() > p2.getY())
					return 1;
				return 0;
			}
		});
	}
	
	//Method populates P with the requested number of points randomly
	public static ArrayList<Point> createPoints(ArrayList<Point> P, int numPoints, double max_x, double max_y) {
						
		Random r = new Random();
		//Create new point objects and add them to 'point'
	    for (int i = 0; i < numPoints; i++) {
	    P.add(new Point(r.nextDouble() * max_x, r.nextDouble() * max_y));
	    }
	    
	    return P;
	}
}
