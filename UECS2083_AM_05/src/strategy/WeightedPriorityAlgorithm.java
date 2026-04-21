package strategy;

import java.util.*;
import model.Delivery;

public class WeightedPriorityAlgorithm extends AbstractDeliveryStrategy<Delivery> {

    public WeightedPriorityAlgorithm(List<Delivery> delivery) {
        super(delivery);
    }

    @Override
    public void schedule(List<Delivery> delivery) {

        //clear previous results
        selected.clear();
        unselected.clear();
        totalProfit = 0;

        //check if the input is valid
        if (delivery == null || delivery.isEmpty()) {
            return;
        }

        //sort delivery jobs in descending order
        List<Delivery> jobs = new ArrayList<>(delivery);
        jobs.sort((a, b) -> Double.compare(b.getProfit(), a.getProfit()));

        //find maximum deadline
        int maxDeadline = jobs.stream()
                .mapToInt(Delivery::getDeadline)
                .max()
                .orElse(0);

        //create array to store scheduled jobs and track taken slots
        Delivery[] schedule = new Delivery[maxDeadline + 1];
        boolean[] occupied = new boolean[maxDeadline + 1];

        for (Delivery job : jobs) {

            //to assign a delivery job to the latest avaiable slot within its deadline
            for (int t = Math.min(job.getDeadline(), maxDeadline); t >= 1; t--) {

            	//if slot is free, assign the job to this time slot
                if (!occupied[t]) {
                    schedule[t] = job;
                    occupied[t] = true;

                    //mark job as selected and update total profit
                    selected.add(job);
                    totalProfit += job.getProfit();
                   
                    break; //move to next job
                }
            }
        }

        //identify unselected jobs
        Set<Delivery> selectedSet = new HashSet<>(selected);
        for (Delivery job : delivery) {
            if (!selectedSet.contains(job)) {
                unselected.add(job);
            }
        }

        //print final schedule
        System.out.println("\nFinal Schedule:");
        int order = 1;
        for (int t = 1; t <= maxDeadline; t++) {
            if (occupied[t]) {
                Delivery d = schedule[t];
                System.out.println(order + ". " + d.getId()
                        + " | Profit: " + d.getProfit()
                        + " | Deadline: " + d.getDeadline()
                        + " | Slot: " + t);
                order++;
            }
        }

        //print total profit 
        System.out.println("\nTotal Profit: " + totalProfit);
    }
}
