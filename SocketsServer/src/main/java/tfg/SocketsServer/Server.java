package tfg.SocketsServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class Server 
{
    public static void main( String[] args ) throws IOException
    {
    	ServerSocket ss = new ServerSocket(4999);
		do {
	    	Socket s = ss.accept();
			
			System.out.println("Client conected");
		}
		while (true);
    }
}
