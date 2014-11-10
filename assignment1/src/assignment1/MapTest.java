package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class MapTest extends MyTest {

  // dataSet() is implemented in MyTest

  private void testMap(Map<Employee> map) {
    List<Employee> list = dataSet();
    IncreaseSalary increaseSalary = new IncreaseSalary();
    map.map(increaseSalary, list);
    int sum = 0;
    for (Employee e : list) {
      sum += e.getSalary();
    }
    org.junit.Assert.assertTrue(sum == 1150);
  }

  @Test
  public void testMapSequential() {
    testMap(new MapSequential<Employee>());
  }

  @Test
  public void testMapParallel() {
    testMap(new MapParallel<Employee>());
  }

  @Test
  public void testMapChunked() {
    testMap(new MapChunked<Employee>());
  }

}
