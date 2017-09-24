package jThreads;

import java.util.ArrayList;

//Eric Agocs
//Project 3
//Spring 2016
//http://www.radford.edu/~itec371/2016spring-ibarland/Homeworks/proj03/concurrent.html

//Note: summation has slight calculation error drift that scales with the number of threads

public class InvLogSum extends Thread {
	

	//**************************************************************************************************
	//Class Sum provides the functionality of retrieving the sum from the calculation
	static class Sum {

		private double sum;

		public double getSum() {

			return sum;

		}

		public void setSum(double sum2) {

			this.sum = sum2;

		}
		
	}
	
	//**************************************************************************************************
	//The summation information is provided by the class Summation
	static class Summation implements Runnable {
		
		private long lower;
		private Sum sumValue;
		private long increment;

		public Summation(long lower, long increment, Sum sumValue) {
			
			this.lower = lower;
			this.sumValue = sumValue;
			this.increment = increment;
		}

		
		//Performs the inverse log summation calculation sigma(lowerbound -> upperbound) of 1/logx
		//increment: the upper bound of summation
		//lower: the lower bound of summation
		public void run() {
			
			//Debug print statement, unused
			//System.out.println("Doing summation calculation from " + lower + " to " + increment);

			double sum = 0;
			for (long i = lower; i <= increment; i++) {
				
				sum += (1 / Math.log(i));	
				
			}
			
			//Debug print statement
			//System.out.println("Summation calculation done with result: " + sum);
			sumValue.setSum(sum);

		}
	}
	
	//**************************************************************************************************MAIN
	public static void main(String[] args) {
	
	//The number of threads to create
	//Default: 1
	int numThreads = 1;
	//The upper limit of the sum
	//Default value used is 2x10^6
	long upperBound = 2000000;
	
	//Check args
	//Args checking is hard coded to accept the number of threads first then the upperbound value
	if (args.length > 0) {
		
		numThreads = Integer.parseInt(args[0]);
		
		if (args.length > 1) {
			
			upperBound = Integer.parseInt(args[1]);
		}
		
	}

	

	//Execute run()
	//Also subtract 1 from upperbound
	run(upperBound - 1, numThreads);	
		
	}
	
	//**************************************************************************************************
	//run() creates the threads for the program to use and does the summation calculation
	//upperBound: the upperbound of the summation - 1
	//thr: array to hold threads
	//numThreads: number of threads used
	public static void run(long upperBound, int numThreads) {
		
		//Create thread arraylist of initial size numThreads
		ArrayList<Thread> thr = new ArrayList<Thread>();
		ArrayList<Sum> sm = new ArrayList<Sum>();
		//The lower limit of the sum
		long lowerBound = 2;
		//The intermediary lower bounds of the summation split across threads
		long increment = upperBound / numThreads;
		//The sum of the calculations
		double sum = 0;

		//Create and assign thread jobs
		for (int x = 0; x < numThreads; x++) {
			
			sm.add(new Sum());
			thr.add(new Thread((new Summation(lowerBound, increment+lowerBound, sm.get(x)))));
			lowerBound = lowerBound + increment;
			System.out.println("Beginning thread: " + x);

		}		
		
		//Start timer for calculation
		long startTime = System.nanoTime();			
		
		//Start threads
		for (Thread x: thr) {
			x.start();
		}
		
		//Join threads
		for (int x = 0; x < numThreads; x++) {
		    try {
		        thr.get(x).join();
		        System.out.println("Thread: " + x + " has finished.");
		        sum += sm.get(x).sum;

		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}

		//Calculate time difference
		long difference = System.nanoTime() - startTime; 
		
		
		//**************************************************************************************************
		//Print out results
		System.out.print(
			"\nSummation: " + sum +
		    "\nUpper limit of summation used: " + upperBound +
 			"\nTime taken: " + difference/1000000 + "ms" + 
			"\nLogical cores on machine: " + Runtime.getRuntime().availableProcessors() +
		    "\nThreads used: " + numThreads);			
	}
	
}