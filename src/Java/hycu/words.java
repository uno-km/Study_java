package Java.hycu;

public class words {
	public static void main(String[] args) {
		String originalString = "This is a long string with ${TITLE} placeholder.";

		// 또 다른 긴 문자열 생성
		String insertedString = "Inserted content here.";

		// StringBuilder를 사용하여 문자열 연결
		StringBuilder newString = new StringBuilder(originalString);
		int placeholderIndex = newString.indexOf("${TITLE}");
		newString.replace(placeholderIndex, placeholderIndex + "${TITLE}".length(), insertedString);

		System.out.println(newString.toString());
	}
}