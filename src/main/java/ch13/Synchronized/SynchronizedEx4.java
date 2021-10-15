package ch13.Synchronized;

import java.util.ArrayList;

class Customer2 implements Runnable {
	private Table2 table2;
	private String food;
	Customer2(Table2 table2, String food) {
		this.table2 = table2;
		this.food = food;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {Thread.sleep(10);} catch (InterruptedException e) {}
			String name = Thread.currentThread().getName();
			
			table2.remove(food);
			System.out.println(name + " ate a" + food);
		}
	}
}


class Cook2 implements Runnable {
	private Table2 table2;

	Cook2(Table2 table2) {this.table2 = table2;}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int idx = (int) (Math.random() * table2.dishNum());
			table2.add(table2.dishNames[idx]);
			try {Thread.sleep(10);} catch (InterruptedException e) {}
		}
	}
}

class Table2 {
	String[] dishNames = { "pizza", "pizza", "bread" };
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();

	public synchronized void add(String dish) {
		while (dishes.size() >= MAX_FOOD) {
			String name = Thread.currentThread().getName();
			System.out.println(name + " is waiting. <-테이블의 음식이 소비되지 않아서 ");
			try {
				wait();
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
		dishes.add(dish);
		notify();
		System.out.println("Dished : " + dishes.toString());
	}

	public boolean remove(String dishName) {
		synchronized (this) {
			String name = Thread.currentThread().getName();
			while (dishes.size() == 0) {
				System.out.println(name + " is waiting. <- 원하는 음식이 없어서 기다리고 있다.");
				try {
					wait();
					Thread.sleep(500);
				} catch (InterruptedException e) {}
			}
			while(true) {
				for (int i = 0; i < dishes.size(); i++) {
					if (dishName.equals(dishes.get(i))) {
						dishes.remove(i);
						notify();
						return true;
					}
				}
				try {
					System.out.println(name + " is waiting.");
					wait();
					Thread.sleep(500);
				}catch(InterruptedException e) {}
			}
		}
	}
	public int dishNum() {return dishNames.length;}
}

public class SynchronizedEx4 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Table2 table2 = new Table2();

		new Thread(new Cook2(table2), "COOK2").start();
		new Thread(new Customer2(table2, "pizza"), "CUST1").start();
		new Thread(new Customer2(table2, "bread"), "CUST2").start();
	}

}
