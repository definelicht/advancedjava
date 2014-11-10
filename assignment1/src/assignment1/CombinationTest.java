package assignment1;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class CombinationTest {

  private Random random = new Random();

  private Employee genEmployee() {
    return new Employee("John Doe", 20 + random.nextInt(41),
                        3000 + random.nextInt(2001));
  }

  private void testCombination(Combination method) {
    Employee employee0 = genEmployee();
    Employee employee1 = genEmployee();
    Employee employee2;
    for (int i = 0; i < 1000; ++i) {
      employee2 = genEmployee();
      org.junit.Assert.assertTrue(
        method.combine(
          employee0.getSalary(),
          method.combine(employee1.getSalary(), employee2.getSalary())
        )
        ==
        method.combine(
          method.combine(employee0.getSalary(), employee1.getSalary()),
          employee2.getSalary()
        )
      );
      org.junit.Assert.assertTrue(
        method.combine(method.neutral(), employee0.getSalary())
        ==
        employee0.getSalary()
        &&
        method.combine(employee0.getSalary(), method.neutral())
        ==
        employee0.getSalary()
      );
      // Recycle previously generated employees
      employee0 = employee1;
      employee1 = employee2;
    }
  }

  @Test
  public void testAddSalary() {
    testCombination(new AddSalary());
  }

  @Test
  public void testMinAge() {
    testCombination(new MinAge());
  }

}
