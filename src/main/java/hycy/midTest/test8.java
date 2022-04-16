package hycy.midTest;

public class test8 {
	public static void main(String[] args) {
		Exam ob = new Exam();
		double x;
		x = ob.op(3, 4);
	}
}

class Exam {
	byte op(byte a, byte b) {
		return (byte) (a + b);

	}

	int op(int a, int b) {
		return a + b;
	}

	float op(float a, float b) {
		return a + b;
	}

	double op(double a, Double b) {
		return a + b;
	}
}

class A {
	public int s;
}