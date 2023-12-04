package Java.DataBase.Memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Java.DataBase.Action.LoadTables;

public class TableMemory {
	public TableMemory() {
		this.init();
	}

	private Map<String, Map<String, List<Object>>> tableData = new HashMap<>();

	public Map<String, Map<String, List<Object>>> getTableData() {
		return tableData;
	}

	public void setTableData(Map<String, Map<String, List<Object>>> tableData) {
		this.tableData = tableData;
	}

	public void init() {
		LoadTables lt = new LoadTables();
		lt.setTables(this.tableData);
	}
}
