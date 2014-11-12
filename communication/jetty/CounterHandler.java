package communication.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class CounterHandler extends AbstractHandler {

	public void handle(String arg0, Request baseRequest, HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {

		
		String uri = req.getRequestURI().trim().toUpperCase();
		
		res.setContentType("text/html;charset=utf-8");
        res.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
		if(uri.equalsIgnoreCase("/INCREMENT")) {
			SynchronizedCounter.increment();
			res.getWriter().println("Incremented counter");
			
		} else if(uri.equalsIgnoreCase("/DECREMENT")) {
			SynchronizedCounter.decrement();
			res.getWriter().println("Decremented counter");
			
		} else if(uri.equalsIgnoreCase("/GETCOUNT")) {
			res.getWriter().println("Counter value is "+SynchronizedCounter.value());
			
		} else if(uri.equalsIgnoreCase("/ADDBY")) {
			int addBy = Integer.parseInt(req.getParameter("value"));
			
			SynchronizedCounter.addBy(addBy);
			res.getWriter().println("Counter incremented by "+addBy);
		}
        
	}

}
