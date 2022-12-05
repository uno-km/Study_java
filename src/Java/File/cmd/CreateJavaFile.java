package Java.File.cmd;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CreateJavaFile
{
	
	public static void main(String[] args)
	{
		CreateJavaFile createJavaFile = new CreateJavaFile();
		createJavaFile.runCommand();
	}
	private void runCommand()
	{
		System.out.println("Do u wanna Start java programming?");
		System.out.println(":::   Yes - Y   :::   Nope- N   :::");
		Scanner sc = new Scanner(System.in);
		if (sc.nextLine().equalsIgnoreCase("Y"))
		{
			try
			{
				System.out.println("Enter new java programming file name u want.");
				String fileName = sc.nextLine();
				String initClass = "public class " + fileName + "{";
				System.out.println("Let's Start Java Programming!! Have fun~!!");
				System.out.println(":wq - Quit(save + quit) \n :!q - Quit(no save + quit)");
				System.out.println("------------------------------------------------------");
				System.out.println(initClass);
				while (true)
				{
					String inputStr = sc.nextLine();
					if (inputStr.equalsIgnoreCase(":wq"))
					{
						System.out.println("End Wrting Mode");
						break;
					}
					else if (inputStr.equalsIgnoreCase(":!q"))
					{
						System.out.println("Program Drop ::: See - yah");
						System.exit(0);
					}
					else
					{
						initClass += inputStr;
					}
				}
				System.out.println("Where do you wanna save?");
				System.out.println(":: (C) - c:/programming  :: (D) - d:/");
				String filePath = "";
				while (true)
				{
					String inputStr = sc.nextLine();
					if (inputStr.equalsIgnoreCase("c"))
					{
						filePath = "c:/programing/" + fileName + ".java";
						break;
					}
					else if (inputStr.equalsIgnoreCase("d"))
					{
						filePath = "d:/" + fileName + ".java";
						break;
					}
				}
				System.out.println(":: Compiling....  :: ");
				FileWriter fileWriter = new FileWriter(filePath);
				fileWriter.write(initClass);
				fileWriter.close();
				Process process = Runtime.getRuntime().exec("javac -d . " + filePath);
				Thread.sleep(1000);
				process = Runtime.getRuntime().exec("java " + fileName);
				Thread.sleep(1000);
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String str = "";
				System.out.println(":: End Compiling!!!! :: ");
				System.out.println(":: Run Your Program  :: ");
				while ((str = reader.readLine()) != null)
				{
					System.out.println(str);
				}
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
		sc.close();
		System.out.println("::: END THIS PROGRAM :::");
	}
}
