package communication.jetty;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ServerXStreamHandler extends AbstractHandler {

	public ServerXStreamHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html;charset=utf-8");
		res.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		String uri = req.getRequestURI().trim().toUpperCase();
		XStream xmlStream = new XStream(new StaxDriver());
		
		int len = req.getContentLength();
		BufferedReader reqReader = req.getReader();
		char[] cbuf = new char[len];
		reqReader.read(cbuf);
		reqReader.close();
		String content = new String(cbuf);
		
		if(uri.equalsIgnoreCase("/PERSONS")) {
			List<Person> persons = (List<Person>) xmlStream.fromXML(content);
			System.out.println(persons);
		} else if(uri.equalsIgnoreCase("/EXTPERSONS")) {
			List<ExtendedPerson> extpers = (List<ExtendedPerson>) xmlStream.fromXML(content);
			System.out.println(extpers);
		}
		
		

	}

}
