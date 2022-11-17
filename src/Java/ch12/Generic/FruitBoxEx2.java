package Java.ch12.Generic;

import java.util.ArrayList;

import javax.lang.model.element.Element;

class Fruit implements Eatable {
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

interface Eatable {
}

public class FruitBoxEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
		FruitBox<Apple> appleBox = new FruitBox<Apple>();
		FruitBox<Grape> grapeBox = new FruitBox<Grape>();
//		FruitBox<Grape> fruitBox<Apple>();
//		FruitBox<Toy> toyBox = new FruitBox<Toy>(); //근데 이건 불가능 왜냐하면 토이는 프룻의 자손이 아니여서!
		Box<Toy> toyBox = new Box<Toy>(); // 이건가능!

		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		appleBox.add(new Apple());
//		fruitBox.add(new Grape());
		grapeBox.add(new Grape());
		toyBox.addToy(new String("고등어"));

		System.out.println("fruitBox-" + fruitBox);
		System.out.println("appleBox-" + appleBox);
		System.out.println("grapeBox-" + grapeBox);
		System.out.println("toyBox-" + toyBox);
	}
}

class FruitBox<T extends Fruit & Eatable> extends Box<T> {
}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	ArrayList<String> listToy = new ArrayList<String>();

	void add(T item) {
		list.add(item);
	}

	void addToy(String string) {
		listToy.add(string);
	}

	T get(int i) {
		return list.get(i);
	}

	int size() {
		return list.size();
	}

	public String toString() {
		if (list.isEmpty()) {
			return listToy.toString();
		} else {
			return list.toString();
		}
	}
}