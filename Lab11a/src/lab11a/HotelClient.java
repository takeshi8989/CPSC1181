package lab11a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author Takeshi Hashimoto
 * This HotelClient class connects to a local server to reserve/cancel hotel rooms in December.
 * Available commands are USER, RESERVE, CANCEL, AVAIL, QUIT
 *
 */
public class HotelClient {
	
	public static void main(String[] args) {
	      final int PORT = 1181;
	      Scanner userInput = new Scanner(System.in);
	      try (Socket s = new Socket("localhost", PORT)) {
	    	  
	    	   
		      InputStream instream = s.getInputStream();
		      OutputStream outstream = s.getOutputStream();
		
		      DataInputStream in = new DataInputStream(instream);
		      DataOutputStream out = new DataOutputStream(outstream);
		      
		     
		      out.writeUTF("USER");
		      
		      userCommand(out, userInput);
		      String response = in.readUTF();
	    	  System.out.println("Receiving: " + response);
		      
		      String command = "";
		      boolean done = false;
		      while(!done) {
		    	  System.out.println("USER, AVAIL, CANCEL, RESERVE, QUIT");
		    	  System.out.print("Command: ");
		    	  while(command.equals("")) {
		    		  command = userInput.nextLine();
		    	  }
		    	  
		    	  out.writeUTF(command);
		    	  out.flush();
		    	  
		    	  if(command.equals("USER"))
		    		  userCommand(out, userInput);
		    	  else if(command.equals("RESERVE"))
		    		  reserveCommand(out, userInput);
		    	  else if (!command.equals("CANCEL") && !command.equals("AVAIL"))
		    		  done = true;
		    	  
		    	  response = in.readUTF();
		    	  System.out.println("Receiving: " + response);
		    	  
		    	  command = "";
		      }

		      
	      } catch(IOException e) {
	    	  System.out.println(e.getStackTrace());
	      }
	}
	
	public static void userCommand(DataOutputStream out, Scanner userInput) throws IOException {
		System.out.print("username: ");
		String username = userInput.nextLine();
		out.writeUTF(username);
		out.flush();
	}
	
	public static void reserveCommand(DataOutputStream out, Scanner userInput) throws IOException {
		
  	  	System.out.print("start day: ");
  	  	int start = userInput.nextInt();
  	  	
	  	System.out.print("end day: ");
	  	int end = userInput.nextInt();
	  	out.writeInt(start);
	  	out.writeInt(end);
	  	out.flush();
	}

}
