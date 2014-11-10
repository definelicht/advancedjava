package assignment1;

public class AddSalary implements Combination<Employee> {

  public int neutral() {
    return 0;
  }

  public int combine(int x, int y) {
    return x + y;
  }

  public int projectInt(Employee employee) {
    return employee.getSalary();
  }

}
