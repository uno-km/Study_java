package main.java.File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

// import com.sun.management.OperatingSystemMXBean;

public class FileMemoryCheck
{
	public static void main(String[] args) throws IOException
	{
		/**
		 * @메모 실행전 메모리 사용량 조회 > 파일 저장/생성 > 실행후 메모리 사용량 및 시간조회
		 **/
		System.out.println(":::::::: START FILE UPLOAD METHOD :::::::::");
		// System.out.println(":::::::::::::: new byte[1] :::::::::::::::");
		System.out.println("::::::::::::: new byte[4096] ::::::::::::::");
		long prevTime = System.currentTimeMillis();
		System.gc();
		long beforeRunMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println("::: 시작전 나에게 부여된 메모리 : " + Runtime.getRuntime().maxMemory() + "	:::");
		System.out.println("::: 시작전 메모리		: " + beforeRunMemory + "	:::");
		/**
		 * @메모 파일 생성/저장 메서드호출
		 **/
		handleFile();
		System.gc();
		long afterRunMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("::: 남아있는 메모리		: " + Runtime.getRuntime().freeMemory() + "	:::");
		System.out.println("::: 사용한 메모리		: " + (beforeRunMemory - afterRunMemory) + "	:::");
		long defTime = System.currentTimeMillis() - prevTime;
		System.out.println("::: 동작된 시간 		: " + defTime / 1000 + "초 " + defTime % 1000 + "	:::");
		System.out.println(":::::::: END METHOD :::::::::::::::::::::::");
	}
	/**
	 * @메모 파일 생성/저장 메서드호출
	 **/
	static void handleFile() throws IOException
	{
		// final String MY_LOCAL_TEST_FILE = "D:\\FileTest\\BigSizeVolumeFile(1.53gb).iso";
		// final String NEW_FILE_PATH = "D:\\FileTest\\saveFile.iso";
		// final File MY_FILE = new File(MY_LOCAL_TEST_FILE);
		// final File NEW_FILE = new File(NEW_FILE_PATH);
		//
		// // 복사 될 곳을 지정
		// FileInputStream fis = new FileInputStream(MY_FILE);
		// FileOutputStream fos = new FileOutputStream(NEW_FILE);
		// int bytesData = 0;
		// byte[] bufferArr = new byte[4096];
		// while ((bytesData = fis.read(bufferArr)) != -1)
		// {
		// fos.write(bufferArr, 0, bytesData);
		// }
	}
	private static void printUsage()
	{
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods())
		{
			method.setAccessible(true);
			if(method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers()))
			{
				Object value;
				try
				{
					value = method.invoke(operatingSystemMXBean, null);
				}
				catch (Exception e)
				{
					value = e;
				} // try
				System.out.println(method.getName() + " = " + value);
			} // if
		} // for
	}
	/**
	 * @메모 파일 생성/저장 메서드호출
	 **/
	static void handleFile2() throws IOException
	{
		final String MY_LOCAL_TEST_FILE = "D:\\FileTest\\BigSizeVolumeFile(1.53gb).iso";
		final String NEW_FILE_PATH = "D:\\FileTest\\saveFile.iso";
		final File MY_FILE = new File(MY_LOCAL_TEST_FILE);
		final File NEW_FILE = new File(NEW_FILE_PATH);

		// 복사 될 곳을 지정
		FileInputStream fis = new FileInputStream(MY_FILE);
		FileOutputStream fos = new FileOutputStream(NEW_FILE);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int bytesData = 0;
		byte[] bufferArr = new byte[1];
		// BufferedReader 로 부터 파일의 내용을 읽어와 저장한다.
		while ((bytesData = bis.read(bufferArr)) != -1)
		{
			bos.write(bufferArr, 0, bytesData);
		}
		Path myFilePath = MY_FILE.toPath();
		Path newFilePath = new File(NEW_FILE_PATH).toPath(); // Path->File 변환
		// File NEW_FILE = myFilePath.toFile();
		Files.createFile(newFilePath); // 해당 주소에 파일 생성
	}
}