package strategy;

import model.Delivery;
import java.util.*;

public class DPAlgorithm extends AbstractDeliveryStrategy<Delivery>{
	public DPAlgorithm(List<Delivery> delivery) {
		super(delivery);
	}
	
	//n = number of job, D = maxDeadline
	@Override
	public void schedule(List<Delivery> deliveries) {
		//O(1)
		selected.clear();
        unselected.clear();
        totalProfit = 0; 
		int n = deliveries.size();
		
		
		//find maximum deadline to know how many time slot exist
		//O(n)
		int maxDeadline = 0;
		for(Delivery d : deliveries) {
			maxDeadline = Math.max(maxDeadline,  d.getDeadline());
		}
		
		//DP table -> dp[i][t] = maximum profit using first i jobs in t slots
		//O(n*D) - loop over n jobs and D time slot
		double [][] dp = new double[n+1][maxDeadline + 1];
		
		//reconstruct track option
		//O(n*D)
		boolean[][] take = new boolean[n+1][maxDeadline + 1];
		
		//DP computation
		//loop through each job
		//O(n*D)
		for(int i = 1; i <= n; i++) {
			//get job detail
			//O(1)
			Delivery d = deliveries.get(i-1);
			double profit = d.getProfit();
			int deadline = d.getDeadline();
			
			//loop through time slot
			//O(D)
			for(int t = 1; t <= maxDeadline; t++) {
				
				//Option 1: skip job(if don't take, keep previous best result)
				//O(1)
				dp[i][t] = dp[i-1][t];
				
				//Option 2: take job if time slot is valid
				//O(1)
				if(t <= deadline && t > 0) {
					//compute profit if take the job and 1 slot is use
					double newProfit = dp[i-1][t-1] + profit;
					
					//compare profit
					if(newProfit > dp[i][t]) {
						dp[i][t] = newProfit;
						take[i][t] = true;
					}
				}
			}
		}
		
		//reconstruct solution(go backward to find selected job
		//O(n)
		int t = maxDeadline;
		
		//loop backward
		//O(n)
		for(int i = n; i >= 1; i--) {
			//O(1)
			if(take[i][t]) {
				Delivery d = deliveries.get(i-1);
				selected.add(d); //add selected job
				totalProfit +=d.getProfit();
				t--; // already use 1 slot
			}
			else {
				//O(1)
				unselected.add(deliveries.get(i-1)); //add unselected job
			}
		}
		
		//Time complexity = O(n*D)
		//Space complexity = O(n*D)
		
		
	}
	

	
	
}
