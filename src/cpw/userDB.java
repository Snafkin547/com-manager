package cpw;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class userDB {
	public static void readCSV(File csv) {
	     try {
		        BufferedReader br = new BufferedReader(new FileReader(csv));
		        // Read CSV file from the top to the bottom
		        String line = "";
		        while ((line = br.readLine()) != null) {
		          StringTokenizer st = new StringTokenizer(line, ",");
		          while (st.hasMoreTokens()) {
		            System.out.print(st.nextToken() + "\t");
		          }
		          System.out.println();
		        }
		        br.close();
		    } catch (FileNotFoundException e) {
		      // File object error upon creation
		      e.printStackTrace();
		    } catch (IOException e) {
		      // File object error upon closure
		      e.printStackTrace();
		    }
	}
	
	  public static void writeCSV(File csv, String toWrite) {
		    try {
		      // Writing CSV
		      BufferedWriter bw 
		        = new BufferedWriter(new FileWriter(csv, true)); 
		      // Create a new line
		      bw.newLine();
		      bw.write(toWrite);
		      bw.close();
		 
		    } catch (FileNotFoundException e) {
		      // File object error upon creation
		      e.printStackTrace();
		    } catch (IOException e) {
		      // File object error upon closure
		      e.printStackTrace();
		    }
	  }
	  
//	  public static void deleteLine(File csv) {
//		  
//		    try {
//		    	BufferedReader br = new BufferedReader(new FileReader(csv));
////		    	BufferedWriter writer = new BufferedWriter(new FileWriter(csv)); 
//		    	int cnt=0;
//		    	String lineToRemove = "V" + "," + "V" + "," + "40";
//		    	String line = "";
//		    	while((line = br.readLine()) != null) { 
//		    		// trim newline when comparing with lineToRemove 
//		    		    String trimmedLine = line.trim(); 
//		    		    if(trimmedLine.equals(lineToRemove)) {
//		    		    	trimmedLine.write("");
//		    		    	continue; 
//		    		    }
////		    		    writer.write(line + System.getProperty("line.separator")); 
//		    		    cnt++;
//	    		} 
//	    		//writer.close();  
//	    		br.close();  
//		    } catch (FileNotFoundException e) {
//		      // File object error upon creation
//		      e.printStackTrace();
//		    } catch (IOException e) {
//		      // File object error upon closure
//		      e.printStackTrace();
//		    }
//	  }
	  
		public static List<String[]> loadCSV(File csv) {
			  List<String[]> allElements = new LinkedList<String[]>();;

 			try {
		    	   BufferedReader br = new BufferedReader(new FileReader(csv));
			        // Read CSV file from the top to the bottom
			        String line = "";
			        int i=0;
			        while ((line = br.readLine()) != null) {
			        	StringTokenizer st = new StringTokenizer(line, ",");
			        	String[] holder= new String[3];
			        	int j=0;
			        	while (st.hasMoreTokens()) {
			        		String x = st.nextToken();
			        		holder[j]=x;
			        		j++;
			        	}
			        	allElements.add(holder);
			        	for (String e:allElements.get(i)) {
		            		System.out.print(e+"   ");
		            	}
			        	System.out.println( "\t");
		   	            i++;
			        }
			        br.close();
			    } catch (FileNotFoundException e) {
			      // File object error upon creation
			      e.printStackTrace();
			    } catch (IOException e) {
			      // File object error upon closure
			      e.printStackTrace();
			    }
		        return allElements;
		     
	}
	  public static void addToDB(List<String[]> allElements, String Ship, String Name, String Cost) {
		  int len= allElements.size();
		  allElements.add(len, new String[]{Ship, Name, Cost});
		  for(String s : allElements.get(len)) {
			  System.out.print(s+"   ");
		  }
		  System.out.println("-added");
	  }	
	  
	  //Export data in allElements list to csv to save it permanently
	  public static void exportToCSV(File csv, List<String[]> allElements) {
		  
		  try {
//			    File newCSV = new File("data/shipDB.csv"); //CSV FIle
			  BufferedWriter be = new BufferedWriter(new FileReader(csv));
		        // Read CSV file from the top to the bottom
		        String line = "";
		        while ((line = be.newLine) != null) {
				      be.write("");
		        }
		        be.close();
		    } catch (FileNotFoundException e) {
		      // File object error upon creation
		      e.printStackTrace();
		    } catch (IOException e) {
		      // File object error upon closure
		      e.printStackTrace();
		    } 
		  
	        
		    for(String[] list:allElements) {
	    		String toWrite = "";
		    	for(String s: list) {
		    		toWrite+=s+",";
		    	}
			    try {
			      // Writing CSV
			      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
			      // Create a new line
			      bw.newLine();
			      bw.write(toWrite);
			      bw.close();
			 
			    } catch (FileNotFoundException e) {
			      // File object error upon creation
			      e.printStackTrace();
			    } catch (IOException e) {
			      // File object error upon closure
			      e.printStackTrace();
			    }
		    }
	  }		
		
	public static void main(String[] args) {
		File csv = new File("data/shipDB.csv"); //CSV FIle
		System.out.println("========= Read the original CSV =========");
		System.out.println("");
		readCSV(csv);
		
//		String toWrite = "V" + "," + "V" + "," + "40";
//		System.out.println("");
//		System.out.println(".... Writing " +toWrite + " into the CSV"+"....");
//		System.out.println("");
//		writeCSV(Ships, toWrite);
//
//		System.out.print("==== Read the CSV with new data entry ====");
//		System.out.println("");
//		readCSV(Ships);
		
//		deleteLine(Ships);
		List<String[]> allElements = loadCSV(csv);
		addToDB(allElements, "S","S","300");
		exportToCSV(csv, allElements);
		readCSV(csv);
	}
}
