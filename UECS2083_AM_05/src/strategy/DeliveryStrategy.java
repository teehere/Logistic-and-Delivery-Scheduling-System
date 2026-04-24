package strategy;

import java.util.*;

public interface DeliveryStrategy<E> {

	public abstract void schedule (List<E> items);
	
}
