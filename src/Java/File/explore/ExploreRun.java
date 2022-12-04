package Java.File.explore;

import java.io.File;
import java.util.Scanner;

public class ExploreRun extends SuperFile
{

	void play()
	{
		super.reset();
		this.selectDrivePath();
	}

	/* 1 */void selectDrivePath()
	{
		boolean rootFlag = true;
		File ROOTS[] = File.listRoots();
		while (rootFlag)
		{
			MessageEnum.ETC_DOTS_LARGE.message();
			for (File f : ROOTS)
			{
				System.out.println(MessageEnum.ETC_DOTS_SMALL.DOTS_S + f.getPath() + "  ");
			}
			MessageEnum.Q_SELECT_DRIVE.message();
			MessageEnum.ETC_USER_INPUT_LINE.message();
			String input = sc.next();
			for (int i = 0; i < ROOTS.length; i++)
			{
				String rootPath = ROOTS[i].getPath();
				if (input.equalsIgnoreCase(rootPath) || input.equalsIgnoreCase(rootPath.replace("\\", ""))
						|| input.equalsIgnoreCase(rootPath.replace("\\", "").replace(":", "")))
				{
					super.setRootAndDir(ROOTS[i].getPath());
					rootFlag = false;
				}
			}
		}
		this.openExplore();
	}
	void openExplore()
	{
		dirPrint();
		boolean progressFlag = true;
		while (progressFlag)
		{
			MessageEnum.ETC_USER_INPUT_LINE.message();
			String userInput = extracted().nextLine();
			super.input = userInput;
			for (KeyEnum e : KeyEnum.values())
			{
				if (super.input.split(" ")[0].toUpperCase().equals(e.name()))
				{
					e.keyPress(this);
					super.resetInput();
					if (super.checkRootMode())
					{
						this.selectDrivePath();
						break;
					}
					break;
				}
			}
		}
	}

	private Scanner extracted()
	{
		return new Scanner(System.in);
	}
	void dirPrint()
	{
		MessageEnum.ETC_DOTS_LARGE.message();
		System.out.println(MessageEnum.ETC_DOTS_SMALL.DOTS_S + "현재는 디렉터리는 " + super.file.getAbsolutePath() + "::::::");
		MessageEnum.N_NOW_DIRECTORY_LIST.message();
		MessageEnum.ETC_DOTS_LARGE.message();
		for (int i = 0; i < super.file.list().length; i++)
		{
			if (i == 0)
			{
				MessageEnum.ETC_DOTS_SMALL.message();
			}
			System.out.print(super.file.list()[i] + "\t");
			if (i != 0 && i % 3 == 0)
			{
				System.out.println();
				if (i != super.file.list().length - 1)
				{
					MessageEnum.ETC_DOTS_SMALL.message();
				}
			}
		}
		MessageEnum.ETC_DOTS_LARGE.message();
	}
	public void checkUserPlayDecision()
	{
		boolean startFlag = true;
		MessageEnum.Q_PROGRAM_START.message();
		MessageEnum.ETC_USER_INPUT_LINE.message();
		while (startFlag)
		{
			String input = sc.next();
			if (input.equalsIgnoreCase("Y"))
			{
				startFlag = false;
			}
			else if (input.equalsIgnoreCase("N"))
			{
				MessageEnum.N_PROGRAM_END.message();
				super.exit();
			}
			else
			{
				MessageEnum.A_PLEASE_ANSWER_Y_N.message();
			}
		}
		this.play();
	}
}
