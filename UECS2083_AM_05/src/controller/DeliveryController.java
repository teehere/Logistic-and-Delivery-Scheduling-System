package controller;

// import from defined packages
import model.Delivery;
import strategy.*;
import view.DisplayView;
import util.*;

import java.util.*;

public class DeliveryController {
    
    private DisplayView view;
    private List<Delivery> deliveries;
    private String source;
    private Scanner scanner;
    
    public DeliveryController() {
        this.view = new DisplayView(); // show UI
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        loadData();
        if (deliveries != null && !deliveries.isEmpty()) {
            view.displayOriginalData(deliveries);
            runAlgorithm();
        }
        scanner.close();
    }
    
    private void loadData() {
        while (true) { // to return to here when case 5 under showResults
            view.showMainMenu();
            String option = scanner.nextLine().trim().toUpperCase();
            
            if (option.equals("1")) {
				deliveries = FileLoader.load("src/delivery.txt");
				
				source = "File Loader";
				break;
				
			} else if (option.equals("2")) {
				System.out.print("Enter Numbers of Deliveries: ");
				try {// negative value check
					int nums = Integer.parseInt(scanner.nextLine()); // scanner.nextLine() make it consistent, avoid \n buffer
					if(nums <= 0) {
						System.out.println("Number must be greater than 0!");
						continue;
					}
					deliveries = RandomGenerator.generate(nums);
					source = "Random Generator";
					break;
				} catch(NumberFormatException e) {
					System.out.println("Invalid number! Please enter digits only.");
				}
				
			} else if (option.equals("Q")) {
				System.exit(0);
				
			} else {
				System.out.println("Invalid Option, Please Try Again! \n");
			}
        }
    }
    
    private void runAlgorithm() {
    	while(true) {
	        AbstractDeliveryStrategy<Delivery> strategy = selectAlgorithm();
	        
	        // get execution time (ms)
	        long start = System.currentTimeMillis();
	        strategy.schedule(deliveries);
	        long end = System.currentTimeMillis();
	        
	        showResults(strategy, end - start);
    	}
    }
    
    private AbstractDeliveryStrategy<Delivery> selectAlgorithm() {
        while (true) {
            view.showAlgorithmMenu();
            String option = scanner.nextLine().trim().toUpperCase();
            
            switch (option) {
			case "1":
				// new catherine(delivery);
			case "2":
				// new evelyn(delivery);
			case "3":
				return new DPAlgorithm(deliveries);
			case "4":
				return new EDFAlgorithm(deliveries);
			case "Q":
				System.exit(0);;
			default:
				System.out.println("Invalid Option, Please Try Again!");
            }
        }
    }
    
    private void showResults(AbstractDeliveryStrategy<Delivery> strategy, long time) {
    	
        while (true) {
            view.showResultMenu(source, strategy.getSelected().size() + strategy.getUnselected().size(),strategy.getClass().getSimpleName(), time); 
            
            String option = scanner.nextLine().trim().toUpperCase();
            switch (option) {
	    	case "1":
	    		// selected (highest/lowest/average profit/deadline analysis/)
	    		view.displaySelectedSequence(strategy.getSelected());
	    		System.out.println("Press any key to return...");
	    		scanner.nextLine();
	    		break;

	    	case "2":
	    		// unselected (potential profit lost/reason why unselected/suggestion/deadline analysis...)
	    		System.out.println("Press any key to return...");
	    		scanner.nextLine();
	    		break;
	    	case "3":
	    		// summary (total jobs/ selected/unselected job/missed deadline/profit earned/loss/average profit per job/...)
	    		System.out.println("Press any key to return...");
	    		scanner.nextLine();
	    		break;
	    	case "4":
	    		// comparison
	    		System.out.println("Press any key to return...");
	    		scanner.nextLine();
	    		break;
	    	case "5":
	    		System.out.println("Returning to Previous Menu"); // error !!!!!!!!!!!! (fixed)
                return;
	    	default:
	    		System.out.println("Invalid Option, Please Try Again!");
            }
        }
    }
}
