package assignment1;

import java.util.List;

public class MapSequential<T> implements Map<T> {

  public void map(Mutation<T> mutation, List<T> list) {
    for (T i : list) {
      mutation.mutate(i);
    }
  }

}
