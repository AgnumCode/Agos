import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class File {

	//the method(s) used to solve problem:
	//(brute, divide and conquer, both)
	private String method;
	//the answer to the problem
	private double distanceanswer;
	//time taken to solve problem
	private double time;
	//the two coordinates that satisfied the solution
	private String coordanswer;
	//max_x - used for when reading from file
	private double max_x;
	//max_y - used for when reading from file
	private double max_y;
	//numpoints - used for when reading from file
	private int numpoints;
	
	//Empty constructor
	public File() { }
	
	//Constructor
	public File(String method, String coordanswer, double distanceanswer, double time) {
		
		this.method = method;
		this.coordanswer = coordanswer;
		this.distanceanswer = distanceanswer;
		this.time = time;		
	}
	
	//Constructor used to read input
	public File(int numpoints, double max_y, double max_x) {
		
			this.numpoints = numpoints;
			this.max_x = max_x;
			this.max_y = max_y;
		}

	//Create the output file after finding results
	public void createFile(String method, String filename, String coordinates, int num_points, double distanceanswer, double time) {

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("Closest-pair-" + method
							+ "-results.txt"), "utf-8"));

			writer.write("Method used: " + method
					   + "\nOriginal filename: " + filename +
					   "\nTime to obtain solution: " + time + " milliseconds" 
					   + "\nShortest distance: " + distanceanswer
					   + "\nPoints coordinates of shortest distance: " + coordinates
					   + "\nNumber of points: " + num_points);

		} catch (IOException ex) {
				ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public double getDistanceanswer() {
		return distanceanswer;
	}

	public String getMethod() {
		return method;
	}

	public double getTime() {
		return time;
	}

	public String getCoordanswer() {
		return coordanswer;
	}

	public double getMax_x() {
		return max_x;
	}

	public double getMax_y() {
		return max_y;
	}

	public int getNumpoints() {
		return numpoints;
	}
}
