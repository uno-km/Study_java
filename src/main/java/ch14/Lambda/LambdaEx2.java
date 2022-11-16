package main.java.ch14.Lambda;

interface MyFunction2 {
	void myMethod();
}

public class LambdaEx2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFunction2 f = () -> {};
		Object o = (MyFunction2) (() -> {});
		String str = ((Object)(MyFunction2)(()->{})).toString();
		Object c = ((MyFunction2)(()->{})).getClass();
		System.out.println("함수인터페이스의 값 : "+f);
		System.out.println("오브젝트형변환 값 : " + o);
		System.out.println("문자열 형변환 값" + str);
		System.out.println("클래스 : " +c);
		System.out.println((MyFunction2)(()->{}));
	}
}
