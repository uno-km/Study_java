package Java.DataBase.Querys;

import java.util.Map;

public interface SQLQuery {

	abstract Map<String, Object> getCache();

	abstract void execute(String query);
}
