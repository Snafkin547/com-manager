package cpw;

public class Port {

	private String name;
	private String status;
	private double lon;
	private double lat;
	private double[] location;
	private static int counter =0;
	private int serial;
	
	public Port(){
		
	}
	
	public Port(String name, int lat, int lon){
		this.name=name;
		serial = ++counter-1;
		this.location= new double[2];
		this.location[0]=lat;
		this.location[1]=lon;
		this.lat=lat;
		this.lon=lon;
	}
	
	// Returns status of a port
	public String getStatus() {
		return status;
	}
	public int getSerial() {
		return serial;
	}
	// Returns location of a port
	public double[] getLocation() {
		return location;
	}
	
	// Returns a name of a port
	public String getName() {
		return name;
	}
	
	//Change the status of port
	public void updateStatus(String newStatus) {
		this.status=newStatus;
		System.out.println("The port status is updated to " + status);
	}
	// Change the status of port
	public void updateLocation(double lat, double lon) {
		location[0]=lat;
		location[1]=lon;
		System.out.println("The port location is updated to " + "lat: "+lat+" lon: "+lon);
	}	
	
	//Change the name of port
	public void changeName(String newName) {
		this.name=newName;
		System.out.println("The port name is updated to " + name);
	}
	
	//Get Distance with another port
	public double getDist(Port P2) {
		double dist=Math.pow(lon-P2.lon,2)+Math.pow(lat-P2.lat,2);
		dist=Math.sqrt(dist);
		return dist;
	}

	//Get Distance with a ship
	public double getDistShip(Ship s) {
		double dist=Math.pow(lon-s.getLocation()[1],2)+Math.pow(lat-s.getLocation()[0],2);
		dist=Math.sqrt(dist);
		return dist;
	}
	
	public static void main(String[] args) {
		Ship X = new Ship("X", 100);
		X.updateLocation(0, 0);
		Port P1 =new Port("P1",100,100);
		Port P2 =new Port("P2",0,0);
		System.out.println(P1.getDist(P2));
		System.out.println(P1.getDistShip(X));
	}
}
