public class Event {
	
	private int toEvent;
	private int fromEvent;
	private int constraint;
	
	//empty constructor
	public Event() {
		
		
	}

	//full constructor
	public Event(int cord1, int cord2, int constraint) {
		
		this.toEvent = cord1;
		this.fromEvent = cord2;
		this.constraint = constraint;
	}
	

	/**
	 * 
	 * Unused toString()
	 * 
	 **/
	 
	public String toString(int c, String t, String f) {
		
		String alteredText = "";
		
		if (c < 0) {
			alteredText = " no more than ";
		}
		
		else if (c > 0) {
			alteredText = " at least ";
		}
		
		return "Event " + t + " must precede " + f 
				+ " by" + alteredText + Math.abs(c) + " minutes.";
	
	}

	public int getCord1() {
		return toEvent;
	}

	public int getCord2() {
		return fromEvent;
	}

	public int getConstraint() {
		return constraint;
	}
}
