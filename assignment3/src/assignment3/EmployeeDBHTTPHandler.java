package assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * EmployeeDBHTTPHandler is invoked when an HTTP request is received by the
 * EmployeeDBHTTPServer
 *
 * @author bonii
 *
 */
public class EmployeeDBHTTPHandler extends AbstractHandler {

	private final SimpleEmployeeDB db;

	public EmployeeDBHTTPHandler(SimpleEmployeeDB db) {
		this.db = db;
	}

	/**
	 * Shamelessly stolen from communication slides
	 */
	private String extractPost(HttpServletRequest req) {
		try {
			BufferedReader reqReader = req.getReader();
			char[] characterBuffer = new char[req.getContentLength()];
			reqReader.read(characterBuffer);
			reqReader.close();
			return new String(characterBuffer);
		} catch (IOException err) {
			// TODO: handle this
			assert false;
		}
		return "";
	}

	/**
	 * Although this method is thread-safe, what it invokes is not thread-safe
	 */
	public void handle(String target, Request baseRequest,
			HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		XStream xstream = new XStream();

		String uri = req.getRequestURI().trim().toUpperCase();
		if (uri.equalsIgnoreCase("/addemployee")) {
			try {
				Employee employee = (Employee)xstream.fromXML(extractPost(req));
				db.addEmployee(employee);
				res.setStatus(HttpServletResponse.SC_OK);
			} catch (Exception err) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				res.getWriter().print(URLEncoder.encode(xstream.toXML(err)));
			}
			baseRequest.setHandled(true);
		}

		// TODO: handle listEmployeesInDept, incrementSalaryOfDepartment
		//       and cleanupDB
	}

}
