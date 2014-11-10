package concurrency.basic;

public class MainThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread myThread;
		for (int i = 0; i < 5; i++) {
			myThread = new Thread(new HelloWorldRunnable(1000));
			myThread.setDaemon(false);
			myThread.start();
		}
		System.out.println("Hello from "+Thread.currentThread().getName());

	}
}
