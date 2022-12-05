package Java.File.cmd;

public class SimpleRunCommand
{
	@SuppressWarnings("unused")
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
