package cpw;

import java.sql.Date;

public class Cargo {

	private  int weight;
	private String name;
	private String status;
	private String location;
	private Ship shipOnboard;
	private static int counter =0;
	private int serial;
	private int DDY; //Deadline Year
	private int DDM; //Deadline Month
	private int DDD; //Deadline Day
	private String deadline;
	private Port orign;
	private Port dest;

	public Cargo(String name, int weight){
		this.name=name;
	    this.weight=weight;
	    this.status="Unknown";
	    this.location="Unknown";
		serial = ++counter-1;
		deadline= "DD is not set";

	}
	// Returns status of a cargo
	public String getStatus() {
		return status;
	}
	public int getSerial() {
		return serial;
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
	// Returns a name of a cargo
	public String getDD() {
		return deadline;
	}
	// Get Origin 
	public Port getorign() {
		return orign;
	}
	// Get Destination 
	public Port getDest() {
		return dest;
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
	//Change the origin of cargo
	public void changeOrign(Port orign) {
		this.orign=orign;
		System.out.println("The cargo origination is updated to " + orign.getName());
	}
	//Change the destination of cargo
	public void changeDest(Port dest) {
		this.dest=dest;
		System.out.println("The cargo destination is updated to " + dest.getName());
	}
	//Change the name of cargo
	public void updateDD(int DDY, int DDM, int DDD) {
		this.DDY=DDY;
		this.DDM=DDM;
		this.DDD=DDD;
		this.deadline=String.valueOf(DDY)+"/"+String.valueOf(DDM)+"/"+String.valueOf(DDD);
		System.out.println("The cargo name is updated to " + deadline);
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

		System.out.println("Cargo " + cgo.getName()+ "'s serial # is: "+ cgo.getSerial());
		Cargo cgo2= new Cargo("B", 50);
		System.out.println("Cargo " + cgo2.getName()+ "'s serial # is: "+ cgo2.getSerial());
		Cargo cgo3= new Cargo("C", 50);
		System.out.println("Cargo " + cgo3.getName()+ "'s serial # is: "+ cgo3.getSerial());
		
		System.out.println(cgo.getDD());
		cgo.updateDD(2021, 8, 11);
		System.out.println(cgo.DDM);
		cgo.updateDD(2021, 9, 11);
		System.out.println(cgo.DDM);
}

}

