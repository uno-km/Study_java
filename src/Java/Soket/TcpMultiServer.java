package Java.Soket;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TcpMultiServer {
	private static HashMap<String, List<Object>> clients = new HashMap<>();

	public static void main(String[] args) {
		new TcpMultiServer().start();
	}

	public static void removeClient(String key) {
		if (clients.containsKey(key)) {
			clients.remove(key);
			System.out.println("::: 접속해제 ::: IP = " + key);
		}
	}

	TcpMultiServer() {
		Collections.synchronizedMap(clients);
	}

	public void start() {
		Socket socket = null;
		try (ServerSocket serverSocket = new ServerSocket(7777)) {
			System.out.println("서버가 시작되었습니다.");
			while (true) {
				socket = serverSocket.accept();
				this.setClientInfo(socket);
				ServerReceiver serverReceiver = new ServerReceiver(socket);
				serverReceiver.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setClientInfo(Socket socket) {
		final String IP = socket.getInetAddress().getHostAddress();
		System.out.println("::Connected Client Info::\nIP : " + IP + ", Port : " + socket.getPort() + ", 접속일시 : "
				+ LocalDateTime.now() + "\n::::");
		List<Object> list = clients.getOrDefault(IP, new ArrayList<>());
		HashMap<String, Object> clientInfoMap = new HashMap<>();
		clientInfoMap.put("IP", IP);
		clientInfoMap.put("Port", socket.getPort());
		clientInfoMap.put("접속일시", LocalDateTime.now());
		list.add(clientInfoMap);
		clients.put(IP, list);
		System.out.println("현재접속자 : " + clients.size());
	}
}
