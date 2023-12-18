package Java.Crawling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileUtils {
	/* 이미지파일 확장자 */
	final static String IMG_EXT = ".png";
	/* 파일명 구분자 */
	final static String FILE_SEPARATOR = "_";

	static String getFileName(String system, int index) {
		return system + FILE_SEPARATOR + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))
				+ FILE_SEPARATOR + index + IMG_EXT;
	}
}
