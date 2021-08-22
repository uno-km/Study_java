package ch11;

import java.util.Iterator;

public class MyVector2Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyVector2 v = new MyVector2();
		v.add("0");
		v.add("1");
		v.add("2");
		v.add("3");
		v.add("4");

		System.out.println("삭제전  : " + v);
		Iterator it = v.iterator();
		it.next();
		it.remove();
		it.next();
		it.remove();

		System.out.println("삭제후 : " + v);
	}

}
