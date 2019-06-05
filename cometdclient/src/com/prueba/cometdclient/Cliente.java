package com.prueba.cometdclient;

import java.util.HashMap;
import java.util.Map;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.client.ClientSessionChannel.MessageListener;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;

public class Cliente {
	
	public static void main(String[] args) {
		
		//String cometdURL = "http://localhost:8080/cometd/cometd";
		String cometdURL = "http://localhost:8080/bayeux";
		
		HttpClient httpClient = new HttpClient();
		 
		Map<String, Object> options = new HashMap<>();

		LongPollingTransport transport = new LongPollingTransport(options, httpClient) {

		    @Override
		    protected void customize(Request request) {
		      super.customize(request);
		    }
		  };	       
		  
		  BayeuxClient client = new BayeuxClient(cometdURL, transport);
		
		 //BayeuxClient client = new BayeuxClient(url, new LongPollingTransport());
		 client.handshake();
		 client.waitFor(1000, BayeuxClient.State.CONNECTED);

		 // Subscription to channels
		 ClientSessionChannel channel = client.getChannel("/data");
		 channel.subscribe(new MessageListener() {
			
			@Override
			public void onMessage(ClientSessionChannel canal, Message mensaje) {
				System.out.println(mensaje.getData());
			}
		});

		 // Publishing to channels
		 Map<String, Object> data = new HashMap<>();
		 data.put("name", "Java");
		 channel.publish(data);

		 // Disconnecting
		 client.disconnect();
		 client.waitFor(1000, BayeuxClient.State.DISCONNECTED);
	}

}
