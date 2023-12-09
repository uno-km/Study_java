package Java.Soket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) {
		String serverName = "127.0.0.1"; // 접속할 서버의 IP 주소
		int portNumber = 7777; // 서버가 실행되고 있는 포트 번호
		try {
			System.out.println("서버에 연결 중: " + serverName + ":" + portNumber);
			Socket clientSocket = new Socket(serverName, portNumber);
			OutputStream out = clientSocket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			String query = "이곳에 쿼리를 입력하세요";
			System.err.println("쿼리쏨");
			dos.writeUTF(query);
			// 서버로부터 응답을 받기 위한 입력 스트림
			InputStream in = clientSocket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			System.out.println("서버로부터 응답: " + dis.readUTF());
			System.out.println("턴을 종료합니다.");
			dis.close();
			clientSocket.close(); // 소켓 닫기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
