package concurrency.extended;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleNamePrinterHarness {

	public static void main(String[] args) {
		SimpleNamePrinter printer1 = new SimpleNamePrinter();
		printer1.setMyName("foo");
		SimpleNamePrinter printer2 = new SimpleNamePrinter();
		printer2.setMyName("bar");
		printer1.setRemotePrinter(printer2);
		printer2.setRemotePrinter(printer1);		
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.submit(new PrinterTask(printer1));
		exec.submit(new PrinterTask(printer2));
		exec.shutdownNow();
	}

}
