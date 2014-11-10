package assignment1;

import java.util.ArrayList;
import java.util.List;

class MyTest {

  protected List<Employee> dataSet() {
    List<Employee> list = new ArrayList<Employee>();
    for (int i = 0; i < 10; ++i) {
      // All employees have salary of 100, resulting in the sum:
      // 10*100 = 1000
      // In the case of IncreaseSalary, half of the population is 30, the other
      // is 60, giving the expected sum:
      // 5*100 + 5*(100+60/2) = 1150
      list.add(new Employee("Name" + i, 30 + (i>=5 ? 30 : 0), 100));
    }
    return list;
  }

}
