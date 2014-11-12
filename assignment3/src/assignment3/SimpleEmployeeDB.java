package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class SimpleEmployeeDB implements EmployeeDB {

	// Use a HashMap to efficiently look up and add to specific departments
	private final HashMap<Integer, ArrayList<Employee>> database;

	public SimpleEmployeeDB(List<Integer> departmentIds) {
		database = new HashMap<Integer, ArrayList<Employee>>(departmentIds.size());
		for (Integer i : departmentIds) {
			database.put(i, new ArrayList<Employee>());
		}
	}

	@Override
	public synchronized void addEmployee(Employee employee)
			throws DepartmentNotFoundException {
		int department = employee.getDepartment();
		if (!database.containsKey(department)) {
			throw new DepartmentNotFoundException();
		}
		database.get(department).add(employee);
	}

	/**
	 * Creates a new list spliced from the lists corresponding to the department
	 * ids passed. If a department is supported, the id will be ignored.
	 */
	@Override
	public synchronized List<Employee> listEmployeesInDept(
			List<Integer> departmentIds) {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		for (Integer i : departmentIds) {
			ArrayList<Employee> departmentEmployees = database.get(i);
			if (departmentEmployees == null) continue;
			employeeList.addAll(departmentEmployees);
		}
		return employeeList;
	}

	@Override
	public synchronized void cleanupDB() {
		database.clear();
	}

	@Override
	public synchronized void incrementSalaryOfDepartment(
			List<SalaryIncrement> salaryIncrements)
			throws DepartmentNotFoundException, NegativeSalaryIncrementException {
		// To ensure all/nothing semantics the increment is performed in two passes:
		// a verification and an execution phase.
		for (SalaryIncrement i : salaryIncrements) {
			if (i.getIncrementBy() < 0) throw new NegativeSalaryIncrementException();
			if (database.containsKey(i.getDepartment())) {
				throw new DepartmentNotFoundException();
			}
		}
		for (SalaryIncrement i : salaryIncrements) {
			ArrayList<Employee> departmentEmployees = database.get(i.getDepartment());
			float salaryIncrease = i.getIncrementBy();
			for (Employee e : departmentEmployees) {
				e.setSalary(e.getSalary() + salaryIncrease);
			}
		}
	}

}
