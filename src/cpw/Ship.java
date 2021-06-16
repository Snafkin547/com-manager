package cpw;

public class Ship {

	private  int cost;
	private String name;
	
	public Ship(){
	}
	public Ship(String name, int cost){
		this.name=name;
    	this.cost=cost;
	}

	/* Returns a cost of a ship
	 * 
	 */
	public int getCost() {
		return cost;
	}
	/* Returns a name of a ship
	 * 
	 */
	public String getName() {
		return name;
	}
	/* Testing if above definitions work
	 */
	public static void main(String[] args) {
		Ship shippingCost= new Ship("X", 100);
		System.out.print("Ship " +shippingCost.getName()+" is $" + shippingCost.getCost() + " per kg");
	}

}

