package main.java.ch14.Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx5 {
	public static void main(String[] args) {
		Supplier<Integer> suplier = () -> (int) (Math.random() * 100) + 1;
		Consumer<Integer> consumer = i -> System.out.print(i + ", ");
		Predicate<Integer> predicate = i -> i % 2 == 0;
		Function<Integer, Integer> function = i -> i / 10 * 10;
		ArrayList<Integer> list = new ArrayList<Integer>();

		makeRandomList(suplier, list);
		System.out.println(list);
		printEventNum(predicate, consumer, list);
		List<Integer> newList = doSomeThing(function, list);
		System.out.println(newList);

	}

	static <T> List<T> doSomeThing(Function<T, T> f, List<T> list) {
		List<T> newList = new ArrayList<T>(list.size());
		for (T i : list) {
			newList.add(f.apply(i));
		}
		return newList;
	}

	static <T> void printEventNum(Predicate<T> p, Consumer<T> c, List<T> list) {
		System.out.print("[");
		for (T i : list) {
			if (p.test(i)) 
				c.accept(i);
		}
		System.out.println("]");
	}

	static <T> void makeRandomList(Supplier<T> s, List<T> list) {
		for (int i = 0; i < 10; i++) {
			list.add(s.get());
		}
	}
}
