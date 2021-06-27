package cpw;
import java.util.Arrays;


public class dbOrganizer {

	private static Integer cntD=1;
	private static Integer cntS=1;
	private static Integer cntO=1;

	public static void Indexfinder(Ship tgt, Ship[] Ships) {
		System.out.print(tgt.getName()+" is ");
		int beg=0, end=Ships.length-1, mid=(end-beg)/2;
		if(Ships[beg]==tgt) System.out.print("at index " + beg);
		else if(Ships[end]==tgt) System.out.print("at index " + end); 
		else {
			while(beg!=end) {
				if(Ships[mid]==tgt){
					System.out.print("at index " + mid); 
					break;
				}
				else if(tgt.getName().compareTo(Ships[mid].getName())<0) end=mid;
				else beg=mid;
			}
			if(beg==end) System.out.print("not in this list");
		}
	}

	public static void mergeSort(Ship[] Ships) {
		if(Ships.length<2) return;

		/*Step1) Instantiate:
			beginning position of the original array, index-0, 
			end position of the original array
			midpoint at which to split the original array
		*/
		int beg=0, end=Ships.length-1, mid=(beg+end)/2;
		
		//Let the two new arrays hold each of those divided original array at the mid point
		Ship[] left =  Arrays.copyOfRange(Ships, beg, mid+1); 
		Ship[] right =  Arrays.copyOfRange(Ships, mid+1, end+1); 

		// Checker for Division Process
		System.out.println("Divide -"+cntD+"-");

		System.out.print(" Divided Left : ");
		cntD++;
		
		printArray(left);
		System.out.println("");
		System.out.print(" Divided right: ");
		printArray(right);
		System.out.println("");
		System.out.println("");
		
		//Step2) Going through the same process recursively till the whole Ships array is conquered
		mergeSort(left);
		mergeSort(right);

		
		// Checker for Sorting Process
		System.out.println("Sort -"+cntS+"-");
		System.out.print(" Sorted left   :");
		printArray(left);
		System.out.println("");
		System.out.print(" Sorted right  :");
		printArray(right);
		System.out.println("");
		System.out.println("");
		cntS++;
		
		//Step3) Start merge the divided arrays once all sub array's length reach <2
		merge(Ships, left, right);
	}

	
	public static void merge(Ship[] Ships, Ship[] left, Ship[] right) {
		int i=0, j=0, k=0;
		
		// Step1) Instantiating temp array to hold the sorted sequence of ships
		Ship[] temp=new Ship[left.length+right.length];
		
		/* Step2) Looping over the left and right arrays at once
		 * Comparing each element from the younger indexes
		 * Plugging in the alphabetically superior(younger) Ship Name to the Ship array created in  Step1)
		 */

		System.out.println("Merge -"+ cntO +"-");
		while(i<left.length & j<right.length) {
			String leftName =left[i].getName();
			String rightName=right[j].getName();
			System.out.println(" Compare: " + leftName + " vs " + rightName);
			
			//If the Ship Name String on the left array is alphabetically prior, then plug it into the master array to return(Ships)
			if(leftName.compareTo(rightName)<0) {
				temp[k++]=left[i++];
			}
			else {
				temp[k++]=right[j++];
			}
		}

		// Step3) Adding remaining ships in the left and right array, if any
		if(i<left.length|j<right.length) { 
			System.out.print(" Add Leftover: ");
		}
		while(i<left.length) {
			System.out.print(left[i].getName() + " ");
			temp[k++]=left[i++];
		}
		while(j<right.length) {
			System.out.print(right[j].getName() + " ");
			temp[k++]=right[j++];
		}
		System.out.println("");
		
		// Step4) Copying the sorted sub-array output to the parent array "Ships"
		System.arraycopy(temp, 0, Ships, 0, Ships.length);

		//Checking the output merged array
		System.out.print(" Output" + cntO +": ");
		cntO++;
		printArray(Ships);
		System.out.println("");
		System.out.println("");
	}	
	
	public static void printArray(Ship[] printed) {
		for(Ship s: printed) {
			System.out.print(s.getName() + " ");
		}
	}
	
	public static void main(String[] args) {
		/* Hard-code register of ships and cargoes
		 * These three ships and cargoes will be paired and computed their shipping cost
		 */
		Cargo A= new Cargo("A", 50);
		Cargo B= new Cargo("B", 10);
		Cargo C= new Cargo("C", 10);
		Cargo D=new Cargo("D",10);

		Ship V= new Ship("Vin", 10);
		Ship X= new Ship("Xavier", 100);
		Ship Y= new Ship("Yale", 150);
		Ship Z= new Ship("Zenice", 10);
		
		// Making an array of cargoes
		Cargo[] cargoes = new Cargo[] {A, B, C, D};

		//Making an array of ships 
		Ship[] Ships = new Ship[] {Z, Y, X, V};

		System.out.print("Pre-Sort List: ");
		printArray(Ships);
		System.out.println("");
		System.out.println("");
		mergeSort(Ships);

		System.out.print("Sorted List: ");
		printArray(Ships);
		System.out.println("");
		System.out.println("");
		
		//invoking index-finder to visualize where a ship is located in the sorted array
		Indexfinder(X, Ships);
	}

}
