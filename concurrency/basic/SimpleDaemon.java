package concurrency.basic;

public class SimpleDaemon implements Runnable {
	private int id = 0;
	private int counter = 5;

	public SimpleDaemon(int id) {
		this.id = id;
	}

	public void run() {
		while (this.counter-- > 0) {
			System.out.println("Hello " + counter + " from thread "+this.id);
			/*try {
				Thread.sleep(1000);
				//Thread.yield();
			} catch (InterruptedException ex) {
				System.out.println("Thread "+this.id+ " got interrupted, exiting");
				return;
			}*/			
		}
	}


}
