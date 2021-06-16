package cpw;

public class Cargo {

	private  int weight;
	private String name;

	public Cargo(){
	}
	public Cargo(String name, int weight){
		this.name=name;
	    this.weight=weight;
	}
	/* Returns weight of a cargo
	 * 
	 */
	public int getWeight() {
		return weight;
	}
	/* Returns a name of a cargo
	 * 
	 */
	public String getName() {
		return name;
	}
	
	public static void main(String[] args) {
		/* Testing if above definitions work
		 */
		Cargo cgo= new Cargo("A", 50);
		System.out.print("Cargo" + cgo.getName()+ " is " + cgo.getWeight() + " kg");
	}

}

