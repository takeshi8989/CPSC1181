package lab11a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Takeshi Hashimoto
 * This HotelService class does hotel actions when users attempt to reserve/cancel, etc
 *
 */
public class HotelService implements Runnable {
	private Socket client;
	private String username;
	private Hotel hotel;
	private DataInputStream in;
	private DataOutputStream out;
	
	public HotelService(Socket c, Hotel h) {
		this.client = c;
		this.hotel = h;
	}

	@Override
	public void run() {
		try(Socket copy = client) {
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
			
			runService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runService() throws IOException {
		while(true) {
			
			String command = in.readUTF();
			System.out.println("command received: " + command);
			switch (command) {
				
				case "USER":
					commandUser();
					break;
				case "RESERVE":
					commandReserve();
					break;
				case "CANCEL":
					commandCancel();
					break;
				case "AVAIL":
					commandAvail();
					break;
				case "QUIT":
					commandQuit();
					return;
				default:
					commandClose(command);
					return;
			}
			out.flush();
			
		}
	}
	
	/**
	 * Ask username and set it
	 * @throws IOException
	 */
	private void commandUser() throws IOException {
		String name = in.readUTF();
		out.writeUTF("Hello, " + name);
		this.username = name;
	}
	
	/**
	 * process reservation and output the result message to the user
	 * @throws IOException
	 */
	private void commandReserve() throws IOException {
		int start = in.readInt();
		int end = in.readInt();
		boolean reserveSucess = hotel.requestReservation(username, start, end);
		String msg = reserveSucess ? 
				"Reservation made: " + username + " from " + start + " through " + end :
				"Reservation unsuccessful: " + username + " from " + start + " through " + end;
					
		out.writeUTF(msg);
	}
	
	/**
	 * process cancellation and output the result message to the user
	 * @throws IOException
	 */
	private void commandCancel() throws IOException {
		// if username null
		boolean cancelSucess = hotel.cancelReservation(username);
		String msg = cancelSucess ? 
				"Reservations successfully canceled for " + username :
				"Reservations not canceled for " + username + ", no current reservation.";

		out.writeUTF(msg);
	}
	
	/**
	 * output available rooms to the user
	 * @throws IOException
	 */
	private void commandAvail() throws IOException {
		String info = hotel.reservationInformation();
		out.writeUTF(info);
	}
	
	/**
	 * quit this service and tells it to the user
	 * @throws IOException
	 */
	private void commandQuit() throws IOException {
		out.writeUTF("Closing Connection");
		out.flush();
	}
	
	/**
	 * when user type invalid command, close this service
	 * @param command
	 * @throws IOException
	 */
	private void commandClose(String command) throws IOException {
		out.writeUTF("Invalid command " + command + "\nClosing Connection");
		out.flush();
	}
	
	
	
}
