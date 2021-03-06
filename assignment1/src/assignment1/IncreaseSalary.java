package assignment1;

public class IncreaseSalary implements Mutation<Employee> {

  public void mutate(Employee employee) {
    int age = employee.getAge();
    if (age > 40) {
      employee.setSalary(employee.getSalary() + age/2);
    }
  }

}
