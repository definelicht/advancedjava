package assignment1;

public class MutationRunnable<T> implements Runnable {

  private Mutation<T> mutation;
  private T target;

  public MutationRunnable(Mutation<T> mutation_, T target_) {
    mutation = mutation_;
    target = target_;
  }

  public void run() {
    mutation.mutate(target);
  }

}
