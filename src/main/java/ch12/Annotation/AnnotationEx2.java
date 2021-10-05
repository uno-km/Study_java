package ch12.Annotation;

import java.util.ArrayList;

public class AnnotationEx2 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		NewClass nc = new NewClass();
		nc.oldField = 10;
		System.out.println(nc.getNewField());

		ArrayList list = new ArrayList();
		list.add(list);
	}

}

class Class {
	int newField;

	@Deprecated
	int oldField;
}

class NewClass extends Class {

	int getNewField() {
		return newField;
	}

	@Deprecated
	int getOldField() {
		return oldField;
	}
}

@FunctionalInterface
interface Testable { // 함수형 인터페이스는 하나의 추상메서드만 가능하거든
	void test(); // 추상메서드

//	void check(); //추상메서드2 
	// 그래서 추상메서드2를 주석처리하니 에러가 안생김
	// 내가 잘못 입력해서 생기는 오류를 잡아준다.
}
