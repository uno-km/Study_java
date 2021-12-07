package ch14.Lambda;

@FunctionalInterface
interface ex3fn1 {
	public abstract void ex3fn1();
}

public class LambdaEx3 {
	public static void main(String[] args) {
		ex3fn1 fn1 = ()->{};
		Object obj= (ex3fn1)(()->{});
		String str = ((Object)(ex3fn1)(()->{})).toString();
		System.out.println(fn1);
		System.out.println(obj);
		System.out.println(str);

		System.out.println((ex3fn1)(()->{}));
		System.out.println(((Object)(ex3fn1)(()->{})).toString());
	}
}
