import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Stack;

public class Receiver implements Runnable
{
	public void run ()
	{
		try {
			// create a new socket, we must bind a port number to the socket to get data
			DatagramSocket ds = new DatagramSocket(10001);
			// prepare the buffer to hold the received data
			byte[] buffer = new byte[1024];
			// create a new data packet based on the buffer
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			// create empty array
			byte[] data = null;
			
			while (true) {
				System.out.println("in while loop");
				// receive data packet
				ds.receive(dp);
				
				// analyze the received data
				data = dp.getData();
		
				// set message to what was sent
				String message = new String(data).substring(0,dp.getLength());
				
				System.out.println("received a message");
				System.out.println(message);
			}
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}
}
