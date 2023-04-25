package lab10;

/**
 * 
 * @author Takeshi Hashimoto
 *
 * Main class that tests Periodic Customer class
 * it creates three customers that do reservation/cancel periodically
 * after 20 seconds, all 3 threads will be interrupted
 */
public class HotelTester {
	public static void main(String[] args) {
		try {
			
			Hotel hotel = new Hotel();
			PeriodicCustomer c1 = new PeriodicCustomer(hotel, "John");
			PeriodicCustomer c2 = new PeriodicCustomer(hotel, "Alice");
			PeriodicCustomer c3 = new PeriodicCustomer(hotel, "Takeshi");
			
			Thread thread1 = new Thread(c1);
			Thread thread2 = new Thread(c2);
			Thread thread3 = new Thread(c3);
			
			thread1.start();
			Thread.sleep(200);
			thread2.start();
			Thread.sleep(200);
			thread3.start();
			
			Thread.sleep(20000);
			
			thread1.interrupt();
			thread2.interrupt();
			thread3.interrupt();
			
			thread1.join();
			thread2.join();
			thread3.join();
			
			Thread.sleep(1000);
			System.out.println(hotel.reservationInformation());
			
		} catch(InterruptedException e) {
			System.out.println("Main interrupted");
		}
		
	}
}
