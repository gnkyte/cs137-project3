package utilities;

public class ShoeHistory {
	private int shoeID;
	private String shoeName;
	private String imagePath;
	
	public ShoeHistory(int shoeID, String shoeName, String imagePath) {
		this.shoeID = shoeID;
		this.shoeName = shoeName;
		this.imagePath = imagePath;
	}
	
	//accessors
	public int getID() {
		return this.shoeID;
	}
	public String getName() {
		return this.shoeName;
	}
	public String getImagePath() {
		return this.imagePath;
	}
}
