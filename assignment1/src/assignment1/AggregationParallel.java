package assignment1;

import java.util.List;

public class AggregationParallel<T> implements Aggregation<T> {

	public int aggregate(Combination<T> combination, List<T> list) {
		if (list.size() == 0) {
			// This should throw some clever exception...
			assert false;
		}
		AggregateResult result = new AggregateResult();
		// AggregateRunnable recursively divides and conquers the list and stores it
		// into result
		Thread thread = new Thread(new AggregateRunnable<T>(combination, list,
		      																						  result));
    thread.start();
    try {
		  thread.join();
		} catch (InterruptedException e) {
			// Crash and burn
			assert false;
		}
		return result.get();
	}

}
