package assignment2;

public class ASubscriber implements Subscriber, Runnable {

	final private LinkedList<int> discomfortLevels = new LinkedList<int>();

	public void run() {
		while (true) {
			int discomfortLevel = getDiscomfortWarning();
			processDiscomfortWarning(discomfortLevel);
		}
	}

	@Override
	public void pushDiscomfortWarning(int discomfortlevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processDiscomfortWarning(int discomfortLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDiscomfortWarning() {
		// TODO Auto-generated method stub
		return 0;
	}



}