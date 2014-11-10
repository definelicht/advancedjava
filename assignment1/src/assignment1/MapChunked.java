package assignment1;

import java.util.ArrayList;
import java.util.List;

/** Spawns up to three parallel maps treating sublists of the input list */
public class MapChunked<T> implements Map<T> {

  public void map(Mutation<T> mutation, List<T> list) {
    int itemsProcessed = 0;
    int itemsTotal = list.size();
    int itemsPerThread = list.size() >= 3 ? itemsTotal / 3 : 1;
    List<Thread> threads = new ArrayList<Thread>();
    while (itemsProcessed < itemsTotal) {
      // Use the step size or the number of remaining elements, whichever is
      // lower
      int subListSize = Math.min(itemsTotal - itemsProcessed, itemsPerThread);
      List<T> subList = list.subList(itemsProcessed,
                                     itemsProcessed + subListSize);
      Thread thread = new Thread(new MapRunnable<T>(new MapParallel<T>(),
                                                    mutation, subList));
      thread.start();
      threads.add(thread);
      itemsProcessed += itemsPerThread;
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
