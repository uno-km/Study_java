package Java.DataBase.Memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Java.DataBase.Action.LoadTables;

public class TableMemory {
	public TableMemory() {
	}

	private Map<String, Map<String, List<Object>>> tableData = new HashMap<>();

	public Map<String, Map<String, List<Object>>> getTableData() {
		return tableData;
	}

	public void setTableData(String tableNm) {
		LoadTables lt = new LoadTables();
		lt.setTables(this.tableData, tableNm);
	}
}
