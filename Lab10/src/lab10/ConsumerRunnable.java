package lab10;

/**
 * 
 * @author Takeshi Hashimoto
 *	Producer class that add date string to a given queue iteratively
 */
public class ConsumerRunnable implements Runnable{
	private Queue queue;
	private int iteration;
	
	public ConsumerRunnable(Queue q, int i) {
		queue = q;
		iteration = i;
	}

	/**
	 * remove the first string when the queue is not empty (FIFO)
	 */
	@Override
	public void run() {
		try {
			for(int i = 0; i < iteration; i++) {
				System.out.println("remove: " + queue.remove());
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
