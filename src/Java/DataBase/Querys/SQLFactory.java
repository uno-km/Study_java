package Java.DataBase.Querys;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;

import Java.DataBase.Utils.ExceptionUtils;

public class SQLFactory {
	private SQLQuery sqlQuery;

	public SQLFactory(final String command) {
		this.sqlQuery = this.findClassesImplementingInterface("Java.DataBase.Querys", SQLQuery.class, command);
	}

	public SQLQuery getSQLQuery() {
		return sqlQuery;
	}

	@SuppressWarnings("deprecation")
	public SQLQuery findClassesImplementingInterface(String packageName, Class<?> interfaze, String command) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = packageName.replace('.', '/');
			URL resource = classLoader.getResource(path);
			String directory = resource.getFile();
			File dir = new File(directory);
			if (!dir.exists()) {
				ExceptionUtils.generateException("directory미존재", directory);
			}
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.getName().endsWith(".class")) {
					String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
					Class<?> clazz = Class.forName(className);
					if (interfaze.isAssignableFrom(clazz) && !clazz.isInterface()) {
						Field queryField = clazz.getDeclaredField("query");
						queryField.setAccessible(true);
						String fieldValue = (String) queryField.get(clazz.newInstance());
						if (fieldValue != null && fieldValue.toUpperCase().equals(command)) {
							Object instance = clazz.getDeclaredConstructor().newInstance();
							return (SQLQuery) instance;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionUtils.generateException("예외발생", e.getMessage());
		}
		ExceptionUtils.generateException("알 수 없는 오류 발생", null);
		return null;
	}
}