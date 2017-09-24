package personal;

public class Selfdescribing {

	public static void main(String[] args) {

		Integer number = 10000000;

		while (number.toString().length() < 10) {

			checkNumber(number);
			number++;

		}

	}

	private static void checkNumber(Integer number) {

		char[] n = number.toString().toCharArray();
		boolean candidate = true;

		int t0 = 0;
		int t1 = 0;
		int t2 = 0;
		int t3 = 0;
		int t4 = 0;
		int t5 = 0;
		int t6 = 0;
		int t7 = 0;

		//number of occurences of a number in a string of numbers
		//1 2 3 6
		//ex: one 1s, one 2s, one 3s, one 6s
		int n0 = 0;
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		int n5 = 0;
		int n6 = 0;
		int n7 = 0;

		while (candidate) {

			for (int i = 0; i < n.length; i++) {

				t0 = Character.getNumericValue(n[0]);
				t1 = Character.getNumericValue(n[1]);
				t2 = Character.getNumericValue(n[2]);
				t3 = Character.getNumericValue(n[3]);
				t4 = Character.getNumericValue(n[4]);
				t5 = Character.getNumericValue(n[5]);
				t6 = Character.getNumericValue(n[6]);
				t7 = Character.getNumericValue(n[7]);

				for (char x : n) {

					switch (Character.getNumericValue(x)) {
					
					case 0: n0++;
					case 1: n1++;
					case 2: n2++;
					case 3: n3++;
					case 4: n4++;
					case 5: n5++;
					case 6: n6++;
					case 7: n7++;

				}

				if (t0 == n0 &&
					t1 == n1 && 
					t2 == n2 && 
					t3 == n3 && 
					t4 == n4 && 
					t5 == n5 && 
					t6 == n6 && 
					t7 == n7) 
				{

					System.out.println("Number: " + number + " is a solution.");
				}

				else candidate = false; 
				break;
				
				} //END FOR #1

			} //END FOR #2

		} //END WHILE
		
	} //END CHECKNUMBER()

} // end
