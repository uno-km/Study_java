package Java.DataBase.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LoadTables {
	public void setTables(Map<String, Map<String, List<Object>>> tableData) {
		final String CSV_FILE_PATH = "C:\\Users\\zhfld\\git\\Study_java\\src\\Java\\DataBase\\tables";
		File folder = new File(CSV_FILE_PATH);
		File[] files = folder.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
				this.readCSVData(tableData, file);
			}
		}
	}

	private void readCSVData(Map<String, Map<String, List<Object>>> tableData, File file) {
		String tableNm = file.getName().replace(".csv", "").toUpperCase();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp949"))) {
			String line = null;
			boolean isFirstLine = true;
			Map<String, List<Object>> relation = new HashMap<>();
			List<Object> columnsList = new ArrayList<>();
			List<Object> reationInstance = new ArrayList<>();
			relation.put("reationInstance", reationInstance);
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(","); // 첫 줄은 컬럼 이름
				if (isFirstLine) {
					for (String col : values) {
						columnsList.add(col);
					}
					relation.put("columns", columnsList);
					isFirstLine = false;
				} else {
					Map<String, String> tuple = new LinkedHashMap<>();
					for (int valIndex = 0; valIndex < values.length; valIndex++) {
						tuple.put((String) columnsList.get(valIndex), values[valIndex]);
					}
					reationInstance.add(tuple);
				}
			}
			tableData.put(tableNm, relation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
