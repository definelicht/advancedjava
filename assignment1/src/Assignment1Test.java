import assignment1.CombinationTest;
import assignment1.MapTest;

public class Assignment1Test {

  public static void main(String[] args) {

    org.junit.runner.JUnitCore.main(
      CombinationTest.class.getName(),
      MapTest.class.getName()
    );

  }

}
