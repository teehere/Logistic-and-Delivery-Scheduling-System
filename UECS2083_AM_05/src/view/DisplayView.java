package view;

// import from defined package
import model.Delivery;
import model.Result;
import strategy.*;

import java.util.*;
import java.time.format.DateTimeFormatter;

;public class DisplayView {
	
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
		System.out.println("<1> Task Priority Scheduling (Weighted) Algorithm");
		System.out.println("<2> Greedy Algorithm");
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
        System.out.printf("%-12s | %-15s | %-18s | %-25s | %-10s | %-15s | %-8s\n",
            "DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
        System.out.println("-".repeat(130));
        
        for (Delivery d : deliveries) {
            System.out.printf("%-12s | %-15s | %-18s | %-20s(%3d) | %-10.2f | %-15d | %-8.2f\n",
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
	    System.out.printf("%-12s | %-15s | %-18s | %-25s | %-10s | %-15s | %-8s\n", 
	    		"DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
	    System.out.println("-".repeat(130));
	   
	    double total = 0;
	    
	    if (selected.isEmpty()) {
            System.out.println("No selected deliveries");
	    } else {
		    for (Delivery d : selected) {	        
		        System.out.printf("%-12s | %-15s | %-18s | %-20s(%3d) | %-10.2f | %-15d | %-8.2f\n",
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
	    }
	
	    System.out.println("=".repeat(130));
	    System.out.printf("TOTAL PROFIT: %.2f\n", total);

    }

	// display unselected jobs
    public void displayUnselectedSequence(List<Delivery> unselected) {
        
        System.out.println("=".repeat(130));
        System.out.println(" ".repeat(55) + "UNSELECTED DELIVERIES");
        System.out.println("=".repeat(130));
        
        System.out.printf("%-12s | %-15s | %-18s | %-25s | %-10s | %-15s | %-8s\n", 
                "DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
        System.out.println("-".repeat(130));

        double totalLost = 0;

        if (unselected.isEmpty()) {
            System.out.println("No unselected deliveries");
        } else {
            for (Delivery d : unselected) {
                System.out.printf("%-12s | %-15s | %-18s | %-20s(%3d) | %-10.2f | %-15d | %-8.2f\n",
                        d.getDeliveryId(),
                        d.getItem(),
                        d.getOrderDate().format(fmt),
                        d.getDeadlineDate().format(fmt),
                        d.getDeadline(),
                        d.getSales(),
                        d.getQuantity(),
                        d.getProfit());

                totalLost += d.getProfit();
            }
        }

        System.out.println("=".repeat(130));
        System.out.printf("TOTAL PROFIT LOST: %.2f\n", totalLost);
    }
    
	// display summary
 	public void printSummary(AbstractDeliveryStrategy<Delivery> strategy, List<Delivery> delivery) {

 		//List<Delivery> delivery = strategy.getDelivery();
 		List<Delivery> selected = strategy.getSelected();
 		List<Delivery> unselected = strategy.getUnselected();
 		
 		int totalJobsCnt = delivery.size(); // total jobs count
 		int selectedJobsCnt = selected.size();
 		int unselectedJobsCnt = unselected.size();

 		// profit analysis
 		double totalProfit = strategy.getTotalProfit();
 		double totalPossibleProfit = delivery.stream()
 				.mapToDouble(Delivery::getProfit)
 				.sum();
 		double profitLost = totalPossibleProfit - totalProfit;
 		double totalPotentialProfit = totalProfit + profitLost;

 		// average profit
 		double avgProfitSelected = selectedJobsCnt == 0 ? 0 : totalProfit / selectedJobsCnt;
 		double avgProfitAll = totalJobsCnt == 0 ? 0 :totalPossibleProfit / totalJobsCnt;

 		// selected & unselected rate
 		double selectedRate = totalJobsCnt == 0 ? 0 : (double) selectedJobsCnt / totalJobsCnt * 100;
 		double unselectedRate = totalJobsCnt == 0 ? 0 : (double) unselectedJobsCnt / totalJobsCnt * 100;

 		// print output to user
 		System.out.println("=".repeat(50));
 		System.out.println(" ".repeat(13) + "DELIVERY SUMMARY");
 		System.out.println("=".repeat(50));

 		System.out.println("Total Deliveries       : " + totalJobsCnt);
 		System.out.printf("Selected Deliveries    : %-2d (%.2f%%)\n", selectedJobsCnt, selectedRate);
 		System.out.printf("Unselected Deliveries  : %-2d (%.2f%%)\n", unselectedJobsCnt, unselectedRate);

 		System.out.println("\n--------- PROFIT ANALYSIS ----------");
 		System.out.printf("Profit Earned                   : %.2f\n", totalProfit);
 		System.out.printf("Profit Lost                     : %.2f\n", profitLost);
 		System.out.printf("Total Potential Profit          : %.2f\n", totalPotentialProfit);
 		System.out.printf("Avg Profit (Selected Deliveries): %.2f\n", avgProfitSelected);
 		System.out.printf("Avg Profit (All Deliveries)     : %.2f\n", avgProfitAll);

 		System.out.println("\n------- SELECTED DELIVERIES --------");
 		System.out.printf("%-12s | %-15s | %-18s | %-25s | %-10s | %-15s | %-8s\n",
 				"DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
 		System.out.println("-".repeat(130));

 		if (selected.isEmpty()) {
            System.out.println("No selected deliveries");
 		} else {
	 		for (Delivery d : selected) {
	 			System.out.printf("%-12s | %-15s | %-18s | %-20s(%3d) | %-10.2f | %-15d | %-8.2f\n",
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

 		System.out.println("=".repeat(130));
 		System.out.printf("TOTAL PROFIT: %.2f\n", totalProfit);

 		System.out.println("\n------- UNSELECTED DELIVERIES -------");
 		System.out.printf("%-12s | %-15s | %-18s | %-25s | %-10s | %-15s | %-8s\n",
 				"DeliveryID", "Item", "Order Date", "Deadline", "Sales(RM)", "Quantity(unit)", "Profit(RM)");
 		System.out.println("-".repeat(130));

 		if (unselected.isEmpty()) {
            System.out.println("No unselected deliveries");
 		} else {
	 		for (Delivery d : unselected) {
	 			System.out.printf("%-12s | %-15s | %-18s | %-20s(%3d) | %-10.2f | %-15d | %-8.2f\n",
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

 		System.out.println("=".repeat(130));
 		System.out.printf("TOTAL PROFIT LOST: %.2f\n", profitLost);
 	}
 	
 	//display comparison by time complexity and profit
 	public void compareAlgorithm(List<Result> result) {
 		System.out.println("\n================ COMPARISON ================");
 		System.out.printf("%-20s | %-10s | %-10s\n", "Algorithm", "Time(ms)", "Profit");
 		
 		String best = "";
 		long bestTime = Long.MAX_VALUE;
 		double bestProfit = -1;
 		
 		for(Result r : result) {
 			String name = r.getName();
 			long t = r.getTime();
 			double profit = r.getProfit();
 			System.out.printf("%-20s | %-10d | %-10.2f\n", name, t, profit);
 			
 			if(profit > bestProfit || profit == bestProfit && t < bestTime){
 				bestProfit = profit;
 				bestTime = t;
 				best = name;
 			}

 		}
 	
 		System.out.println("============================================");
 	    System.out.println("BEST ALGORITHM RESULT");
 	    System.out.println("Algorithm : " + best);
 	    System.out.printf("Profit    : %.2f\n", bestProfit);
 	    System.out.println("Time (ms) : " + bestTime);
 	}
 	
}
