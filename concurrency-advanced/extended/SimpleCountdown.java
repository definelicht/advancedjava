package concurrency.extended;


public class SimpleCountdown implements Runnable {
	private int counter = 5;

	public void run() {
		while (this.counter-- > 0) {
			System.out.println("Hello " + counter + " from thread "+Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				System.out.println("Thread "+Thread.currentThread().getName()+ " got interrupted, exiting");
				return;
			} finally {
			}
		}
	}


}
