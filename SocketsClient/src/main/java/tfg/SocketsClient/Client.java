package tfg.SocketsClient;

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
public abstract class Client extends Thread
{
    public void run()
    {
    	while(true){
    		
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
    			Date dateEvent = new Date();
    			dateEvent.setTime(eventJSON.getLong("date"));
    			String messageEvent = eventJSON.getString("mensaje");
    			
    			System.out.println(personName+" a las "+dateEvent.toString()+" "+messageEvent);
    			System.out.println(eventJSON.toString());
    			
    			String[] messageArray = messageEvent.split(" ");
    			
				String accion=messageArray[0];
				int clase=Integer.parseInt(messageArray[messageArray.length-1]);
				
				//int numActual = Integer.parseInt(label.getText());
				if(accion.toLowerCase().equals("entró")){
					enter();
					//label.setText(String.valueOf(numActual+1));
				}
				else if(accion.toLowerCase().equals("salió")){
					out();
					//label.setText(String.valueOf(numActual-1));
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
    
    public abstract void enter();
    
    public abstract void out();
}
