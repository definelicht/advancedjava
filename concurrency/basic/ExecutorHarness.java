package concurrency.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorHarness {
	static int NUMBER_THREADS = 5;

	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < NUMBER_THREADS; i++) {
			exec.execute(new ExtendedDemo(i, null));
		}
		exec.shutdown();
		System.out.println("Hello from Main Harness");
	}

}
