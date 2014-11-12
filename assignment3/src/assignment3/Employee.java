package assignment3;

/**
 * Employee represents an employee record
 *
 * @author bonii
 *
 */
public class Employee {

	int id; // Primary key assumed unique
	String name;
	int department;
	float salary;

	public Employee(int id, String name, int department, float salary) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}

	public boolean equals(Employee emp) {
		return (this.getId() == emp.getId());
	}

	public int hashCode() {
		return id;
	}
}
