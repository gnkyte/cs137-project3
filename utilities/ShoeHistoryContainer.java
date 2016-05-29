package utilities;

import java.util.LinkedList;
import java.util.Queue;

public class ShoeHistoryContainer {
	//Stores the last 5 shoes the user has seen
	final static int MAX_SHOES = 5; 
	
	private int count;
	private Queue<Integer> shoes;
	public ShoeHistoryContainer() {
		count = 0;
		shoes = new LinkedList<Integer>();
	}
	
	public void insert(int shoeID) {
		if(count == MAX_SHOES) {
			//if there are already 5 shoes in the history,
			//then remove the oldest one and insert the new shoe.
			shoes.remove();
			shoes.add(shoeID);
		} else {
			//If there are not 5 items, then you can just insert it
			shoes.add(shoeID);
			count++;
		}
	}
	
	

}
