/**
 * 
 */
package communication.jetty;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @author bonii
 * 
 */
public class SimpleXStreamDemo {
	private static HttpClient client;

	public static void startClient() throws Exception {
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.setMaxConnectionsPerAddress(300);
		client.setThreadPool(new QueuedThreadPool(20));
		client.setTimeout(30000);
		client.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		startClient();

		
		XStream xmlStream = new XStream(new StaxDriver()); // Keep this around,
															// you dont have to
															// do this everytime
		
		Person per = new Person(1, "John");
		
		String xmlString = xmlStream.toXML(per);
		
		System.out.println(xmlString);
		
		Person newper = (Person) xmlStream.fromXML(xmlString);
		
		System.out.println(per);
		assert(per.equals(newper));
		
		Person per1 = new Person(10, "Perry");
		List<Person> persons = new ArrayList<Person>();
		persons.add(per);
		persons.add(per1);

		xmlString = xmlStream.toXML(persons);
		
		System.out.println(xmlString);
		List<Person> newpersons = (List<Person>) xmlStream.fromXML(xmlString);
		System.out.println(newpersons);
		assert(persons.equals(newpersons));
		
		
		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("POST");
		exchange.setURL("http://localhost:8080/persons");
		Buffer buffer = new ByteArrayBuffer(xmlString);
		exchange.setRequestContent(buffer);
		client.send(exchange);
		int exchangeState = exchange.waitForDone();
		
		List<String> emails1 = new ArrayList<String>();
		emails1.add("tom@gmail.com");
		emails1.add("tom@yahoo.com");
		ExtendedPerson exper1 = new ExtendedPerson(12, "Tom", emails1);
		List<String> emails2 = new ArrayList<String>();
		emails2.add("ben@gmail.com");
		emails2.add("ben@yahoo.com");
		ExtendedPerson exper2 = new ExtendedPerson(15, "Ben", emails2);
		List<ExtendedPerson> expers = new ArrayList<ExtendedPerson>();
		expers.add(exper1);
		expers.add(exper2);
		xmlString = xmlStream.toXML(expers);
		System.out.println(xmlString);
		
		List<ExtendedPerson> newexpers = (List<ExtendedPerson>) xmlStream.fromXML(xmlString);
		System.out.println(newexpers);
		assert(expers.equals(newexpers));
		
		exchange = new ContentExchange();
		exchange.setMethod("POST");
		exchange.setURL("http://localhost:8080/extpersons");
		buffer = new ByteArrayBuffer(xmlString);
		exchange.setRequestContent(buffer);
		client.send(exchange);
		exchangeState = exchange.waitForDone();
		
		client.stop();
		
	}

}
