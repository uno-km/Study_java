package Java.File.CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHandler {
	private static final String FILE_PATH = "C:\\Users\\zhfld\\git\\Study_java\\src\\Java\\File\\CSV\\Storage";

	public static void main(String[] args) {
		Map<String, List<Map<String, Object>>> csvData = readCSVFiles(FILE_PATH);
		for (Map.Entry<String, List<Map<String, Object>>> entry : csvData.entrySet()) {
			System.out.println(entry.getKey() + ": ");
			for (Map<String, Object> vals : entry.getValue()) {
				for (String key : vals.keySet()) {
					System.out.print(vals.get(key) + "\t");
				}
				System.out.println();
			}
		}

		writeCSV(FILE_PATH + "\\NewCsvFile.csv", csvData.get("SubWayTable.csv"));
	}

	private static Map<String, List<Map<String, Object>>> readCSVFiles(String folderPath) {
		Map<String, List<Map<String, Object>>> csvData = new HashMap<>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null) {
			for (File file : listOfFiles) {
				if (file.isFile() && file.getName().endsWith(".csv")) {
					List<Map<String, Object>> dataList = readCSV(file);
					csvData.put(file.getName(), dataList);
				}
			}
		}
		return csvData;
	}

	private static List<Map<String, Object>> readCSV(File file) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp949"))) {
			String line;
			String[] headers = null;
			while ((line = reader.readLine()) != null) {
				if (headers == null) {
					headers = line.split(",");
					continue;
				}
				String[] values = line.split(",");
				Map<String, Object> data = new HashMap<>();
				for (int i = 0; i < headers.length; i++) {
					data.put(headers[i], values[i]);
				}
				dataList.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	private static void writeCSV(String filePath, List<Map<String, Object>> dataList) {
		if (!fileExists(filePath)) {
			createNewFile(filePath);
		}
		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath, true), "cp949"))) {
			if (!dataList.isEmpty()) {
				Map<String, Object> firstData = dataList.get(0);
				StringBuilder header = new StringBuilder();
				for (String key : firstData.keySet()) {
					header.append(key).append(",");
				}
				header.deleteCharAt(header.length() - 1);
				writer.write(header.toString());
				writer.newLine();
				for (Map<String, Object> data : dataList) {
					StringBuilder line = new StringBuilder();
					for (Object value : data.values()) {
						line.append(value).append(",");
					}
					line.deleteCharAt(line.length() - 1);
					writer.write(line.toString());
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean fileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	private static void createNewFile(String filePath) {
		try {
			File file = new File(filePath);
			if (file.createNewFile()) {
				System.out.println("New file created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
