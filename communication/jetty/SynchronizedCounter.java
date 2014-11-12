package communication.jetty;

class SynchronizedCounter {

    private static int c = 0;

    public static synchronized void increment() {
        c++;
    }
    public static synchronized void decrement() {
        c--;
    }
    
    public static synchronized int value() {
        return c;
    }
    
    public static synchronized void addBy(int value) {
    	c+=value;
    }
}

