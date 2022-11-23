package Java.ch13.Synchronized;

import java.util.ArrayList;

public class SynchronizedEx3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Table table = new Table();

		new Thread(new Cook(table), "COOK1").start();
		new Thread(new Customer(table, "pizza"), "CUST1").start();
		new Thread(new Customer(table, "bread"),"CUST2").start();
		Thread.sleep(5000);
		System.exit(0);
	}

}

class Customer implements Runnable {
	private Table table;
	private String food;

	Customer(Table table, String food) {
		this.table = table;
		this.food = food;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			String name = Thread.currentThread().getName();
			if (eatFood()) {
				System.out.println(name + " ate a " + food);
			} else {
				System.out.println(name + " failed to eat... : (");
			}
		}
	}

	boolean eatFood() {
		return table.remove(food);
	}
}

class Cook implements Runnable {
	private Table table;

	Cook(Table table) {
		this.table = table;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			int idx = (int) (Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
		}
	}
}

class Table {
	String[] dishNames = { "pizza", "pizza", "bread" };
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();

	public void add(String dish) {
		if (dishes.size() >= MAX_FOOD) {
			return;
		}
		dishes.add(dish);
		System.out.println("Dished : " + dishes.toString());
	}

	public boolean remove(String dishName) {
		for (int i = 0; i < dishes.size(); i++) {
			if (dishName.equals(dishes.get(i))) {
				dishes.remove(i);
				return true;
			}
		}
		return false;
	}

	public int dishNum() {
		return dishNames.length;
	}
}
