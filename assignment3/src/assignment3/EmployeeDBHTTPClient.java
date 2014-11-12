package assignment3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.ByteArrayBuffer;

/**
 * EmployeeDBHTTPClient implements the client side methods of EmployeeDB
 * interface using HTTP protocol. The methods must send HTTP requests to the
 * EmployeeDBHTTPServer
 *
 * @author bonii
 *
 */
public class EmployeeDBHTTPClient implements EmployeeDBClient, EmployeeDB {

	private static final String FILE_PATH =
			System.getProperty("user.dir") + "departmentservermapping.xml";

	private final HttpClient client = new HttpClient();
	private final Map<Integer, String> departmentToServerURLMap;

	public EmployeeDBHTTPClient() throws FileNotFoundException {
		//Returns a concurrent hashmap :-)
		departmentToServerURLMap =
		   Utility.getDepartmentToServerURLMapping(FILE_PATH);
	}

	@Override
	public void addEmployee(Employee employee)
	    throws DepartmentNotFoundException {

	  XStream xstream = new XStream();

		String serverUrl = getServerURLForDepartment(employee.getDepartment());
		ByteArrayBuffer employeeData = new
		    ByteArrayBuffer(xstream.toXML(employee));

		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("POST");
		exchange.setURL(serverUrl + "/addEmployee");
		exchange.setRequestContent(employeeData);

		try {
			client.send(exchange);
		} catch (IOException err) {
			// TODO: handle
		}
		int exchangeState;
		try {
			exchangeState = exchange.waitForDone();
		} catch (InterruptedException err) {
			// TODO: handle
			return;
		}

		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			// TODO: Handle response somehow?????
		} else {
			// TODO: Handle this
			// throw new ExchangeFailedException(
			//     "addEmployee failed to POST employee to " + serverUrl);
		}
	}

	@Override
	public List<Employee> listEmployeesInDept(List<Integer> departmentIds) {

		XStream xstream = new XStream();

		ArrayList<ContentExchange> exchanges =
		    new ArrayList<ContentExchange>(departmentIds.size());

		String serverUrl;
		for (Integer i : departmentIds) {
			try {
				serverUrl = getServerURLForDepartment(i);
			} catch (DepartmentNotFoundException err) {
				// TODO: handle
				return new ArrayList<Employee>();
			}
			ByteArrayBuffer listData =
			    new ByteArrayBuffer(xstream.toXML(departmentIds));

			ContentExchange exchange = new ContentExchange();
			exchange.setMethod("POST");
			exchange.setURL(serverUrl + "/listEmployeesInDept");
			exchange.setRequestContent(listData);
			exchanges.add(exchange);
			try {
			  client.send(exchange);
			} catch (IOException err) {
				// TODO: handle
			}
		}

		// Use a HashMap to avoid duplicate employees
		HashMap<Integer, Employee> employeeList = new HashMap<Integer, Employee>();

		for (ContentExchange ex : exchanges) {
			try {
				if (ex.waitForDone() == HttpExchange.STATUS_COMPLETED) {
					try {
						// TODO: retrieve and parse response
						List<Employee> retrievedEmployees = new ArrayList<Employee>();
						for (Employee em : retrievedEmployees) {
							employeeList.put(em.getId(), em);
						}
					} catch (Exception err) {
						// TODO: Handle this
					}
				} else {
					// TODO: Handle this
				}
		  } catch (InterruptedException err) {
				// TODO: handle this
			}
		}

		return new ArrayList<Employee>(employeeList.values());
	}

	@Override
	public void incrementSalaryOfDepartment(
			List<SalaryIncrement> salaryIncrements)
			throws DepartmentNotFoundException, NegativeSalaryIncrementException {
		// TODO: implement
	}

	@Override
	public void cleanupDB() {
		// TODO: implement
	}

	/**
	 * Returns the server URL (starting with http:// and ending with /) to
	 * contact for a department
	 */
	public String getServerURLForDepartment(int departmentId)
			throws DepartmentNotFoundException {
		if (!departmentToServerURLMap.containsKey(departmentId)) {
			throw new DepartmentNotFoundException("Department " + departmentId +
			                                      " does not exist in mapping.");
		}
		return departmentToServerURLMap.get(departmentId);
	}

}
