package concurrency.extended;

public class ReaderWriterImpl implements ReaderWriter {
	private int readers = 0;
	
	public synchronized void acquireExclusive() throws InterruptedException {
		
		while(readers != 0) {
			wait();
		}
		readers = -1;
	}

	public synchronized void releaseExclusive() throws InterruptedException {
		readers = 0;
		notifyAll();
	}

	public synchronized void acquireShared() throws InterruptedException {

		while(readers < 0) {
			wait();
		}
		readers += 1;
	}

	public synchronized void releaseShared() throws InterruptedException {
		readers-=1;
		if(readers == 0) {
			notify();
		}
	}

}
