package utilities;

import java.util.ArrayList;

public class ShoppingCart {
	private double totalPrice;
	private int numItems;	//quantity of each shoes in cart
	private ArrayList<Shoe> shoes;
	
	public ShoppingCart() {
		totalPrice = 0;
		numItems = 0;
		shoes = new ArrayList<Shoe>();
	}
	
	public boolean isEmpty() {
		return numItems <= 0;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public ArrayList<Shoe> getShoes() {
		return shoes;
	}
	
	public void addToCart(int id, String name, double indPrice, double size, int quantity) {
		int indexOfShoe = getIndexOfShoe(id, size);
		if(shoeInCart(indexOfShoe)) {
			//update the quantity by increasing it
			shoes.get(indexOfShoe).increaseQuantityBy(quantity);
			totalPrice += indPrice*quantity;
			numItems += quantity;
		} else {
			//add the shoe
			Shoe s = new Shoe(id, name, indPrice, size, quantity);
			shoes.add(s);
			totalPrice += indPrice*quantity;
			numItems += quantity;
		}
	}
	
	public void removeFromCart(int id, double size) {
		int index = getIndexOfShoe(id, size);
		Shoe s = shoes.get(index);
		numItems -= s.getQuantity();
		totalPrice -= s.getPrice()*s.getQuantity();
		shoes.remove(index);		
	}

	private boolean shoeInCart(int index) {
		return index > -1;
	}
	
	private int getIndexOfShoe(int id, double size) {
		//assumes that the shoe already exists. otherwise return -1
		for(int i = 0; i < shoes.size(); ++i) {
			if(shoes.get(i).getID() == id && shoes.get(i).getSize()==size) {
				return i;
			}
		}
		return -1;
	}
	
	
}
