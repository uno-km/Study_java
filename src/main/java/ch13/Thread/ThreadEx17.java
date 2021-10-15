package ch13.Thread;

public class ThreadEx17 {
	public static void main(String[] args) {
		Thread17 t1 = new Thread17("*");
		Thread17 t2 = new Thread17("**");
		Thread17 t3 = new Thread17("***");
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(2000);
			t1.suspend();
			Thread.sleep(2000);
			t2.suspend();
			Thread.sleep(2000);
			t1.resume();
			Thread.sleep(2000);
			t1.stop();
			t2.stop();
			Thread.sleep(1000);
			t3.stop();
		} catch (InterruptedException e) {
		}
	}
}

class Thread17 implements Runnable {
	volatile boolean suspended = false;
	volatile boolean stopped = false;
	Thread th;

	Thread17(String name) {
		th = new Thread(this, name);
	}

	void start() {
		th.start();
	}

	@Override
	public void run() {
		while (!stopped) {
			if (!suspended) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
		System.out.println(Thread.currentThread().getName() + " - stopped");
	}

	void stop() {
		this.stopped = true;
	}

	public void suspend() {
		this.suspended = true;
	}

	public void resume() {
		this.suspended = false;
	}

}