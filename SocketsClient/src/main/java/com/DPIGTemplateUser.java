package com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public abstract class DPIGTemplateUser extends Thread
{
	private boolean continuar = true;
	
    public void run()
    {
    	while(this.continuar){
    		
    		Socket s;
			try {
				s = new Socket("localhost", 4999);
				ObjectInputStream input = new ObjectInputStream(s.getInputStream());
				
    			String dataJSON = String.valueOf(input.readObject());
    			//System.out.println(dataJSON);
    			
    			JSONObject data = new JSONObject(dataJSON);
    			
    			JSONObject personJSON = (JSONObject) data.get("person");
    			String personName = personJSON.getString("name");
    			
    			JSONObject eventJSON = (JSONObject) data.get("event");
    			if(eventJSON!=null){
    				Date dateEvent = new Date();
        			dateEvent.setTime(eventJSON.getLong("date"));
        			String messageEvent = eventJSON.getString("mensaje");
        			
        			System.out.println(personName+" a las "+dateEvent.toString()+" "+messageEvent);
        			System.out.println(eventJSON.toString());
        			
        			String[] messageArray = messageEvent.split(" ");
        			
    				String accion=messageArray[0];
    				int hall=Integer.parseInt(messageArray[messageArray.length-1]);
    				
    				if(accion.toLowerCase().equals("enter")){
    					enter(personName, dateEvent, hall);
    				}
    				else if(accion.toLowerCase().equals("out")){
    					out(personName, dateEvent, hall);
    				}
    			}
    			else{
    				JSONObject alertJSON = (JSONObject) data.get("alert");
    				Date dateAlert = new Date();
    				dateAlert.setTime(alertJSON.getLong("date"));
        			
    				String operatorAlert = alertJSON.getString("operator");
    				
    				JSONObject eventAlertJSON = (JSONObject) alertJSON.get("event");
    				String eventAlertMessage = eventAlertJSON.getString("mensaje");
    				
    				String[] messageArray = eventAlertMessage.split(" ");
    				String eventAlertAccion=messageArray[0];
    				
    				int eventAlertHall=Integer.parseInt(messageArray[messageArray.length-1]);
    				
    				if(operatorAlert.toLowerCase().equals("max")){
    					alertMax(personName, dateAlert, eventAlertAccion, eventAlertHall);
    				}
    				else if(operatorAlert.toLowerCase().equals("min")){
    					alertMin(personName, dateAlert, eventAlertAccion, eventAlertHall);
    				}
    				
    			}
    			
	    			
	    			
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    public abstract void enter(String personName, Date dateEvent, int hall);
    public abstract void out(String personName, Date dateEvent, int hall);
    
    public abstract void alertMax(String personName, Date dateAlert, String eventAccion, int eventHall);
    public abstract void alertMin(String personName, Date dateAlert, String eventAccion, int eventHall);
    
    public void stopThread(){
    	this.continuar = false;
    }
}
