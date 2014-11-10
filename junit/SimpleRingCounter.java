package junit;

public class SimpleRingCounter implements RingCounter {

	private int x = 0;

	@Override
	public int get() {
		return x + 1;
	}

	@Override
	public synchronized void inc(int n) throws IllegalArgumentException {
		if(n < 0)
			throw new IllegalArgumentException();
		x = (x + n) & 15;
	}

}
