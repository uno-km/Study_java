package ch14.Lambda;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

public class LambdaEx6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntSupplier s = () -> (int) (Math.random() * 100) + 1;
		IntConsumer c = i -> System.out.println(i + ", ");
		IntPredicate p = i -> i % 2 == 0;
		IntUnaryOperator op = i -> i / 10 * 10;
		int[] arr = new int[10];

		maekeRandomList(s, arr);
		System.out.println(Arrays.toString(arr));
		printEvenNum(p, c, arr);
		int[] newArr = doSomething(op, arr);
		System.out.println(Arrays.toString(newArr));

	}

	static void maekeRandomList(IntSupplier s, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.getAsInt();
		}

	}

	static void printEvenNum(IntPredicate p, IntConsumer c, int[] arr) {
		System.out.println("[");
		for (int i : arr) {
			c.accept(i);
		}
		System.out.println("]");
	}

	static int[] doSomething(IntUnaryOperator op, int[] arr) {
		int[] newArr = new int[arr.length];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = op.applyAsInt(arr[i]);
		}
		return newArr;
	}

}
