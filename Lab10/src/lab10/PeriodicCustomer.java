package lab10;

/**
 * 
 * @author Takeshi Hashimoto
 *	Periodic Customer class that reserve or cancel hotel room
 *	until interrupted, it tries to reserver or cancel randomly and print the result
 */
public class PeriodicCustomer implements Runnable {
	private Hotel hotel;
	private String name;
	/**
	 * Constructor receiving hotel object and customer name
	 * @param h
	 * @param n
	 */
	public PeriodicCustomer(Hotel h, String n) {
		hotel = h;
		name = n;
	}
	
	/**
	 * Run method that periodically do task(reservation or cancellation)
	 * after the task, it prints the result
	 * after a while, it will be interrupted and prints shutdown message
	 */
	@Override
	public void run() {
		try {
			while(true) {
				String resultMessage = randomCustomerAction();
				System.out.println(resultMessage);
				System.out.println();
				Thread.sleep(1500);
			}
		} catch (InterruptedException e) {
			System.out.println("Periodic Test Customer " + name + " Shutting Down");
			e.printStackTrace();
		}
	}
	
	/**
	 * randomly reserve a hotel room or cancel if it already has it
	 * it tries to reserve a room 70%, otherwise it tries to cancel his reservation
	 * @return result message
	 */
	private String randomCustomerAction() {
		int start = (int)(Math.random() * 31 + 1);
		int end = (int)(Math.random() * 31 + 1);
		boolean reserve = Math.random() > 0.3;
		
		if(start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		
		boolean result;
		if(reserve) {
			result = hotel.requestReservation(name, start, end);
			return writeReservationResult(result, name, start, end);
		} 
		
		result = hotel.cancelReservation(name);
		return writeCancellationResult(result, name);
	}
	
	
	/**
	 * return result string reservation result success or fail, date and name
	 * @param successful
	 * @param name
	 * @param start
	 * @param end
	 * @return result message
	 */
	private String writeReservationResult(boolean successful, String name, int start, int end) {
		if(successful) 
			return "Reservation made: " + name + " from " + start + " through " + end;
		else 
			return "Reservation unsuccessful: " + name + " from " + start + " through " + end;
	}
	
	/**
	 * return result string reservation result success or fail, and name
	 * @param successful
	 * @param name
	 * @return
	 */
	private String writeCancellationResult(boolean successful, String name) {
		if(successful) 
			return "Reservations successfully canceled for " + name;
		else 
			return "Reservations not canceled for " + name + ", no current reservation.";
	}

}
