package Java.DataBase.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import Java.DataBase.Memory.SQLResult;

public class SocketClient extends SQLResult {
	public void openSocket(String query) {
		String serverName = "127.0.0.1";
		int portNumber = 7777;
		try {
			System.out.println("서버에 연결 중: " + serverName + ":" + portNumber);
			Socket clientSocket = new Socket(serverName, portNumber);
			OutputStream out = clientSocket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			System.out.println("쿼리가 서버로 전송됩니다. : " + query);
			dos.writeUTF(query);
			InputStream in = clientSocket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			super.setResult(dis.readUTF());
			dis.close();
			clientSocket.close(); // 소켓 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
