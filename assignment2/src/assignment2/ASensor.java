package assignment2;

import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.Random;

public class ASensor implements Sensor, Runnable {

	// A sensor can push readings to one or many monitors
	private List<Monitor> monitors = null;
	private final Random random = new Random();

	private final ReentrantLock monitorsLock = new ReentrantLock();

	/** java.util.Random is thread safe */
	public SensorReading generateSensorReading() {
		float humidity    = 50 + 20*random.nextGaussian(); // Mean 50, std 20
		float temperature = 20 + 10*random.nextGaussian(); // Mean 20, std 10
		return new SensorReading(humidity, temperature);
	}

	public void run() {
		while (true) {
			SensorReading reading = generateSensorReading();
			monitorsLock.lock();
			try {
				for (Monitor sm : monitors) {
					// Needs to acquire lock to modify reading table of monitor, but
					// should otherwise return quickly
					sm.pushReading(reading);
				}
			} catch (NullPointerException e) {
				// TODO: block and wait for registerMonitor to notify
			} finally {
				monitorsLock.unlock();
			}
			Thread.sleep(1000); // Chill for a sec
		}
	}

	@Override
	public void registerMonitor(List<Monitor> monitors) {
		monitorsLock.lock();
		try {
			this.monitors = monitors;
		} finally {
			monitorsLock.unlock();
		}
	}

}
