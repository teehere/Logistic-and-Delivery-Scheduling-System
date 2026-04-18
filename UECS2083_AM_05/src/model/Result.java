package model;

public class Result {
	String name;
	long time;
	double profit;
	
	public Result(String name, long time, double profit) {
		this.name = name;
		this.time = time;
		this.profit = profit;
	}

	public String getName() {
		return name;
	}

	public long getTime() {
		return time;
	}

	public double getProfit() {
		return profit;
	}

	
}
