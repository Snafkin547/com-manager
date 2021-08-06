package cpw;

import java.util.LinkedList;
import java.util.List;

public class Storage {

	private  int capacity;
	private  int avail;
	private  int inside;
	private  int cost;
	private String name;
	private String status;
	private String location;
	private LinkedList<Cargo> items;  // Items inside the storage
	private LinkedList<String> allowed;
	private static int counter =0;
	private int serial;
	
	public Storage(){
	}
	public Storage(String name, int cost, int capacity){
		this.name=name;
	    this.capacity=capacity;
	    this.cost=cost;
	    this.status="Unknown";
	    this.location="Unknown";
	    this.avail=capacity;
	    this.items=new LinkedList<Cargo>();
	    this.allowed=new LinkedList<String>();
		serial = ++counter-1;
	}
	// Returns status of a storage
	public String getStatus() {
		return status;
	}
	public int getSerial() {
		return serial;
	}
	// Returns location of a storage
	public String getLocation() {
		return location;
	}
	// Returns weight of a storage
	public int getCapacity() {
		return capacity;
	}
	// Returns a name of a storage
	public String getName() {
		return name;
	}
	// Returns cost of a storage
	public int getCost() {
		return cost;
	}
	// Returns available space of a storage
	public int getAvail() {
		return avail;
	}
	// Prints a list of acceptable items
	public void getAllowed() {
		System.out.print("Accepted items are: ");
		for(int i=0;i<allowed.size();i++) {
			System.out.print(allowed.get(i)+" ");
		}
		System.out.println("");
	}
	public void getItems() {
		System.out.print("Items inside the storage "+name+" is: ");
		for(int i=0;i<items.size();i++) {
			System.out.print(items.get(i).getName()+" ");
		}
		System.out.println("");
	}
	// Change the status of storage
	public void updateStatus(String newStatus) {
		this.status=newStatus;
		System.out.println("Status is updated to: "+status);
	}
	//Change the status of storage
	public void updateLocation(String newLocation) {
		this.location=newLocation;
		System.out.println("Location is updated to: "+location);

	}	
	// Change the Capacity of storage
	public void updateCapacity(int newCapacity) {
		if(avail>=newCapacity-inside) {
			this.avail=newCapacity-inside;
			this.capacity=newCapacity;
			System.out.println("The storage capacity is changed to " + capacity+"mt");

		}
		else {
			System.out.println("The new Capacity must contain an error");
			System.out.println("The new Capacity must contain an error " + newCapacity+" < "+inside);
		}
	}
	//Change the name of cargo
	public void changeName(String newName) {
		this.name=newName;
		System.out.println("The storage name is changed to " + name);
	}
	
	// Change the cost of storage
	public void changeCost(int newCost) {
		this.cost=newCost;
	}
	// Update the available space
	public void updateAvail(int use) {
		this.avail-=use;
	}
	
	// Set or Change the overall item list allowed in the storage
	public void setAllowedItems(String[] allowedItems) {
		for(String item : allowedItems) {
			allowed.add(item);
		}
	}
	public void addAllowedItems(String newItem) {
		allowed.add(newItem);
		System.out.println(newItem+" Added to allowed item list of "+name);
	}
	public void removeAllowedItems(String removeItem) {
		allowed.remove(removeItem);
		System.out.println(removeItem+" Removed from allowed item list of "+name);
	}
	
	public void addItems(Cargo newItem) {
		if(allowed.contains(newItem.getName())) {
			if(avail>=newItem.getWeight()) {
				items.add(newItem);
				avail-=newItem.getWeight();
				inside+=newItem.getWeight();
				System.out.println(newItem.getName()+" Added to storage "+name + " - "+newItem.getWeight()+"mt - "+ avail +"mt available Capacity");
			}
			else System.out.println("Insufficient Capacity to add "+newItem.getName());
			if(avail==0) {
				updateStatus("Full");
			}
		}
		else System.out.println(newItem.getName()+" is not allowed in this storage");
	}
	public void removeItems(Cargo removeItem) {
		items.remove(removeItem);
		avail+=removeItem.getWeight();
		inside-=removeItem.getWeight();
		System.out.println(removeItem.getName()+" Removed from storage "+name+ " + "+removeItem.getWeight()+"mt - "+ avail +"mt available Capacity");
		if(avail!=0) {
			updateStatus("Available");
		}
	}
	public static void main(String[] args) {
		/* Testing if above definitions work
		 */
		Storage stg = new Storage("P", 100, 1000);
		System.out.println("Storage " + stg.getName()+ " is " + stg.getCapacity() + " mt " + " - status: "+stg.getStatus()+ " - available Space: "+stg.getAvail() + " mt");
		System.out.println("");
		//test if allowed items work
				
		stg.changeName("Q");
		stg.updateCapacity(70);
		stg.updateStatus("Available");
		System.out.println("New name for Storage is " + stg.getName()+" and capacity: " + stg.getCapacity() + " mt" + " - status: "+stg.getStatus() + " - Avail Space: "+stg.getAvail() + " mt");
		
		stg.addAllowedItems("A");
		stg.addAllowedItems("B");
		stg.addAllowedItems("C");
		stg.getAllowed();
		
		stg.removeAllowedItems("Wheat");
		stg.getAllowed();
		
		//Test if new cargoes are registered to the storage properly
		Cargo A= new Cargo("A", 50);
		Cargo B= new Cargo("B", 10);
		Cargo C= new Cargo("C", 10);
		Cargo D= new Cargo("D", 10);
		
		System.out.println(""); 
		System.out.println("Capacity is: "+ stg.getCapacity());
		System.out.println("Available Capa is: "+ stg.getAvail());
		stg.addItems(A);
		stg.addItems(B);
		stg.addItems(C);
		stg.addItems(D);
		stg.getItems();
		System.out.println("Available Capacity is: "+ stg.getAvail()+ " - status: "+stg.getStatus());
		
		stg.removeItems(C);
		stg.getItems();
		stg.getCapacity();
		System.out.println("Available Capacity is: "+ stg.getAvail()+ " - status: "+stg.getStatus());
		
	}

}

