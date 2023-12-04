package test.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String number = "110012003";
	        List<String> extractedNumbers = extractNumbers(number);
	        System.out.println(extractedNumbers); // 출력: [1100]
	}

    public static List<String> extractNumbers(String number) {
        List<String> extractedNumbers = new ArrayList<>();
        int index = number.indexOf("00");
        if (index != -1) {
            extractedNumbers.add(number.substring(0, index)+"00");
        }
        return extractedNumbers;
    }

}
