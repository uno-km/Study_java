package Java.ch13.Thread;

public class ThreadEx20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx_20 gc = new ThreadEx_20();
		gc.setDaemon(true);
		gc.start();

		int requireMemory = 0;
		for (int i = 0; i < 20; i++) {
			requireMemory = (int) (Math.random() * 10) * 20;
			if (gc.freeMemory() < requireMemory || gc.freeMemory() < gc.totalMemory() * 0.4) {
				gc.interrupt();
				try {
					gc.join(1000);
				} catch (InterruptedException e) {
				}
			}
			gc.usedMemory += requireMemory;
			System.out.println("usedMemory : " + gc.usedMemory);
		}
	}
}

class ThreadEx_20 extends Thread {
	final static int MAX_MEMORY = 1000;
	int usedMemory = 0;

	public void run() {
		while (true) {
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				System.out.println("Awaken by Free Memory : " + freeMemory());
			}
			gc();
			System.out.println("Garbage Collected, Free Memory : " + freeMemory());
		}
	}

	public void gc() {
		usedMemory -= 300;
		if (usedMemory < 0) {
			usedMemory = 0;
		}
	}

	public int totalMemory() {
		return MAX_MEMORY;
	}

	public int freeMemory() {
		return MAX_MEMORY - usedMemory;
	}
}
