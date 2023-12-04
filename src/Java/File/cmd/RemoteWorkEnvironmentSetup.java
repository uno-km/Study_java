package Java.File.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.commons.collections4.map.HashedMap;

public class RemoteWorkEnvironmentSetup {
	private static JTextArea textArea;
	private static ProcessBuilder processBuilder = new ProcessBuilder();
	private static boolean isEnd = false;

	public static void main(String[] args) throws IOException, InterruptedException {
		/* 시작버튼 */
		startApp();
	}

	private static void startApp() throws IOException, InterruptedException {
		JFrame frame = new JFrame("Remote Work Environment Setup");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea = new JTextArea(10, 60);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane);
		frame.pack();
		frame.setVisible(true);
		processBuilder.command("cmd.exe", "/c");
		showMessage("::: message ::: 재택근무환경 자동셋팅 프로그램 is running...");
		try {
			if (checkDHCPMode(RemoteWork.CHECK_DHCP.get())) {
				showMessage("::: message ::: DHCP 설정이 YES로 설정되어있습니다...  재택환경으로 셋팅을 시작하겠습니다.");
				if (excuteCommandLine(RemoteWork.SET_AUTO_IP_SETUP.get())) {
					showMessage("::: message ::: 자동 IP 할당으로 셋팅완료...");
					if (excuteCommandLine(RemoteWork.SET_AUTO_DNS_SETUP.get())) {
						showMessage("::: message ::: 자동 DNS 할당으로 셋팅완료...");
						if (excuteCommandLine(RemoteWork.OPEN_IEXPLORE.get())) {
							showMessage("::: message ::: 재택근무환경이 성공적으로 셋팅되었습니다.");
						} else {
							showMessage("::: ERROR ::: 익스플로어 실행에서 문제가 발생했습니다.");
						}
					} else {
						showMessage("::: ERROR ::: DNS 주소 자동할당중 설정중 오류가 발생했습니다.");
					}
				} else {
					showMessage("::: ERROR ::: IP 주소 자동할당중 설정중 오류가 발생했습니다.");
				}
			} else {
				String userName = "김은호"; // 사용자 이름 설정
				UserDB userDB = new UserDB(userName);
				showMessage("::: message ::: DHCP 설정이 No로 설정되어있습니다...  출근환경으로 셋팅을 시작하겠습니다.");
				String ipCmdLine = RemoteWork.SET_USER_IP_SETUP.get() + userDB.getEnvironmentSetupData("IP_ADDRESS");
				if (excuteCommandLine(ipCmdLine)) {
					isEnd = true;
					showMessage("::: message ::: 유저의 기존 IP 값으로 셋팅완료...");
					showMessage("::: message ::: 유저의 기존 기본 DNS 값으로 셋팅시작...");
					waitForSomeTime();
					String primaryDnsCmdLine = RemoteWork.SET_USER_DNS_PRIMARY_SETUP.get()
							+ userDB.getEnvironmentSetupData("PRIMARY_DNS_ADDRESS");
					if (excuteCommandLine(primaryDnsCmdLine)) {
						isEnd = true;
						showMessage("::: message ::: 유저의 기존 기본 DNS 값으로 셋팅완료...");
						showMessage("::: message ::: 유저의 기존 보조 DNS 값으로 셋팅시작...");
						waitForSomeTime();
						String secondaryDnsCmdLine = RemoteWork.SET_USER_DNS_SECONDARY_SETUP.get()
								+ userDB.getEnvironmentSetupData("SECONDARY_DNS_ADDRESS");
						if (excuteCommandLine(secondaryDnsCmdLine)) {
							isEnd = true;
							showMessage("::: message ::: 유저의 기존 보조 DNS 값으로 셋팅완료...");
							showMessage("::: message ::: 기존 환경설정으로 복구완료했습니다.\n" + "::: IP ::: "
									+ userDB.getEnvironmentSetupData("IP_ADDRESS") + " \n" + "기본 DNS : "
									+ userDB.getEnvironmentSetupData("PRIMARY_DNS_ADDRESS") + "\n" + "::: 보조 DNS ::: "
									+ userDB.getEnvironmentSetupData("SECONDARY_DNS_ADDRESS"));
						} else {
							showMessage("::: ERROR ::: 보조 DNS 설정시 오류가 발생했습니다.");
						}
					} else {
						showMessage("::: ERROR ::: 기본설정 DNS 설정시 오류가 발생했습니다.");
					}
				} else {
					showMessage("::: ERROR ::: IP 설정시 오류가 발생했습니다.");
				}
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void showMessage(final String message) {
		SwingUtilities.invokeLater(() -> {
			textArea.append(message + "\n");
			textArea.setCaretPosition(textArea.getDocument().getLength());
		});
	}

	private static boolean checkDHCPMode(String cmdLine) throws IOException {
		BufferedReader reader = result(cmdLine);
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains("Yes")) {
				return false;
			}
		}
		return true;
	}

	private static void waitForSomeTime() {
		isEnd=false;
		Thread thread = new Thread(() -> {
			try {
				int waitTime = 10; // 대기 시간 (초)
				for (int i = waitTime; i > 0; i--) {
					if (isEnd) {
						Thread.interrupted();
					} else {
						showMessage("잠시만 기다려주세요... " + i + "초 남았습니다.");
						Thread.sleep(1000); // 1초 대기
					}
				}
				showMessage("대기가 완료되었습니다...(10초를 초과할수도 있습니다)");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}

	private static boolean excuteCommandLine(String cmdLine) throws IOException, InterruptedException {
		return isSuccesExcuceCommandLine(cmdLine) == 0;
	}

	private static BufferedReader result(String cmdLine) throws IOException {
		return new BufferedReader(new InputStreamReader(startCommandLine(cmdLine).getInputStream()));
	}

	private static Process startCommandLine(String cmdLine) throws IOException {
		processBuilder.command().add(cmdLine);
		Process process = processBuilder.start();
		processBuilder.command().remove(processBuilder.command().size() - 1);
		return process;
	}

	private static int isSuccesExcuceCommandLine(String cmdLine) throws IOException, InterruptedException {
		return startCommandLine(cmdLine).waitFor();
	}
}

class UserDB {
	private Map<String, Map<String, String>> allUserEnvironmentSetup = new HashedMap<>();
	private Map<String, String> userEnvironmentSetup;

	UserDB(String userName) {
		Map<String, String> sampleUserDB = new HashedMap<>();
		sampleUserDB.put("IP_ADDRESS", "192.111.111.10 255.255.255.0 192.111.111.10");
		sampleUserDB.put("PRIMARY_DNS_ADDRESS", "199.111.111.10");
		sampleUserDB.put("SECONDARY_DNS_ADDRESS", "198.111.111.10");

		this.allUserEnvironmentSetup.put("김은호", sampleUserDB);
		this.allUserEnvironmentSetup.put("홍길동", sampleUserDB);
		this.allUserEnvironmentSetup.put("이순신", sampleUserDB);

		this.userEnvironmentSetup = this.allUserEnvironmentSetup.get(userName);
	}

	public String getEnvironmentSetupData(String col) {
		return this.userEnvironmentSetup.get(col);
	}
}

enum RemoteWork {
	CHECK_DHCP("netsh interface ip show address \"Wi-Fi\" "),
	SET_AUTO_IP_SETUP("netsh interface ip set address \"Wi-Fi\" dhcp "),
	SET_AUTO_DNS_SETUP("netsh interface ip set dns \"Wi-Fi\" dhcp "),
	SET_USER_IP_SETUP("netsh interface ip set address \"Wi-Fi\" static "),
	SET_USER_DNS_PRIMARY_SETUP("netsh interface ip set dns \"Wi-Fi\" static "),
	SET_USER_DNS_SECONDARY_SETUP("netsh interface ip add dns \"Wi-Fi\" "),
	OPEN_IEXPLORE("start iexplore https://naver.com");

	private final String cmd;

	RemoteWork(String cmd) {
		this.cmd = cmd;
	}

	public String get() {
		return this.cmd;
	}
}
