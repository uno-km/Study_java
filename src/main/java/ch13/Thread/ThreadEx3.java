package ch13.Thread;

public class ThreadEx3 {
	public static void main(String args[]) {
		ThreadTest2_1 t1 = new ThreadTest2_1();
		t1.run();
	}
}

class ThreadTest3_1 extends Thread {
	public void run() {
		System.out.println("과연될까?");
		throwException();
	}

	private void throwException() {
		// TODO Auto-generated method stub
		try {
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
