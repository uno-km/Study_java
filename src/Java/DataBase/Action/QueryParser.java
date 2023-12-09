package Java.DataBase.Action;

import Java.DataBase.Querys.SQLFactory;
import Java.DataBase.Utils.ExceptionUtils;

public class QueryParser {
	final String[] validCommands = { "SELECT", "UPDATE", "INSERT", "DELETE" };

	public String parseCommand(String userInput) {
		String[] lines = userInput.split("\\s+");
		String firstLine = lines[0].trim().toUpperCase();
		for (String command : validCommands) {
			if (firstLine.startsWith(command)) {
				SQLFactory sqlfac = new SQLFactory(command);
				if (sqlfac.getSQLQuery() == null) {
					ExceptionUtils.generateException("해당하는 SQL 명령문이 없습니다.", sqlfac);
				} else {
					sqlfac.getSQLQuery().execute(userInput);
					return sqlfac.getSQLQuery().getResult();
				}
			}
		}
		ExceptionUtils.generateException("Invalid command", firstLine);
		return null;
	}
}
