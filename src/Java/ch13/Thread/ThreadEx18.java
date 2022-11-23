package Java.ch13.Thread;

public class ThreadEx18 {
	public static void main(String[] args) {
		Thread18 t1 = new Thread18("*");
		Thread18 t2 = new Thread18("**");
		Thread18 t3 = new Thread18("***");
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

class Thread18 implements Runnable {
	volatile boolean suspended = false;
	volatile boolean stopped = false;
	Thread th;

	Thread18(String name) {
		th = new Thread(this, name);
	}

	@Override
	public void run() {
		String name = th.getName();
		while (!stopped) {
			if (!suspended) {
				System.out.println(name);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(name + " - interrupted");
				}
			} else {
				Thread.yield();
			}
		}
		System.out.println(name + " - stopped");
	}

	void start() {
		th.start();
	}

	void stop() {
		stopped = true;
		th.interrupt();
		System.out.println(th.getName() + " - interrupt() by stop()");
	}

	public void suspend() {
		stopped = true;
		th.interrupt();
		System.out.println(th.getName() + " - interrupt() by suspend()");
	}

	public void resume() {
		suspended = false;
	}// 이건 메서드 전체가 임계영역

}