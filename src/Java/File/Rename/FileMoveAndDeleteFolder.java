package Java.File.Rename;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class FileMoveAndDeleteFolder {
	private static HashMap<String, Function<String, String>> functionMap = new HashMap<>();
	private static List<String> supportedFormats = Arrays.asList(".avi", ".mp4", ".mkv", ".mov", ".wmv", ".bmp");

	public static void main(String[] args) {
		JFrame frame = new JFrame("Move File");
		JLabel label = new JLabel("Move File");
		JButton selectButton = new JButton("폴더 선택");
		JTextArea resultArea = new JTextArea(10, 15);
		JButton renameButton = new JButton("변환하기!");
		JTextArea convertedListTextArea = new JTextArea(10, 15);
		convertedListTextArea.setLineWrap(true); // 줄 바꿈 설정
		convertedListTextArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈
		JScrollPane scrollPane = new JScrollPane(convertedListTextArea);
		List<JComponent> componentList = Arrays.asList(label, selectButton, resultArea, renameButton,
				convertedListTextArea);
		setMainFrame(frame, componentList);
		setMainLabel(label);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		selectButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(frame);
			if (option == JFileChooser.APPROVE_OPTION) {
				File folder = fileChooser.getSelectedFile();
				resultArea.setText(folder.getAbsolutePath());
				renameButton.setEnabled(true);
			}
		});
		renameButton.addActionListener(e -> {
			String folderPath = resultArea.getText();
			if (!folderPath.isEmpty()) {
				File folder = new File(folderPath);
				moveFile(folder, convertedListTextArea);
				confirmRenameFiles(folder);
			}
		});
		setFunctionMap();
	}

	private static void setMainLabel(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setHorizontalAlignment(JLabel.CENTER);
	}

	private static void setMainFrame(JFrame frame, List<JComponent> componentList) {
		frame.setPreferredSize(new Dimension(400, 500));
		frame.getContentPane().add(createMainPanel(componentList));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null); // 화면 정중앙에 위치
		frame.setVisible(true);
	}

	public static JPanel createMainPanel(List<JComponent> arr) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		arr.forEach(component -> panel.add(component));
		return panel;
	}

	private static void moveFile(final File sourceDirectory, JTextArea convertedListTextArea) {
		if (!checkDirectory(sourceDirectory)) {
			convertedListTextArea.setText("선택한 경로는 폴더가 아닙니다.");
			return;
		}
		File[] subDirectories = sourceDirectory.listFiles(File::isDirectory);
		if (!checkFileExisting(subDirectories)) {
			convertedListTextArea.setText("하위 디렉토리가 없습니다.");
			confirmRenameFiles(sourceDirectory);
			return;
		}
		List<String> convertedFiles = new ArrayList<>();
		moveFileAndSetSuccessFileNames(sourceDirectory, subDirectories, convertedFiles);
		displayConvertedFiles(convertedFiles, convertedListTextArea);
	}

	private static void moveFileAndSetSuccessFileNames(final File sourceDirectory, File[] subDirectories,
			List<String> convertedFiles) {
		for (File subDir : subDirectories) {
			File[] videoFiles = subDir.listFiles((dir, name) -> {
				String lowercaseName = name.toLowerCase();
				return supportedFormats.stream().anyMatch(format -> lowercaseName.endsWith(format));
			});
			if (checkFileExisting(videoFiles)) {
				for (File videoFile : videoFiles) {
					String targetPath = sourceDirectory.getPath() + File.separator + videoFile.getName();
					if (videoFile.renameTo(new File(targetPath))) {
						convertedFiles.add("파일 성공 : " + videoFile.getName());
					} else {
						convertedFiles.add("파일 이동 실패 : " + videoFile.getName());
					}
				}
			}
		}
	}

	private static void confirmRenameFiles(File sourceDirectory) {
		int confirmResult = JOptionPane.showConfirmDialog(null, "파일명도 일괄 변경하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
		if (confirmResult == JOptionPane.YES_OPTION) {
			checkFilesAndRenameFiles(sourceDirectory);
		} else if (confirmResult == JOptionPane.NO_OPTION) {
			return;
		}
	}

	private static void displayConvertedFiles(List<String> convertedFiles, JTextArea convertedListLabel) {
		SwingUtilities.invokeLater(() -> {
			convertedListLabel.append("변환된 파일 리스트:\n");
			for (String filePath : convertedFiles) {
				convertedListLabel.append(filePath + "\n");
				convertedListLabel.setCaretPosition(convertedListLabel.getDocument().getLength());
			}
		});
	}

	private static void setFunctionMap() {
		functionMap.put("@", filename -> filename.substring(filename.lastIndexOf("@") + 1));
		functionMap.put("%", filename -> filename.substring(filename.indexOf("%") + 1, filename.lastIndexOf(".")));
	}

	private static boolean checkDirectory(File file) {
		return (file != null && file.isDirectory());
	}

	private static boolean checkFileExisting(File[] files) {
		return (files != null && files.length > 0);
	}

	private static void checkFilesAndRenameFiles(File folder) {
		if (checkDirectory(folder)) {
			File[] files = folder.listFiles();
			if (files == null || files.length == 0)
				return;
			for (File file : files) {
				renameFile(folder, file);
			}
			JOptionPane.showMessageDialog(null, "파일 이름 변경이 완료되었습니다.");
		}
	}

	private static void renameFile(File toFolder, File file) {
		if (file.isFile()) {
			String newFilename = file.getName();
			Set<String> separators = functionMap.keySet();
			for (String separator : separators) {
				if (newFilename.contains(separator)) {
					newFilename = functionMap.get(separator).apply(newFilename);
					break;
				}
			}
			rename(toFolder, file, newFilename);
		}
	}

	private static void rename(File toFolder, File file, String newFilename) {
		File newFile = new File(toFolder, newFilename);
		try {
			Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 이름 변경 중 오류가 발생했습니다.");
			return;
		}
	}
}