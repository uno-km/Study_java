package ch13.Synchronized;

import java.util.ArrayList;

class Customer2 implements Runnable {
	private Table2 table;
	private String food;

	Customer2(Table2 table, String food) {
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

class Cook2 implements Runnable {
	private Table2 table;

	Cook2(Table2 table) {
		this.table = table;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int idx = (int) (Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
		}
	}
}

class Table2 {
	String[] dishNames = { "pizza", "pizza", "bread" };
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();

	public synchronized void add(String dish) {
		if (dishes.size() >= MAX_FOOD) {
			return;
		}
		dishes.add(dish);
		System.out.println("Dished : " + dishes.toString());
	}

	public boolean remove(String dishName) {
		synchronized (this) {
			while (dishes.size() == 0) {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is waiting.");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {

				}
			}
			for (int i = 0; i < dishes.size(); i++) {
				if (dishName.equals(dishes.get(i))) {
					dishes.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	public int dishNum() {
		return dishNames.length;
	}
}

public class SynchronizedEx4 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Table2 table = new Table2();

		new Thread(new Cook2(table), "COOK1").start();
		new Thread(new Customer2(table, "pizza"), "CUST1").start();
		new Thread(new Customer2(table, "bread"), "CUST2").start();
		Thread.sleep(5000);
		System.exit(0);
	}

}
