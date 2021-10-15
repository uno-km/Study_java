package ch13.Thread;

public class ThreadEx9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadGroup main = Thread.currentThread().getThreadGroup();
		ThreadGroup g1 = new ThreadGroup("Group1");
		ThreadGroup g2 = new ThreadGroup("Group2");
		ThreadGroup subg1 = new ThreadGroup(g1, "subGroup1");

		g1.setMaxPriority(3);
		Runnable r = new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {

				}
			}
		};
		new Thread(g1, r, "th1").start();
		new Thread(g2, r, "th2").start();
		new Thread(subg1, r, "th3").start();
		System.out.println(">>List of ThreadGroup : " + main.getName() + ", Active ThreadGroup : "
				+ main.activeGroupCount() + ", Active Thread : " + main.activeCount());
		main.list();

	}

}
