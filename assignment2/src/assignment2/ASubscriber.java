package assignment2;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.LinkedList;

public class ASubscriber implements Subscriber, Runnable {

	final private LinkedList<Integer> discomfortLevels =
	    new LinkedList<Integer>();

	final private ReentrantLock lock = new ReentrantLock();
	final private Condition newEntry = lock.newCondition();

	public void run() {
		try {
			while (true) {
				int discomfortLevel = getDiscomfortWarning();
				processDiscomfortWarning(discomfortLevel);
			}
		} catch (InterruptedException e) {
			// Just return
		}
	}

	@Override
	public void pushDiscomfortWarning(int discomfortLevel) {
		lock.lock();
		try {
			discomfortLevels.add(discomfortLevel);
			// Notify any consumers that might be waiting for new entries
			newEntry.signalAll();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void processDiscomfortWarning(int discomfortLevel) {
		System.out.println("WARNING: Discomfort level " + discomfortLevel + "!");
	}

	@Override
	public int getDiscomfortWarning() throws InterruptedException {
		lock.lock();
		try {
			while (discomfortLevels.size() == 0) {
				newEntry.await();
			}
			return discomfortLevels.remove();
		} finally {
			lock.unlock();
		}
	}

}
