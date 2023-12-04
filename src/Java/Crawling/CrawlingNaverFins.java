package Java.Crawling;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlingNaverFins extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 실습을 위한 상수형의 Url 주소 : 내 티스토리 포스팅 주소 */
	final static String URL_STRING = "https://uno-kim.tistory.com/215";
	/* 현재 과업(과제)구분을 위한 상수형 */
	final static String TASK_ID = "MyPost";
	/* 파일이 생성될 곳의 주소, 실경로를 설정 */
	final static String SAVE_DIR = "C:\\Users\\zhfld\\downloads\\img";
	/* 이미지파일 확장자 */
	final static String IMG_EXT = ".png";
	/* 파일명 구분자 */
	final static String FILE_SEPARATOR = "_";
	/* 버퍼사이즈 맥스로 주고 상수형으로 관리 */
	final static int BUFFER_SIZE = 4096;
	/* 이미지 태그 */
	final static String TAG_IMG = "img";
	/* 소스 속성(attribute) */
	final static String ATTR_SRC = "src";

	private final JTextField urlField;
	private final JButton downloadButton;
	private final JTextArea resultArea;
	private JButton selectFolderButton; // 새로 추가된 버튼
	private String saveDir; // 저장될 폴더 경로

	public CrawlingNaverFins() {
		super.setTitle("이미지 다운로더");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(400, 300);
		super.setLayout(new FlowLayout());
		urlField = new JTextField(URL_STRING, 30);
		super.add(urlField);
		downloadButton = new JButton("다운로드");
		super.add(downloadButton);
		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		super.add(scrollPane);
		selectFolderButton = new JButton("폴더 선택");
		super.add(selectFolderButton);
		selectFolderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFolder();
			}
		});
		JButton openFolderButton = new JButton("폴더 열기");
		openFolderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFolder();
			}
		});
		super.add(openFolderButton);
		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String urlString = urlField.getText().trim();
				downloadImages(urlString);
			}
		});
	}

	private void selectFolder() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = fileChooser.getSelectedFile();
			saveDir = selectedFolder.getAbsolutePath();
			showMessage("저장 폴더 선택: " + saveDir);
		}
	}

	private void openFolder() {
		if (saveDir != null && !saveDir.isEmpty()) {
			try {
				Desktop.getDesktop().open(new File(saveDir));
			} catch (IOException e) {
				showMessage("폴더 열기 실패: " + e.getMessage());
			}
		} else {
			showMessage("먼저 폴더를 선택하세요.");
		}
	}

	private void downloadImages(String urlString) {
		if (urlString.isEmpty()) {
			showMessage("URL을 입력하세요.");
			return;
		}
		try {
			Document document = Jsoup.connect(urlString).get();
			Elements images = document.select(".contents_style").select(TAG_IMG);
			int successCount = 0;
			for (int i = 0; i < images.size(); i++) {
				String connImgUrl = images.get(i).attr(ATTR_SRC);
				HttpURLConnection connUrl = (HttpURLConnection) new URL(connImgUrl).openConnection();

				if (connUrl.getResponseCode() == HttpURLConnection.HTTP_OK) {
					String fileName = "MyPost" + FILE_SEPARATOR
							+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + FILE_SEPARATOR + i
							+ IMG_EXT;
					FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_DIR, fileName));
					InputStream inputStream = connUrl.getInputStream();

					byte[] bufferArr = new byte[BUFFER_SIZE];
					int bytesData;
					while ((bytesData = inputStream.read(bufferArr)) != -1) {
						fileOutputStream.write(bufferArr, 0, bytesData);
					}
					fileOutputStream.close();
					inputStream.close();
					showMessage(fileName + " 저장 성공");
					successCount++;
				} else {
					showMessage(i + "번째 이미지 URL 접속 실패");
				}
			}
			showMessage("총 " + successCount + "개의 이미지 다운로드 완료");
		} catch (IOException e) {
			showMessage("오류 발생: " + e.getMessage());
		}
	}

	private void showMessage(String message) {
		resultArea.append(message + "\n");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CrawlingNaverFins downloaderApp = new CrawlingNaverFins();
			downloaderApp.setVisible(true);
		});
	}
}