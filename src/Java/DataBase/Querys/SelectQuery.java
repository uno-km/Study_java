package Java.DataBase.Querys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Java.DataBase.Memory.SQLCache;
import Java.DataBase.Memory.TableMemory;
import Java.DataBase.Utils.ExceptionUtils;
import Java.DataBase.Utils.StringUtils;

public class SelectQuery extends SQLCache implements SQLQuery {
	public static final String query = "select";

	private int selectIndex;
	private int fromIndex;
	private int whereIndex;

	@Override
	public Map<String, Object> getCache() {
		return super.getCache();
	}

	@Override
	protected void setCache(Map<String, Object> cache) {
		super.setCache(cache);
	}

	@Override
	public void execute(String sqlQuery) {
		Map<String, Object> cache = new HashMap<>();
		this.selectIndex = StringUtils.findIndexIgnoringCase(sqlQuery, "SELECT");
		this.fromIndex = StringUtils.findIndexIgnoringCase(sqlQuery, "FROM");
		this.whereIndex = StringUtils.findIndexIgnoringCase(sqlQuery, "WHERE");
		if (this.selectIndex == -1) {
			ExceptionUtils.generateException("Select 구문이 비정상적입니다.", null);
		}
		String tableInputName = this.getTableName(sqlQuery);
		TableMemory tm = new TableMemory();
		if (!tm.getTableData().containsKey(tableInputName.toUpperCase())) {
			ExceptionUtils.generateException("찾으시는 테이블이 없습니다", tableInputName);
		}
		Map<String, List<Object>> table = tm.getTableData().get(tableInputName.toUpperCase());
		ArrayList<Object> columns = new ArrayList<>(table.get("columns"));
		ArrayList<Object> selection = this.getSelectonList(sqlQuery, columns);
		this.checkSelection(selection, columns);
		List<Object> reationInstance = table.get("reationInstance");
		ArrayList<Object> parsedConditions = this.getConditions(sqlQuery);
		ArrayList<Map<String, String>> viewTable = this.getViewTable(reationInstance, selection, parsedConditions);
		this.drawTable(selection, viewTable);
		cache.put("query", sqlQuery);
		cache.put("type", query);
		cache.put("full-table", table);
		cache.put("columns", columns);
		cache.put("selection", selection);
		cache.put("reationInstance", reationInstance);
		cache.put("parsedConditions", parsedConditions);
		cache.put("viewTable", viewTable);
		this.setCache(cache);
	}

	private ArrayList<Object> getSelectonList(String sqlQuery, ArrayList<Object> columns) {
		ArrayList<Object> selection = new ArrayList<>();
		String selectCol = sqlQuery.substring(this.selectIndex + "SELECT".length(), this.fromIndex).trim();
		if (selectCol.equals("*")) {
			selection.addAll(columns);
		} else {
			String[] selectionArr = selectCol.split(",");
			for (int i = 0; i < selectionArr.length; i++) {
				selection.add(i, selectionArr[i].trim());
			}
		}
		return selection;
	}

	private String getTableName(String sqlQuery) {
		if (this.whereIndex != -1) {
			return sqlQuery.substring(this.fromIndex + "FROM".length(), this.whereIndex).trim();
		} else {
			return sqlQuery.substring(this.fromIndex + "FROM".length()).trim();
		}
	}

	private ArrayList<Object> getConditions(String sqlQuery) {
		ArrayList<Object> parsedConditions = new ArrayList<>();
		if (this.whereIndex != -1) {
			String conditions = sqlQuery.substring(this.whereIndex + "WHERE".length()).trim();
			String[] conditionArr = conditions.split("(?i)\\bAND\\b");
			for (String condition : conditionArr) {
				String[] parts = condition.split("(?<=[<>=!])|(?=[<>=!])");
				List<String> parsedCondition = new ArrayList<>();
				for (String part : parts) {
					String cleanedPart = part.trim().replaceAll("'", "");
					parsedCondition.add(cleanedPart);
				}
				parsedConditions.add(parsedCondition);
			}
		}
		return parsedConditions;
	}

	private void drawTable(ArrayList<Object> selectionList, ArrayList<Map<String, String>> viewTable) {
		this.drawColumsHeader(selectionList);
		this.drawReationInstance(viewTable, selectionList);
	}

	private void drawReationInstance(ArrayList<Map<String, String>> viewTable, ArrayList<Object> selectionList) {
		int rowCnt = 1;
		String row = "";
		String endLine = "-";
		for (Map<String, String> tuple : viewTable) {
			row = "|" + rowCnt + "|";
			for (Object selection : selectionList) {
				row += tuple.get(selection) + "|";
			}
			System.out.print(row);
			System.out.println();
			rowCnt++;
		}
		for (int i = 0; i < row.chars().count(); i++) {
			endLine += "-";
		}
		System.out.println(endLine);
	}

	private void checkSelection(ArrayList<Object> selection, ArrayList<Object> colums) {
		boolean foundMatch = false;
		for (Object selected : selection) {
			for (Object column : colums) {
				if (selected instanceof String && column instanceof String) {
					if (selected.equals(column)) {
						foundMatch = true;
						break;
					}
				} else {
					String msg = "";
					msg += ":: 속성(셀렉트) 타입에러 :: " + selected + " : " + selected.getClass() + "\n";
					msg += ":: 속성(컬럼) 타입에러 :: " + column + " : " + column.getClass();
					throw new IllegalArgumentException(msg);
				}
			}
			if (!foundMatch) {
				ExceptionUtils.generateException("테이블 내에 매칭되는 컬럼이 없습니다", selected);
			}
		}
	}

	@SuppressWarnings("unchecked")
	// select 일자, 역명 from subwaytable where 역명 ='장암' and 일정 ='20230202'
	private ArrayList<Map<String, String>> getViewTable(List<Object> reationInstance, ArrayList<Object> colums,
			ArrayList<Object> parsedConditions) {
		ArrayList<Map<String, String>> viewTable = new ArrayList<>();
		for (Object col : reationInstance) {
			if (col instanceof Map) {
				viewTable.add((Map<String, String>) col);
			}
		}
		if (parsedConditions != null && parsedConditions.size() != 0) {
			for (Object conditionObj : parsedConditions) {
				ArrayList<Map<String, String>> tmpTable = new ArrayList<>();
				final List<String> condition = (List<String>) conditionObj;
				final String conditionCol = condition.get(0);
				final String conditionPart = condition.get(1);
				final String conditionVal = condition.get(2);
				for (Map<String, String> tuple : viewTable) {
					if (colums.contains(conditionCol)) {
						switch (conditionPart) {
						case "=":
							if (tuple.get(conditionCol).equals(conditionVal)) {
								tmpTable.add(tuple);
							}
							break;
						case "!=":

							break;
						case ">":

							break;
						case "<":

							break;
						}
					} else {
						ExceptionUtils.generateException("조회조건에서 입력하신 컬럼명이 올바르지 않습니다", conditionCol + "는 없습니다.");
					}
				}
				viewTable = new ArrayList<>(tmpTable);
			}
		}
		return viewTable;
	}

	private void drawColumsHeader(ArrayList<Object> selectionList) {
		String colLine = "|C|";
		for (Object col : selectionList) {
			colLine += (String) col + "|";
		}
		System.out.println(colLine);
	}
}
