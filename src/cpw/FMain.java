package cpw;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FMain {
	private static final JButton bs1 = new JButton("Ship Sort: Name");
	private static final JButton bs2 = new JButton("Ship Sort: Cost");
	private static final JButton bc1 = new JButton("Cargo Sort: Name");
	private static final JButton bc2 = new JButton("Cargo Sort: Weight");
	
	public FMain() {
		
    	///Creating jPanel
    	System.out.println("=============Java GUI Swing Table=================");
        JFrame frame=new JFrame("Java Swing Table");
        frame.setSize(800,500);

        //Panel Code
        JPanel jPanel=new JPanel();
        BoxLayout verticalLayout=new BoxLayout(jPanel,BoxLayout.Y_AXIS);
        jPanel.setLayout(verticalLayout);
        frame.getContentPane().add(jPanel);
        
        
        ////////////////   Ship DB    ////////////////
        
        //Label
        JLabel csv_data_label=new JLabel("Ship Data Table");
        csv_data_label.setAlignmentX(JLabel.CENTER);
        csv_data_label.setBackground(Color.BLACK);
        csv_data_label.setForeground(Color.WHITE);
        csv_data_label.setOpaque(true);
        EmptyBorder emptyBorder1=new EmptyBorder(20,20,20,20);
        csv_data_label.setBorder(emptyBorder1);
        jPanel.add(csv_data_label);
        
        //Parsing CSV Data
        File csv_file=new File("data/shipDB.csv");
        DefaultTableModel csv_data=new DefaultTableModel();
        
        try{

            int start=0;
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(csv_file));
            CSVParser csvParser= CSVFormat.DEFAULT.parse(inputStreamReader);
            for(CSVRecord csvRecord:csvParser){
                if(start==0){
                    start=1;
                    csv_data.addColumn(csvRecord.get(0));
                    csv_data.addColumn(csvRecord.get(1));
                    csv_data.addColumn(csvRecord.get(2));
                }
                else{
                    Vector row=new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    csv_data.addRow(row);
                }
            }

        }
        catch (Exception e){
            System.out.println("Error in Parsing CSV File");
        }
        JTable jTable1=new JTable();
        jTable1.setAutoCreateRowSorter(true); // added
        jTable1.setModel(csv_data);
        JScrollPane jScrollPane1=new JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jPanel.add(jScrollPane1);
        

        //Table Listener
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                System.out.println("Row : "+jTable1.getSelectedRow()+" | Column : "+jTable1.getSelectedColumn()
                +" | Current Value : "+jTable1.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()).toString());
            }
        });

    //    Pane for buttons
        JPanel jPanelBS=new JPanel();
        jPanelBS.setLayout(new GridLayout(2,2));
//        frame.getContentPane().add(jPanelBS);  
        
        ////////////////   Button for Ship   ////////////////
        bs1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Name
        		jTable1.getRowSorter().toggleSortOrder(1);
        	}			 
        }); 
        jPanel.add(bs1);
        
        bs2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Cost
        		jTable1.getRowSorter().toggleSortOrder(2);
        	}			 
        }); 
        
        jPanel.add(bs2);
        
    ////////////////   Cargo DB    ////////////////
        
        //Label
        JLabel csv_data_label2=new JLabel("Ship Data Table");
        csv_data_label2.setAlignmentX(JLabel.CENTER);
        csv_data_label2.setBackground(Color.BLACK);
        csv_data_label2.setForeground(Color.WHITE);
        csv_data_label2.setOpaque(true);
        EmptyBorder emptyBorder2=new EmptyBorder(20,20,20,20);
        csv_data_label2.setBorder(emptyBorder2);
        jPanel.add(csv_data_label2);
        
        //Parsing CSV Data
        File csv_file2=new File("data/cargoDB.csv");
        DefaultTableModel csv_data2=new DefaultTableModel();

        try{
            int start=0;
            InputStreamReader inputStreamReader2=new InputStreamReader(new FileInputStream(csv_file2));
            CSVParser csvParser2= CSVFormat.DEFAULT.parse(inputStreamReader2);
            for(CSVRecord csvRecord:csvParser2){
                if(start==0){
                    start=1;
                    csv_data2.addColumn(csvRecord.get(0));
                    csv_data2.addColumn(csvRecord.get(1));
                    csv_data2.addColumn(csvRecord.get(2));
                }
                else{
                    Vector row=new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    csv_data2.addRow(row);
                }
            }

        }
        catch (Exception e){
            System.out.println("Error in Parsing CSV File");
        }

        JTable jTable2=new JTable();
        jTable2.setAutoCreateRowSorter(true); // added
        jTable2.setModel(csv_data2);
        JScrollPane jScrollPane2=new JScrollPane();
        jScrollPane2.setViewportView(jTable2);
        jPanel.add(jScrollPane2);

        //Table Listener
        jTable2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                System.out.println("Row : "+jTable2.getSelectedRow()+" | Column : "+jTable2.getSelectedColumn()
                +" | Current Value : "+jTable2.getValueAt(jTable2.getSelectedRow(),jTable2.getSelectedColumn()).toString());
            }
        });
        
        ////////////////   Button for Cargo   ////////////////
        bc1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Name
        		jTable2.getRowSorter().toggleSortOrder(1);
        	}			 
        }); jPanel.add(bc1);
        
        bc2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Cost
        		jTable2.getRowSorter().toggleSortOrder(2);
        	}			 
        }); jPanel.add(bc2);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	
	
    public static void main(String args[]){
    	
    	//new FMain();
    	
    	///Creating jPanel
    	System.out.println("=============Java GUI Swing Table=================");
        JFrame frame=new JFrame("Java Swing Table");
        frame.setSize(800,500);

        //Panel for Ship DB
        JPanel jPanel=new JPanel();
        jPanel.setLayout(new GridLayout(6,1));
        frame.getContentPane().add(jPanel);
        
        
        ////////////////   Ship DB    ////////////////
        
        //Label
        JLabel csv_data_label=new JLabel("Ship Data Table");
        csv_data_label.setAlignmentX(JLabel.CENTER);
        csv_data_label.setBackground(Color.BLACK);
        csv_data_label.setForeground(Color.WHITE);
        csv_data_label.setOpaque(true);
        csv_data_label.setSize(5,10);
        EmptyBorder emptyBorder1=new EmptyBorder(20,20,20,20);
        csv_data_label.setBorder(emptyBorder1);
        jPanel.add(csv_data_label);
        
        //Parsing CSV Data
        File csv_file=new File("data/shipDB.csv");
        DefaultTableModel csv_data=new DefaultTableModel();
        
        try{
            int start=0;
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(csv_file));
            CSVParser csvParser= CSVFormat.DEFAULT.parse(inputStreamReader);
            for(CSVRecord csvRecord:csvParser){
                if(start==0){
                    start=1;
                    csv_data.addColumn(csvRecord.get(0));
                    csv_data.addColumn(csvRecord.get(1));
                    csv_data.addColumn(csvRecord.get(2));
                }
                else{
                    Vector row=new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    csv_data.addRow(row);
                }
            }

        }
        catch (Exception e){
            System.out.println("Error in Parsing CSV File");
        }
        JTable jTable1=new JTable();
        jTable1.setAutoCreateRowSorter(true); // added
        jTable1.setModel(csv_data);
        JScrollPane jScrollPane1=new JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jPanel.add(jScrollPane1);
        

        //Table Listener
        jTable1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                System.out.println("Row : "+jTable1.getSelectedRow()+" | Column : "+jTable1.getSelectedColumn()
                +" | Current Value : "+jTable1.getValueAt(jTable1.getSelectedRow(),jTable1.getSelectedColumn()).toString());
            }
        });

        //    Pane for Ship buttons
        JPanel jPanelBS=new JPanel();
        jPanelBS.setLayout(new GridLayout(1,2));
        jPanel.add(jPanelBS);


        ////////////////   Button for Ship   ////////////////
        bs1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Name
        		jTable1.getRowSorter().toggleSortOrder(1);
        	}			 
        }); 
        jPanelBS.add(bs1);
        
        bs2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Cost
        		jTable1.getRowSorter().toggleSortOrder(2);
        	}			 
        }); 
        
        jPanelBS.add(bs2);
        
    ////////////////   Cargo DB    ////////////////
        
        //Label
        JLabel csv_data_label2=new JLabel("Cargo Data Table");
        csv_data_label2.setAlignmentX(JLabel.CENTER);
        csv_data_label2.setBackground(Color.BLACK);
        csv_data_label2.setForeground(Color.WHITE);
        csv_data_label2.setOpaque(true);
        EmptyBorder emptyBorder2=new EmptyBorder(20,20,20,20);
        csv_data_label2.setBorder(emptyBorder2);
        jPanel.add(csv_data_label2);
        
        //Parsing CSV Data
        File csv_file2=new File("data/cargoDB.csv");
        DefaultTableModel csv_data2=new DefaultTableModel();

        try{
            int start=0;
            InputStreamReader inputStreamReader2=new InputStreamReader(new FileInputStream(csv_file2));
            CSVParser csvParser2= CSVFormat.DEFAULT.parse(inputStreamReader2);
            for(CSVRecord csvRecord:csvParser2){
                if(start==0){
                    start=1;
                    csv_data2.addColumn(csvRecord.get(0));
                    csv_data2.addColumn(csvRecord.get(1));
                    csv_data2.addColumn(csvRecord.get(2));
                }
                else{
                    Vector row=new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    csv_data2.addRow(row);
                }
            }

        }
        catch (Exception e){
            System.out.println("Error in Parsing CSV File");
        }

        JTable jTable2=new JTable();
        jTable2.setAutoCreateRowSorter(true); // added
        jTable2.setModel(csv_data2);
        JScrollPane jScrollPane2=new JScrollPane();
        jScrollPane2.setViewportView(jTable2);
        jPanel.add(jScrollPane2);

        //Table Listener
        jTable2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent) {
                System.out.println("Row : "+jTable2.getSelectedRow()+" | Column : "+jTable2.getSelectedColumn()
                +" | Current Value : "+jTable2.getValueAt(jTable2.getSelectedRow(),jTable2.getSelectedColumn()).toString());
            }
        });
        
        //    Pane for Ship buttons
        JPanel jPanelBC=new JPanel();
        jPanelBC.setLayout(new GridLayout(1,2));
        jPanel.add(jPanelBC);

        
        ////////////////   Button for Cargo   ////////////////
        bc1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Name
        		jTable2.getRowSorter().toggleSortOrder(1);
        	}			 
        }); jPanelBC.add(bc1);
        
        bc2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Sort the DB by Cost
        		jTable2.getRowSorter().toggleSortOrder(2);
        	}			 
        }); jPanelBC.add(bc2);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}