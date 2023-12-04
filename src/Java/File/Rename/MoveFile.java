package Java.File.Rename;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MoveFile {
	public static void main(String[] args) {
		String sourceDirectory = "D:\\희안\\moview"; // 원본 폴더 경로
		File sourceDir = new File(sourceDirectory);
		if (!sourceDir.isDirectory()) {
			System.out.println("소스 디렉토리가 존재하지 않습니다.");
			return;
		}
		File[] subDirectories = sourceDir.listFiles(File::isDirectory);
		if (subDirectories == null || subDirectories.length == 0) {
			System.out.println("하위 디렉토리가 없습니다.");
			return;
		}
		// 지원할 동영상 확장자들
		List<String> supportedFormats = Arrays.asList(".avi", ".mp4", ".mkv", ".mov", ".wmv");
		for (File subDir : subDirectories) {
			File[] videoFiles = subDir.listFiles((dir, name) -> {
				String lowercaseName = name.toLowerCase();
				return supportedFormats.stream().anyMatch(format -> lowercaseName.endsWith(format));
			});
			if (videoFiles != null && videoFiles.length > 0) {
				for (File videoFile : videoFiles) {
					String targetPath = sourceDirectory + File.separator + videoFile.getName();
					boolean success = videoFile.renameTo(new File(targetPath));
					if (success) {
						System.out.println("파일 이동 성공: " + videoFile.getAbsolutePath());
					} else {
						System.out.println("파일 이동 실패: " + videoFile.getAbsolutePath());
					}
				}
			}
		}
	}
}
