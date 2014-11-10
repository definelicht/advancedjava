package concurrency.basic;

import java.util.concurrent.Callable;

public class SimpleCallableDemo implements Callable<String> {
	private int id;

	public SimpleCallableDemo(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(id*1000);
		return ("Hello World from "+id);
	}

}
