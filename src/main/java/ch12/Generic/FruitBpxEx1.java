package ch12.Generic;

import java.util.ArrayList;

class Fruit {
	public String toString() {
		return "Fruit";
	}
}

class Apple extends Fruit {
	public String toString() {
		return "Apple";
	}
}

class Grape extends Fruit {
	public String toString() {
		return "Grape";
	}
}

class Toy {
	public String toString() {
		return "Toy";
	}
}

public class FruitBpxEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box<Fruit> fruitBox = new Box<Fruit>();
		Box<Apple> appleBox = new Box<Apple>();
//		Box<Apple> appleBox = new Box<Fruit>(); //아무리 상속관계고 조상관계여도 일치는 되어야함
		Box<Toy> toyBox = new Box<Toy>();
//		Box<Grape> grapeBox = new Box<Apple>();
//		List<Toy> tL = new ArrayList<Toy>(); // 이건됨

		fruitBox.add(new Fruit());
		fruitBox.add(new Grape());
		fruitBox.add(new Apple());

		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Toy());

		toyBox.add(new Toy());
//		toyBox.add(new Apple());

		System.out.println(fruitBox);
		System.out.println(appleBox);
		System.out.println(toyBox);

	}

}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();

	void add(T item) {
		list.add(item);
	}

	T get(int i) {
		return list.get(i);
	}

	int size() {
		return list.size();
	}

	public String toString() {
		return list.toString();
	}
}