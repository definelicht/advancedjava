/**
 * TODO: Rename maxBufferSize  
 */

package concurrency.extended;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bonii
 * 
 */
public class ConditionBoundedBuffer implements BoundedBuffer {
	private List<Object> objectList = new ArrayList<Object>();
	private int maxBufferSize;
	Lock internallock = new ReentrantLock();
	Condition isEmpty = internallock.newCondition();
	Condition isFull = internallock.newCondition();

	public ConditionBoundedBuffer(int buffer_size) {
		this.maxBufferSize = buffer_size;
	}

	public boolean isListEmpty() {
		return objectList.size() == 0 ? true : false;
	}

	public boolean isListFull() {
		return objectList.size() >= this.maxBufferSize ? true : false;
	}

	public Object get() throws InterruptedException {
		Object temp;
		try {
			internallock.lock();
			while (isListEmpty()) { 
				this.isEmpty.await();
			}
			temp = objectList.get(0);
			objectList.remove(0);
			this.isFull.signal();
			return temp;
		} finally {
			internallock.unlock();
		}
	}

	public void put(Object o) throws InterruptedException {
		try {
			internallock.lock();
			while (isListFull()) { 
				this.isFull.await();
			}
			objectList.add(o);
			this.isEmpty.signal();
		} finally {
			internallock.unlock();
		}
	}

}
