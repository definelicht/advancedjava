package assignment2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SensorNetworkHarness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ASensor sensor0 = new ASensor();
		ASensor sensor1 = new ASensor();
		ASensor sensor2 = new ASensor();
		AMonitor monitor0 = new AMonitor();
		AMonitor monitor1 = new AMonitor();
		AMonitor monitor2 = new AMonitor();
		List<Monitor> monitors0 = Arrays.asList(monitor0);
		List<Monitor> monitors1 = Arrays.asList(monitor1);
		List<Monitor> monitors2 = Arrays.asList(monitor2);
		// ASubscriber subscriber0 = new ASubscriber();
		ASubscriber subscriber1 = new ASubscriber();
		// ASubscriber subscriber2 = new ASubscriber();
		sensor0.registerMonitor(monitors0);
		sensor1.registerMonitor(monitors1);
		sensor1.registerMonitor(monitors2);
		sensor2.registerMonitor(monitors0);
		sensor2.registerMonitor(monitors1);
		sensor2.registerMonitor(monitors2);
		// monitor0.registerSubscriber(1, subscriber0);
		monitor2.registerSubscriber(3, subscriber1);
		// monitor2.registerSubscriber(5, subscriber2);
		LinkedList<Thread> threadList = new LinkedList<Thread>();
		try {
			threadList.add(new Thread(sensor0)); threadList.peekLast().start();
			threadList.add(new Thread(sensor1)); threadList.peekLast().start();
			threadList.add(new Thread(sensor2)); threadList.peekLast().start();
			threadList.add(new Thread(monitor0)); threadList.peekLast().start();
			threadList.add(new Thread(monitor1)); threadList.peekLast().start();
			threadList.add(new Thread(monitor2)); threadList.peekLast().start();
			// threadList.add(new Thread(subscriber0)); threadList.peekLast().start();
			threadList.add(new Thread(subscriber1)); threadList.peekLast().start();
			// threadList.add(new Thread(subscriber2)); threadList.peekLast().start();
			// Run for 10 seconds
			for (Thread t : threadList) {
				t.join();
			}
		} catch (InterruptedException e) {}
	}

}
