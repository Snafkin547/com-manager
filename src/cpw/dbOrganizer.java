package cpw;
import java.util.ArrayList;
import java.util.Arrays;


public class dbOrganizer {

	private static Integer cntD;
	private static Integer cntS;
	private static Integer cntO;
	
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
	
	public static void IndexfinderC(Cargo tgt, Cargo[] Cargoes) {
		System.out.print(tgt.getName()+" is ");
		int beg=0, end=Cargoes.length-1, mid=(end-beg)/2;
		if(Cargoes[beg]==tgt) System.out.print("at index " + beg);
		else if(Cargoes[end]==tgt) System.out.print("at index " + end); 
		else {
			while(beg!=end) {
				if(Cargoes[mid]==tgt){
					System.out.print("at index " + mid); 
					break;
				}
				else if(tgt.getName().compareTo(Cargoes[mid].getName())<0) end=mid;
				else beg=mid;
			}
			if(beg==end) System.out.print("not in this list");
		}
	}
	
	public static void printArray(Ship[] printed) {
		for(Ship s: printed) {
			System.out.print(s.getName() + " ");
		}
	}
	
	public static void printArrayC(Cargo[] printed) {
		for(Cargo s: printed) {
			System.out.print(s.getName() + " ");
		}
	}
	
	public static void main(String[] args) {
		/* Hard-code register of ships and cargoes
		 * These three ships and cargoes will be paired and computed their shipping cost
		 */

		Ship V= new Ship("Vin", 10);
		Ship X= new Ship("Xavier", 100);
		Ship Y= new Ship("Yale", 150);
		Ship Z= new Ship("Zenice", 10);
		
		//Making an array of ships 
		Ship[] Ships = new Ship[] {Z, Y, X, V};

		/* Hard-code register of ships and cargoes
		 * These three ships and cargoes will be paired and computed their shipping cost
		 */
		Cargo A= new Cargo("A", 50);
		Cargo B= new Cargo("B", 10);
		Cargo C= new Cargo("C", 10);
		Cargo D= new Cargo("D", 10);

		// Making an array of cargoes
		Cargo[] cargoes = new Cargo[] {D, C, B, A};

		System.out.println("++++++++++++ Non-Threaded Operation ++++++++++++");
		System.out.print("Pre-Sort Cargo List: ");
		printArrayC(cargoes);
		System.out.println("");
		cntD=1;
		cntS=1;
		cntO=1;
				
		System.out.print("Pre-Sort Ship List: ");
		printArray(Ships);
		System.out.println("");
		System.out.println("");
		
		long startTime = System.nanoTime();
			mergeSort(Ships);
			mergeSortC(cargoes);
		long endTime = System.nanoTime();
		
        ///Printout Sorted List of the Cargo
		System.out.print("Sorted Cargo List: ");
		printArrayC(cargoes);
		System.out.println("");
		
        ///Printout Sorted List of the ships
		System.out.print("Sorted Ship List: ");
		printArray(Ships);
		System.out.println("");
		System.out.println("");
		
		//invoking index-finder to visualize where a ship is located in the sorted array
		Indexfinder(X, Ships);
		System.out.println("");
		IndexfinderC(A, cargoes);
		System.out.println("");
		System.out.println("");

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Non-Threaded took " + duration + " milliseconds");
		System.out.println("");
		
		///Parallel Operation Experimentation
		System.out.println("++++++++++++   Threaded Operation  ++++++++++++");
		//Making an array of ships 
		//Ship[] ShipsThread = new Ship[] {Z, Y, X, V};
		Ship[] ShipsT = new Ship[] {Z, Y, X, V};
		
		cntD=1;
		cntS=1;
		cntO=1;
		
		// Making an array of cargoes
		Cargo[] cargoesT = new Cargo[] {D, C, B, A};
		
		System.out.print("Pre-Sort Cargo List: ");
		printArrayC(cargoesT);
		System.out.println("");
		
		System.out.print("Pre-Sort Ship List: ");
		printArray(ShipsT);
		System.out.println("");
		System.out.println("");
		
		// Ship Sort Thread
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
    			mergeSort(ShipsT);
            }
        });
		// Cargo Sort Thread
        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
    			mergeSortC(cargoesT);
            }
        });
        // Actual Threaded operation begins here
        startTime = System.nanoTime();
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
        //    t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		endTime = System.nanoTime();
		// End of the Threaded operation
		
        ///Printout Sorted List of the ships
		System.out.print("Sorted Cargo List: ");
		printArrayC(cargoesT);
		System.out.println("");
		
        ///Printout Sorted List of the Cargo
		System.out.print("Sorted Ship List: ");
		printArray(Ships);
		System.out.println("");
		System.out.println("");
		
		//invoking index-finder to visualize where a ship/cargo is located in the sorted array
		Indexfinder(X, ShipsT);
		System.out.println("");
		IndexfinderC(A, cargoesT);
		System.out.println("");
		System.out.println("");


		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Parallel Operation took " + duration + " milliseconds");
	}



	public static void mergeSortC(Cargo[] Cargoes) {
		if(Cargoes.length<2) return;

		/*Step1) Instantiate:
			beginning position of the original array, index-0, 
			end position of the original array
			midpoint at which to split the original array
		*/
		int beg=0, end=Cargoes.length-1, mid=(beg+end)/2;
		
		//Let the two new arrays hold each of those divided original array at the mid point
		Cargo[] left =  Arrays.copyOfRange(Cargoes, beg, mid+1); 
		Cargo[] right =  Arrays.copyOfRange(Cargoes, mid+1, end+1); 

		
		//Step2) Going through the same process recursively till the whole Cargoes array is conquered
		mergeSortC(left);
		mergeSortC(right);
		
		//Step3) Start merge the divided arrays once all sub array's length reach <2
		mergeC(Cargoes, left, right);
	}
	
	public static void mergeC(Cargo[] Cargoes, Cargo[] left, Cargo[] right) {
		int i=0, j=0, k=0;
		
		// Step1) Instantiating temp array to hold the sorted sequence of Cargoes
		Cargo[] temp=new Cargo[left.length+right.length];
		
		/* Step2) Looping over the left and right arrays at once
		 * Comparing each element from the younger indexes
		  Plugging in the alphabetically superior(younger) Cargo Name to the Cargo array created in  Step1)*/
		while(i<left.length & j<right.length) {
			String leftName =left[i].getName();
			String rightName=right[j].getName();
			
			//If the Cargo Name String on the left array is alphabetically prior, then plug it into the master array to return(Cargoes)
			if(leftName.compareTo(rightName)<0) {
				temp[k++]=left[i++];
			}
			else {
				temp[k++]=right[j++];
			}
		}
		// Step3) Adding remaining Cargoes in the left and right array, if any
		while(i<left.length) {
			temp[k++]=left[i++];
		}
		while(j<right.length) {
			temp[k++]=right[j++];
		}
		// Step4) Copying the sorted sub-array output to the parent array "Cargoes"
		System.arraycopy(temp, 0, Cargoes, 0, Cargoes.length);
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

//	
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
}

	
//
//	public static void Indexfinder(Ship tgt, Ship[] Ships) {
//		System.out.print(tgt.getName()+" is ");
//		int beg=0, end=Ships.length-1, mid=(end-beg)/2;
//		if(Ships[beg]==tgt) System.out.print("at index " + beg);
//		else if(Ships[end]==tgt) System.out.print("at index " + end); 
//		else {
//			while(beg!=end) {
//				if(Ships[mid]==tgt){
//					System.out.print("at index " + mid); 
//					break;
//				}
//				else if(tgt.getName().compareTo(Ships[mid].getName())<0) end=mid;
//				else beg=mid;
//			}
//			if(beg==end) System.out.print("not in this list");
//		}
//	}
//
//	public static void main(String[] args) {
//		/* Hard-code register of ships and cargoes
//		 * These three ships and cargoes will be paired and computed their shipping cost
//		 */
//
//		Ship V= new Ship("Vin", 10);
//		Ship X= new Ship("Xavier", 100);
//		Ship Y= new Ship("Yale", 150);
//		Ship Z= new Ship("Zenice", 10);
//		
//		//Making an array of ships 
//		Ship[] Ships = new Ship[] {Z, Y, X, V};
//
//		System.out.print("Pre-Sort List: ");
//		printArray(Ships);
//		System.out.println("");
//		System.out.println("");
//		mergeSort(Ships);
//
//		System.out.print("Sorted List: ");
//		printArray(Ships);
//		System.out.println("");
//		System.out.println("");
//		
//		//invoking index-finder to visualize where a ship is located in the sorted array
//		Indexfinder(X, Ships);
//		
//		/*
//		 * Cargo Sort
//		 */
//				
//		System.out.println("");
//		/* Hard-code register of ships and cargoes
//		 * These three ships and cargoes will be paired and computed their shipping cost
//		 */
//		Cargo A= new Cargo("A", 50);
//		Cargo B= new Cargo("B", 10);
//		Cargo C= new Cargo("C", 10);
//		Cargo D=new Cargo("D",10);
//
//		// Making an array of cargoes
//		Cargo[] cargoes = new Cargo[] {D, C, B, A};
//
//		System.out.print("Pre-Sort List: ");
//		printArrayC(cargoes);
//		System.out.println("");
//		System.out.println("");
//		mergeSortC(cargoes);
//
//		System.out.print("Sorted List: ");
//		printArrayC(cargoes);
//		System.out.println("");
//		System.out.println("");
//		
//		//invoking index-finder to visualize where a ship is located in the sorted array
//		IndexfinderC(A, cargoes);
//		
//	}
//
//	public static void IndexfinderC(Cargo tgt, Cargo[] Cargoes) {
//		System.out.print(tgt.getName()+" is ");
//		int beg=0, end=Cargoes.length-1, mid=(end-beg)/2;
//		if(Cargoes[beg]==tgt) System.out.print("at index " + beg);
//		else if(Cargoes[end]==tgt) System.out.print("at index " + end); 
//		else {
//			while(beg!=end) {
//				if(Cargoes[mid]==tgt){
//					System.out.print("at index " + mid); 
//					break;
//				}
//				else if(tgt.getName().compareTo(Cargoes[mid].getName())<0) end=mid;
//				else beg=mid;
//			}
//			if(beg==end) System.out.print("not in this list");
//		}
//	}
//
//	public static void mergeSortC(Cargo[] Cargoes) {
//		if(Cargoes.length<2) return;
//
//		/*Step1) Instantiate:
//			beginning position of the original array, index-0, 
//			end position of the original array
//			midpoint at which to split the original array
//		*/
//		int beg=0, end=Cargoes.length-1, mid=(beg+end)/2;
//		
//		//Let the two new arrays hold each of those divided original array at the mid point
//		Cargo[] left =  Arrays.copyOfRange(Cargoes, beg, mid+1); 
//		Cargo[] right =  Arrays.copyOfRange(Cargoes, mid+1, end+1); 
//
//		// Checker for Division Process
//		System.out.println("Divide -"+cntD+"-");
//
//		System.out.print(" Divided Left : ");
//		cntD++;
//		
//		printArrayC(left);
//		System.out.println("");
//		System.out.print(" Divided right: ");
//		printArrayC(right);
//		System.out.println("");
//		System.out.println("");
//		
//		//Step2) Going through the same process recursively till the whole Cargoes array is conquered
//		mergeSortC(left);
//		mergeSortC(right);
//
//		
//		// Checker for Sorting Process
//		System.out.println("Sort -"+cntS+"-");
//		System.out.print(" Sorted left   :");
//		printArrayC(left);
//		System.out.println("");
//		System.out.print(" Sorted right  :");
//		printArrayC(right);
//		System.out.println("");
//		System.out.println("");
//		cntS++;
//		
//		//Step3) Start merge the divided arrays once all sub array's length reach <2
//		mergeC(Cargoes, left, right);
//	}
//
//	
//	public static void mergeC(Cargo[] Cargoes, Cargo[] left, Cargo[] right) {
//		int i=0, j=0, k=0;
//		
//		// Step1) Instantiating temp array to hold the sorted sequence of Cargoes
//		Cargo[] temp=new Cargo[left.length+right.length];
//		
//		/* Step2) Looping over the left and right arrays at once
//		 * Comparing each element from the younger indexes
//		 * Plugging in the alphabetically superior(younger) Cargo Name to the Cargo array created in  Step1)
//		 */
//
//		System.out.println("Merge -"+ cntO +"-");
//		while(i<left.length & j<right.length) {
//			String leftName =left[i].getName();
//			String rightName=right[j].getName();
//			System.out.println(" Compare: " + leftName + " vs " + rightName);
//			
//			//If the Cargo Name String on the left array is alphabetically prior, then plug it into the master array to return(Cargoes)
//			if(leftName.compareTo(rightName)<0) {
//				temp[k++]=left[i++];
//			}
//			else {
//				temp[k++]=right[j++];
//			}
//		}
//
//		// Step3) Adding remaining Cargoes in the left and right array, if any
//		if(i<left.length|j<right.length) { 
//			System.out.print(" Add Leftover: ");
//		}
//		while(i<left.length) {
//			System.out.print(left[i].getName() + " ");
//			temp[k++]=left[i++];
//		}
//		while(j<right.length) {
//			System.out.print(right[j].getName() + " ");
//			temp[k++]=right[j++];
//		}
//		System.out.println("");
//		
//		// Step4) Copying the sorted sub-array output to the parent array "Cargoes"
//		System.arraycopy(temp, 0, Cargoes, 0, Cargoes.length);
//
//		//Checking the output merged array
//		System.out.print(" Output" + cntO +": ");
//		cntO++;
//		printArrayC(Cargoes);
//		System.out.println("");
//		System.out.println("");
//	}	
//	
	



