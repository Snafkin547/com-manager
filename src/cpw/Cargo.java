package cpw;

public class Cargo {

	private  int weight;
	private String name;
	private String status;
	private String location;
	private Ship shipOnboard;
	
	public Cargo(){
	}
	public Cargo(String name, int weight){
		this.name=name;
	    this.weight=weight;
	    this.status="Unknown";
	    this.location="Unknown";
	    
	}
	// Returns status of a cargo
	public String getStatus() {
		return status;
	}
	// Returns location of a cargo
	public String getLocation() {
		return location;
	}
	// Returns weight of a cargo
	public int getWeight() {
		return weight;
	}
	// Returns a name of a cargo
	public String getName() {
		return name;
	}
	//Change the status of cargo
	public void updateStatus(String newStatus) {
		this.status=newStatus;
		System.out.println("The cargo status is updated to " + status);
	}
	// Change the status of cargo
	public void updateLocation(String newLocation) {
		this.location=newLocation;
		System.out.println("The cargo location is updated to " + location);
	}	
	// Change the weight of cargo
	public void updateWeight(int newWeight) {
		this.weight=newWeight;
		System.out.println("The cargo weight is updated to " + weight);
	}
	//Change the name of cargo
	public void changeName(String newName) {
		this.name=newName;
		System.out.println("The cargo name is updated to " + name);
	}
	
	public void linkShip(Ship s) {
		shipOnboard=s;
		System.out.println(name+" is linked to " + shipOnboard.getName());
		updateStatus("Linked");
		updateLocation("On Board");
	}
	
	public Ship ShipOnBoard() {
		return shipOnboard;
	}
	
	public static void main(String[] args) {
		/* Testing if above definitions work
		 */
		Cargo cgo= new Cargo("A", 50);
		System.out.println("Cargo" + cgo.getName()+ " is " + cgo.getWeight() + " MT" + " - status: "+cgo.getStatus()+ " - Location: "+cgo.getLocation());
		
		cgo.changeName("XXX");
		cgo.updateWeight(100);
		cgo.updateStatus("Cancelled");
		System.out.print("New name for Cargo is " + cgo.getName()+" and weighs: " + cgo.getWeight() + " MT" + " - status: "+cgo.getStatus() + " - Location: "+cgo.getLocation());
		Ship X= new Ship("X", 100);
		
		cgo.linkShip(X);
		
		System.out.println("Cargo " + cgo.getName()+ " is on Ship "+ cgo.ShipOnBoard().getName());

	}

}

