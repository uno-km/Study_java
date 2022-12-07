package Java.Reflect;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MemoryCheck
{
	public static void main(String[] args) throws InterruptedException
	{
		JFrame window = new JFrame("CPU Memory Check");
		JButton cpuLabel = new JButton();
		JButton memoryLabel = new JButton();
		window.add(cpuLabel);
		window.add(memoryLabel);
		window.setBounds(800, 100, 400, 200);
		window.setLayout(new GridLayout(1, 3));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		while (true)
		{
			// Thread.sleep(1000);
			cpuLabel.setText(Math.round(MemoryUtils.getSystemCpuLoad() * 100) + "%");
			memoryLabel.setText(String.valueOf(Math.round(MemoryUtils.getFreePhysicalMemorySize() / 1000)));
		}
	}
}