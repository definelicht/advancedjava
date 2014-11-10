package assignment1;

import java.lang.Integer;

public class MinAge implements Combination<Employee> {

  public int neutral() {
    return java.lang.Integer.MAX_VALUE;
  }

  public int combine(int x, int y) {
    return Math.min(x, y);
  }

  public int projectInt(Employee employee) {
    return employee.getSalary();
  }

}
