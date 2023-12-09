package Java.Soket;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class NetworkStudy {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://finance.naver.com/item/news.naver?code=003280#index2");
			System.out.println(url.getAuthority());
			System.out.println(url.getContent());
			System.out.println(url.getDefaultPort());
			System.out.println(url.getPort());
			System.out.println(url.getProtocol());
			System.out.println(url.getFile());
			System.out.println(url.getHost());
			System.out.println(url.getQuery());
			System.out.println(url.getRef());
			System.out.println(url.getUserInfo());
			System.out.println(url.toExternalForm());
			System.out.println(url.toURI());
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}