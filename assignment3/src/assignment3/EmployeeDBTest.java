package assignment3;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

/**
 * Generated JUnit test class to test the EmployeeDB interface. It can be used
 * to test both the client and the server implementation.
 *
 * @author bonii
 *
 */
public class EmployeeDBTest {

	private static EmployeeDB employeeDB;

	/**
	 * The method runs just once before the test cases run. If you want to
	 * change the behavior look at @Before and other annotations in JUnit
	 *
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		List<Integer> departmentIds = Arrays.asList(0, 1, 2);
		employeeDB = new SimpleEmployeeDB(departmentIds);

		// By setting the employeeDB to either the server implementation first, you
		// can write the server implementation and test it. You dont even have to
		// run the Jetty HTTP server, you can just implement SimpleEmployeeDB and
		// test it.
		//
		// employeeDB = new EmployeeDBHTTPClient(); Once you have written the
		// client you can test the full system for the same test cases and it
		// should work, welcome to Unit testing :-)
		//
	}

	/**
	 * This method is run just once after the test cases finish to cleanup. If
	 * you want to change the behavior look at @After and other annotations in
	 * JUnit
	 */
	@AfterClass
	public static void tearDownAfterClass() {
	}

	public void testDatabase(EmployeeDB employeeDB) {
		for (int i = 0; i < 10; ++i) {
			Employee employee = new Employee(i, "Bob"+i, 0, 1000);
			try {
				employeeDB.addEmployee(employee);
			} catch (DepartmentNotFoundException e) {
				org.junit.Assert.assertTrue(false);
			}
		}
		List<Integer> departmentIds = Arrays.asList(0, 1, 2);
		List<Employee> list = employeeDB.listEmployeesInDept(departmentIds);
		org.junit.Assert.assertTrue(list.size() == 10);
	}

	@Test
	public void testSimpleEmployeeDB() {
		testDatabase(employeeDB);
	}

	@Test
	public void testHTTPEmployeeDB() {
		// TODO: Test HTTP implementation
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main(
		  EmployeeDBTest.class.getName()
		);
	}

}
