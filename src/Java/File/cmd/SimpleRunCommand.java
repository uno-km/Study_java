package Java.File.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleRunCommand
{
	public static void main(String[] args)
	{
		try
		{
			Process theProcess = Runtime.getRuntime().exec("cd c:/programming HelloWorld.java");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
