package Java.DataBase.Action;

import java.util.Scanner;

import Java.DataBase.Socket.SocketClient;

public class InputCollector {
	public void collectInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter multiple lines of input (finish with ';'):");
		String lineText = "";
		while (scanner.hasNextLine()) {
			StringBuilder sb = new StringBuilder();
			lineText += scanner.nextLine();
			if (lineText.contains(";")) {
				int index = lineText.indexOf(';');
				sb.append(lineText, 0, index);
				System.out.println(sb.toString().trim());
				SocketClient socket = new SocketClient();
				socket.openSocket(sb.toString().trim());
				System.out.println(socket.getResult());
				lineText = "";
			}
		}
		scanner.close();
	}
}