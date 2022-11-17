package Java.ch12.Anootation;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class AnnotationEx3 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		nclass nc = new nclass();

		nc.oldField = 10;
		System.out.println(nc.getnewField());

		ArrayList<nclass> list = new ArrayList();
		list.add(nc);
	}
}



class nclass {
	int newField;
	@Deprecated
	int oldField;

	int getnewField() {
		return newField;
	}

	@Deprecated
	int getOldField() {
		return oldField;
	}
}
