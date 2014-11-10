package assignment1;

import java.util.List;

public class MapRunnable<T> implements Runnable {

  private Map<T> map;
  private Mutation<T> mutation;
  private List<T> list;

  public MapRunnable(Map<T> map_, Mutation<T> mutation_, List<T> list_) {
    map = map_;
    mutation = mutation_;
    list = list_;
  }

  public void run() {
    map.map(mutation, list);
  }

}
