package shell;

//Eric Agocs
//May 2016
//ITEC380
//Command shell implementation with various functionality

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rush {

	public static void main(String[] args) {

		// Start program
		shellStart();

	}

	//Shell begins and runs the program until completion
	public static void shellStart() {
		
		// tracks and terminates the program loop
		// does not change value in program
		boolean run = true;
		// create arraylist of class rushclass
		ArrayList<Rushclass> rush = new ArrayList<Rushclass>();
		Scanner scan = new Scanner(System.in);
		// the command input by user
		String command = null;

		System.out.println("Rush command shell start, enter a command.");

		while (run) {

			// assume input was a command
			boolean isCommand = true;
			// display prompt
			System.out.print("Rush>: ");
			// scan line
			command = scan.nextLine();
			// split command into an array of elements
			String commands[] = command.split(" ");

			// switch functionality based on 1st argument
			switch (commands[0]) {

			// the exit command case
			// ****************************************************************
			case "exit": {

				// prompt
				System.out.println("Thanks for using rush.");
				System.exit(0);
			} // end case "exit"

				// the variable command case
				// ****************************************************************
			case "var": {

				// the input was not an executable
				isCommand = false;

				// case if user simply entered "var"
				if (commands.length == 1) {

					// case if the array is empty, prompt user
					if (rush.isEmpty()) {

						System.out.println("No shell-variables have been defined.");
					} // end if

					else {

						// iterate through the arraylist of all id's entered by
						// user and print
						for (Rushclass r : rush) {

							System.out.println(r.id + " = " + r.var);

						} // end for

					} // end else
				} // end if

				// case if user creates a variable without an assignment
				else if (commands.length == 2) {

					// add to rush arraylist
					rush.add(new Rushclass(commands[1], "", null));

				} // end else if

				// case if user creates a variable with an assignment
				else {

					// add to rush arraylist
					rush.add(new Rushclass(commands[1], commands[3], null));

				} // end else

				// Debug
				// System.out.println("New variable id: " + commands[1] + " set
				// to: " + commands[3]);

				break;
			} // end case "var"

			// the set variable command case
			// ****************************************************************
			case "set": {

				// the input was not an executable
				isCommand = false;

				// check for format of 4 arguments
				// format: x y = z
				if (commands.length == 4) {

					// tracks if the id was contained within the arraylist
					boolean contains = false;

					for (int i = 0; i < rush.size(); i++) {

						// check if the current element's variable id is equal
						// to the command's variable
						if (rush.get(i).id.equals(commands[1])) {

							// set the new variable
							rush.get(i).setVar(commands[3]);

							// Debug
							// System.out.println("Successfully set " +
							// rush.get(i).id + " to " + rush.get(i).var);

							// element was contained within the list, set true
							contains = true;
						} // end if
					} // end for

					// case if variable id was not found in the array list for
					// which to set
					if (contains == false) {
						System.out.println(commands[1] + ": " + "no such shell-variable.");
					} // end if

				} // end if

				else {

					// if 3 arguments were not present, warn user of malformed
					// input
					System.out.println("Error, missing argument.");
				} // end else

				break;

			} // end case "set"

			} // end switch

			// rush: /usr/bin/wc -l /etc/passwd

			// case if commands[0] was not a set or var argument
			if (isCommand) {

				// true if "&" was at end of command, assumed false
				boolean ampersand = false;
				// case if an optional arg was provided
				// this would imply input was 3 parts and commands[1]
				// would be the optional argument and commands[2] the argument
				// assumed false
				boolean optarg = false;

				//case if the input was followed by a &
				if (commands[commands.length - 1].equals("&")) {

					ampersand = true;
					
				} // end if

				//case if the input is a 3-tuple argument
				if (commands.length == 3) {

					optarg = true;

				} // end if
				
				//distinguish between optarg and arg of which with or without & tail
				try {

					if (optarg == false) {

						Process p = Runtime.getRuntime().exec(commands[0]);

						if (!ampersand) {
							p.waitFor();
						} // end if

					} // end if

					else if (optarg == true) {
						
						Process p = Runtime.getRuntime().exec(commands[0]);

						if (!ampersand) {
							p.waitFor();
						} // end if
					} // end else if

				} // end try

				catch (IOException | InterruptedException e) {
					System.out.println(commands[0] + ": Command not found.");
				} // end catch

			} // end if

		} // end while loop

		scan.close();

	} // end shellStart()

	// handles the data structure of Rush
	static class Rushclass {

		private String var;
		private String id;
		private String subst;

		// full constructor
		public Rushclass(String id, String var, String subst) {

			this.var = var;
			this.id = id;
			this.subst = subst;
		}

		// empty constructor
		public Rushclass() {
		}

		public String getVar() {
			return var;
		}

		public String getId() {
			return id;
		}

		public String getSubst() {
			return subst;
		}

		public void setSubst(String subst) {
			this.subst = subst;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setVar(String var) {
			this.var = var;
		}

	}

}
