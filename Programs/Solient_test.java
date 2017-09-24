package shell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solient_test {

	public static void main(String[] args) {

		BufferedReader reader = null;
		ArrayList<Data> dataLines = new ArrayList<Data>();
		String file = "src/data.txt";	
		
		try {

			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {

				String[] textSplit = text.split(",");
				dataLines.add(new Data(textSplit[0], textSplit[1], textSplit[2], textSplit[3]));
				
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (reader != null) {
					
					reader.close();
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		} // End finally

		run(dataLines);

	} // End main
	
	public static void run(ArrayList<Data> dataLines) {
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

		System.out.println(
					
					  "What would you like to do? Enter number.\n" + 
					  "\n 1. Print all contacts starting with given letter." +
				      "\n 2. Find contact by email." +
					  "\n 3. Exit program.");

			switch (s.nextInt()) {

			case 1: {  System.out.println("Enter letter: ");
					   String letter = s.next();
					   printContactSorted(dataLines, letter); 
					   run(dataLines);
					}
			
			case 2: { System.out.println("Enter email: ");
					  String email = s.next();
					  findContactByEmail(dataLines, email);
					  run(dataLines);
			        }
			
			case 3: System.exit(0);

			} //End switch

		} // End run

	public static void printContactSorted(ArrayList<Data> data, String letter) {

		System.out.println();
		
		Collections.sort(data, new Comparator<Data>() {
			
	        public int compare(Data d1, Data d2) {
	        return d1.last_name.compareToIgnoreCase(d2.last_name);
	        
	        }});

		for (int i = 0; i < data.size(); i++) {

			if (((String) data.get(i).last_name.subSequence(0, 1)).equalsIgnoreCase(letter)) {

				System.out.println(data.get(i).toString());

			}

		}

		System.out.println();

	} // End printContactSorted
	
	public static void findContactByEmail(ArrayList<Data> data, String email) {
		
	  for (Data x: data) {

			if (email.equalsIgnoreCase(x.email)) {
				System.out.println(x + "\n");
				break;
			}
		}
	} // End findContactByEmail

	static class Data {

		private String first_name;
		private String last_name;
		private String email;
		private String phone;

		public Data() {

		}

		public Data(String first_name, String last_name, String email, String phone) {
			this.first_name = first_name;
			this.last_name = last_name;
			this.email = email;
			this.phone = phone;
		}

		public String toString() {
		
			return "Last: " + last_name + 
				   ", First: " + first_name + 
				   ", Phone: " + phone +
				   ", Email: " + email;
		}

		public String getFirst_name() {
			return first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public String getEmail() {
			return email;
		}

		public String getPhone() {
			return phone;
		}

	} // End Data class

} // End Solient_Test class

