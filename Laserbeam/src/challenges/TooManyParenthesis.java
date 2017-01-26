
//Author: Eric Agocs
//Purpose: program challenge

import java.util.Scanner;
import java.util.Stack;

public class TooManyParenthesis {

	public static void main(String[] args) {

		begin();

	}

	public static void begin() {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a parenthesis'd statement.");
		String str = scan.nextLine();
		System.out.println("\nResults: " + process(str).toString());

	}

	public static StringBuilder process(String str) {

		int leftparen = 0;
		int rightparen = 0;
		
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < str.length(); i++) {

			char s = str.charAt(i);

			switch (s) {

			case ')': {
								
				rightparen++;

				break;
			}

			case '(': {

				leftparen++;
				
				break;
			}

			default: {

				break;
			}

			} //switch
		} //for loop
		
		
		
		
		while (!stack.empty()) {
			
			switch (stack.peek().charValue()) {

			case ')': {
				
				// edge case for a right ) at the beginning
				if (stack.size() == 1) {
					sb.append("**)**");
					stack.pop();
				}
				
				
				break;
			}

			case '(': {

				// edge case for a left ( at the end
				if (stack.size() == str.length()) {

					sb.append("**(**");
					stack.pop();
				}

				break;
			}

			default: {
				
				sb.append(stack.pop());
				break;
			}

			}
		}
	
		if (leftparen == rightparen) {
			
			System.out.println("Balanced equation.");
		}
		
		else System.out.println("Imbalanced equation.");	
		
		return sb.reverse();  	
    	
    } //process()
}
