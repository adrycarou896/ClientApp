package com.prueba.cometd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.server.CometDServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import sun.misc.BASE64Encoder;

public class Main {
	
	//private static BayeuxServer bayeux;
	
	private static final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
	public static void main(String [] args) throws Exception {
	        
		// Configure Jetty
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    server.setHandler(context);
        
	    // Configure Cometd
       CometDServlet servlet = new CometDServlet();
       context.addServlet(new ServletHolder(servlet), "/bayeux");
       // Start Jetty
		server.start();
		
		
		// Create a channel for our Java server to publish on
		BayeuxServer bayeux = servlet.getBayeux();
		String channelName = "/data";
		bayeux.createChannelIfAbsent(channelName, new ServerChannel.Initializer() {
			public void configureChannel(ConfigurableServerChannel channel)
		    {
		        channel.setPersistent(true);
		    }
		});
		final ServerChannel channel = bayeux.getChannel(channelName);
		

		// publish some random data
		byte [] bytes = new byte[40];
		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = (byte)(Math.random() * 255);
		}
		
		BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
		final String data = base64Encoder.encode(bytes);
		
		final Map<String, String> firstData = new HashMap<>();
		firstData.put("a", "b");
		//firstData.put("something", "val");
		
		// Maps are converted to JavaScript objects
		final Map<String, String> secondData = new HashMap<>();
		secondData.put("base64data", data);
		
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				//Session session = bayeux.newLocalSession("external");
			    //session.handshake();
				
				if (Math.random() < 0.5) {
					channel.publish(null, firstData);
					//channel.publish(null,firstData,"1");
				} else {
					channel.publish(null, secondData);
					//channel.publish(null,secondData,"2");
				}
			}
		}, 1, 1, TimeUnit.SECONDS);
		
		// Keep running until the server dies
		server.join();
	}
}
