package communication.jetty;


import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;


public class SimpleClient {
	private static HttpClient client;

	public static void startClient() throws Exception {
		client = new HttpClient();
		//client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		//client.setMaxConnectionsPerAddress(300);
		//client.setThreadPool(new QueuedThreadPool(20));
		//client.setTimeout(30000);
		client.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			startClient();
			sendGetRequest();
			sendPostRequest();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			client.stop();
		}

	}
	
	public static void sendGetRequest() throws Exception {
		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("GET");
		exchange.setURL("http://localhost:8080/foo?time=a&pan=b&time=b");
		client.send(exchange);
		int exchangeState = exchange.waitForDone();
		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			System.out.println("Exchange completed");
			System.out.println(exchange.getResponseContent());
		} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
			System.out.println("Error occured");
		} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
			System.out.println("Request timed out");
		}
	}
	
	public static void sendPostRequest() throws Exception {
		ContentExchange exchange = new ContentExchange();
		exchange.setMethod("POST");
		exchange.setURL("http://localhost:8080/foo?time=a&pan=b&time=b");
		Buffer postData = new ByteArrayBuffer("lkdsj sdlkj lskdj \n ksjd lksdj ");
		exchange.setRequestContent(postData);
		
		client.send(exchange);
		int exchangeState = exchange.waitForDone();
		if (exchangeState == HttpExchange.STATUS_COMPLETED) {
			System.out.println("Exchange completed");
			System.out.println(exchange.getResponseContent());
		} else if (exchangeState == HttpExchange.STATUS_EXCEPTED) {
			System.out.println("Error occured");
		} else if (exchangeState == HttpExchange.STATUS_EXPIRED) {
			System.out.println("Request timed out");
		}
	}

}
