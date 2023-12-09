package Java.Soket;

import java.net.InetAddress;
import java.util.Arrays;

public class NetworkEx1 {

	public static void main(String[] args) {
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		try {
			ip = InetAddress.getByName("www.naver.com");
			System.out.println(ip.getHostName());
			System.out.println(ip.getHostAddress());
			System.out.println(ip.toString());

			byte[] ipAddr = ip.getAddress();
			System.out.println(Arrays.toString(ipAddr));
			String res = "";
			for (byte b : ipAddr) {
				res += (b < 0) ? b + 256 : b;
				res += ".";
			}
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ip = InetAddress.getLocalHost();
			System.out.println(ip.getHostName());
			System.out.println(ip.getHostAddress());
			System.out.println();

		} catch (Exception e) {
			e.getStackTrace();
		}
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");
			for (InetAddress ia : ipArr) {
				System.out.println(ia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}