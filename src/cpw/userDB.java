package cpw;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	  
	  public static void deleteLine(File csv) {
		    try {
		    	BufferedReader br = new BufferedReader(new FileReader(csv));
//		    	BufferedWriter writer = new BufferedWriter(new FileWriter(csv)); 
		    	int cnt=0;
		    	String lineToRemove = "D" + "," + "D" + "," + "40";
		    	String line = "";
		    	while((line = br.readLine()) != null) { 
		    		// trim newline when comparing with lineToRemove 
		    		    String trimmedLine = line.trim(); 
		    		    if(trimmedLine.equals(lineToRemove)) {
		    		    	System.out.print(cnt);
		    		    	continue; 
		    		    }
//		    		    writer.write(line + System.getProperty("line.separator")); 
		    		    cnt++;
	    		} 
	    		//writer.close();  
	    		br.close();  
		    } catch (FileNotFoundException e) {
		      // File object error upon creation
		      e.printStackTrace();
		    } catch (IOException e) {
		      // File object error upon closure
		      e.printStackTrace();
		    }
	  }
		
	public static void main(String[] args) {
		File Ships = new File("data/shipDB.csv"); //CSV FIle
		System.out.println("========= Read the original CSV =========");
		System.out.println("");
		readCSV(Ships);
		
		String toWrite = "V" + "," + "V" + "," + "40";
		System.out.println("");
		System.out.println(".... Writing " +toWrite + " into the CSV"+"....");
		System.out.println("");
		writeCSV(Ships, toWrite);

		System.out.print("==== Read the CSV with new data entry ====");
		System.out.println("");
		readCSV(Ships);
	}
}
