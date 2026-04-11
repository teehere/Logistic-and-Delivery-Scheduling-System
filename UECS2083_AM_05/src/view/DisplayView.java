package view;

// import from defined package
import model.Delivery;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class DisplayView {
	
	public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
    // will be displayed when load data
    public void showMainMenu() {
    	System.out.println("==========================================");
		System.out.println("         DELIVERY SCHEDULE SYSTEM");
		System.out.println("==========================================");
		
		System.out.println("<1> Load Data From File");
		System.out.println("<2> Load Data From Random Generator");
		System.out.println("<Q> Quit");
		System.out.println("-------------------------------------------");
		System.out.print("Select Method to Retrieve Data: ");
    }
    
    // display algorithm menu
    public void showAlgorithmMenu() {
    	System.out.println();
		System.out.println("=".repeat(50));
		System.out.println(" ".repeat(16) + "SELECT ALGORITHMS");
		System.out.println("=".repeat(50));
		System.out.println("<1> ");
		System.out.println("<2> ");
		System.out.println("<3> Dynamic Programming Algorithm(DP)");
		System.out.println("<4> Earliest Deadline First Algorithm (EDF)");
		System.out.println("<Q> Quit");
		System.out.println("-".repeat(50));
		System.out.print("Select Algorithm to Analyze: ");
    }
    
    // display result after select an algorithm
    public void showResultMenu(String source, int total, String algorithm, long time) {
    	System.out.println();
		System.out.println("=".repeat(50));
	    System.out.println(" ".repeat(13) + "DELIVERY SCHEDULER");
	    System.out.println("=".repeat(50));
	    
	    // header
	    System.out.println("Input Source      : " + source);
	    System.out.println("Total Deliveries  : " + total);
	    System.out.println("Algorithm Used    : " + algorithm);
	    System.out.println("Execution Time    : " + time + " ms"); 
	    System.out.println("-".repeat(50));
	    
	    // option
	    System.out.println("<1> Selected Sequence of Job");
	    System.out.println("<2> Unselected Sequence of Job");
	    System.out.println("<3> Summary");
	    System.out.println("<4> Algorithm Performance Comparison");
	    System.out.println("<5> Back To Previous Menu");
	    System.out.println("-".repeat(50));
	    System.out.print("Select Option: ");
    }
    
    // display original data (before select an algorithm)
    public void displayOriginalData(List<Delivery> deliveries) {
        System.out.println("\n" + "=".repeat(130));
        System.out.println(" ".repeat(55) + "ORIGINAL DELIVERIES");
        System.out.println("=".repeat(130));
        System.out.printf("%-12s | %-15s | %-18s | %-24s | %-10s | %-15s | %-8s\n",
            "DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
        System.out.println("-".repeat(130));
        
        for (Delivery d : deliveries) {
            System.out.printf("%-12s | %-15s | %-18s | %-20s(%02d) | %-10d | %-15d | %-8d\n",
                d.getDeliveryId(),
                d.getItem(),
                d.getOrderDate().format(fmt), 
                d.getDeadlineDate().format(fmt),
                d.getDeadline(), // day difference
                d.getSales(),
                d.getQuantity(),
                d.getProfit());
        }
    }
    
    // display selected job
    public void displaySelectedSequence(List<Delivery> selected) {
	    
	    System.out.println("=".repeat(130));
	    System.out.println(" ".repeat(55) + "SELECTED DELIVERIES");
	    System.out.println("=".repeat(130));
	    System.out.printf("%-12s | %-15s | %-18s | %-24s | %-10s | %-15s | %-8s\n", 
	    		"DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
	    System.out.println("-".repeat(130));
	   
	    int total = 0;
	    for (Delivery d : selected) {	        
	        System.out.printf("%-12s | %-15s | %-18s | %-20s(%02d) | %-10d | %-15d | %-8d\n",
	                d.getDeliveryId(),
	                d.getItem(),
	        		d.getOrderDate().format(fmt),
	                d.getDeadlineDate().format(fmt),
	                d.getDeadline(), // day difference
	                d.getSales(),
	        		d.getQuantity(),
	                d.getProfit());
	        total += d.getProfit();
	    }
	
	    System.out.println("=".repeat(130));
	    System.out.println("TOTAL PROFIT: " + total);

    }
}