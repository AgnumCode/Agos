import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class GUI {

	//Empty constructor
	public GUI() {
		
	}

	
	//Begins program
	public void Begin() {

		//sched is used to keep track of coordinates in a matrix and 
		//the respective cells' constraint
		ArrayList<Event> sched = new ArrayList<Event>();
		
		//event is used to keep track of row/column "labels" as well as
		//dictate the size of a matrix through use of its .size()
		ArrayList<String> event = new ArrayList<String>();
		
		//Used to loop the program
		//Value is not changed by program
		boolean answered = true;
		
		String fromEvent = "";
		String toEvent = "";
		String constraint = "";
		//Used to swap 'to' and 'from' events
		String tempEvent = "";
		
		while (answered) {

			//Obtain toEvent from user input
			toEvent = JOptionPane.showInputDialog("Enter 'to event' name.");
			//Obtain fromEvent from user input
			fromEvent = JOptionPane.showInputDialog("Enter 'from event' name.");
			
			//User entered the same event, which cannot occur
			if (toEvent.equals(fromEvent)) {

				jOptionWarning("Error!", "Event names cannot be the same!");
					Begin();
			}
					
			//Obtain constraint from user
			constraint = JOptionPane.showInputDialog("Enter a numerical constraint.");
			
			//User was missing a toEvent, a constraint, or a fromEvent from input
			if (toEvent.length() == 0 || constraint.length() == 0 || fromEvent.length() == 0) {

				jOptionWarning("Missing event and/or constraint!", "Enter all information!");	
				Begin();
				
				}

			//User has entered all information successfully
			else if (toEvent.length() != 0 && fromEvent.length() != 0 && constraint.length() != 0) {
				
				//If negative constraint was entered, swap event order
				if (Integer.parseInt(constraint) < 0) {
					
					tempEvent = toEvent;
					toEvent = fromEvent;
					fromEvent = tempEvent;
					
				}

				Matrix m = new Matrix();
				//Add toEvent to arraylist event
				event.add(toEvent);
				//Add fromEvent to arraylist event
				event.add(fromEvent);
				//Method call to prepareMatrix() in class "Matrix"
			    m.prepareMatrix(toEvent, fromEvent, event, sched, Integer.parseInt(constraint));

			}

		}

	}

	//Used to display error messages to inform the user
	public void jOptionWarning(String warning, String title) {
		
		//New JOptionPane object created with title passed in as its string argument
		JOptionPane optionPane = new JOptionPane(title, JOptionPane.ERROR_MESSAGE);
		//Create dialog box passing in warning as its string argument
		JDialog dialog = optionPane.createDialog(warning);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

}
