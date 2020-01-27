package dpigUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import org.json.JSONObject;

import dpigUser.rules.Alert;
import dpigUser.rules.Event;

/**
 * Hello world!
 *
 */
public abstract class DPIGTemplateUser extends Thread
{
	private boolean continuar = true;
	
	@Override
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
    			String person = personJSON.getString("name");
    			
    			JSONObject eventJSON = (JSONObject) data.get("event");
    			if(eventJSON!=null){
    				Date accomplishedDate = new Date();
        			accomplishedDate.setTime(eventJSON.getLong("accomplishedDate"));
        			String eventMessage = eventJSON.getString("message");
        			
        			System.out.println(person+" a las "+accomplishedDate.toString()+" "+eventMessage);
        			System.out.println(eventJSON.toString());
        			
        			String[] messageArray = eventMessage.split(" ");
        			
    				String accion=messageArray[0];
    				int hall=Integer.parseInt(messageArray[messageArray.length-1]);
    				
    				Event event = new Event(person, accomplishedDate, hall);
    				if(accion.toLowerCase().equals("enter")){
    					enter(event);
    				}
    				else if(accion.toLowerCase().equals("out")){
    					out(event);
    				}
    			}
    			else{
    				JSONObject alertJSON = (JSONObject) data.get("alert");
    				Date dateAlert = new Date();
    				dateAlert.setTime(alertJSON.getLong("dateAlert"));
        			
    				String operatorAlert = alertJSON.getString("operator");
    				
    				JSONObject eventAlertJSON = (JSONObject) alertJSON.get("event");
    				Date accomplishedDate = new Date();
        			accomplishedDate.setTime(eventAlertJSON.getLong("accomplishedDate"));
    				String eventAlertMessage = eventAlertJSON.getString("message");
    				
    				String[] messageArray = eventAlertMessage.split(" ");
    				int eventHall=Integer.parseInt(messageArray[messageArray.length-1]);
    				
    				Event event = new Event(person, accomplishedDate, eventHall);
    				Alert alert = new Alert(event, dateAlert);
    				if(operatorAlert.toLowerCase().equals("max")){
    					alertMax(alert);
    				}
    				else if(operatorAlert.toLowerCase().equals("min")){
    					alertMin(alert);
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
    
    public abstract void enter(Event event);
    public abstract void out(Event event);
    
    public abstract void alertMax(Alert alert);
    public abstract void alertMin(Alert alert);
    
    public void stopThread(){
    	this.continuar = false;
    }
}
