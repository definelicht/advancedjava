package assignment1;

import java.util.ArrayList;
import java.util.List;

public class MapParallel<T> implements Map<T> {

  public void map(Mutation<T> mutation, List<T> list) {
    List<Thread> threads = new ArrayList<Thread>();
    for (T i : list) {
      Thread thread = new Thread(new MutationRunnable<T>(mutation, i));
      thread.start();
      threads.add(thread);
    }
    try {
      for (Thread t : threads) {
        t.join();
      }
    } catch (InterruptedException e) {
      // Crash and burn
      assert false;
    }
  }

}
