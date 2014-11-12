/**
 * 
 */
package communication.jetty;

import org.eclipse.jetty.server.*;


/**
 * @author bonii
 *
 */
public class SimpleServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Server server = new Server(8080);
		server.setHandler(new ServerXStreamHandler());
		server.start();
		server.join();
	}

}
