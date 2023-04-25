package lab11a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Takeshi Hashimoto
 * This HotelServer class has a hotel and manage the rooms in December
 * client can connect to this server and reserve/cancel rooms
 *
 */
public class HotelServer {
	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		try (ServerSocket server = new ServerSocket(1181)){
			System.out.println("Waiting for client to connect...");
			
			while(true) {
				try {
					Socket client = server.accept();
					System.out.println("Client connected.");
					HotelService service = new HotelService(client, hotel);
					Thread t = new Thread(service);
					t.start();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
