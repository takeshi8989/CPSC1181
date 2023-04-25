package lab10;

import java.util.Date;

/**
 * 
 * @author Takeshi Hashimoto
 *
 *	Producer class that add date string to a given queue iteratively
 */
public class ProducerRunnable implements Runnable{
	private Queue queue;
	private int iteration;
	
	public ProducerRunnable(Queue q, int i) {
		queue = q;
		iteration = i;
	}

	/**
	 * add one hundred strings when the queue is not full
	 */
	@Override
	public void run() {
		try {
			for(int i = 0; i < iteration; i++) {
				String str = new Date().toString();
				System.out.println("add: " + str);
				queue.add(str);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
