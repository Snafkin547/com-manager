package cpw;

public class Ship {

	private  int cost;
	private String name;
	private String status;
	private String location;
	private Cargo cargoOnboard;
	
	public Ship(){
	}
	public Ship(String name, int cost){
		this.name=name;
    	this.cost=cost;
	}

	// Returns a cost of a ship
	public int getCost() {
		return cost;
	}
	// Returns status of a cargo
	public String getStatus() {
		return status;
	}
	// Returns location of a cargo
	public String getLocation() {
		return location;
	}
	
	// Returns a name of a ship
	public String getName() {
		return name;
	}
	
	//Change the status of ship
	public void updateStatus(String newStatus) {
		this.status=newStatus;
		System.out.println("The ship status is updated to " + status);
	}
	// Change the status of ship
	public void updateLocation(String newLocation) {
		this.location=newLocation;
		System.out.println("The ship location is updated to " + location);
	}	
	
	//Change the name of ship
	public void changeName(String newName) {
		this.name=newName;
		System.out.println("The ship name is updated to " + name);
	}
	
	public void linkCargo(Cargo c) {
		cargoOnboard=c;
		System.out.println(name+" is linked to " + cargoOnboard.getName());
		updateStatus("Linked");
		c.linkShip(this);
	}
	public Cargo cargoOnBoard() {
		return cargoOnboard;
	}
	
	
	// Testing if above definitions work
	public static void main(String[] args) {
		Ship shippingCost= new Ship("X", 100);
		System.out.println("Ship " +shippingCost.getName()+" is $" + shippingCost.getCost() + " per kg");
		Cargo A= new Cargo("A", 50);
		shippingCost.linkCargo(A);
		System.out.println("Status of Cargo "+A.getName()+ " is " + A.getStatus());
		System.out.println("Cargo on " +shippingCost.getName()+" is "+shippingCost.cargoOnBoard().getName());

	}

}

