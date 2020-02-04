package dpigUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Calendar;
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

	private int port;
	private String direction = "localhost";
	private boolean connectionOpen = false;
	
	@Override
    public void run()
    {
    	while(this.connectionOpen){
    		
    		Socket s;
			try {
				s = new Socket(direction, port);
				ObjectInputStream input = new ObjectInputStream(s.getInputStream());
				
    			String dataJSON = String.valueOf(input.readObject());
    			
    			JSONObject data = new JSONObject(dataJSON);
    			
    			JSONObject personJSON = (JSONObject) data.get("person");
    			String person = personJSON.getString("name");
    			
    			JSONObject eventJSON = (JSONObject) data.get("event");
    			if(eventJSON!=null){
    				Long timeInMillis = eventJSON.getLong("accomplishedDate");
    				Date accomplishedDate = getDateTime(timeInMillis);
    				
        			String action = eventJSON.getString("action");
        			int hall = eventJSON.getInt("hall");
    				
    				Event event = new Event(person, accomplishedDate, action, hall);
    				if(action.toLowerCase().equals("in")){
    					in(event);
    				}
    				else if(action.toLowerCase().equals("enter")){
    					enter(event);
    				}
    				else if(action.toLowerCase().equals("out")){
    					out(event);
    				}
    			}
    			else{
    				JSONObject alertJSON = (JSONObject) data.get("alert");
    				
    				Long timeInMillisDateAlert = alertJSON.getLong("dateAlert");
    				Date dateAlert = getDateTime(timeInMillisDateAlert);
        			
    				String operatorAlert = alertJSON.getString("operator");
    				
    				JSONObject eventAlertJSON = (JSONObject) alertJSON.get("event");
    				
    				Long timeInMillis = eventAlertJSON.getLong("accomplishedDate");
    				Date accomplishedDate = getDateTime(timeInMillis);
    				
        			String action = eventAlertJSON.getString("action");
        			int hall = eventAlertJSON.getInt("hall");
    				
    				Event event = new Event(person, accomplishedDate, action, hall);
    				Alert alert = new Alert(event, dateAlert);
    				if(operatorAlert.toLowerCase().equals("max")){
    					alertMax(alert);
    				}
    				else if(operatorAlert.toLowerCase().equals("min")){
    					alertMin(alert);
    				}
    				
    			}		
	    			
			} catch (IOException | ClassNotFoundException e1) {
				e1.printStackTrace();
				this.connectionOpen = false;
			}
    	}
    	
    }
	
	private Date getDateTime(Long timeInMillis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMillis);
		return calendar.getTime();
	}
    
	public void startConnectionToDPIGServer(String direction, int port){
		this.direction = direction;
		this.port = port;
		this.connectionOpen = true;
		start();
	}
	
	public void startConnectionToDPIGServer(int port){
		this.port = port;
		this.direction = "localhost";
		this.connectionOpen = true;
		start();
	}
	
	public abstract void in(Event event);
    public abstract void enter(Event event);
    public abstract void out(Event event);
    
    public abstract void alertMax(Alert alert);
    public abstract void alertMin(Alert alert);
    
    public void stopThread(){
    	this.connectionOpen = false;
    }
}
