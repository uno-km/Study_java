package Java.ch13.Thread;

public class ThreadEx16 {
	public static void main(String[] args) {
		RunImpleEx16 r1 = new RunImpleEx16();
		RunImpleEx16 r2 = new RunImpleEx16();
		RunImpleEx16 r3 = new RunImpleEx16();
		Thread t1 = new Thread(r1, "*");
		Thread t2 = new Thread(r2, "**");
		Thread t3 = new Thread(r3, "***");
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

class RunImpleEx16 implements Runnable {
	volatile boolean suspended = false;
	volatile boolean stopped = false;

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