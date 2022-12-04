package Java.File.explore;

public enum MessageEnum implements MessageInterface
{
	Q_SELECT_DRIVE
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::		찾으실 드라이브를 선택해주세요	::::::::::::::");
		}
	},
	Q_PROGRAM_START
	{
		@Override
		public void message()
		{
			System.out.println(this.DOTS_L);
			System.out.println("::::::::::::		파일탐색기를 열으셨습니다.	::::::::::::::");
			System.out.println("::::::::::::		파일탐색을 시작하시겠습니까?	::::::::::::::");
			System.out.println(this.DOTS_L);
		}
	},
	A_PLEASE_ANSWER_Y_N
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::		Y 또는 N을 눌러서 의사를 명확히 하세요	::::::");
		}
	},
	ETC_DOTS_LARGE
	{
		@Override
		public void message()
		{
			System.out.println(this.DOTS_L);
		}
	},
	ETC_DOTS_SMALL
	{
		@Override
		public void message()
		{
			System.out.print(this.DOTS_S);
		}
	},
	ETC_USER_INPUT_LINE
	{
		@Override
		public void message()
		{
			// TODO Auto-generated method stub
			System.out.print(this.DOTS_S + "User : ");
		}
	},
	N_IS_FILE
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::		파일을 선택하셨습니다		::::::::::::::");
		}
	},
	N_PROGRAM_END
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::		프로그램을 종료합니다		::::::::::::::");
		}
	},
	N_NOW_DIRECTORY_LIST
	{
		@Override
		public void message()
		{
			// TODO Auto-generated method stub
			System.out.println("::::::::::::		현재디렉터리 리스트		::::::::::::::");
		}
	},
	N_NO_DIRECTORYPATH
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::		알 수 없는 경로입니다		::::::::::::::");
		}
	},

	N_NO_PARENTDIR
	{
		@Override
		public void message()
		{
			System.out.println("::::::::::::::::::::::::상위 디렉터리가 없습니다!! ::::::::::::::::::");
			System.out.println(":::Root 선택 모드로 진입하고 싶으시면 cd root 또는 cd /root 을 입력하세요:::");
		}
	},
	N_NOW_DIRECTORY_DIR
	{
		@Override
		public void message()
		{
			// TODO Auto-generated method stub

		}
	};
	final String DOTS_L = "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::";
	final String DOTS_S = "::::::::::::		";

}
