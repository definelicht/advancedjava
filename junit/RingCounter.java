package junit;

/**
 * 
 * A ring counter, counting 1..16 in a circular fashion.
 * 
 * @author fmma
 *
 */
public interface RingCounter {
	
	/**
	 * @return A counter. Initially 1.
	 */
	int get();
	
	
	/**
	 * Increment counter by n.
	 * 
	 * @throws IllegalArgumentException If n is negative.
	 */
	void inc(int n) throws IllegalArgumentException;
}
