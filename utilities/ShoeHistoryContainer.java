package utilities;

import java.util.LinkedList;
import java.util.Queue;

public class ShoeHistoryContainer {
	//Stores the last 5 shoes the user has seen
	final static int MAX_SHOES = 5; 
	
	private int count;
	//stores the shoeID
	private Queue<Integer> shoeIDs;
	private Queue<ShoeHistory> shoes;
	public ShoeHistoryContainer() {
		count = 0;
		shoeIDs = new LinkedList<Integer>();
		shoes = new LinkedList<ShoeHistory>();
	}
	
	public void insert(ShoeHistory sh) {
		if(!shoeIDs.contains(sh)) {
			if(count == MAX_SHOES) {
				//if there are already 5 shoes in the history,
				//then remove the oldest one and insert the new shoe.
				shoes.remove();
				shoes.add(sh);
				shoeIDs.remove();
				shoeIDs.add(sh.getID());
			} else {
				//If there are not 5 items, then you can just insert it
				shoeIDs.add(sh.getID());
				shoes.add(sh);
				count++;
			}
		}
	}
	
	public Queue<ShoeHistory> getShoes() {
		return shoes;
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	

}
