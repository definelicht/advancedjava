package concurrency.extended;

/**
 * 
 * @author bonii
 * 
 */
public interface BoundedBuffer {

	/**
	 * 
	 * @return
	 */
	public Object get() throws InterruptedException;

	/**
	 * 
	 */
	public void put(Object o) throws InterruptedException;
}
