package strategy; 

import java.util.*;

public abstract class AbstractDeliveryStrategy<E> implements DeliveryStrategy<E> {
	protected List<E> delivery; // main storage
	protected List<E> selected = new ArrayList<>(); // selected job
	protected List<E> unselected = new ArrayList<>(); // rejected job
	protected double totalProfit = 0;
	 
	public AbstractDeliveryStrategy(List<E> delivery) {
		this.delivery = delivery;
	}
	
	public List<E> getDelivery() {return delivery;}
	public List<E> getSelected() {return selected;}
    public List<E> getUnselected() {return unselected;}
    public double getTotalProfit() {return totalProfit;}	    
}

/*
AbstractDeliveryStrategy doesn't implement schedule:
- not common/shared features for all algorithms  (due to different logics)
(e.g. greedy: sort by profit / EDF: sort by deadline / shortest job first: sort by duration...)

- getSelected/getUnselected/getTotalProfit are common features for all algorithms,
  here to avoid code duplication (can reuse)
  
---------------------------
Interface:
- all drivers must deliver orders

Abstract: (common things)
- earnings
- accepted orders
- rejected orders

Concrete:
- fastest route (EDF)
- highest profit route (Greedy)
- Shortest distance route 
- ...
*/