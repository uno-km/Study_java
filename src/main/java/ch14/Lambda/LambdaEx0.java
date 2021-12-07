package ch14.Lambda;

@FunctionalInterface
interface MyFunction0 {
	public abstract int max(int a, int b);
}

@FunctionalInterface
interface MyFunction1 {
	public abstract int min(int a, int b);
}

public class LambdaEx0 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFunction0 f = (a, b) -> a > b ? a : b;
		MyFunction1 f2 = (a, b) -> a < b ? a : b;
		System.out.println(f.max(3, 5));
		System.out.println(f2.min(3, 5));
	}
}