package utilities;

public class Shoe {
	int shoeID;
	String shoeName;
	double price;	//individual price
	double shoeSize;
	int quantity;
	private int increasedQuantity;
	
	public Shoe(int shoeID, String shoeName, double indPrice, double shoeSize, int quantity) {
		this.shoeID = shoeID;
		this.shoeName = shoeName;
		this.price = indPrice;
		this.shoeSize = shoeSize;
		this.quantity = quantity;
	}
	
	//accessors
	public int getID() {
		return this.shoeID;
	}
	public String getName() {
		return this.shoeName;
	}
	public double getPrice() {
		return this.price;
	}
	public double getSize() {
		return this.shoeSize;
	}
	public int getQuantity() {
		return this.quantity;
	}

	//modifiers
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void increaseQuantityBy(int increasedQuantity) {
		this.quantity += increasedQuantity;
	}
}
