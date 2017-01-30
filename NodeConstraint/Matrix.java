import java.util.*;

public class Matrix  {
	
	//Used to maintain a square matrix
	private int cell = 0;
	//Used to facilitate information user has entered
	private int[][] matrix;
	//Empty constructor
	public Matrix() {
				
	}

	//Method used to create, populate, and perform calculations on a matrix
	//Program is also TERMINATED within checkMatrix() in this method
	public void prepareMatrix(String toEvent, String fromEvent, 
			ArrayList<String> event, ArrayList<Event> aShed, int constraint) {

		//Method to remove duplicates, while maintaining sequence, from event list
		removeDuplicateWithOrder(event);
		
		//If cell is less than number of column/rows ("events")
		//Set cell to that value and create a larger matrix
		if (cell < event.size()) {

			setCell(event.size());
			matrix = new int[cell][cell];

		}
		
		//Create object of "Schedule" class 
		//passing in information to its appropriate constructor
		Event sched = new Event
		(event.indexOf(toEvent), event.indexOf(fromEvent), constraint);
		//Add newly created object to ArrayList<Schedule> sched
		aShed.add(sched);
		
		//Populates matrix with constraints using data from aShed as well as place -99s
		for (int i = 0; i < aShed.size(); i++) {
			
			//matrix[cord1][cord2] = constraint
			matrix[aShed.get(i).getCord1()][aShed.get(i).getCord2()] 
					= aShed.get(i).getConstraint();
			
			//Place -99s in cells that equal zero but aren't on the
			//diagonal of the matrix.
			for (int a = 0; a < event.size(); a++) {

				for (int b = 0; b < event.size(); b++) {
					
					if (matrix[a][b] == 0 && a != b) {

						matrix[a][b] = -99;
					}
				}
			}
			
		}

	
		floydWarshall(matrix);
		checkMatrix(matrix, event.size());
		printMatrix(event, matrix, aShed);

	}

	public void printMatrix(ArrayList<String> event, int[][] matrix, 
			ArrayList<Event> aShed) {

		System.out.printf("%3s", " ");

		//Print out event names
		for (int i = 0; i < event.size(); i++) {

			System.out.printf("%7s|", event.get(i));

		}

		System.out.println();

		//Print out event names and constraints
		for (int i = 0; i < event.size(); i++) {

			System.out.printf("%1s|", event.get(i));

			for (int k = 0; k < event.size(); k++) {

				System.out.printf("%7s|", matrix[i][k]);
			}

			
			System.out.println();

		}

		//Prints out the english text version of  
		//what was printed  out by the above code
		
		//Does not work
		
//		for (int i = 0; i < event.size(); i++) {
//			
//			System.out.print("\n" + e.toString(aShed.get(i).getConstraint(),
//						event.get(i), event.get(i + 1)));
//			
//
//		}
//		
		System.out.print("\n\n\n");

	}
	
	//http://www.devx.com/tips/Tip/20864
	//Credit: Vijayanandraj Amaladoss
	//Method takes in an array list then removes duplicates while maintaining order.
	public void removeDuplicateWithOrder(ArrayList<String> arlList) {
		Set<String> set = new HashSet<String>();
		ArrayList<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = arlList.iterator(); 
			 iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
	}
	
	
	//Method checks if diagonals of the floyd'd matrix does not equal zero.
	//If a non-zero number is found on diagonal, illegal argument is thrown.
	public void checkMatrix (int[][] matrix, int eventSize) {
		
		for (int a = 0; a < eventSize; a++) {

			for (int b = 0; b < eventSize; b++) {

				if (a == b && matrix[a][b] != 0) {
					
					 System.out.println("MATRIX IS OVERCONSTRAINED!");
					 System.out.println("NO ADDITIONAL CONSTRAINTS POSSIBLE.");
					 System.out.println("\n");
					 throw new IllegalArgumentException();
					
				}

			}
		}
		
	}

	public void floydWarshall(int[][] path) {

		for (int k = 0; k < path.length; k++) {
			for (int i = 0; i < path.length; i++) {
				for (int j = 0; j < path.length; j++) {

					path[i][j] = Math.max(path[i][j], path[i][k] + path[k][j]);

				}
			}
		}

	}

	public int getCell() {
		return cell;
	}

	public void setCell(int cell) {
		this.cell = cell;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}
