package strategy;

import java.util.*;
import model.Delivery;

public class EDFAlgorithm extends AbstractDeliveryStrategy<Delivery> {

	public EDFAlgorithm(List<Delivery> delivery) { 
		super(delivery);  
	}
	
	@Override
    public void schedule(List<Delivery> delivery) {
		
		// clear previous result - O(1)
		selected.clear();
        unselected.clear();
        totalProfit = 0;       
        
        // PriorityQueue automatically sorts by deadline - O(n log n)
        PriorityQueue<Delivery> queue = new PriorityQueue<>((a, b) -> {
            int deadlineCompare = Integer.compare(a.getDeadline(), b.getDeadline());
            if (deadlineCompare != 0) return deadlineCompare;
            // For same deadline, higher profit comes first
            return Double.compare(b.getProfit(), a.getProfit());
        });
        queue.addAll(delivery);  // Each add is O(log n), repeated n times
        
        // Initialize time - O(1)
        int currentTime = 1; // start at day 1
        
        // Process jobs in priority order - O(n log n)
        while (!queue.isEmpty()) {
            Delivery d = queue.poll();  // O(log n) per removal
            
            if (currentTime <= d.getDeadline()) {
                selected.add(d); // O(1) 
                totalProfit += d.getProfit(); // O(1)
                currentTime++; // O(1)
            } else {
                unselected.add(d); // O(1)
            } 
        }
       
        // Total Time Complexity: O(n log n)
    }
}
