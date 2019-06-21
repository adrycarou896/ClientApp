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
    	Socket s = new Socket("localhost", 4999);
    	ObjectInputStream dataInput = new ObjectInputStream(s.getInputStream());
    	String text;
		try {
			text = String.valueOf(dataInput.readObject());
			System.out.println(text);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}
