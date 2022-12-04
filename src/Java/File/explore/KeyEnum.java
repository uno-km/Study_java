package Java.File.explore;

import java.io.File;

public enum KeyEnum implements FileInterface
{
	CD
	{
		@Override
		public void keyPress(ExploreRun pack)
		{
			String dir = pack.input.substring(pack.input.indexOf(" "))
					.replaceAll(" ", "");
			if (dir.equalsIgnoreCase("root") 
					|| dir.equalsIgnoreCase("/root") 
					|| dir.equalsIgnoreCase("\\"))
			{
				pack.rootMode = true;
				pack.reset();
			}
			else
			{
				if (dir.charAt(0) == '/')
				{
					this.checkExist(pack.root + dir.replaceFirst("/", ""), pack);
				}
				else if (dir.equals(".."))
				{
					try
					{
						this.checkExist(pack.file.getParent(), pack);
					}
					catch (NullPointerException ne)
					{
						MessageEnum.N_NO_PARENTDIR.message();
					}
				}
				else if (dir.contains("/"))
				{
					this.checkExist(pack.path+ "/" + dir, pack);
				}
				else
				{
					for (int i = 0; i < pack.file.list().length; i++)
					{
						if (pack.file.list()[i].equals(dir))
						{
							this.checkExist(pack.path + "/" + dir, pack);
							break;
						}
					}
				}
			}
		}
	},
	LS
	{
		@Override
		public void keyPress(ExploreRun pack)
		{
			MessageEnum.ETC_DOTS_LARGE.message();
			MessageEnum.N_NOW_DIRECTORY_LIST.message();
			for (int i = 0; i < pack.file.list().length; i++)
			{
				if (i == 0)
				{
					MessageEnum.ETC_DOTS_SMALL.message();
				}
				System.out.print(pack.file.list()[i] + "\t");
				if (i != 0 && i % 3 == 0)
				{
					System.out.println();
					if (i != pack.file.list().length - 1)
					{
						MessageEnum.ETC_DOTS_SMALL.message();
					}
				}
			}
			MessageEnum.ETC_DOTS_LARGE.message();
		}
	},
	PWD
	{
		@Override
		public void keyPress(ExploreRun pack)
		{
			System.out.println(MessageEnum.ETC_DOTS_SMALL.DOTS_S + "현재는 디렉터리는 " + pack.file.getAbsolutePath() + " 입니다.");
		}
	},
	EXIT
	{
		@Override
		public void keyPress(ExploreRun pack)
		{
			MessageEnum.N_PROGRAM_END.message();
			pack.exit();
		}
	};

	protected void checkExist(String dir, ExploreRun pack)
	{
		File tmpFile = new File(dir);
		if (tmpFile.exists())
		{
			if (tmpFile.isDirectory())
			{
				pack.setDir(tmpFile.getAbsolutePath());
				pack.dirPrint();
			}
			else if (tmpFile.isFile())
			{
				MessageEnum.N_IS_FILE.message();
			}
			else
			{
				MessageEnum.N_NO_DIRECTORYPATH.message();
			}
		}
		else
		{
			MessageEnum.N_NO_DIRECTORYPATH.message();
		}
	}
}
