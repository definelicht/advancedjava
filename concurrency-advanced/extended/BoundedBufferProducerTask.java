package concurrency.extended;

/**
 * 
 * @author bonii
 * 
 */
public class BoundedBufferProducerTask implements Runnable {
	BoundedBuffer buffer;

	public BoundedBufferProducerTask(BoundedBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Object o = new Object();
		try {
			while (true) {
				System.out.println("Producer " + Thread.currentThread().getName()
						+ " attempts to push...");
				this.buffer.put(o);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			return;
		}
	}

}
