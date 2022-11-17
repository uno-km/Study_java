package Java.ch12.Generic;

import java.util.ArrayList;

class Product {

}

class Tv extends Product {

}

class Audio extends Product {

}

public class GenericEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Product> pL = new ArrayList<Product>();
		ArrayList<Tv> tL = new ArrayList<Tv>();
		pL.add(new Tv());
		pL.add(new Audio());
		pL.add(new Audio());
		pL.add(new Audio());

		tL.add(new Tv());
//		tL.add(new Audio()); // 에러남
		printAllProduct(pL);
		printAllTv(tL);
	}

	public static void printAllProduct(ArrayList<Product> list) {
		for (Product p : list) {
			System.out.println(p);
		}
	}

	public static void printAllTv(ArrayList<Tv> list) {
		for (Product p : list) {
			System.out.println(p);
		}
	}
}
