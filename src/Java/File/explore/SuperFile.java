package Java.File.explore;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SuperFile
{
	public File file;
	public String root;
	public String path;
	public String input;
	public boolean rootMode = true;
	public Scanner sc = new Scanner(System.in);
	public ArrayList<String> heap = new ArrayList<String>();

	void setRootAndDir(final String dir)
	{
		this.setRoot(dir);
		this.setDir(dir);
	}

	void setRoot(final String dir)
	{
		this.root = dir;
		this.rootMode = false;
	}

	void setDir(final String dir)
	{
		this.path = dir;
		this.file = new File(dir);
	}

	void resetInput()
	{
		this.input = null;
	}

	void reset()
	{
		this.input = null;
		this.path = null;
		this.file = new File("");
		this.heap.clear();
	}

	void exit()
	{
		this.sc.close();
		System.exit(0);
	}

	boolean checkRootMode()
	{
		return this.rootMode;
	}
}
