package Java.ch14.lambda;

@FunctionalInterface
interface ex3fn1 {
	public abstract void ex3fn1();
}
@FunctionalInterface
interface myFunction_ex3 {
	public void myFunction_ex3() ;
}
class Outer{
	int val =10;
	class Inner{
		int val =20;
		void method(int i) {
			int val = 30;
			myFunction_ex3 fn1= ()->{
				System.out.println("                i : "+i);
				System.out.println("              val : " + val);
				System.out.println("         this.val : " + this.val);
				System.out.println("   Outer.this.val : " + Outer.this.val);
			};
			fn1.myFunction_ex3();
			
		}
	}
	
	
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
		System.out.println("-----------------------");
		Outer out = new Outer();
		Outer.Inner in = out.new Inner();
		in.method(100);
		
	}
}
