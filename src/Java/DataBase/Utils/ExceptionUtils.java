package Java.DataBase.Utils;

public final class ExceptionUtils {
	public final static void generateException(final String title, final Object msg) {
		throw new IllegalArgumentException(":: " + title + " :: " + msg);
	}
}
