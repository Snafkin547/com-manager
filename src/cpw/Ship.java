package cpw;

public class Ship {

	private  int cost;
	private String name;
	private String status;
	private double lon;
	private double lat;
	private double[] location;
	private Cargo cargoOnboard;
	private static int counter =0;
	private int serial;
	private int EYA; //ETA Year
	private int EMA; //ETA Month
	private int EDA; //ETA Day
	private String ETA;
	private Port dest;

	
	public Ship(String name, int cost){
		this.name=name;
    	this.cost=cost;
		serial = ++counter-1;
		this.location= new double[2];
		ETA= "ETA is not set";
	}

	// Returns a cost of a ship
	public int getCost() {
		return cost;
	}
	// Returns status of a ship
	public String getStatus() {
		return status;
	}
	public int getSerial() {
		return serial;
	}
	// Returns location of a ship
	public double[] getLocation() {
		return location;
	}
	
	// Returns a name of a ship
	public String getName() {
		return name;
	}
	// Returns ship ETA
	public String getETA() {
		return ETA;
	}
	// Get Destination 
	public Port getDest() {
		return dest;
	}
	//Change the status of ship
	public void updateStatus(String newStatus) {
		this.status=newStatus;
		System.out.println("The ship status is updated to " + status);
	}
	// Change the status of ship
	public void updateLocation(double lat, double lon) {
		this.location[0]=lat;
		this.location[1]=lon;
		this.lat=lat;
		this.lon=lon;
		System.out.println("The ship location is updated to " + "lat: "+lat+" lon: "+lon);
	}	
	//Change the name of ship
	public void changeName(String newName) {
		this.name=newName;
		System.out.println("The ship name is updated to " + name);
	}
	//Change the name of ship
	public void updateETA(int EYA, int EMA, int EDA) {
		this.EYA=EYA;
		this.EMA=EMA;
		this.EDA=EDA;
		this.ETA=String.valueOf(EYA)+"/"+String.valueOf(EMA)+"/"+String.valueOf(EDA);
		System.out.println("The ship ETA is updated to " + ETA);	
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
	//Change the destination of cargo
	public void changeDest(Port dest) {
		this.dest=dest;
		System.out.println("The ship destination is updated to " + dest.getName());
	}
	
	//Get Distance with a ship
	public double getDistPort(Port p) {
		double dist=Math.pow(lon-p.getLocation()[1],2)+Math.pow(lat-p.getLocation()[0],2);
		dist=Math.sqrt(dist);
		return dist;
	}

	// Testing if above definitions work
	public static void main(String[] args) {
		Ship shippingCost= new Ship("X", 100);
		System.out.println("Ship " +shippingCost.getName()+" is $" + shippingCost.getCost() + " per kg");
		Cargo A= new Cargo("A", 50);
		shippingCost.linkCargo(A);
		System.out.println("Status of Cargo "+A.getName()+ " is " + A.getStatus());
		System.out.println("Cargo on " +shippingCost.getName()+" is "+shippingCost.cargoOnBoard().getName());

		Ship X = new Ship("X", 100);
		X.updateLocation(0, 0);
		Port P1 =new Port("P1",100,100);
		Port P2 =new Port("P2",0,0);
		System.out.println(X.getDistPort(P1));
		System.out.println(X.getDistPort(P2));
		
		X.updateETA(2021, 8, 11);
		X.getETA();
		
	}

}

