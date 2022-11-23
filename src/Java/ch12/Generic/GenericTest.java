package Java.ch12.Generic;

import java.util.ArrayList;

public class GenericTest
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// ArrayList list = new ArrayList();
		// list.add(new Tv());
		// list.add(new Tv());
		// list.add(new Audio()); //여긴 이상없이 됨
		ArrayList<Tv> list = new ArrayList<Tv>(); // <- 일치시킴 Tv타입의 객체만 저장가능
		// ArrayList<Object> list = new ArrayList<Object>(); //
		list.add(new Tv());
		list.add(new Tv());
		// list.add(new Audio()); // 여긴 오류처리됨

		// Tv t = (Tv) list.get(0); // 지네릭을 안쓰면 형변환해줘야함
		Tv t = list.get(0); // 지네릭을 안쓰면 형변환해줘야함
		System.out.println(t);
	}

}
