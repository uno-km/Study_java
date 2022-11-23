package Java.ch14.lambda;

import java.util.Iterator;

@FunctionalInterface
interface Ex0_fn1 {
	public abstract int max(int a, int b);
}

@FunctionalInterface
interface Ex0_fn2 {
	public abstract int min(int a, int b);
}

@FunctionalInterface
interface Ex0_fn3 {
	public void aMethod();
}

public class LambdaEx0 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ex0_fn1 fn1 = (a, b) -> a > b ? a : b;
		Ex0_fn2 fn2 = (a, b) -> a < b ? a : b;
		Ex0_fn3 fn3 = (() -> System.out.println("myMethod()"));
		System.out.println(fn1.max(3, 5));
		System.out.println(fn2.min(3, 5));
		fn3.aMethod();
	}
}