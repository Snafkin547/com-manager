package cpw;

import java.util.LinkedList;
import java.util.List;

public class CargoMatching {
	
	public CargoMatching(){
	}
	

	/* Shipping Cost Calculator
	 * 
	 */
	public static int CostEst(Cargo A, Ship X) {
		return X.getCost()*A.getWeight();
	}
	
	/* Permutation of the ship List 
	 * If x, y ,z in a list, this will return a list with 6 different lists of every pattern of x,y,z
	 * Such as List0=[z, y, x] and List1=[y, z, x] 
	 * This is to be used for multiplications with cargo 
	 */
	
	public static List<List<Ship>> createShipList(List<Ship> obj){
		// Step 1) Initiating "res" list to hold all ship sequences
		List<List<Ship>> res = new LinkedList<List<Ship>>();

		// Step 2) Adding empty list inside "res" so it can make a first loop 
		res.add(new LinkedList<>());

		// Step 3) Looping over the list with ships(obj) in the original order
		for(int i=0;i<obj.size();i++) {
			// Step 4) Initiating ans list to hold permutated lists at each loop over "obj", x,y,z in this example, 
			//and eventually pass the outcome to "res" at step 7)
			List<List<Ship>> ans = new LinkedList<List<Ship>>();
			/* Step 5) Looping over existing list within res
			 It can loop over res even in the first loop because it was added an empty list at step 2)*/
			for(List<Ship> a: res) {
				/* Step 6) Looping over existing lists in res
				 * In the first parent loop(initiated in Step 3), the list is empty, but the second loop will pick a<X>, the third loop, will pick pick a<Y, X> and a<X, Y>
				 */
				for(int j=0; j<=a.size(); j++) {
					/*6-1) adding value(x,y,z) to "a" list, X is added in the first loop(initiated in Step 3)
					 * In the second parent loop it adds Y in front of X(index-0) and X is shifted to index-1
					 * In the following loop of this smallest loop, it adds Y after X(index-1)*/
					a.add(j,obj.get(i));
					//6-2) Copying "a" list to a separate list curr
					List<Ship> curr=new LinkedList<Ship>(a);
					//6-3) adding curr(containing only x in the 1st round and  to ans, which eventually will be passed to res
					ans.add(curr);
					a.remove(j);
					///At the end of the twice of this smallest loop, the list ans is holding two lists <Y, X> and <X, Y>, which is passed to the list res
				}
			}
			// Step 7) passing values inside ans to res because ans was created within the highest loop
			res = new LinkedList<>(ans);
			
			//The following code visualize how permutation res evolves. Since this was not part of permutation algorithm, non-trivial, it is omitted in the 1.6.3 screen shot.
			int p=i+1;
			System.out.println("Round: "+p);
			for(List<Ship> ss: res) {
				for(Ship s: ss) {
				System.out.print(s.getName());
				}
				System.out.println(" ");
			}
			System.out.println(" ");
		}			
		return res;
	}
	
	public static void visualize(List<List<Ship>> ss, List<Cargo> cargoes) {
		
		//Looping over both ship sequence list and extract one of lists of ships, e.g. [x, y, z]
				int min=Integer.MAX_VALUE;
				int index=0;
				for(int i=0;i<ss.size();i++) {
					int k=i+1;
					System.out.println("Scenario "+k);
					List<Ship> s=ss.get(i);
					int temp=0;
					//Sub looping over cargo list and the list of ships extracted above, in order to compute total cost of each pair of cagro and list
					for(int j=0;j<s.size();j++) {
						int cost=CostEst(cargoes.get(j),s.get(j));
						System.out.println(" Cargo "+cargoes.get(j).getName()+" and Ship " + s.get(j).getName()+" :$"+cost);
						temp+=cost;
					}
					System.out.println("");

					/* Comparing temp(total cost of ship-cargo pair at each loop)
					 * Update min if temp is smaller than the then-latest cheapest pair
					 * Also update index of the pair for visualization later
					 */
					
					if(min>temp) {
						min=temp;
						index=i;
					}
				}
				/*  Using the index and min values updated during the loop, this visualize the cheapest shipping plan
				 */
				int k=index + 1;
				System.out.println("Total cheapest shipping plan is: Scenario" + k);
				System.out.println(" ");
				System.out.println("  Cargo    Ship");
				for(int i=0;i<ss.get(index).size();i++) {
					System.out.println("  "+cargoes.get(i).getName() + "    and    "+ss.get(index).get(i).getName());
				}
				System.out.println(" ");
				System.out.println("  Total Cost $" + min);
		
	}
	/* Implementation (Explanations to follow at each detail)
	 */
	
	public static void main(String[] args) {
		/* Hard-code register of ships and cargoes
		 * These three ships and cargoes will be paired and computed their shipping cost
		 */
		Cargo A= new Cargo("A", 50);
		Cargo B= new Cargo("B", 10);
		Cargo C= new Cargo("C", 10);
		//Cargo D=new Cargo("D",10);

		Ship X= new Ship("X", 100);
		Ship Y= new Ship("Y", 150);
		Ship Z= new Ship("Z", 10);
		//Ship K= new Ship("K", 10);
		
		/* Making a list of cargoes
		 */
		List<Cargo> cargoes = new LinkedList<Cargo>();
		cargoes.add(A);	cargoes.add(B); cargoes.add(C); //cargoes.add(D);

		/* Making a list of ships 
		 */
		List<Ship> ships = new LinkedList<Ship>();
		ships.add(X); ships.add(Y); ships.add(Z); //ships.add(K);

		/* Making a list to hold lists of ships in different sequences
		 * In this implementation, there will be 6 (3!)
		 * This will be called ship sequence List in the following section
		 */

		List<List<Ship>> ss = new LinkedList<List<Ship>>();
		ss=createShipList(ships);
		
		//invoking visualize method with those lists created above
		visualize(ss, cargoes);
	}

}