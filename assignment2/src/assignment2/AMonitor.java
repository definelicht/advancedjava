package assignment2;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.LinkedList;

public class AMonitor implements Monitor, Runnable {

	private static final int MOVING_AVERAGE_SIZE = 10;

	// Added but unprocessed readings
	final private LinkedList<SensorReading> readings =
	    new LinkedList<SensorReading>();

	// Bounded list of readings used to compute moving average
	final private LinkedList<SensorReading> movingAverage =
	    new LinkedList<SensorReading>();
	final private LinkedList<SubscriberRegistration> subscribers =
	    new LinkedList<SubscriberRegistration>();

	final private ReentrantLock readingsLock = new ReentrantLock();
	final private Condition newReading = readingsLock.newCondition();
	final private ReentrantLock movingAverageLock = new ReentrantLock();
	final private ReentrantLock subscribersLock = new ReentrantLock();

	/** Does not block, but locks on access to the readings list */
	@Override
	public void pushReading(SensorReading sensorInput) {
		readingsLock.lock();
		try {
			readings.add(sensorInput);
			// If any threads are waiting for readings, wake them up
			newReading.notify();
		} finally {
			readingsLock.unlock();
		}
	}

	@Override
	public void processReading(SensorReading sensorInput) {
		float temperature = 0;
		float humidity = 0;
		int nEntries = 0;
		movingAverageLock.lock();
		try {
			movingAverage.add(sensorInput);
			// Remove oldest element if size is exceeded
			while (movingAverage.size() > MOVING_AVERAGE_SIZE) movingAverage.remove();
			for (SensorReading r : movingAverage) {
				temperature += r.getTemperature();
				humidity += r.getHumidity();
				++nEntries;
			}
		} finally {
			movingAverageLock.unlock();
		}
		temperature /= nEntries;
		humidity /= nEntries;
		int discomfortLevel = 0;
		if (temperature >= 50 || humidity >= 90) discomfortLevel = 5; else
		if (temperature >= 40 || humidity >= 80) discomfortLevel = 4; else
		if (temperature >= 30 || humidity >= 70) discomfortLevel = 3; else
		if (temperature >= 20 || humidity >= 60) discomfortLevel = 2; else
		if (temperature >= 10 || humidity >= 50) discomfortLevel = 1;
		subscribersLock.lock();
		try {
			for (SubscriberRegistration s : subscriber) {
				if (discomfortLevel >= s.getDiscomfortLevel()) {
				  s.pushDiscomfortWarning(discomfortLevel);
			  }
			}
		} finally {
			subscribersLock.unlock();
		}
	}

	@Override
	public void registerSubscriber(int discomfortLevel, Subscriber subscriber) {
		subscribersLock.lock();
		try {
			subscribers.add(new SubscriberRegistration(subscriber, discomfortLevel));
		} finally {
			subscribersLock.unlock();
		}
	}

	@Override
	public SensorReading getSensorReading() {
		readingsLock.lock();
		try {
			// If no new readings are present, go to sleep and wait for a sensor to
			// push one before processing
			while (readings.size() == 0) {
				newReading.await();
			}
			return readings.remove();
		} finally {
			readingsLock.unlock();
		}
	}

	public void run() {
		// Set the first
		while (true) {
			SensorReading sensorInput = getSensorReading();
			processReading(sensorInput);
		}
	}

}
