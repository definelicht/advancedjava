package concurrency.extended;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyBoundedBuffer implements BoundedBuffer {
	private List<Object> objectList = new ArrayList<Object>();
	private int maxBufferSize;

	public WaitNotifyBoundedBuffer(int buffer_size) {
		this.maxBufferSize = buffer_size;
	}

	public synchronized boolean isListEmpty() {
		if (objectList.size() == 0) {
			return true;
		}
		return false;
	}

	public synchronized boolean isListFull() {
		if (this.objectList.size() >= maxBufferSize) {
			return true;
		}
		return false;
	}

	public synchronized Object get() throws InterruptedException {
		Object temp;
		while (isListEmpty()) {
			wait();
		}
		temp = objectList.get(0);
		objectList.remove(0);
		notify();
		return temp;
	}

	public synchronized void put(Object o) throws InterruptedException {
		while (isListFull()) {
			wait();
		}
		objectList.add(o);
		notify();
	}

}
