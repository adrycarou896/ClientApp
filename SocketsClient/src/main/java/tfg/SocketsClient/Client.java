package tfg.SocketsClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
    			String personJSON = String.valueOf(input.readObject());
    			System.out.println(personJSON);
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
}
