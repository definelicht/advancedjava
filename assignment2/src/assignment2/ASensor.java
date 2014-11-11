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
		double humidity    = 50. + 50.*random.nextGaussian();
		double temperature = 20. + 50.*random.nextGaussian();
		return new SensorReading((float)humidity, (float)temperature);
	}

	public void run() {
		try {
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
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// Just return
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
