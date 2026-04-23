package strategy;

import java.util.*;
import model.Delivery;

public class TPSWeightedAlgorithm extends AbstractDeliveryStrategy<Delivery> {

    public TPSWeightedAlgorithm(List<Delivery> delivery) {
        super(delivery); 
    }

    @Override
    public void schedule(List<Delivery> delivery) {

        //clear previous results 
        selected.clear();
        unselected.clear();
        totalProfit = 0;

        //check if input is valid
        if (delivery == null || delivery.isEmpty()) {
            return;
        }

        //find maximum deadline
        int maxDeadline = 0;
        for (Delivery d : delivery) {
            if (d.getDeadline() > maxDeadline) {
                maxDeadline = d.getDeadline();
            }
        }

        //create array to store scheduled jobs and track taken slots
        Delivery[] scheduleSlots = new Delivery[maxDeadline + 1];
        boolean[] slotTaken = new boolean[maxDeadline + 1];

        // priority queue sorted by highest profit
        PriorityQueue<Delivery> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getProfit(), a.getProfit())
        );

        pq.addAll(delivery);

        int order = 1;

        // process highest profit job first
        while (!pq.isEmpty()) {
            Delivery d = pq.poll();

            //to assign a delivery job to the latest available slot within its deadline
            boolean placed = false;

            for (int t = d.getDeadline(); t >= 1; t--) {
                if (t <= maxDeadline && !slotTaken[t]) {
                    scheduleSlots[t] = d;
                    slotTaken[t] = true;

                    selected.add(d);
                    totalProfit += d.getProfit();

                    System.out.println(order + ". " + d.getDeliveryId()
                            + " | Profit: " + d.getProfit()
                            + " | Deadline: " + d.getDeadline()
                            + " | Scheduled at slot: " + t);

                    placed = true;
                    order++;
                    break;
                }
            }

            //identify unselected jobs
            if (!placed) {
                unselected.add(d);
            }
        }
    }
}
