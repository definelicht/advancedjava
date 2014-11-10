package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Assert;
import org.junit.Test;

public class MapTest {

  private List<Employee> dataSet() {
    List<Employee> list = new ArrayList<Employee>();
    for (int i = 0; i < 10; ++i) {
      // Half of the population is 30, the other is 60, giving the expected sum
      // 5*100 + 5*(100+60/2) = 1150
      list.add(new Employee("Name" + i, 30 + (i>=5 ? 30 : 0), 100));
    }
    return list;
  }

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
