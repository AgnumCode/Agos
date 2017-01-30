package cpu;

import java.util.ArrayList;

//Eric Agocs
//Spring 2016 ITEC371
//http://www.radford.edu/~itec371/2016spring-ibarland/Homeworks/cpusim/cpusim.html

public class CpuSim {
	
	//The three queues for the multiqueue construct
	final static ArrayList<Cpu> fore = new ArrayList<Cpu>();
	final static ArrayList<Cpu> inter = new ArrayList<Cpu>();
	final static ArrayList<Cpu> back = new ArrayList<Cpu>();
	//*********************************************
	//foreground quantum
	final static int foreQuantum = 8;
	//intermediate quantum
	final static int interQuantum = 16;
	//value to check for if jobs are left to simulate
	static boolean simulate = true;
	//number of jobs
	static int numJobs = 3;

	/*******************************************************************/
	public static void main(String[] args) {
				 
		//random seed
		int seed = 17;
		//λ
		double jobFreq = 0.25;
		//randomize job arrivals
		long jobArrival = (long) (-Math.log(Math.random())/jobFreq);
		
		//begin simulation
		Simulate(jobArrival, seed);

	} //End main
	
	
	//Simulate() simulates the schedule and multi-level queue
	//Job arrival is when the next job is put on the queue 
	//Seed is the value to seed the random generator in job arrival
	public static void Simulate(long jobArrival, int seed) {
			    	
		//while jobs != 0, simulate job creation and processing
		//see line 89 for how this loop terminates
		while (simulate) {
			
			//randomize job length
			double jobLength = (Math.random() * 5) * seed;

			//Sleep to simulate varying job arrivals
			try { Thread.sleep((jobArrival)); } 
			catch (InterruptedException e) { e.printStackTrace(); } 

			//add a new job onto the foreground queue
			fore.add(new Cpu(jobLength));
			
			//execute the multi level queue
			foreQ();
			interQ();
			backQ();		
			
		} //end while loop
	} //End simulate()
	

	//foreground queue function
	//simulates forground queue
	//takes nothing, returns nothing
	public static void foreQ() {
	
		//iterate through all jobs in foreground queue
		for (int i = 0; i < fore.size(); i++) {

			//check if a job is predicted to finish within the current queue 
			if (fore.get(i).getWorkLeft() <= foreQuantum) {
				//process the job
				try { Thread.sleep((long)fore.get(i).getWorkLeft()); } 
				catch (InterruptedException e) { e.printStackTrace(); }
				//status printout
				System.out.println("A job completed in the foreground queue."); 
				//remove job from foreground queue
				fore.remove(fore.get(i));
				//decrement the jobs left
				numJobs--;
				//if no jobs are left, exit program and complete
				if (numJobs == 0) {
					simulate = false;
					break;
				} //end if

			} //end if
			
			else try {
				
				//process job for as long as the foreground quantum allows
				Thread.sleep(foreQuantum);
				//subtract off what was processed for the job from the work left
			    fore.get(i).setWorkLeft((int) (fore.get(i).getWorkLeft() - foreQuantum)); 
			    //move job from foreground queue to intermediate queue
			    inter.add((Cpu) fore.get(i));
			    //status printout
				System.out.println("A job was moved to the intermediate queue with work left: " + fore.get(i).getWorkLeft() + "ms.");
				//remove job from foreground queue
			    fore.remove(fore.get(i)); }
			    catch (InterruptedException e) { e.printStackTrace(); }
			
			} //end for loop	
	} //end foreQ() 
	
	//intermediate queue function
	//simulates intermediate queue
	//takes nothing, returns nothing
	public static void interQ() {
				
		//iterate through all jobs in intermediate queue
		for (int i = 0; i < inter.size(); i++) {

			//check if a job is predicted to finish within the current queue 
			if (inter.get(i).getWorkLeft() <= interQuantum) {
				//process the job
				try { Thread.sleep((long)inter.get(i).getWorkLeft()); } 
				catch (InterruptedException e) { e.printStackTrace(); }
				//status printout
				System.out.println("A job completed in the intermediate queue."); 
				//remove job from intermediate queue
				inter.remove(inter.get(i));
				//decrement the jobs left
				numJobs--;
				//if no jobs are left, exit program and complete
				if (numJobs == 0) {
					simulate = false;
					break;
					
				} //end if
			} //end if
			
			else try {

				//process job for as long as the intermediate quantum allows
				Thread.sleep(interQuantum);
				//subtract off what was processed for the job from the work left
			    inter.get(i).setWorkLeft((int) (inter.get(i).getWorkLeft() - interQuantum)); 
			    //move job to background queue
			    back.add((Cpu) inter.get(i));
			    //status printout
				System.out.println("A job was moved to background queue with work left: " + inter.get(i).getWorkLeft() + "ms.");
				//remove job from intermediate queue
			    inter.remove(inter.get(i)); }
			    catch (InterruptedException e) { e.printStackTrace(); }
			
			} //end for loop	
		} //end interQ()
	
	//background queue function
	public static void backQ() {
		
		//iterate through all jobs in background queue
		for (int i = 0; i < back.size(); i++) {

			//sleep the current job in background queue for as long as it wants
			try { Thread.sleep((long)back.get(i).getWorkLeft()); } 
			catch (InterruptedException e) { e.printStackTrace(); }
			//status printout
			System.out.println("A job was completed in the background queue which took: " + back.get(i).getWorkLeft() + "ms.");
			//remove the job from the background queue
			back.remove(back.get(i));
			//decrement the jobs left
			numJobs--;
			//if no jobs are left, exit program and complete
			if (numJobs == 0) {
				simulate = false;
				break;	
				
				} //end if
			} //end for loop
		} //end backQ()
	
	//CPU class which holds and organizes work data on jobs
	public static class Cpu {
		
		//the work (in ms) left for a job to be executed for
		private double workLeft;
		
		//empty constructor
		public Cpu() {
			
		}
		
		//full constructor
		public Cpu(double _workLeft) {

			workLeft = _workLeft;

		}
		
		public void setWorkLeft(double workLeft) {
			this.workLeft = workLeft;
		}

		public double getWorkLeft() {
			return workLeft;
		}

	} //End Cpu

} //End CpuSim


/*
 * Thoughts and stuff:
 * 
 * Small values of lambda (<0.1) causes arrival times to increase.
 * Large values of lambda (>0.1) causes arrival times to decrease
 * 
 * Strengths of this schedule: finishes short jobs quickly
 * Weaknesses of this schedule: no priority or aging on jobs to accommodate important jobs
 * 
 * Program does not "interrupt" to accommodate new jobs. Instead, each job is processed until they are terminated.
 * 
 */

