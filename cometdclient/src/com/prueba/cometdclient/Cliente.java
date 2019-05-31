package com.prueba.cometdclient;

import java.util.HashMap;
import java.util.Map;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.client.ClientSessionChannel.MessageListener;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;

public class Cliente {

	public static void main(String[] args) {
		
		String url = "http://localhost:8080/cometd/cometd";
		 BayeuxClient client = new BayeuxClient(url, new LongPollingTransport());
		 client.handshake();
		 client.waitFor(1000, BayeuxClient.State.CONNECTED);

		 // Subscription to channels
		 ClientSessionChannel channel = client.getChannel("/hello");
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