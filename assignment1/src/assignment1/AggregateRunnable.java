package assignment1;

import java.util.List;

public class AggregateRunnable<T> implements Runnable {

  Combination<T> combination;
  List<T> list;
  AggregateResult result;

  public AggregateRunnable(Combination<T> combination_, List<T> list_,
                           AggregateResult result_) {
    combination = combination_;
    list = list_;
    result = result_;
  }

  public void run() {
    if (list.size() == 1) {
      // If one element is left, just return that element
      result.set(combination.projectInt(list.get(0)));
      return;
    }
    if (list.size() == 2) {
      // If only two elements are left, combine and return
      result.set(combination.combine(combination.projectInt(list.get(0)),
                                     combination.projectInt(list.get(1))));
      return;
    }
    // Otherwise the list is divided to be conquered by two new runnables
    int dividedSize = list.size()/2;
    AggregateResult resultA = new AggregateResult();
    AggregateResult resultB = new AggregateResult();
    Thread threadA = new Thread(new AggregateRunnable<T>(
        combination, list.subList(0, dividedSize), resultA));
    threadA.start();
    Thread threadB = new Thread(new AggregateRunnable<T>(
        combination, list.subList(dividedSize, list.size()), resultB));
    threadB.start();
    try {
      threadA.join();
      threadB.join();
    } catch (InterruptedException e) {
      // Crash and burn
      assert false;
    }
    result.set(combination.combine(resultA.get(), resultB.get()));
  }

}
