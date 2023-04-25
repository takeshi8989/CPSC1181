package lab10;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Queue class that stores strings in a queue
 * When the queue is full, it waits to add and if it's empty, it waits to remove the element
 * @author takes
 *
 */
public class Queue {
	private int capacity;
	private ArrayList<String> dates;
	private Lock queueLock; // lock the queue
	private Condition empty;
	
	/**
	 * Constructor with capacity, create a new arraylist as a queue
	 * @param size
	 */
	public Queue(int size) {
		dates = new ArrayList<String>();
		capacity = size;
		queueLock = new ReentrantLock();
		empty = queueLock.newCondition();
	}
	
	/**
	 * This method adds new string when the queue is not full
	 * @param str
	 */
	public void add(String str) {
		queueLock.lock();
		try {
			while(dates.size() > capacity) {
				empty.await();
			}
			dates.add(str);
			empty.signalAll();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
	
	/**
	 * This method removes new string when the queue is not empty
	 * @param str
	 */
	public String remove() {
		queueLock.lock();
		try {
			while(dates.size() <= 0) {
				empty.await();
			}
			return dates.remove(0);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
		return "";
	}
}
