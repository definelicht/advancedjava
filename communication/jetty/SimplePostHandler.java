package communication.jetty;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class SimplePostHandler extends AbstractHandler {

	public void handle(String target, Request baseRequest,
			HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		Map<String, String[]> parameterMap = null;

		res.setContentType("text/html;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		res.getWriter().println("Hello from server: URI was "+req.getRequestURI());

		if (req.getMethod().equalsIgnoreCase("GET")) {
			parameterMap = req.getParameterMap();
			Iterator<Map.Entry<String, String[]>> it = parameterMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Map.Entry<String, String[]> pairs = it.next();
				res.getWriter().println(
						"parameter name: " + pairs.getKey()
								+ " parameter values: ");
				for (String value : pairs.getValue()) {
					res.getWriter().println(value + ",");
				}
			}
	
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			int len = req.getContentLength();
			BufferedReader reqReader = req.getReader();
			char[] cbuf = new char[len];
			reqReader.read(cbuf);
			reqReader.close();
			res.getWriter().println(new String(cbuf));
		}

		
	}

}
