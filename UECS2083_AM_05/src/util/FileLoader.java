package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import model.Delivery;

public class FileLoader {
	
	public static List<Delivery> load(String filename) {
        List<Delivery> deliveryList = new ArrayList<>(); 
	
		try {
			Scanner in = new Scanner(new File(filename));
			
			String line;
			while(in.hasNext()) {
				line = in.nextLine();
				String[] part = line.split(",");
				
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				if (part.length == 7) {
	                String id = part[0].trim();
	                String item = part[1].trim();
	                LocalDate orderDate = LocalDate.parse(part[2].trim(), fmt);
	                LocalDate deadline = LocalDate.parse(part[3].trim(), fmt);
	                int sales = Integer.parseInt(part[4].trim());
	                int quantity = Integer.parseInt(part[5].trim());
	                int profit = Integer.parseInt(part[6].trim());
	                
	                deliveryList.add(new Delivery(id, item, orderDate, deadline, sales, quantity, profit));
                }
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found at " + filename);
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid number format in file");
		}
		return deliveryList;
	}
}
