package Java.ch11.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int LIMIT = 10;
		String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
		int length = source.length();

		List<String> list = new ArrayList<String>(length / LIMIT + 10); //자르고자하는 글자의 개수를 지정
		for (int i = 0; i < length; i += LIMIT) {
			if (i + LIMIT < length) {
				list.add(source.substring(i, i + LIMIT)); //크기를 약간 여유있게 잡는다.
			} else {
				list.add(source.substring(i));
			}

		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
