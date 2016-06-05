package utilities;

public class Purchase {
	private String name;
	private String ship_address;
	private String bill_address;
	private String email;
	private String credit;
	
	public Purchase(){
		name = "";
		ship_address = "";
		bill_address = "";
		email = "";
		credit = "";
	}
	
	public  String getName(){
		return name;
	}
	
	public String getShip(){
		return ship_address;
	}
	
	public String getBill(){
		return bill_address;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getCredit(){
		return credit;
	}
	
	public void makePurchase(String [] params){
		name = params[0];
		credit = params[1];
		ship_address = params[2];
		bill_address = params[3];
		email = params[4];
	}
	
	public String getCreditEnd(){
		int index = credit.length()/4;
		String toReturn = credit.substring(index);
		return toReturn;
	}
}
