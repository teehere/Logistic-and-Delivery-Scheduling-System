package strategy;

import java.util.*;
import model.Delivery;

// initialize task -> task priority -> scheduling -> execution -> all tasks? -> result 
public class EDFAlgorithm extends AbstractDeliveryStrategy<Delivery> {

	public EDFAlgorithm(List<Delivery> delivery) { 
		super(delivery); 
	}
	
	@Override
    public void schedule(List<Delivery> delivery) {
		
		// sort by earliest deadline (highest priority)
		List<Delivery> sorted = new ArrayList<>(delivery);
        sorted.sort(Comparator.comparingInt(Delivery::getDeadline));

        int currentTime = 1; // start at day 1

        for (Delivery d : sorted) {
            if (currentTime <= d.getDeadline()) { // check if deadline >= current day
                selected.add(d); // O(1)
                totalProfit += d.getProfit(); // O(1)
                currentTime++; // next day // O(1)
            } else {
                unselected.add(d); // will be skipped, due to deadline passed // O(1)
            }
        }
        // Total: O(n log n) + O(n) + O(n log n)
    }
}
