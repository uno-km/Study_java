package ch12.Annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated
@TestInfo(cnt = 3, name = "kim", members = { "lee", "park",
		"choi" }, testType = TestType.FIRST, testDate = @DateTime(yymmdd = "211006", hhmmss = "223030"))
public class AnnotationEx5 {
	public static void main(String args[]) {
		java.lang.Class<AnnotationEx5> cls = AnnotationEx5.class;

		TestInfo anno = (TestInfo) cls.getAnnotation(TestInfo.class);
		System.out.println("anno.name()=" + anno.name());
		System.out.println("anno.testDate().yymmdd()=" + anno.testDate().yymmdd());
		System.out.println("anno.testDate().hhmmss() = " + anno.testDate().hhmmss());
		for (String str : anno.members()) {
			System.out.println("members=" + str);
		}
		System.out.println();
		Annotation[] annoArr = cls.getAnnotations();
		for (Annotation a : annoArr) {
			System.out.println(a);
		}
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo {
	int cnt() default 1;

	String name();

	String[] members();

	TestType testType(); // enum TestType{FIRST, FINAL}

	DateTime testDate(); // 자신이 아닌 다른 애너테이션 @DateTime을 포함할 수있 다.
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime {
	String yymmdd();

	String hhmmss();
}

enum TestType {
	FIRST, FINAL
}