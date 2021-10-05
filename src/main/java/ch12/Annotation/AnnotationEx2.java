package ch12.Annotation;

public class AnnotationEx2 {
	public static void main(String args[]) {
		NewClass nc = new NewClass();
		nc.oldField = 10;
		System.out.println(nc.getNewField());
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
