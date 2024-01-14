package Java.Crawling;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlingInstagramPost {
	static final String connImgUrl = "https://www.instagram.com/p/C0obU4zJy3L/";

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/chromedriver-win64/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setCapability("goog:chromeOptions", Map.of("w3c", true));
		options.addArguments("--remote-allow-origins=*");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.6099.71 Safari/537.36");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait waitTen = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get(connImgUrl);
		int imgCnt = 0;
		boolean found = true;
		while (found) {
			try {
				WebElement imgElement = null;
				WebElement acayElement = waitTen
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._acay")));
				List<WebElement> imgElements = acayElement.findElements(By.tagName("img"));
				if (imgCnt == 0) {
					imgElement = imgElements.get(0);
				} else {
					imgElement = imgElements.get(1);
				}
				String srcValue = imgElement.getAttribute("src");
				System.out.println(srcValue);
				downloadImg(srcValue, imgCnt);
				WebElement nextBtn = driver.findElement(By.className("_9zm2"));
				nextBtn.click();
				imgCnt++;
				Thread.sleep(1000); // 1초 대기
			} catch (Exception e) {
				System.out.println("_9zm2 클래스를 가진 요소를 찾을 수 없습니다.");
				found = false;
			}
		}
		driver.quit();
	}

	static void downloadImg(String srcValue, int imgCnt) {
		try {
			URL url = new URL(srcValue);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			String downloadPath = "C:/Users/zhfld/downloads/img/test";
			String fileName = FileUtils.getFileName("instagram", imgCnt);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(downloadPath + fileName));
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			in.close();
			System.out.println("이미지 다운로드가 완료되었습니다.");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}