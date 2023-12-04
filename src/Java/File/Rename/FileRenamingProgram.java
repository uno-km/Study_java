package Java.File.Rename;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileRenamingProgram {
	private static HashMap<String, Function<String, String>> functionMap = new HashMap<>();

	public static void main(String[] args) {
		JFrame frame = new JFrame("파일 이름 변경 프로그램");
		JButton button = new JButton("폴더 선택");
		button.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showOpenDialog(frame);
			if (option == JFileChooser.APPROVE_OPTION) {
				File folder = fileChooser.getSelectedFile();
				checkFilesAndRenameFiles(folder);
			}
		});
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		setFunctionMap();
	}

	private static void setFunctionMap() {
		functionMap.put("@", filename -> filename.substring(filename.lastIndexOf("@") + 1));
		functionMap.put("%", filename -> filename.substring(filename.indexOf("%") + 1, filename.lastIndexOf(".")));
	}

	private static boolean checkDirectory(File file) {
		return (file != null && file.isDirectory());
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
			String originFileName = file.getName();
			String newFilename = originFileName;
			Set<String> separators = functionMap.keySet();
			for (String separator : separators) {
				if (newFilename.contains(separator)) {
					newFilename = functionMap.get(separator).apply(newFilename);
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
