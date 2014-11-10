package concurrency.basic;

public class HelloWorldRunnable implements Runnable {
	private long sleep_milliseconds = 2000;

	public HelloWorldRunnable(long sleep_milliseconds) {
		this.sleep_milliseconds = sleep_milliseconds;
	}

	public void run() {
		try {
			Thread.sleep(sleep_milliseconds);
		} catch (InterruptedException ex) {
			return;
		}
		System.out.println("Hello World from "
				+ Thread.currentThread().getName());

	}

	public static void main(String[] args) {
		new Thread(new HelloWorldRunnable(3000)).start();
	}

}
