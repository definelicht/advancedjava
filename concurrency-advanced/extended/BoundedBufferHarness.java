/**
 * 
 */
package concurrency.extended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bonii
 *
 */
public class BoundedBufferHarness {
	public static final int producer_threads = 5;
	public static final int consumer_threads = 5;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundedBuffer buffer = new WaitNotifyBoundedBuffer(1);
		
		BoundedBufferConsumerTask consumer = new BoundedBufferConsumerTask(buffer);
		BoundedBufferProducerTask producer = new BoundedBufferProducerTask(buffer);
		ExecutorService exec = Executors.newFixedThreadPool(producer_threads+consumer_threads);
		
		for(int i=0;i<producer_threads;i++) {
			exec.submit(producer);
		}
		
		/*for(int i=0;i<consumer_threads;i++) {
			exec.submit(consumer);
		}*/
		exec.shutdown();
		
		

	}

}
