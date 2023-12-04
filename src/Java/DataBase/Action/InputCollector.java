package Java.DataBase.Action;

import java.util.Scanner;

public class InputCollector {
	public void collectInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter multiple lines of input (finish with ';'):");
		while (scanner.hasNextLine()) {
			StringBuilder sb = new StringBuilder();
			String line = scanner.nextLine();
			if (line.contains(";")) {
				int index = line.indexOf(';');
				sb.append(line, 0, index); // append only before ';'
				System.out.println(sb.toString().trim());
				QueryParser qp = new QueryParser();
				try {
					qp.parseCommand(sb.toString());
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		scanner.close();
	}
}
