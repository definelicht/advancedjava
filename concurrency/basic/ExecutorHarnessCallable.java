package concurrency.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorHarnessCallable {

	public static void main(String[] args) throws ExecutionException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		List<Future<String>> results = new ArrayList<Future<String>>();
		for(int i=0;i<5;i++) {
			SimpleCallableDemo task = new SimpleCallableDemo(i);
			results.add(exec.submit(task));
		}
		
		for(Future<String> rs: results) {
			try {
				System.out.println(rs.get());
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		exec.shutdown();
		
	}

}
