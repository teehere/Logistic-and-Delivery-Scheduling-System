package strategy;

import java.util.*;
import model.Delivery;

public class GreedyAlgorithm extends AbstractDeliveryStrategy<Delivery> {

    public GreedyAlgorithm(List<Delivery> delivery) {
        super(delivery);
    }

    @Override
    public void schedule(List<Delivery> delivery) {
    	
    	// clear previous result
		selected.clear();
		unselected.clear();
		totalProfit = 0;

        // sort by highest profit
    	delivery.sort((a, b) -> Double.compare(b.getProfit(), a.getProfit()));

        // find max deadline
        int maxDeadline = delivery.stream()
                .mapToInt(Delivery::getDeadline)
                .max()
                .orElse(0);

        // slots: decide which task to do each day
        Delivery[] slots = new Delivery[maxDeadline + 1]; // index 1-based

        for (Delivery d : delivery) {
            int deadline = d.getDeadline();

            // place all high profit into latest possible slot
            for (int t = deadline; t >= 1; t--) {
                if (slots[t] == null) {
                    slots[t] = d;
                    selected.add(d);
                    totalProfit += d.getProfit();
                    break;
                }
            }
        }

        // clean up for unselected
        for (Delivery d : delivery) {
            if (!selected.contains(d)) {
                unselected.add(d);
            }
        }
    }

}
