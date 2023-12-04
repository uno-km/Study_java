package Java.Reflect;

import java.util.ArrayList;

import Java.Reflect.Sample.SampleInterface;

public class FindImplementedClasses {
	public static void main(String[] args) {
		String targetPackage = "Java.Reflect.Sample";
		Class<?> interfaze = SampleInterface.class;
		ArrayList<Class<?>> foundClass = findClassWithSelectQuery(targetPackage, interfaze);
		if (foundClass != null && foundClass.size() != 0) {
			for (Class<?> cls : foundClass) {
				System.out.println(cls.getName());
			}
		}
	}

	public static ArrayList<Class<?>> findClassWithSelectQuery(String packageName, Class<?> interfaze) {
		ArrayList<Class<?>> clsArr = new ArrayList<Class<?>>();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = packageName.replace('.', '/');
			java.net.URL resource = classLoader.getResource(path);
			String directory = resource.getFile();
			java.io.File dir = new java.io.File(directory);
			if (dir.exists()) {
				java.io.File[] files = dir.listFiles();
				for (java.io.File file : files) {
					if (file.getName().endsWith(".class")) {
						String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
						try {
							Class<?> clazz = Class.forName(className);
							if (interfaze.isAssignableFrom(clazz) && !clazz.isInterface()) {
								clsArr.add(clazz);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clsArr;
	}
}