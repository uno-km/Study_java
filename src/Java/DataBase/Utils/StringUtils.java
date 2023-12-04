package Java.DataBase.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static int findIndexIgnoringCase(String input, String keyword) {
		Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find() ? matcher.start() : -1;
	}
}
