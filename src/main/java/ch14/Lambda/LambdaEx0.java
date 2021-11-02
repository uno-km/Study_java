package ch14.Lambda;

@FunctionalInterface
interface MyFunction0 {
	public abstract int max(int a, int b);
}

public class LambdaEx0 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFunction0 f = (a, b) -> a > b ? a : b;
		System.out.println(f.max(3, 5));
	}
}