package assignment1;

public class LowerCaseName implements Mutation<Employee> {

  public void mutate(Employee employee) {
    employee.setName(employee.getName().toLowerCase());
  }

}
