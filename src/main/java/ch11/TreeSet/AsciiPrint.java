package ch11.TreeSet;

public class AsciiPrint {

	public static void main(String[] args) {
		char ch = ' ';
		int cnt = 1;
		for (int i = 0; i < 95; i++) {
			if (i != 0) {
				System.out.print(ch++ + " ");
				if (i == 10 * cnt) {
					System.out.println();
					cnt++;
				}
			}
		}
	}
}
