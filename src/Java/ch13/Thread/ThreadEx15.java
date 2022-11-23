package Java.ch13.Thread;

public class ThreadEx15 {
	public static void main(String[] args) {
		MyThread t1 = new MyThread("*");
		MyThread t2 = new MyThread("**");
		MyThread t3 = new MyThread("***");
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

class MyThread implements Runnable {
	volatile boolean suspended = false;
	volatile boolean stopped = false;
	Thread th;

	MyThread(String name) {
		th = new Thread(this, name);
	}

	void start() {
		th.start();
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
	}
}