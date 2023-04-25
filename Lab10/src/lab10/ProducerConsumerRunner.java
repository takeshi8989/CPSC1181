package lab10;

/**
 * Producer Consumer Runner class that creates a Consumer and a Producer
 * run two thread 
 * @author Takeshi Hashimoto
 *
 */
public class ProducerConsumerRunner {
	private static final int QUEUE_MAX_SIZE = 10;
	private static final int ITERATIONS = 100;
	
	public static void main(String[] args) {
		Queue q = new Queue(QUEUE_MAX_SIZE);
		ProducerRunnable producer = new ProducerRunnable(q, ITERATIONS);
		ConsumerRunnable consumer = new ConsumerRunnable(q, ITERATIONS);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}
}
