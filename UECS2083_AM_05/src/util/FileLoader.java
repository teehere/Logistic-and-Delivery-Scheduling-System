package util;

import model.Delivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class FileLoader {
	
	public static List<Delivery> load(String filename) {
        List<Delivery> deliveryList = new ArrayList<>(); 
	
		try {
			File file = new File(filename);
            
            // Check if file exists
            if (!file.exists()) {
                System.err.println("Error: File not found at " + filename);
                return deliveryList;  // Return empty list
            }
            
            // Check if file is empty
            if (file.length() == 0) {
                System.err.println("Warning: File is empty at " + filename);
                System.err.println("No deliveries loaded. Please add data to the file");
                return deliveryList;  // Return empty list
            }
            
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
	                double sales = Double.parseDouble(part[4].trim());
	                int quantity = Integer.parseInt(part[5].trim());
	                double profit = Double.parseDouble(part[6].trim());
	                
	                deliveryList.add(new Delivery(id, item, orderDate, deadline, sales, quantity, profit));
                }
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found at " + filename);
		} catch (NumberFormatException e) {
			System.err.println("Error: Invalid number format in file");
		} catch (Exception e) {
			System.err.println("Warning: File is empty at " + filename);
		}
		return deliveryList;
	}
}
