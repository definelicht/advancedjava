package communication.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class SimpleConnectorServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Server server = new Server();
		
		SelectChannelConnector httpConnector = new SelectChannelConnector();
		httpConnector.setHost("127.0.0.1");
		httpConnector.setPort(8080);
		httpConnector.setRequestHeaderSize(8192);
		httpConnector.setMaxIdleTime(30000);
		httpConnector.setThreadPool(new QueuedThreadPool(20));
		
		server.setConnectors(new Connector[] {httpConnector});
		server.start();
		server.join();
	}

}
