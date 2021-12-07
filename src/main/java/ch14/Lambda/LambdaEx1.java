package ch14.Lambda;

interface ex1_fn1 {
	void run(); // public abstract void run();
}

public class LambdaEx1 {
	static void execute(ex1_fn1 fn1) {
		fn1.run();
	}

	static ex1_fn1 getMyFunction() {
		ex1_fn1 fn1 = () -> System.out.println("f3.run()");
		return fn1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ex1_fn1 fn1 = () -> System.out.println("f1.run()");

		ex1_fn1 fn2 = new ex1_fn1() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("f2.run()");
			}
		};
		ex1_fn1 fn3 = getMyFunction();
		fn1.run();
		fn2.run();
		fn3.run();

		execute(fn1);
		execute(() -> System.out.println("run()"));

	}

}