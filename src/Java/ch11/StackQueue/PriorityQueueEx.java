package Java.ch11.StackQueue;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue pq = new PriorityQueue();
		pq.offer(3); // pq.offer(new Integer(3));의 오토박싱
		pq.offer(1);
		pq.offer(5);
		pq.offer(2);
		pq.offer(4);

		System.out.println(pq);

		Object obj = null;

		while ((obj = pq.poll()) != null) {
			System.out.println(obj);
		}
	}

}
