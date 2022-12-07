package Java.Reflect;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MemoryChecker extends Thread
{
	@Override
	public void run()
	{
		this.MemoryCheckWindow();
	}
	
	public void MemoryCheckWindow()
	{
		boolean visible = true;
		JFrame window = new JFrame("CPU Memory Check");
		JButton cpuLabel = new JButton();
		JButton memoryLabel = new JButton();
		window.add(cpuLabel);
		window.add(memoryLabel);
		window.setBounds(800, 100, 400, 200);
		window.setLayout(new GridLayout(1, 3));
		window.setVisible(true);
		while (visible)
		{
			try
			{
				if (window.isVisible())
				{
					Thread.sleep(1000);
					cpuLabel.setText(Math.round(MemoryUtils.getSystemCpuLoad() * 100) + "%");
					memoryLabel.setText(String.valueOf(Math.round(MemoryUtils.getFreePhysicalMemorySize() / 1000)));
				}
				else
				{
					visible = window.isVisible();
				}
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
