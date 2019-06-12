package com.tfg.WebSocketsClient;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

public class App2 {
	
	final static CountDownLatch messageLatch = new CountDownLatch(1);
	
	public static void main(String[] args) {
	        try {
	            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	            HelloEndPoint endPoint = new HelloEndPoint();
	            
	            container.connectToServer(endPoint, new URI("ws://localhost:8080/WebSocketsServer/hello"));
	            endPoint.sendMessage("Hello from client");
	            
	            Thread.sleep(1000);
	            
	            /*String uri = "ws://echo.websocket.org:80/";
	            System.out.println("Connecting to " + uri);
	            container.connectToServer(ServerDashboardWebSocket.class, URI.create(uri));
	            messageLatch.await(100, TimeUnit.SECONDS);*/
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}

}
