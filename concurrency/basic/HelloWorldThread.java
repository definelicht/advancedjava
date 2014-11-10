package concurrency.basic;

public class HelloWorldThread extends Thread {

	public void run() {
		System.out.println("Hello World from "+Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		new HelloWorldThread().start();
		System.out.println("Hello from "+Thread.currentThread().getName());
	}

}
