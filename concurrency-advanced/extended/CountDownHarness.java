package concurrency.extended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownHarness {
	static int NUMBER_THREADS = 5;

	public static void main(String[] args) {
		SimpleCountdown task = new SimpleCountdown();
		ExecutorService exec = Executors.newFixedThreadPool(NUMBER_THREADS);
		for (int i = 0; i < NUMBER_THREADS; i++) {
			task = new SimpleCountdown();
			exec.submit(task);
		}
		exec.shutdownNow();
		System.out.println("Hello from Main Harness");

	}

}
