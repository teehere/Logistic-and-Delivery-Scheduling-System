package util;

import java.util.*;
import java.io.*;
import java.time.*;
import model.Delivery;

public class RandomGenerator {
	
	public static List<Delivery> generate(int n) {
		
		List<Delivery> deliveryList = new ArrayList<>();
		List<String> items = loadProducts();
		Random ran = new Random();
		
		for (int i=1; i<=n; i++) {
			            
			// pick random item and city
			String item = items.get(ran.nextInt(items.size()));
            
            // random generate   
            LocalDate orderDate = LocalDate.now();
			LocalDate deadline = orderDate.plusDays(ran.nextInt(5) + 1); // 1 to 5 days
			int sales = ran.nextInt(100)+1;
			int quantity = ran.nextInt(30)+1;
			int profit = sales*quantity;
			deliveryList.add(new Delivery("Order#" + i, item, orderDate, deadline, sales, quantity, profit));
		}
		return deliveryList;
	}
		
	public static List<String> loadProducts() {
		List<String> items = new ArrayList<>();
		
        try (Scanner sc = new Scanner(new File("src/item.txt"))) {
            while (sc.hasNextLine()) {
            	
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\n");
                    for (String s : parts) {
                    	items.add(s.trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Note: item.txt not found");
        }
        return items;
	}
}
