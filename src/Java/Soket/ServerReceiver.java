package Java.Soket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public ServerReceiver(Socket socket) {
		this.socket = socket;
		try {
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		String receivedMessage = "";
		try {
			receivedMessage = this.in.readUTF(); // 클라이언트로부터 메시지 수신
			System.out.println(receivedMessage);
			String responseMessage = "처리된 결과입니다."; // 처리 결과 예시
			// 클라이언트에게 결과 전송
			this.out.writeUTF(responseMessage);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			TcpMultiServer.removeClient(this.socket.getInetAddress().getHostAddress());
			try {
				this.socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}
}