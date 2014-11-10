package assignment1;

/** Tiny wrapper to be able to pass an int by reference */
public class AggregateResult {

  private int result;

  public int get() {
    return result;
  }

  public void set(int result_) {
    result = result_;
  }

}
