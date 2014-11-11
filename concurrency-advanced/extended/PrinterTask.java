package concurrency.extended;

public class PrinterTask implements Runnable {
	SimpleNamePrinter namePrinter;

	PrinterTask(SimpleNamePrinter argument) {
		this.namePrinter = argument;
	}

	public void run() {
		namePrinter.printNames();
	}

}
