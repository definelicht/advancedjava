package concurrency.basic;

public class ExtendedDemoHarness {
	static int NUMBER_THREADS = 5;

	public static void main(String[] args) {

		Thread[] threads = new Thread[NUMBER_THREADS];
		for (int i = 0; i < NUMBER_THREADS; i++) {
			if (i == 0) {
				threads[i] = new Thread(new ExtendedDemo(i, null));
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i] = new Thread(new ExtendedDemo(i, threads[i-1]));
				//threads[i] = new Thread(new ExtendedDemo(i, null));
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			
			//threads[i].setDaemon(true);
			threads[i].start();
		}

		System.out.println("Hello from Main Harness");

		
		for (int i = 0; i < NUMBER_THREADS; i++) {
			try {
				Thread.sleep(2000);
			} catch(InterruptedException ex) {
				;
			}
			threads[i].interrupt();
		}
	}

}
