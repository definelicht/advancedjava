package concurrency.basic;



public class SimpleDaemonHarness {
	static int NUMBER_THREADS = 5;
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[NUMBER_THREADS];
		for(int i=0;i<NUMBER_THREADS;i++) {
			threads[i] = new Thread(new SimpleDaemon(i));
			threads[i].setDaemon(true);
			threads[i].start();
		}
		
		System.out.println("Hello from Main Harness");
		
		/*for(int i=0;i<NUMBER_THREADS;i++) {
			threads[i].interrupt();	
		}*/
	}

}
