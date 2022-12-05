package Java.File.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunCommand
{
	static String FIND_COMMAND = "netstat -ano ";
	static String TASKKILL_COMMAND = "taskkill /f /pid ";
	
	public static void main(String[] args)
	{
		/* 실행 부 */
		RunCommand rc = new RunCommand();
		rc.runCommand();
	}
	
	private void runCommand()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Process process = Runtime.getRuntime().exec(FIND_COMMAND);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			String str = "";
			System.out.println("What port number do you find?");
			String inputStr = sc.nextLine();
			String pid = null;
			while ((str = reader.readLine()) != null)
			{
				if (str.contains(inputStr))
				{
					if (str.split("    ")[1].contains(inputStr))
					{
						String[] strArr = str.split(" ");
						pid = strArr[strArr.length - 1];
						break;
					}
				}
			}
			if (null == pid)
			{
				System.out.println("It doesn't have one");
			}
			else
			{
				System.out.println("Kill this port number...");
				process = Runtime.getRuntime().exec(TASKKILL_COMMAND + pid);
			}
			sc.close();
			System.out.println("::: END THIS PROGRAM :::");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}