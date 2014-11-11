package concurrency.extended;

/**
 * 
 * @author bonii
 * 
 */
public class BoundedBufferConsumerTask implements Runnable {
	private BoundedBuffer buffer;

	public BoundedBufferConsumerTask(BoundedBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			while (true) {
				System.out.println("Consumer " + Thread.currentThread().getName()
						+ " attempts to get .. ");
				buffer.get();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			return;
		}
	}

}
