package com.tfg;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/hello")
public class HelloEndpoint {
	
	private Session session;
	
	@OnOpen
	public void onCreateSession(Session session) {
		this.session = session;
	}
	
	@OnMessage
	public void onTextMessage(String message) {
		System.out.println("message -> "+message);
		if(this.session!=null && this.session.isOpen()) {
			try {
				this.session.getBasicRemote().sendText("From server "+message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
