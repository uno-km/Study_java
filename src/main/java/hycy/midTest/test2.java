package hycy.midTest;

public class test2 {

	public static void main(String[] args) {
		test2 test2 = new test2();
		int s = test2.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println(s);
	}

	int add(int... a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
}
