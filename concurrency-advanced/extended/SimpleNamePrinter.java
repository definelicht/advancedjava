package concurrency.extended;

class SimpleNamePrinter {
	private String myName;
	private SimpleNamePrinter remotePrinter;
	
	public synchronized void setMyName(String myName) {
		this.myName = myName;
	}

	public synchronized void setRemotePrinter(SimpleNamePrinter otherPrinter) {
		this.remotePrinter = otherPrinter;
	}

	public synchronized void printNames() {
		this.printName();
		remotePrinter.printName();
	}
	
	public synchronized void printName() {
		System.out.println(myName);
	}

}
