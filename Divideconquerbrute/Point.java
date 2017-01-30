public class Point {

	//x coordinate
	private double x;
	//y coordinate
	private double y;

	//Empty constructor
	public Point() { }
	
	//Constructor for creating a general Point object
	public Point (double x, double y) {
				
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
