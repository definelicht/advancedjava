package junit;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class RingCounterTest {

	private static final int N_TEST = 1000;
	Random r = new Random();
	
	private RingCounter fresh() {
		return new SimpleRingCounter();
	}
	
	@Test
	public void testGet() {
		RingCounter rc = fresh();
		assertEquals(1, rc.get());
	}
	
	@Test
	public void testConsistency() {
		for (int i = 0; i < N_TEST; i++) {
			RingCounter rc = fresh();
			rc.inc(r.nextInt(1000));
			assertTrue(rc.get() > 0 && rc.get() <= 16);
		}
	}


	@Test
	public void testInc() {
		RingCounter rc = fresh();
		rc.inc(16);
		assertEquals(1, rc.get());
		rc.inc(0);
		assertEquals(1, rc.get());
		rc.inc(5);
		assertEquals(6, rc.get());
		rc.inc(32);
		assertEquals(6, rc.get());
		try {
			rc.inc(-4);
			fail("Expected exception");
		} catch (IllegalArgumentException e) {
			// All good
		}
	}
	
	@Test
	public void testSerializability() throws InterruptedException {
		RingCounter rc1 = fresh();
		RingCounter rc2 = fresh();
		
		int[] incs = new int[1000];
		
		for (int i = 0; i < incs.length; i++) {
			incs[i] = r.nextInt(1000);
		}
		
		Thread[] threads = new Thread[1000];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Incrementer(rc1, incs[i]));
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		for (int i = 0; i < incs.length; i++) {
			rc2.inc(incs[i]);
		}
		
		assertEquals(rc2.get(), rc1.get());
	}

	public class Incrementer implements Runnable {
		
		private RingCounter rc;
		private int i;

		public Incrementer(RingCounter rc1, int i) {
			this.rc = rc1;
			this.i = i;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			rc.inc(i);
		}
		
	}
}
