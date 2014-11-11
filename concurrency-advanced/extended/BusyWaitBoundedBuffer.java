package concurrency.extended;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author bonii
 * 
 */
public class BusyWaitBoundedBuffer implements BoundedBuffer {
	private List<Object> objectList = new ArrayList<Object>();
	private Lock internalLock = new ReentrantLock();
	private int maxBufferSize;

	public BusyWaitBoundedBuffer(int buffer_size) {
		this.maxBufferSize = buffer_size;
	}

	public Object get() {
		Object temp;
		internalLock.lock();
		while (objectList.size()==0) {
			internalLock.unlock();
			;
			internalLock.lock();
		}
		temp = objectList.get(0);
		objectList.remove(0);
		internalLock.unlock();
		return temp;
	}

	public void put(Object o) {
		internalLock.lock();
		while (objectList.size() == this.maxBufferSize) {
			internalLock.unlock();
			;
			internalLock.lock();
		}
		objectList.add(o);
		internalLock.unlock();
	}

}
