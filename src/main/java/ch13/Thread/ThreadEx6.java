package ch13.Thread;

import javax.swing.JOptionPane;

public class ThreadEx6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
		System.out.println("당신이 입력한 값 : " + input);
		for (int i = 10; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);

			} catch (Exception e) {

			}
		}
	}

}
