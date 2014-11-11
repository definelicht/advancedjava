package concurrency.extended;

public interface ReaderWriter {
	
	public void acquireExclusive() throws InterruptedException; 
	
	public void releaseExclusive() throws InterruptedException;
	
	public void acquireShared() throws InterruptedException;
	
	public void releaseShared() throws InterruptedException;

}
