package concurrency.basic;

public class ExtendedDemo implements Runnable {
	private int id = 0;
	private int counter = 5;
	private Thread joinToThread = null;

	public ExtendedDemo(int id, Thread joinToThread) {
		this.id = id;
		this.joinToThread = joinToThread;
	}

	public void run() {
		try {
			if (joinToThread != null) {
				joinToThread.join();
			}
			while (this.counter-- > 0) {
				System.out.println("Hello " + counter + " from thread "
						+ this.id);
				Thread.sleep(1000);
				if(Thread.interrupted()) {
					System.out.println("Thread " + this.id
							+ " got interrupted, exiting");
					return;
				}
				//Thread.yield();
			}

		} catch (InterruptedException ex) {
			System.out.println("Thread " + this.id
					+ " got interrupted, exiting");
			return;
		}
	}

}
