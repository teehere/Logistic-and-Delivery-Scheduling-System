package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Delivery {

	private String deliveryId;
	private String item;
	private LocalDate orderDate;
	private LocalDate deadline;
	private double sales;
	private int quantity;
	private double profit;
	
	public Delivery(String deliveryId, String item, LocalDate orderDate, LocalDate deadline, double sales, int quantity, double profit) {
		this.deliveryId = deliveryId;
		this.item = item;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.sales = sales;
        this.quantity = quantity;
		this.profit = profit;
	}
	
	// getter
	public String getDeliveryId() {return deliveryId;}
	public String getItem() {return item;}
	public LocalDate getOrderDate() {return orderDate;}
	public LocalDate getDeadlineDate() {return deadline;}
	public double getSales() {return sales;}
	public int getQuantity() {return quantity;}
	public double getProfit() {return profit;}
	
	// to get days difference (slot)
	public int getDeadline() {
		return (int) ChronoUnit.DAYS.between(orderDate, deadline); 
	}
	
	@Override
	public String toString() {
	    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
		return "Delivery#" + deliveryId + "\nProduct: " + item + 
				"\nOrder Date: "+ orderDate.format(fmt) + "\nDeadline: " + deadline.format(fmt) + 
				"\nSales: " + sales + "\nQuantity: " + quantity + "\nProfit: " + sales*quantity;
	} 
	
}
