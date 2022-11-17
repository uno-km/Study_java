package Java.ch12.WildCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Fruit2 {
	String name;
	int weight;

	Fruit2(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return name + "(" + weight + ")";
	}
}

class Apple2 extends Fruit2 {
	Apple2(String name, int weight) {
		super(name, weight);
	}
}

class Grape2 extends Fruit2 {
	Grape2(String name, int weight) {
		super(name, weight);
	}
}

class AppleComp implements Comparator<Apple2> {
	public int compare(Apple2 t1, Apple2 t2) {
		return t2.weight - t1.weight;
	}
}

class GrapeComp implements Comparator<Grape2> {
	public int compare(Grape2 t1, Grape2 t2) {
		return t2.weight - t1.weight;
	}
}

class FruitComp implements Comparator<Fruit2> {
	public int compare(Fruit2 t1, Fruit2 t2) {
		return t1.weight - t2.weight;
	}
}

public class FruitBoxEx4 {
	public static void main(String[] args) {

//		Box b = null;
//		Box b =  new Bo x<String>();
//		Box<String> b =  new Box<String>();
//		b.add(new Integer(100)); //스트링박스인데[ 왜 들어가고 경고만나오지?
		// Box 가 원시타입이라서 그럼 , 그래서 다들어감 , 그런데 Box b에 <String>을 넣으면 에러생김
//		Box<String> bStr = null;

//		b = (Box) bStr;//Box<String> - > Box 가능 but error
//		bStr = (Box<String>) b; //Box-> Box<String> 가능 but error

		FruitBox2<? extends Fruit2> fbox = new FruitBox2<Fruit2>();
		FruitBox2<? extends Fruit2> fbox2 = (FruitBox2<? extends Fruit2>) new FruitBox2<Fruit2>(); //이게원조임
		FruitBox2<Apple2> abox = new FruitBox2<Apple2>();

		FruitBox2<Apple2> appleBox = new FruitBox2<Apple2>();
		FruitBox2<Grape2> grapeBox = new FruitBox2<Grape2>();

		appleBox.add(new Apple2("GreenApple", 200));
		appleBox.add(new Apple2("GreenApple", 300));
		appleBox.add(new Apple2("GreenApple", 100));

		grapeBox.add(new Grape2("GreenGrape", 400));
		grapeBox.add(new Grape2("GreenGrape", 300));
		grapeBox.add(new Grape2("GreenGrape", 200));

		Collections.sort(appleBox.getList(), new AppleComp());
		Collections.sort(grapeBox.getList(), new GrapeComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
		System.out.println();
		Collections.sort(appleBox.getList(), new FruitComp());
		Collections.sort(grapeBox.getList(), new FruitComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
	}
}

class FruitBox2<T extends Fruit2> extends Box2<T> {
}

class Box2<T> {
	ArrayList<T> list = new ArrayList<T>();

	void add(T item) {
		list.add(item);
	}

	T get(int i) {
		return list.get(i);
	}

	ArrayList<T> getList() {
		return list;
	}

	int size() {
		return list.size();
	}

	public String toString() {
		return list.toString();
	}
}