package Java.ch13.Thread;

public class ThreadEx4 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			System.out.printf("%s", new String("-"));

		}
		System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime));
		for (int i = 0; i < 10000; i++) {
			System.out.printf("%s", new String("|"));
		}
		System.out.println("소요시간2 : " + (System.currentTimeMillis() - startTime));
	}
}
