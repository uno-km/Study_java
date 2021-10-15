package ch13.Synchronized;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Customer3 implements Runnable{
	private Table3 table;
	private String food;
	Customer3(Table3 table,String food){
		this.table =  table;
		this.food = food;
	}
	public void run() {
		while(true) {
			try {Thread.sleep(100);}catch(InterruptedException e) {}
			String name = Thread.currentThread().getName();
			table.remove(food);
			System.out.println(name + " ate a" + food);
		}
	}
}
class Cook3 implements Runnable{
	private Table3 table;
	Cook3(Table3 table){this.table = table;}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			int idx =(int)(Math.random()*table.dishNum());
			table.add(table.dishNames[idx]);
			try {Thread.sleep(10);}catch(InterruptedException e) {}
		}
	}
	
}
class Table3 {
	String[] dishNames = { "pizza", "pizza", "bread" };
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();
	
	private ReentrantLock lock =  new ReentrantLock();
	private Condition forCook = lock.newCondition();
	private Condition forCust= lock.newCondition();
	
	public void add(String dish) {
		lock.lock();
		try {
			while(dishes.size()>=MAX_FOOD) {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is waiting.");
				try {
					forCook.await();
					Thread.sleep(500);
					
				}catch(InterruptedException e) {}
			}
			dishes.add(dish);
			forCust.signal();
			System.out.println("Dishes : "+dishes.toString());
		}finally {
			lock.unlock();
		}
	}
	public void remove(String dishName) {
		lock.lock();
		String name =  Thread.currentThread().getName();
		
		try {
			while(dishes.size()==0) {
				System.out.println(name + " is waiting.");
				try {
					forCust.await();
					Thread.sleep(500);
				}catch(InterruptedException e) {}
				}
			while(true) {
				for(int i = 0 ; i<dishes.size();i++) {
					if(dishName.equals(dishes.get(i))) {
						dishes.remove(i);
						forCook.signal();
					return ;
					}
				}
				try {
					System.out.println(name +" is waiting.");
					forCust.await();
					Thread.sleep(500);
				}catch(InterruptedException e) {}
			}
		}
		finally {
			lock.unlock();
		}
	}
	public int dishNum() {
		return dishNames.length;
	}
	
}
public class SynchronizedEx5 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Table3 table = new Table3();
		new Thread(new Cook3(table),"COOK1").start();
		new Thread(new Customer3(table, "pizza"),"Cust1").start();
		new Thread(new Customer3(table, "bread"),"Cust2").start();
		Thread.sleep(2000);
		System.exit(0);
	}
}
