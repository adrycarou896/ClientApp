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
public class Client 
{
    public static void main( String[] args ) throws UnknownHostException, IOException
    {
    	while(true){
    		Socket s = new Socket("localhost", 4999);
        	ObjectInputStream input = new ObjectInputStream(s.getInputStream());
        	try {
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
    			
    			
    			
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
}
